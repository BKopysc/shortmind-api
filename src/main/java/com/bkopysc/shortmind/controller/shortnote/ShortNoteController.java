package com.bkopysc.shortmind.controller.shortnote;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.bkopysc.shortmind.service.shortnote.IShortNoteService;
import com.bkopysc.shortmind.service.shortnoteLike.IShortNoteLikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shortnotes")
public class ShortNoteController implements IShortNoteOperations{

    private final IShortNoteService shortNoteService;
    private final IShortNoteLikeService shortNoteLikeService;


    @Override
    public ResponseEntity<ShortNoteGetDTO> getShortNoteById(Long id) {
        return ResponseEntity.ok(this.shortNoteService.getShortNoteDTOById(id));
    }

    @Override
    public ResponseEntity<List<ShortNoteGetDTO>> getAllShortNotes() {
        return ResponseEntity.ok(this.shortNoteService.getAllShortNotes());
    }

    @Override
    public ResponseEntity<ShortNoteGetDTO> createShortNote(ShortNotePostDTO shortNotePostDTO) {
        ShortNoteGetDTO shortNoteGetDTO = this.shortNoteService.createShortNote(shortNotePostDTO);
        return ResponseEntity.ok(shortNoteGetDTO);
    }

    @Override
    public ResponseEntity<Boolean> likeShortNote(Long id) {
        return ResponseEntity.ok(this.shortNoteService.likeShortNoteById(id));
    }

    @Override
    public ResponseEntity<Boolean> unlikeShortNote(Long id) {
        return ResponseEntity.ok(this.shortNoteService.unlikeShortNoteById(id));
    }
    
}
