package com.bkopysc.shortmind.service.auth;

import com.bkopysc.shortmind.dto.auth.AuthRequestDTO;
import com.bkopysc.shortmind.dto.auth.AuthResponseDTO;

public interface IAuthService {

    public AuthResponseDTO signup(AuthRequestDTO authRequestDTO);

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);
    
}

