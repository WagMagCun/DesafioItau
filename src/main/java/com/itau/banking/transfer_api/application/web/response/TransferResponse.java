package com.itau.banking.transfer_api.application.web.response;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferResponse {

    public TransferResponse(String idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    private String idTransferencia;

    public static TransferResponse fromDomain(TransferModel transferModel) {
        return new TransferResponse(transferModel.getIdTransfer());
    }
}
