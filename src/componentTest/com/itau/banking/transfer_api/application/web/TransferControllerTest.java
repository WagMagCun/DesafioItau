package com.itau.banking.transfer_api.application.web;

import com.itau.banking.transfer_api.application.web.controller.TransferController;
import com.itau.banking.transfer_api.domain.contract.ITransferService;
import com.itau.banking.transfer_api.domain.exception.AccountInactiveException;
import com.itau.banking.transfer_api.domain.exception.ClientNotFoundException;
import com.itau.banking.transfer_api.domain.exception.DailyLimitExceededException;
import com.itau.banking.transfer_api.domain.exception.InsufficientBalanceException;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import com.itau.banking.transfer_api.domain.model.TransferModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ITransferService transferService;

    @InjectMocks
    private TransferController transferController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransfer_Success() throws Exception {

        TransferModel response = new TransferModel("123",100, new TransferAccountModel("123","456"));

        when(transferService.transfer(any())).thenReturn(response);

        mockMvc.perform(post("/transferencia")
                        .contentType("application/json")
                        .content("{\"idCliente\":\"2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f\", \"valor\":500.00, \"conta\":{\"idOrigem\":\"d0d32142-74b7-4aca-9c68-838aeacef96b\", \"idDestino\":\"41313d7b-bd75-4c75-9dea-1f4be434007f\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idTransferencia").value(notNullValue()));

    }

    @Test
    public void testTransfer_ClientNotFound() throws Exception {

        when(transferService.transfer(any())).thenThrow(new ClientNotFoundException("Client not found"));

        mockMvc.perform(post("/transferencia")
                        .contentType("application/json")
                        .content("{\"idCliente\":\"\", \"valor\":500.00, \"conta\":{\"idOrigem\":\"d0d32142-74b7-4aca-9c68-838aeacef96b\", \"idDestino\":\"41313d7b-bd75-4c75-9dea-1f4be434007f\"}}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testTransfer_AccountInactive() throws Exception {

        when(transferService.transfer(any())).thenThrow(new AccountInactiveException("Client inactive"));

        mockMvc.perform(post("/transferencia")
                        .contentType("application/json")
                        .content("{\"idCliente\":\"\", \"valor\":500.00, \"conta\":{\"idOrigem\":\"d0d32142-74b7-4aca-9c68-838aeacef96b\", \"idDestino\":\"41313d7b-bd75-4c75-9dea-1f4be434007f\"}}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTransfer_DailyLimitExceeded() throws Exception {

        when(transferService.transfer(any())).thenThrow(new DailyLimitExceededException("Daily Limit Exceeded"));

        mockMvc.perform(post("/transferencia")
                        .contentType("application/json")
                        .content("{\"idCliente\":\"\", \"valor\":500.00, \"conta\":{\"idOrigem\":\"d0d32142-74b7-4aca-9c68-838aeacef96b\", \"idDestino\":\"41313d7b-bd75-4c75-9dea-1f4be434007f\"}}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTransfer_InsufficientBalance() throws Exception {

        when(transferService.transfer(any())).thenThrow(new InsufficientBalanceException(("Insufficient Balance")));

        mockMvc.perform(post("/transferencia")
                        .contentType("application/json")
                        .content("{\"idCliente\":\"\", \"valor\":500.00, \"conta\":{\"idOrigem\":\"d0d32142-74b7-4aca-9c68-838aeacef96b\", \"idDestino\":\"41313d7b-bd75-4c75-9dea-1f4be434007f\"}}"))
                .andExpect(status().isBadRequest());
    }

}
