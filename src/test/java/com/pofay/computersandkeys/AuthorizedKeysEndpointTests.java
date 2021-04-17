package com.pofay.computersandkeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pofay.computersandkeys.RequestBuilders.AuthorizedKeyRequestBuilder;
import com.pofay.computersandkeys.entities.AuthorizedKey;
import com.pofay.computersandkeys.repositories.AuthorizedKeysRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthorizedKeysEndpointTests {

    MockMvc mvc;
    @Autowired
    WebApplicationContext context;
    @Autowired
    AuthorizedKeysRepository repo;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        repo.deleteAll();
    }

    @AfterEach
    public void teardown() {
        repo.deleteAll();
    }

    @Test
    public void post_without_body_returns_400() throws Exception {

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void post_with_body_returns_201() throws Exception {
        JSONObject request = AuthorizedKeyRequestBuilder.create().withKeyType("ssh-ed25519")
                .withPublicKey("AAAAC3NzaC1lZDI1NTE5AAAAIOiKKC7lLUcyvJMo1gjvMr56XvOq814Hhin0OCYFDqT4")
                .withComment("happy@isr").build();

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())).andExpect(status().isCreated());
    }

    @Test
    public void post_with_key_not_matching_key_type_returns_400_with_error_message() throws Exception {
        JSONObject request = AuthorizedKeyRequestBuilder.create().withKeyType("ssh-rsa")
                .withPublicKey("AAAAC3NzaC1lZDI1NTE5AAAAIC8MVGWZd6LWisqHcKcupOcMI3vnw4CDjYsBNeF07cZs")
                .withComment("happy@isr").build();

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON)
                .content(request.toString())).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", equalTo("The content of the public key is invalid for type ssh-rsa")));
    }

    @Test
    public void post_should_store_data_in_db() throws Exception {
        JSONObject request = AuthorizedKeyRequestBuilder.create().withKeyType("ssh-ed25519")
                .withPublicKey("AAAAC3NzaC1lZDI1NTE5AAAAIC8MVGWZd6LWisqHcKcupOcMI3vnw4CDjYsBNeF07cZs")
                .withComment("happy@isr").build();

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON)
                .content(request.toString()));

        AuthorizedKey actual = repo.findById("AAAAC3NzaC1lZDI1NTE5AAAAIC8MVGWZd6LWisqHcKcupOcMI3vnw4CDjYsBNeF07cZs")
                .get();
        assertEquals("AAAAC3NzaC1lZDI1NTE5AAAAIC8MVGWZd6LWisqHcKcupOcMI3vnw4CDjYsBNeF07cZs", actual.getPublicKey());
    }

}
