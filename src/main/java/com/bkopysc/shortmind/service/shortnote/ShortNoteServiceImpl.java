package com.bkopysc.shortmind.service.shortnote;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bkopysc.shortmind.dto.shortnote.ShortNoteGetDTO;
import com.bkopysc.shortmind.dto.shortnote.ShortNotePostDTO;
import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.model.ShortNote;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.ShortNoteRepository;
import com.bkopysc.shortmind.repository.UserRepository;
import com.bkopysc.shortmind.service.user.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShortNoteServiceImpl implements IShortNoteService{

    private final ShortNoteRepository shortNoteRepository;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Override
    public ShortNoteGetDTO createShortNote(ShortNotePostDTO shortNotePostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getAuthenticatedUser(authentication);

        ShortNote shortNote = ShortNote.builder()
                .title(shortNotePostDTO.getTitle())
                .content(shortNotePostDTO.getContent())
                .build();

        var savedShortNote = this.shortNoteRepository.save(shortNote);

        ShortNoteGetDTO shortNoteGetDTO = ShortNoteGetDTO.builder()
                .id(savedShortNote.getId())
                .title(savedShortNote.getTitle())
                .content(savedShortNote.getContent())
                .createdAt(savedShortNote.getCreatedAt())
                .user(modelMapper.map(user, UserGetDTO.class))
                .build();

        return shortNoteGetDTO;
    }

    @Override
    public ShortNoteGetDTO getShortNoteById(Long id) {
        ShortNote shortNote = this.shortNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));

        ShortNoteGetDTO shortNoteGetDTO = ShortNoteGetDTO.builder()
                .id(shortNote.getId())
                .title(shortNote.getTitle())
                .content(shortNote.getContent())
                .createdAt(shortNote.getCreatedAt())
                .user(modelMapper.map(shortNote.getUser(), UserGetDTO.class))
                .build();

        return shortNoteGetDTO;
    }

    @Override
    public void deleteShortNoteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShortNoteById'");
    }

    @Override
    public List<ShortNoteGetDTO> getAllShortNotes() {
        List<ShortNote> shortNotes = this.shortNoteRepository.findAll();

        List<ShortNoteGetDTO> shortNoteGetDTOs = shortNotes.stream()
                .map(shortNote -> modelMapper.map(shortNote, ShortNoteGetDTO.class))
                .toList();

        return shortNoteGetDTOs;
    }
    
}
