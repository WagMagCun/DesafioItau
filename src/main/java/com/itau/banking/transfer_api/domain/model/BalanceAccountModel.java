package com.itau.banking.transfer_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BalanceAccountModel {

    private String sourceId;
    private String destinationId;

    public BalanceAccountModel(String idOrigem, String destinationId) {
        this.sourceId = idOrigem;
        this.destinationId = destinationId;
    }

}
