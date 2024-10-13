package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.request.BalanceDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BalanceModel {

    @Getter
    @Setter
    private double ammount;
    private BalanceAccountModel account;

    public BalanceModel(double ammount, BalanceAccountModel account) {
        this.ammount = ammount;
        this.account = account;
    }

    public BalanceAccountModel getBalanceAccount() {
        return account;
    }

    public void setBalanceAccount(BalanceAccountModel account) {
        this.account = account;
    }

    public BalanceDTO toDTORequest() {
        return new BalanceDTO(this.ammount, this.account);
    }
}
