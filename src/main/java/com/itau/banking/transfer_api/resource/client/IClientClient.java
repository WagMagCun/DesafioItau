package com.itau.banking.transfer_api.resource.client;

import com.itau.banking.transfer_api.resource.dto.response.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ClientClient", url = "http://localhost:9090")
public interface IClientClient {

    @GetMapping("/clientes/{id}")
    ClientDTO searchClientById(@PathVariable("id") String id);

}
