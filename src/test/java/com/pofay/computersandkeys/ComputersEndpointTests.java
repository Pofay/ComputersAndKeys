package com.pofay.computersandkeys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ComputersEndpointTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void full_endpoint_with_manufacturer_and_model_number_returns_200() throws Exception {
        mvc.perform(get("/computers/asus/X507UA")).andExpect(status().isOk());
    }

    @Test
    public void with_a_saved_computer_requesting_from_full_endpoint_returns_json() {

    }
}
