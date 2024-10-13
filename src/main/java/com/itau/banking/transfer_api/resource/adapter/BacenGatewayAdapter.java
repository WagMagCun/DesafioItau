package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.common.LogTags;
import com.itau.banking.transfer_api.domain.contract.IBacenGateway;
import com.itau.banking.transfer_api.domain.model.BalanceModel;
import com.itau.banking.transfer_api.resource.client.IBacenClient;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BacenGatewayAdapter implements IBacenGateway {

    private final IBacenClient bacenClient;

    @Autowired
    public BacenGatewayAdapter(IBacenClient bacenClient) {
        this.bacenClient = bacenClient;
    }


    @Override
    public ResponseEntity<Void> notifyBacen(BalanceModel balanceModel) {

        try {

            log.info(LogTags.GATEWAY + " Start transfer: {}", logTransfer(balanceModel));
            ResponseEntity<Void> response = bacenClient.notifyBacen(balanceModel.toDTORequest());
            log.info(LogTags.GATEWAY + " Successfully transferred! : {} HTTP Status: {} ", logTransfer(balanceModel), response.getStatusCode());
            return response;

        } catch (Exception e) {
            log.error(LogTags.GATEWAY + " Error while tranfering :{} Error: {}", logTransfer(balanceModel), e.getMessage());
            throw e;
        }

    }

    @NotNull
    private String logTransfer(@NotNull BalanceModel balanceModel) {
        return String.format("cliente: %s, conta de origem: %s, conta de destino: %s, valor: %s",
                balanceModel.getBalanceAccount(),
                balanceModel.getAccount().getSourceId(),
                balanceModel.getAccount().getDestinationId(),
                balanceModel.getAmmount());
    }

}
