package com.itau.banking.transfer_api.resource.client;

import com.itau.banking.transfer_api.resource.dto.request.BalanceDTO;
import com.itau.banking.transfer_api.resource.dto.response.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AccountClient", url = "http://localhost:9090")
public interface IAccountClient {

    @GetMapping("/contas/{id}")
    AccountDTO searchAccountById(@PathVariable("id") String id);

    @PutMapping("/contas/saldos")
    ResponseEntity<Void> updateBalance(@RequestBody BalanceDTO request);

}
