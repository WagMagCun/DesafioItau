package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.TransferRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferModel {

    private String idCliente;
    private double valor;
    private TransferAccountModel conta;
    private String idTransferencia;

    public TransferModel(String idCliente, double valor, TransferAccountModel conta) {
        this.idCliente = idCliente;
        this.valor = valor;
        this.conta = conta;
    }

    public TransferRequestDTO toDTORequest(TransferModel transferModel) {
        return new TransferRequestDTO(
                transferModel.getIdCliente(),
                transferModel.getValor(),
                transferModel.getConta()
        );

    }


}
