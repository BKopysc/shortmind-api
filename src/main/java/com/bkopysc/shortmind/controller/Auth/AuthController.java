package com.bkopysc.shortmind.controller.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.Auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.Auth.AuthResponseDTO;
import com.bkopysc.shortmind.service.AuthService.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthOperations{

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponseDTO> signup(AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(this.authService.signup(authRequestDTO));
    }

    @Override
    public ResponseEntity<AuthResponseDTO> authenticate(AuthRequestDTO authRequestDTO) {
        return ResponseEntity.ok(this.authService.authenticate(authRequestDTO));
    }

    @Override
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }

    
    
}
