package com.itau.banking.transfer_api.domain.service;

import com.itau.banking.transfer_api.domain.exception.*;
import com.itau.banking.transfer_api.domain.model.*;
import com.itau.banking.transfer_api.metrics.MetricsService;
import com.itau.banking.transfer_api.resource.adapter.AccountGatewayAdapter;
import com.itau.banking.transfer_api.resource.adapter.BacenGatewayAdapter;
import com.itau.banking.transfer_api.resource.adapter.ClientGatewayAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransferServiceImplTest {

    @Mock
    private AccountGatewayAdapter accountGatewayAdapter;

    @Mock
    private BacenGatewayAdapter bacenGatewayAdapter;

    @Mock
    private ClientGatewayAdapter clientGatewayAdapter;

    @InjectMocks
    private TransferServiceImpl transferService;

    private TransferModel transferModel;
    private BalanceModel balanceModel;
    private AccountModel accountModel;
    private ClientModel clientModel;

    @Mock
    private MetricsService metricsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        accountModel = new AccountModel();
        accountModel.setId("123");
        accountModel.setBalance(500.0);
        accountModel.setActive(true);
        accountModel.setDailyLimit(1000.0);

        clientModel = new ClientModel();
        clientModel.setId("client123");

        transferModel = new TransferModel(
                "client123",
                100.0,
                new TransferAccountModel("source123", "destination123")
        );

        balanceModel = new BalanceModel(100.0, new BalanceAccountModel("destination123", "source123"));

        transferService = new TransferServiceImpl(accountGatewayAdapter, bacenGatewayAdapter,clientGatewayAdapter, metricsService);
    }

    @Test
    public void testTransfer_Successful() {
        when(clientGatewayAdapter.searchClientById(anyString())).thenReturn(clientModel);
        when(accountGatewayAdapter.searchAccountById(anyString())).thenReturn(accountModel);
        when(accountGatewayAdapter.updateBalance(any(BalanceModel.class))).thenReturn(ResponseEntity.ok().build());
        when(bacenGatewayAdapter.notifyBacen(any(BalanceModel.class))).thenReturn(ResponseEntity.ok().build());

        assertDoesNotThrow(() -> transferService.transfer(transferModel));

        verify(clientGatewayAdapter).searchClientById(anyString());
        verify(accountGatewayAdapter).searchAccountById(anyString());
        verify(accountGatewayAdapter).updateBalance(any(BalanceModel.class));
        verify(bacenGatewayAdapter).notifyBacen(any(BalanceModel.class));
    }

    @Test
    public void testTransfer_ClientNotFound() {
        when(clientGatewayAdapter.searchClientById(anyString())).thenReturn(null);

        assertThrows(ClientNotFoundException.class, () -> transferService.transfer(transferModel));

        verify(accountGatewayAdapter, never()).updateBalance(any(BalanceModel.class));
        verify(bacenGatewayAdapter, never()).notifyBacen(any(BalanceModel.class));
    }

    @Test
    public void testTransfer_AccountInactive() {
        accountModel.setActive(false);
        when(clientGatewayAdapter.searchClientById(anyString())).thenReturn(clientModel);
        when(accountGatewayAdapter.searchAccountById(anyString())).thenReturn(accountModel);

        assertThrows(AccountInactiveException.class, () -> transferService.transfer(transferModel));

        verify(accountGatewayAdapter, never()).updateBalance(any(BalanceModel.class));
        verify(bacenGatewayAdapter, never()).notifyBacen(any(BalanceModel.class));
    }

    @Test
    public void testTransfer_InsufficientBalance() {
        accountModel.setBalance(50.0);
        when(clientGatewayAdapter.searchClientById(anyString())).thenReturn(clientModel);
        when(accountGatewayAdapter.searchAccountById(anyString())).thenReturn(accountModel);

        assertThrows(InsufficientBalanceException.class, () -> transferService.transfer(transferModel));

        verify(accountGatewayAdapter, never()).updateBalance(any(BalanceModel.class));
        verify(bacenGatewayAdapter, never()).notifyBacen(any(BalanceModel.class));
    }

    @Test
    public void testTransfer_DailyLimitExceeded() {
        accountModel.setDailyLimit(50.0);
        when(clientGatewayAdapter.searchClientById(anyString())).thenReturn(clientModel);
        when(accountGatewayAdapter.searchAccountById(anyString())).thenReturn(accountModel);

        assertThrows(DailyLimitExceededException.class, () -> transferService.transfer(transferModel));

        verify(accountGatewayAdapter, never()).updateBalance(any(BalanceModel.class));
        verify(bacenGatewayAdapter, never()).notifyBacen(any(BalanceModel.class));
    }
}
