package com.itau.banking.transfer_api.resource.client;

import com.itau.banking.transfer_api.resource.dto.response.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class IClientClientUnitTest {

    @Mock
    private IClientClient clientClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchClientById() {

        ClientDTO mockResponse = new ClientDTO(
                "123",
                "Test Client",
                "11998877665",
                        "Fisica"

        );

        when(clientClient.searchClientById("123")).thenReturn(mockResponse);

        ClientDTO response = clientClient.searchClientById("123");

        assertEquals("123", response.id());
        assertEquals("Test Client", response.name());
    }

}
