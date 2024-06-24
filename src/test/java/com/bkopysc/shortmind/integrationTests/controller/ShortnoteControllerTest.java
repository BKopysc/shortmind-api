package com.bkopysc.shortmind.integrationTests.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.header.Header;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class ShortnoteControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ControllerTestHelper helper;

    private AuthResponseDTO setupAuth = null;
    private ShortNoteGetDTO setupShortnote = null;

    @BeforeAll
    public void setup() throws Exception {
        //create user
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setUsername("Tester");
        authRequestDTO.setPassword("test");

        //create shortnote
        ShortNotePostDTO shortNotePostDTO = ShortNotePostDTO.builder()
            .title("Test title")
            .content("Test content")
            .build();

        this.setupAuth = helper.createUser(authRequestDTO);
        this.setupShortnote = helper.createShortnote(shortNotePostDTO, setupAuth.getAccessToken());
        
    }

    @Test
    public void shouldCreateShortnote() throws Exception {
        ShortNotePostDTO shortNotePostDTO = ShortNotePostDTO.builder()
            .title("New title")
            .content("New content")
            .build();

        String path = "/api/shortnotes/";
        String jsonBody = objectMapper.writeValueAsString(shortNotePostDTO);

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(shortNotePostDTO.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(shortNotePostDTO.getContent()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(setupAuth.getUser().getId()));
    }

    @Test 
    void shouldThrowUnauthorizedWhenCreateShortnote() throws Exception {
        ShortNotePostDTO shortNotePostDTO = ShortNotePostDTO.builder()
            .title("New title")
            .content("New content")
            .build();

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/")
            .content(objectMapper.writeValueAsString(shortNotePostDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    //getAllShortnotes
    @Test
    public void shouldReturnAllShortnotes() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get("/api/shortnotes/")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(setupShortnote.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(setupShortnote.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(setupShortnote.getContent()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].user.id").value(setupAuth.getUser().getId()));
    }

    //getShortnoteById
    @Test
    public void shouldReturnShortnoteById() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get("/api/shortnotes/" + setupShortnote.getId())
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(setupShortnote.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(setupShortnote.getTitle()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(setupShortnote.getContent()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.user.id").value(setupAuth.getUser().getId()));
    }

    @Test
    public void shouldReturnNotFoundWhenShortnoteNotExists() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .get("/api/shortnotes/" + setupShortnote.getId() + 99)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    //likeShortnote

    @Test
    public void shouldLikeShortnote() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + "/like")
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + "/unlike")
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldThrowUnauthorizedWhenLikeShortnote() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + "/like")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isForbidden());

        
    }

    @Test
    public void shouldThrowNotFoundWhenLikeShortnote() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + 99 + "/like")
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    //unlikeShortnote
    @Test
    public void shouldUnlikeShortnote() throws Exception {
        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + "/like")
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post("/api/shortnotes/" + setupShortnote.getId() + "/unlike")
            .header("Authorization", "Bearer " + setupAuth.getAccessToken())
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }




}
