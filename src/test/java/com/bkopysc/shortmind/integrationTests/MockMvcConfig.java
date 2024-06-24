package com.bkopysc.shortmind.integrationTests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

@TestConfiguration
@AutoConfigureMockMvc
public class MockMvcConfig {

    @Autowired
    private MockMvc mockMvc;

    @Bean
    public MockMvc mockMvc() {
        return mockMvc;
    }
    
}
