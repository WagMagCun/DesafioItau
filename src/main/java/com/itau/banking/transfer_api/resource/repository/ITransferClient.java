package com.itau.banking.transfer_api.resource.repository;

import com.itau.banking.transfer_api.resource.dto.AccountResponseDTO;
import com.itau.banking.transfer_api.resource.dto.ClientResponseDTO;
import com.itau.banking.transfer_api.resource.dto.TransferRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ClienteClient", url = "http://localhost:9090")
public interface ITransferClient {

    @GetMapping("/clientes/{id}")
    ClientResponseDTO searchById(@PathVariable("id") String id);

    @GetMapping("/contas/{id}")
    AccountResponseDTO buscarPorId(@PathVariable("id") String id);

    @PutMapping("/contas/saldos")
    ResponseEntity<Void> updateBalance(@RequestBody TransferRequestDTO request);

    @PostMapping("/notificacoes")
    ResponseEntity<Void> notifyBacen(@RequestBody TransferRequestDTO request);

}
