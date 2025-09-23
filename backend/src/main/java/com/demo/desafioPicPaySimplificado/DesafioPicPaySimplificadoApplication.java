package com.demo.desafioPicPaySimplificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesafioPicPaySimplificadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPicPaySimplificadoApplication.class, args);
	}

}
