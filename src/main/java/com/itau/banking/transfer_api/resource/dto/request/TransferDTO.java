package com.itau.banking.transfer_api.resource.dto.request;

import com.itau.banking.transfer_api.domain.model.TransferAccountModel;

public record TransferDTO(String clientId, double ammount, TransferAccountModel account, String transferId){
}
