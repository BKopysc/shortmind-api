package com.bkopysc.shortmind.integrationTests.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.dto.comment.CommentPostDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.service.auth.AuthServiceImpl;
import com.bkopysc.shortmind.service.auth.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ControllerTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthResponseDTO createUser(AuthRequestDTO auth) throws Exception{

        String jsonBody = objectMapper.writeValueAsString(auth);
        String path = "/api/auth/signup";

        MvcResult result = this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        String content = result.getResponse().getContentAsString();
        return objectMapper.readValue(content, AuthResponseDTO.class);
    }

    public ShortNoteGetDTO createShortnote(ShortNotePostDTO shortnotePostDTO, String accessToken) throws Exception{
        String jsonBody = objectMapper.writeValueAsString(shortnotePostDTO);
        String path = "/api/shortnotes/";

        MvcResult result = this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(jsonBody)
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        String content = result.getResponse().getContentAsString();
        return objectMapper.readValue(content, ShortNoteGetDTO.class);
    }

    public void addComment(CommentPostDTO commentPostDTO, String accessToken) throws Exception{
        String path = "/api/comments/";

        String requestJson = this.objectMapper.writeValueAsString(commentPostDTO);

        this.mockMvc.perform(
            MockMvcRequestBuilders
            .post(path)
            .content(requestJson)
            .header("Authorization", "Bearer " + accessToken)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        );
    }
    
}
