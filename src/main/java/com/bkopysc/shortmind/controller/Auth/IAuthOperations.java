package com.bkopysc.shortmind.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;

@RequestMapping("/default")
public interface IAuthOperations {
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody AuthRequestDTO authRequestDTO);

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequestDTO);

    @Secured("ROLE_USER")
    @GetMapping("/test")
    public ResponseEntity<String> test();

}
