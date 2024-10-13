package com.itau.banking.transfer_api.resource.dto.request;

import com.itau.banking.transfer_api.domain.model.BalanceAccountModel;

public record BalanceDTO(double ammount, BalanceAccountModel account) {
}
