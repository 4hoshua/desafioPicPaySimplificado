package com.demo.desafioPicPaySimplificado.client;

import com.demo.desafioPicPaySimplificado.transaction.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "NotificationClient",
        url = "${client.notification-service.url}"
)
interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendNotification();
}
