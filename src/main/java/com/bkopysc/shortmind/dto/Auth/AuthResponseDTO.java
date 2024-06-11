package com.bkopysc.shortmind.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {
    
    @JsonProperty("access_token")
    private String accessToken;
}
