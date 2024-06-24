package com.bkopysc.shortmind.integrationTests.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.dto.user.SimpleUserGetDTO;
import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ControllerTestHelper helper;

    private AuthResponseDTO setupAuth = null;

    @BeforeAll
    public void setup() throws Exception {
        //create user
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("test");
        this.setupAuth = helper.createUser(authRequestDTO);
    }

    @Test
    public void shouldReturnUserWhenExists() throws Exception {
        SimpleUserGetDTO user = setupAuth.getUser();

        String path = "/api/users/" + user.getId();

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get(path)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(user.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.getUsername()));
    }

    @Test
    public void shouldReturnTrueWhenUsernameExists() throws Exception {
        SimpleUserGetDTO user = setupAuth.getUser();

        String path = "/api/users/is-username-taken/" + user.getUsername();

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get(path)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void shouldReturnFalseWhenUsernameNotExists() throws Exception {
        String path = "/api/users/is-username-taken/" + "notexists";

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get(path)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("false"));
    }

    @Test
    public void shouldReturnNotFoundWhenUserNotExistsById() throws Exception {
        SimpleUserGetDTO user = setupAuth.getUser();

        String path = "/api/users/" + user.getId() + 1;

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get(path)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}
