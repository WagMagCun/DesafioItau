package com.itau.banking.transfer_api.domain.contract;

import com.itau.banking.transfer_api.domain.model.BalanceModel;
import org.springframework.http.ResponseEntity;

public interface IBacenGateway {

    ResponseEntity<Void> notifyBacen(BalanceModel transferModel);

}