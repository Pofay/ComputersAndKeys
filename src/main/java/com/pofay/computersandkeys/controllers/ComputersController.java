package com.pofay.computersandkeys.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pofay.computersandkeys.entities.Computer;
import com.pofay.computersandkeys.repositories.ComputersRepository;
import com.pofay.computersandkeys.services.ComputersService;

import org.json.JSONException;
import org.json.JSONObject;
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
    public ResponseEntity getComputerModel(@PathVariable("maker") String maker,
            @PathVariable("model_number") String modelNumber, HttpServletRequest req, HttpServletResponse res) {
        Optional<Computer> computerOrEmpty = service.findMatchingComputerByMakerAndModel(maker, modelNumber);

        if (computerOrEmpty.isPresent()) {
            Computer c = computerOrEmpty.get();
            JSONObject response = composeResponse(c);
            System.out.println(response.toString());
            return ResponseEntity.ok().body(response.toString());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private JSONObject composeResponse(Computer c) throws JSONException {
        JSONObject color = new JSONObject();
        color.put("color", c.getColors());
        JSONObject computerDetails = toJSON(c);
        computerDetails.put("colors", color);
        JSONObject response = new JSONObject();
        response.put("computer", computerDetails);
        return response;
    }

    private JSONObject toJSON(Computer c) throws JSONException {
        JSONObject computerDetails = new JSONObject();
        computerDetails.put("maker", c.getMaker());
        computerDetails.put("language", c.getLanguage());
        computerDetails.put("model", c.getModelNumber());
        computerDetails.put("type", c.getType());
        return computerDetails;
    }

}
