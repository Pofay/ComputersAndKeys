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
    public void post_using_full_endpoint_returns_201() throws Exception {

        mvc.perform(post("/build-server/jenkins/authorized_keys").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
}
