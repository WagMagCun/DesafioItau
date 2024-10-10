package com.itau.banking.transfer_api.domain.model;

import com.itau.banking.transfer_api.resource.dto.ClientResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientModel {

    private String id;
    private String nome;
    private String telefone;
    private String tipoPessoa;

    public ClientModel() {
    }

    public static ClientModel fromDTO(ClientResponseDTO dto) {
        ClientModel clientModel = new ClientModel();
        clientModel.setId(dto.getId());
        clientModel.setNome(dto.getNome());
        clientModel.setTelefone(dto.getTelefone());
        clientModel.setTipoPessoa(dto.getTipoPessoa());
        return clientModel;
    }
}
