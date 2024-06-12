package com.bkopysc.shortmind.dto.shortnoteLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortNoteLikeGetDTO {
    
    private Long id;
    private Long shortNoteId;
    private Long userId;
}
