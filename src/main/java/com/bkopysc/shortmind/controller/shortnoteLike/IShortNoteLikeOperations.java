package com.bkopysc.shortmind.controller.shortnoteLike;

import java.net.http.HttpResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkopysc.shortmind.dto.shortnoteLike.ShortNoteLikeGetDTO;
import com.bkopysc.shortmind.dto.shortnoteLike.ShortNoteLikePostDTO;

@RequestMapping("/default")
public interface IShortNoteLikeOperations {

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfLikes(@RequestParam(required = true, name="shortNoteId") Long shortNoteId);

    @GetMapping("/if-like")
    public ResponseEntity<Boolean> checkLikeForShortnote(@RequestParam(required = true,name="shortNoteId") Long shortNoteId);

}
