package com.itau.banking.transfer_api.resource.dto;

import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {

    private String idCliente;
    private double valor;
    private TransferAccountModel conta;
    private String idTransferencia;

    public TransferRequestDTO(String idCliente, double valor, TransferAccountModel conta) {
        this.idCliente = idCliente;
        this.valor = valor;
        this.conta = conta;
    }

}
