package com.demo.desafioPicPaySimplificado.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "AuthorizationClient",
        url = "${client.authorization-service.url}"
)
interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationDTO> isAuthorized();
}
