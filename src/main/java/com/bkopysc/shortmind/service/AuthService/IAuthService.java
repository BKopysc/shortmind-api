package com.bkopysc.shortmind.service.AuthService;

import com.bkopysc.shortmind.dto.Auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.Auth.AuthResponseDTO;

public interface IAuthService {

    public AuthResponseDTO signup(AuthRequestDTO authRequestDTO);

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);
    
}

