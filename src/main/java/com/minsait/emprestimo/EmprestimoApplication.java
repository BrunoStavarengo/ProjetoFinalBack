package com.minsait.emprestimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EmprestimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmprestimoApplication.class, args);
	}

}
