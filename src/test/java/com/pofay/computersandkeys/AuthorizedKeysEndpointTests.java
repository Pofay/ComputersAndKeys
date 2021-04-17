package com.pofay.computersandkeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthorizedKeysEndpointTests {

    MockMvc mvc;
    @Autowired
    WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void teardown() {
    }

    @Test
    public void post_without_body_returns_400() throws Exception {

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_with_body_returns_201() throws Exception {
        JSONObject subPayload = new JSONObject();
        subPayload.put("type", "ssh-ed25519");
        subPayload.put("public", "AAAAC3NzaC1lZDI1NTE5AAAAIOiKKC7lLUcyvJMo1gjvMr56XvOq814Hhin0OCYFDqT4");
        subPayload.put("comment", "happy@isr");
        JSONObject payload = new JSONObject();
        payload.put("ssh-key", subPayload);

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON)
                .content(payload.toString())).andExpect(status().isCreated());
    }

    @Test
    public void post_with_key_not_matching_key_type_returns_400_with_error_message() throws Exception {
        JSONObject subPayload = new JSONObject();
        subPayload.put("type", "ssh-rsa");
        subPayload.put("public", "AAAAC3NzaC1lZDI1NTE5AAAAIC8MVGWZd6LWisqHcKcupOcMI3vnw4CDjYsBNeF07cZs");
        subPayload.put("comment", "happy@isr");
        JSONObject payload = new JSONObject();
        payload.put("ssh-key", subPayload);

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON)
                .content(payload.toString())).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("The content of the public key is invalid for type ssh-rsa")));
    }
}
