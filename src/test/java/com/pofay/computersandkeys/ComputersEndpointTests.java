package com.pofay.computersandkeys;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.pofay.computersandkeys.entities.Computer;
import com.pofay.computersandkeys.entities.ComputerTypes;
import com.pofay.computersandkeys.repositories.ComputersRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComputersEndpointTests {

    MockMvc mvc;
    @Autowired
    ComputersRepository repo;
    @Autowired
    WebApplicationContext context;

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
    public void with_a_saved_computer_requesting_from_full_endpoint_returns_json() throws Exception {
        Computer c = new Computer("X507UA", "ASUS", ComputerTypes.LAPTOP, "日本語", Arrays.asList("black", "silver"));
        repo.save(c);

        mvc.perform(get("/computers/asus/X507UA")).andExpect(jsonPath("$.computer.type", equalTo("LAPTOP")))
                .andExpect(jsonPath("$.computer.model", equalTo("X507UA")))
                .andExpect(jsonPath("$.computer.language", equalTo("日本語")))
                .andExpect(jsonPath("$.computer.maker", equalTo("ASUS")))
                .andExpect(jsonPath("$.computer.colors.color", hasItem("black")))
                .andExpect(jsonPath("$.computer.colors.color", hasItem("silver")));
    }
}
