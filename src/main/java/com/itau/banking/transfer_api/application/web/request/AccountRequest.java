package com.itau.banking.transfer_api.application.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.banking.transfer_api.domain.model.TransferAccountModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    @JsonProperty("idOrigem")
    private String sourceId;

    @JsonProperty("idDestino")
    private String destinationId;

    public TransferAccountModel toModel() {
        return new TransferAccountModel(sourceId, destinationId);
    }
}
