package com.bkopysc.shortmind.service.shortnoteLike;

import com.bkopysc.shortmind.dto.shortnoteLike.ShortNoteLikePostDTO;
import com.bkopysc.shortmind.model.ShortNote;

public interface IShortNoteLikeService {
    
    public Boolean like(ShortNote shortNote);

    public Boolean unlike(ShortNote shortNote);

    public Long getCountOfLikes(Long shortNoteId);

    public Boolean ifLikeByShortNoteId(Long shortNoteId);

}
