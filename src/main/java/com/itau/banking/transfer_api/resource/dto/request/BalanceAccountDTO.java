package com.itau.banking.transfer_api.resource.dto.request;

public record BalanceAccountDTO(String sourceId, String destinationId) {
    public BalanceAccountDTO(String sourceId, String destinationId) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
    }
}
