package com.itau.banking.transfer_api.application.web.response;

import com.itau.banking.transfer_api.domain.model.TransferModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferResponse {

    private String idTransferencia;

    public static TransferResponse fromDomain(TransferModel transferModel) {
        TransferResponse transferResponse =  new TransferResponse();
        transferResponse.setIdTransferencia(transferModel.getIdTransferencia());
        return transferResponse;
    }
}
