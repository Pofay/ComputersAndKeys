package com.pofay.computersandkeys.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pofay.computersandkeys.entities.AuthorizedKey;
import com.pofay.computersandkeys.repositories.AuthorizedKeysRepository;
import com.pofay.computersandkeys.requests.AddSSHKeyRequest;
import com.pofay.computersandkeys.services.KeyVerifierService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizedKeysController {

    private KeyVerifierService keyVerifier;
    private AuthorizedKeysRepository repo;

    @Autowired
    public AuthorizedKeysController(KeyVerifierService keyVerifier, AuthorizedKeysRepository repo) {
        this.keyVerifier = keyVerifier;
        this.repo = repo;
    }

    @PostMapping(value = "/build-server/jenkins/authorized_keys", produces = "application/json")
    public ResponseEntity addKeyForJenkins(@RequestBody AddSSHKeyRequest body, HttpServletRequest req,
            HttpServletResponse res) {

        if (body == null) {
            return ResponseEntity.badRequest().build();
        }

        String key = body.getSshkey().getPublicKey();
        String keyType = body.getSshkey().getType();
        String comment = body.getSshkey().getComment();
        if (keyVerifier.verifyKeyForKeyType(key, keyType)) {
            AuthorizedKey authorizedKey = new AuthorizedKey(key, keyType, comment);
            repo.save(authorizedKey);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            String errorMessage = String.format("The content of the public key is invalid for type %s", keyType);
            JSONObject payload = new JSONObject();
            payload.put("message", errorMessage);
            return ResponseEntity.status(400).body(payload.toString());
        }
    }
}
