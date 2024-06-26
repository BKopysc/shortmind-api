package com.bkopysc.shortmind.integrationTests.service.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.exceptions.ObjectExistedException;
import com.bkopysc.shortmind.exceptions.WrongPasswordException;
import com.bkopysc.shortmind.repository.UserRepository;
import com.bkopysc.shortmind.service.auth.IAuthService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.AfterEach;


@SpringBootTest
@ActiveProfiles("test")
public class IAuthServiceTest {

    @Autowired
    private IAuthService authService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void sucessfulSignup() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");
        AuthResponseDTO response = authService.signup(authRequestDTO);
        assertNotNull(response);
        assertNotNull(response.getAccessToken());
    }

    @Test
    public void sucessfulAuthenticate() {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");
        authService.signup(authRequestDTO);
        AuthResponseDTO loginResponse = authService.authenticate(authRequestDTO);
        assertNotNull(loginResponse);
        assertNotNull(loginResponse.getAccessToken());
    }

    @Test
    public void successfulCreatedUser(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");
        authService.signup(authRequestDTO);
        assertNotNull(userRepository.getByUsername("test2"));
    }

    @Test
    public void userAlreadyExists(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");
        AuthRequestDTO nextRequest = new AuthRequestDTO("usertest", "test");
        
        authService.signup(authRequestDTO);
        
        assertThrows(ObjectExistedException.class, () -> {
            authService.signup(nextRequest);
        });
        
    }

    @Test
    public void wrongPassword(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO("usertest", "test");   
        AuthRequestDTO nextRequest = new AuthRequestDTO("usertest", "wrongpassword"); 
        authService.signup(authRequestDTO);
        
        assertThrows(WrongPasswordException.class, () -> {
            authService.authenticate(nextRequest);
        });
    }

    
}
