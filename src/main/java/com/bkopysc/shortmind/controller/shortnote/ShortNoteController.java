package com.bkopysc.shortmind.controller.shortnote;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.bkopysc.shortmind.service.shortnote.IShortNoteService;

@RestController
@RequestMapping("/api/shortnote")
public class ShortNoteController implements IShortNoteOperations{

    private final IShortNoteService shortNoteService;

    public ShortNoteController(IShortNoteService shortNoteService) {
        this.shortNoteService = shortNoteService;
    }

    @Override
    public ResponseEntity<ShortNoteGetDTO> getShortNoteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShortNoteById'");
    }

    @Override
    public ResponseEntity<List<ShortNoteGetDTO>> getAllShortNotes() {
        return ResponseEntity.ok(this.shortNoteService.getAllShortNotes());
    }

    @Override
    public ResponseEntity<ShortNoteGetDTO> createShortNote(ShortNotePostDTO shortNotePostDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createShortNote'");
    }
    
}
