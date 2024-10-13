package com.itau.banking.transfer_api.domain.service;

import com.itau.banking.transfer_api.domain.exception.*;
import com.itau.banking.transfer_api.domain.model.*;
import com.itau.banking.transfer_api.domain.contract.ITransferService;
import com.itau.banking.transfer_api.resource.adapter.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class TransferServiceImpl implements ITransferService {

    private final AccountGatewayAdapter accountGatewayAdapter;
    private final BacenGatewayAdapter bacenGatewayAdapter;
    private final ClientGatewayAdapter clientGatewayAdapter;


    public TransferServiceImpl(AccountGatewayAdapter accountGatewayAdapter,
                               BacenGatewayAdapter bacenGatewayAdapter,
                               ClientGatewayAdapter clientGatewayAdapter
    ) {
        this.accountGatewayAdapter = accountGatewayAdapter;
        this.bacenGatewayAdapter = bacenGatewayAdapter;
        this.clientGatewayAdapter = clientGatewayAdapter;

    }

    @Override
    public TransferModel transfer(@NotNull TransferModel transfer)
            throws AccountInactiveException, InsufficientBalanceException, DailyLimitExceededException, ClientNotFoundException {

        validTransferRequest(transfer);

        CompletableFuture<ClientModel> clientCall = CompletableFuture.supplyAsync(() ->
                clientGatewayAdapter.searchClientById(transfer.getClientId()));

        CompletableFuture<AccountModel> accountCall = CompletableFuture.supplyAsync(() ->
                accountGatewayAdapter.searchAccountById(transfer.getAccount().getSourceId()));

        CompletableFuture<Void> parallelCalls = CompletableFuture.allOf(clientCall, accountCall);

        parallelCalls.exceptionally(ex -> {
            Throwable cause = ex.getCause();
            if (cause instanceof InterruptedException) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Transfer operation was interrupted", cause);
            } else if (cause instanceof ExecutionException) {
                throw new RuntimeException("Error during execution", cause.getCause());
            }
            return null;
        });

        parallelCalls.join();

        ClientModel clientCallResult = clientCall.join();
        AccountModel accountCallResult = accountCall.join();

        validaPreTransferData(clientCallResult, accountCallResult, transfer);

        BalanceModel balance = new BalanceModel(
                transfer.getAmmount(),
                new BalanceAccountModel(
                        transfer.getAccount().getDestinationId(),
                        transfer.getAccount().getSourceId()
                )
        );

        transfer.setIdTransfer(UUID.randomUUID().toString());
        ResponseEntity<Void> updateBalance = updateBalance(balance);

        notifyBacen(balance);

        return transfer;
    }


    private void validaPreTransferData(ClientModel client, AccountModel account, TransferModel transfer) {
        if(client == null || client.getId() == null){
            throw new ClientNotFoundException("Cliente não localizado: " + client);
        }

        if (!account.getActive()) {
            throw new AccountInactiveException("Conta não está ativa: " + transfer.getAccount().getSourceId());
        }
        if (account.getBalance() <= 0 || account.getBalance() < transfer.getAmmount()) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transferência.");
        }
        if (account.getDailyLimit() <= 0 || account.getDailyLimit() < transfer.getAmmount()) {
            throw new DailyLimitExceededException("Valor de transferência excede o limite diário.");
        }
    }

    private void validTransferRequest(TransferModel transfer) {

        if (transfer.getClientId() == null || transfer.getClientId().isBlank()) {
            throw new TransferValidationException("Campo idCliente é obrigatório");
        }

        if (transfer.getAmmount() <= 0) {
            throw new TransferValidationException("Campo valor é obrigatório    ");
        }

        if (transfer.getAccount() == null) {
            throw new TransferValidationException("Campo Conta é obrigatório");
        }

        if (transfer.getAccount().getSourceId() == null || transfer.getAccount().getSourceId().isBlank()) {
            throw new TransferValidationException("Campo Conta de origem é obrigatório");
        }

        if (transfer.getAccount().getDestinationId() == null || transfer.getAccount().getDestinationId().isBlank()) {
            throw new TransferValidationException("Campo Conta de destino é obrigatório");
        }

    }

    public ResponseEntity<Void> updateBalance(BalanceModel balanceModel) {
        return accountGatewayAdapter .updateBalance(balanceModel);
    }

    @Retry(name = "notifyBacenRetry", fallbackMethod = "notifyBacenFallback")
    @CircuitBreaker(name = "notifyBacen")
    public ResponseEntity<Void> notifyBacen(BalanceModel balanceModel) {
        return bacenGatewayAdapter.notifyBacen(balanceModel);
    }

    public ResponseEntity<Void> notifyBacenFallback(BalanceModel balanceModel, Throwable t) {
        log.error("Error notifying BACEN, using fallback. Error: {}", t.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
