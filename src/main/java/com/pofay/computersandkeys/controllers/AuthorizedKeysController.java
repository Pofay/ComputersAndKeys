package com.pofay.computersandkeys.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizedKeysController {

    @PostMapping(value = "/build-server/{service}/authorized_keys", produces = "application/json")
    public ResponseEntity getComputerModelJSON(@PathVariable("service") String serviceName, HttpServletRequest req,
            HttpServletResponse res) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
