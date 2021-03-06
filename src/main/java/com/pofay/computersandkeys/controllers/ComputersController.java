package com.pofay.computersandkeys.controllers;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pofay.computersandkeys.services.ComputersService;
import com.pofay.computersandkeys.utils.ResponseFormatter;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ComputersController {

    private ComputersService service;

    @Autowired
    public ComputersController(ComputersService service) {
        this.service = service;
    }

    @GetMapping(value = "/computers/{maker}/{model_number}", produces = "application/json")
    public ResponseEntity getComputerModelJSON(@PathVariable("maker") String maker,
            @PathVariable("model_number") String modelNumber, HttpServletRequest req, HttpServletResponse res) {

        return service.findMatchingComputerByMakerAndModel(maker, modelNumber).map(ResponseFormatter::composeResponse)
                .map(r -> ResponseEntity.ok().body(r.toString())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/computers/{maker}/{model_number}", produces = "application/xml")
    public ResponseEntity getComputerModelXML(@PathVariable("maker") String maker,
            @PathVariable("model_number") String modelNumber, HttpServletRequest req, HttpServletResponse res) {

        return service.findMatchingComputerByMakerAndModel(maker, modelNumber).map(ResponseFormatter::composeResponse)
                .map(r -> XML.toString(r)).map(r -> ResponseEntity.ok().body(r))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/computers/{maker}")
    public ResponseEntity getComputersByMaker(@PathVariable("maker") String maker, HttpServletRequest req,
            HttpServletResponse res) {

        if (service.hasAssociatedModels(maker)) {
            return ResponseEntity.status(403).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
