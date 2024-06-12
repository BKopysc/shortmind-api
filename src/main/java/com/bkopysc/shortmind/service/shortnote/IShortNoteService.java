package com.bkopysc.shortmind.service.shortnote;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.bkopysc.shortmind.model.ShortNote;

public interface IShortNoteService {

    public ShortNoteGetDTO createShortNote(ShortNotePostDTO shortNotePostDTO);

    public ShortNoteGetDTO getShortNoteDTOById(Long id);

    public ShortNote getShortNoteById(Long id);

    public List<ShortNoteGetDTO> getAllShortNotes();

    public void deleteShortNoteById(Long id);

    public Boolean likeShortNoteById(Long id);

    public Boolean unlikeShortNoteById(Long id);

}
