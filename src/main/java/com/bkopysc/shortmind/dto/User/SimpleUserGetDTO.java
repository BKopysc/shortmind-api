package com.bkopysc.shortmind.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserGetDTO {
    
    private Long id;
    private String username;

    @Override
    public String toString() {
        return "SimpleUserGetDTO [id=" + id + ", username=" + username + "]";
    }
}
