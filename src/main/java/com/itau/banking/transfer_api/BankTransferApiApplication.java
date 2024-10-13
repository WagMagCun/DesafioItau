package com.itau.banking.transfer_api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.itau.banking.transfer_api.resource.client")
public class BankTransferApiApplication {

	public static void main(String[] args) {

		log.info("Initializing the application...");
		SpringApplication.run(BankTransferApiApplication.class, args);
	}

}
