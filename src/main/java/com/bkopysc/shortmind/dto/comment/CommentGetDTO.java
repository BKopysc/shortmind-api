package com.bkopysc.shortmind.dto.comment;

import java.sql.Date;

import com.bkopysc.shortmind.dto.user.UserGetDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentGetDTO {
    
    private Long id;
    private String content;
    private Date createdAt;
    private UserGetDTO user;

}
