package com.bkopysc.shortmind.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentPostDTO {
    
    private String content;
    private Long shortNoteId;
    private Long userId;
}
