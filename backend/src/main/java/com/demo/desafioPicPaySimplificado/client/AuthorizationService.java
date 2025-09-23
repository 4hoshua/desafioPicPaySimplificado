package com.demo.desafioPicPaySimplificado.client;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized() {
        var resp = authorizationClient.isAuthorized();

        if (resp.getStatusCode().isError()) {
            throw new RuntimeException("Forbidden");
        }

        assert resp.getBody() != null;
        return resp.getBody().authorized();
    }
}
