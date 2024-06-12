package com.bkopysc.shortmind.dto.shortnote;

import java.util.Date;

import com.bkopysc.shortmind.dto.user.UserGetDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortNoteGetDTO {
    
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private UserGetDTO user;

    @Builder.Default
    private Long likes = 0L;

}
