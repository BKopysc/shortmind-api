package com.bkopysc.shortmind.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;
import com.bkopysc.shortmind.service.auth.AuthServiceImpl;
import com.bkopysc.shortmind.service.auth.IAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController implements IAuthOperations{

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
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

}
