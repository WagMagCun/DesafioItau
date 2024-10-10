package com.itau.banking.transfer_api.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransferResponseDTO {

    @JsonProperty("IdTransferencia")
    private UUID idTransferencia;

}
