package com.itau.banking.transfer_api.resource.client;

import com.itau.banking.transfer_api.resource.dto.request.BalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BacenClient", url = "${wiremock.service.url}")
public interface IBacenClient {

    @PostMapping("/notificacoes")
    ResponseEntity<Void> notifyBacen(@RequestBody BalanceDTO request);

}
