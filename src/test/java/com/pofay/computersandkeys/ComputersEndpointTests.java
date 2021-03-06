package com.pofay.computersandkeys;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

        String fullEndpoint = "/computers/asus/X507UA";

        mvc.perform(get(fullEndpoint)).andExpect(jsonPath("$.computer.type", equalTo("LAPTOP")))
                .andExpect(jsonPath("$.computer.model", equalTo("X507UA")))
                .andExpect(jsonPath("$.computer.language", equalTo("日本語")))
                .andExpect(jsonPath("$.computer.maker", equalTo("ASUS")))
                .andExpect(jsonPath("$.computer.colors.color", hasItem("black")))
                .andExpect(jsonPath("$.computer.colors.color", hasItem("silver")));
    }

    @Test
    public void with_a_saved_computer_request_that_matches_model_but_not_maker_returns_404() throws Exception {
        Computer c = new Computer("777777", "IBM", ComputerTypes.LAPTOP, "EN", Arrays.asList("red"));
        repo.save(c);

        String endpointWithMatchingModelNonMatchingMaker = "/computers/asus/777777";

        mvc.perform(get(endpointWithMatchingModelNonMatchingMaker)).andExpect(status().isNotFound());
    }

    @Test
    public void with_only_nonmatching_maker_returns_404() throws Exception {
        Computer c = new Computer("111111", "ROG", ComputerTypes.LAPTOP, "EN", Arrays.asList("blue"));
        repo.save(c);

        String endpointWithNonMatchingMaker = "/computers/asus/";

        mvc.perform(get(endpointWithNonMatchingMaker)).andExpect(status().isNotFound());
    }

    @Test
    public void with_only_matching_maker_returns_403() throws Exception {
        Computer c = new Computer("111111", "ROG", ComputerTypes.LAPTOP, "EN", Arrays.asList("blue"));
        repo.save(c);

        String endpointWithMatchingMaker = "/computers/rog/";

        mvc.perform(get(endpointWithMatchingMaker)).andExpect(status().isForbidden());
    }
}
