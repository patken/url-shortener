package com.patken.urlshortener.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UrlShortenerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shortenReturnsCreated() throws Exception {
        mockMvc.perform(post("/api/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://www.example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").exists());
    }

    @Test
    void invalidUrlReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/urls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"pas-une-url\"}"))
                .andExpect(status().isBadRequest());
    }
}
