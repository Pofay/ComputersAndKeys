package com.pofay.computersandkeys.controllers;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ComputersController {

    @GetMapping(value = "/computers/{manufacturer}/{model_number}", produces = "application/json")
    public ResponseEntity getComputerModel(@PathVariable("manufacturer") String manufacturer,
            @PathVariable("model_number") String modelNumber, HttpServletRequest req, HttpServletResponse res) {
        return ResponseEntity.ok().build();
    }

}
