package com.bkopysc.shortmind.dto.shortnote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortNotePostDTO {
    private String title;
    private String content;
}
