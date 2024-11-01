package com.itau.banking.transfer_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferAccountModel {

    private String sourceId;
    private String destinationId;

    public TransferAccountModel(String sourceId, String destinationId) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
    }
}