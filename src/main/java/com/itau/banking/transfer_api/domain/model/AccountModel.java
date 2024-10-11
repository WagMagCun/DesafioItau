package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.AccountResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountModel {

    private String id;
    private double saldo;
    private Boolean ativo;
    private double limiteDiario;

    public static AccountModel fromDTO(AccountResponseDTO dto) {
        AccountModel accountModel = new AccountModel();
        accountModel.setId(dto.getId());
        accountModel.setSaldo(dto.getSaldo());
        accountModel.setAtivo(dto.getAtivo());
        accountModel.setLimiteDiario(dto.getLimiteDiario());
        return accountModel;
    }
}

