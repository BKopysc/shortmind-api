package com.bkopysc.shortmind.integrationTests.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ControllerTestHelper helper;

    @BeforeAll
    public void setup() throws Exception {
        //create user
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("test");
        helper.createUser(authRequestDTO);
    }

    @Test
    public void shouldReturnOkWhenSignup() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("newUser");
        authRequestDTO.setPassword("test");
        
        String jsonBody = objectMapper.writeValueAsString(authRequestDTO);
        String path = "/api/auth/signup";

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("accessToken").exists());
    }

    @Test
    public void shouldReturnOkWhenAuthenticate() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("test");
        
        String jsonBody = objectMapper.writeValueAsString(authRequestDTO);
        String path = "/api/auth/authenticate";

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("accessToken").exists());
    }

    @Test
    public void shouldReturnUnauthorizedWhenAuthenticate() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("wrong");
        
        String jsonBody = objectMapper.writeValueAsString(authRequestDTO);
        String path = "/api/auth/authenticate";

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void shouldReturnExistingUserWhenSignup() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("test");
        
        String jsonBody = objectMapper.writeValueAsString(authRequestDTO);
        String path = "/api/auth/signup";

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is(HttpStatus.CONFLICT.value()));
    }


}
