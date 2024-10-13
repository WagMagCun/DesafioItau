package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.response.ClientDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientModel {

    private String id;
    private String name;
    private String telephone;
    private String personType;

    public ClientModel() {
    }

}
