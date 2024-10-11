package com.itau.banking.transfer_api.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("saldo")
    private double saldo;

    @JsonProperty("ativo")
    private Boolean ativo;

    @JsonProperty("limiteDiario")
    private double limiteDiario;

}
