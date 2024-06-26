package com.bkopysc.shortmind.controller.shortnote;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;

@RequestMapping("/default")
public interface IShortNoteOperations {
    
    @GetMapping("/{id}")
    public ResponseEntity<ShortNoteGetDTO> getShortNoteById(@PathVariable Long id);

    @PostMapping("/{id}/like")
    public ResponseEntity<Boolean> likeShortNote(@PathVariable Long id);

    @PostMapping("/{id}/unlike")
    public ResponseEntity<Boolean> unlikeShortNote(@PathVariable Long id);

    @GetMapping("/")
    public ResponseEntity<List<ShortNoteGetDTO>> getAllShortNotes();

    @PostMapping("/")
    public ResponseEntity<ShortNoteGetDTO> createShortNote(@RequestBody ShortNotePostDTO shortNotePostDTO);
}
