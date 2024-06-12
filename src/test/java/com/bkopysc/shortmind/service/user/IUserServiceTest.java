package com.bkopysc.shortmind.service.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.ActiveProfiles;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.service.auth.IAuthService;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IUserServiceTest {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthService authService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    private final AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");

    @BeforeAll
    public void setup() {
        authService.signup(authRequestDTO);
    }
    

    @Test
    void successfulExistsByUsername() {
        boolean state = this.userService.existsByUsername(authRequestDTO.getUsername());
        assertTrue(state);
    }

    @Test
    void unsuccessfulExistsByUsername() {
        boolean state = this.userService.existsByUsername("nonexistent");
        assertFalse(state);
    }

    @Test
    void successfulGetAuthenticatedUser() {
        AuthResponseDTO response = authService.authenticate(authRequestDTO);
        Authentication auth = authenticationProvider.
    }

    @Test
    void sucessfulGetByUsername() {

    }
}
