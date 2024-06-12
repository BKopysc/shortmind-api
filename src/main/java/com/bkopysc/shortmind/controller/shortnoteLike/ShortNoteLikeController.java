package com.bkopysc.shortmind.controller.shortnoteLike;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.bkopysc.shortmind.service.shortnoteLike.IShortNoteLikeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/shortnotes-likes")
public class ShortNoteLikeController implements IShortNoteLikeOperations{
    
    private final IShortNoteLikeService shortNoteLikeService;


    @Override
    public ResponseEntity<Long> getCountOfLikes(Long shortNoteId) {
        return ResponseEntity.ok(this.shortNoteLikeService.getCountOfLikes(shortNoteId));
    }

    @Override
    public ResponseEntity<Boolean> checkLikeForShortnote(Long shortNoteId) {
        return ResponseEntity.ok(this.shortNoteLikeService.ifLikeByShortNoteId(shortNoteId));
    }

}
