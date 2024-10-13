package com.itau.banking.transfer_api.resource.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itau.banking.transfer_api.domain.model.ClientModel;

public record ClientDTO(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String name,
        @JsonProperty("telefone") String telephone,
        @JsonProperty("tipoPessoa") String personType) {

    public ClientModel toDomain() {
        ClientModel clientModel = new ClientModel();
        clientModel.setId(this.id);
        clientModel.setName(this.name);
        clientModel.setTelephone(this.telephone);
        clientModel.setPersonType(this.personType);
        return clientModel;
    }
}
