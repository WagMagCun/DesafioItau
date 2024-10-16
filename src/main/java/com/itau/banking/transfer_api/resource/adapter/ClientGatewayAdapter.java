package com.itau.banking.transfer_api.resource.adapter;

import com.itau.banking.transfer_api.common.LogTags;
import com.itau.banking.transfer_api.domain.contract.IClientGateway;
import com.itau.banking.transfer_api.domain.model.ClientModel;
import com.itau.banking.transfer_api.resource.client.IClientClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientGatewayAdapter implements IClientGateway {

    private final IClientClient clientClient;

    @Autowired
    public ClientGatewayAdapter(IClientClient clientClient) {
        this.clientClient = clientClient;
    }

    @Override
    public ClientModel searchClientById(String id) {

        try {
            log.debug(LogTags.GATEWAY + " Search Client by ID: {}", id);
            ClientModel client = clientClient.searchClientById(id).toDomain();
            log.info(LogTags.GATEWAY + " Successfully searched Client by ID: {}", id);

            return client;

        } catch (Exception e) {

            log.error(LogTags.GATEWAY + " Error while searching for Client ID: {} , Error: ", id, e.getMessage());
            throw e;
        }
    }

}
