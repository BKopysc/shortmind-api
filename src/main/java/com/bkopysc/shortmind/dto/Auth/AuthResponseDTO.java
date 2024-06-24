package com.bkopysc.shortmind.dto.auth;

import com.bkopysc.shortmind.dto.user.SimpleUserGetDTO;
import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    
    private SimpleUserGetDTO user;
    private String accessToken;

    @Override
    public String toString() {
        return "AuthResponseDTO [accessToken=" + accessToken + ", user=" + user + "]";
    }
}
