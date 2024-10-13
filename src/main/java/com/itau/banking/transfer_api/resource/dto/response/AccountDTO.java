package com.itau.banking.transfer_api.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.banking.transfer_api.domain.model.AccountModel;

public record AccountDTO(
        @JsonProperty("id") String id,
        @JsonProperty("saldo") double saldo,
        @JsonProperty("ativo") Boolean ativo,
        @JsonProperty("limiteDiario") double limiteDiario) {

    public AccountModel toDomain() {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(this.id);
        accountModel.setBalance(this.saldo);
        accountModel.setActive(this.ativo);
        accountModel.setDailyLimit(this.limiteDiario);
        return accountModel;
    }
}
