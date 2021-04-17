package com.pofay.computersandkeys.controllers;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pofay.computersandkeys.requests.AddSSHKeyRequest;
import com.pofay.computersandkeys.services.KeyVerifierService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizedKeysController {

    private KeyVerifierService keyVerifier;

    @Autowired
    public AuthorizedKeysController(KeyVerifierService keyVerifier) {
        this.keyVerifier = keyVerifier;
    }

    @PostMapping(value = "/build-server/{service}/authorized_keys", produces = "application/json")
    public ResponseEntity addSSHKeyForService(@PathVariable("service") String serviceName,
            @RequestBody AddSSHKeyRequest body, HttpServletRequest req, HttpServletResponse res) {

        if (body == null) {
            return ResponseEntity.badRequest().build();
        }

        String key = body.getSshkey().getPublicKey();
        String keyType = body.getSshkey().getType();
        if (keyVerifier.verifyKeyForKeyType(key, keyType)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        else {
            String errorMessage = String.format("The content of the public key is invalid for type %s", keyType);
            JSONObject payload = new JSONObject();
            payload.put("message", errorMessage);
            return ResponseEntity.status(400).body(payload.toString());
        }

    }
}
