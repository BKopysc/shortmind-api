package com.bkopysc.shortmind.controller.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkopysc.shortmind.dto.Auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.Auth.AuthResponseDTO;

@RequestMapping("/default")
public interface IAuthOperations {
    
    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@RequestBody AuthRequestDTO authRequestDTO);

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequestDTO);

    @GetMapping("/test")
    public ResponseEntity<String> test();

}
