package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.response.AccountDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountModel {

    private String id;
    private double balance;
    private Boolean active;
    private double dailyLimit;

}

