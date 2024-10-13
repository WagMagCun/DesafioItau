package com.itau.banking.transfer_api.resource.client;

import com.itau.banking.transfer_api.domain.model.BalanceAccountModel;
import com.itau.banking.transfer_api.resource.dto.request.BalanceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IBacenClientUnitTest {

    @Mock
    private IBacenClient bacenClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNotifyBacen() {

        BalanceDTO request = new BalanceDTO(
                500.0,
                new BalanceAccountModel("123", "456")
        );

        ResponseEntity<Void> expectedResponse = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        when(bacenClient.notifyBacen(request)).thenReturn(expectedResponse);

        ResponseEntity<Void> response = bacenClient.notifyBacen(request);

        verify(bacenClient, times(1)).notifyBacen(request);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
