package com.bkopysc.shortmind.service.shortnote;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
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
import com.bkopysc.shortmind.model.ShortNoteLike;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.ShortNoteLikeRepository;
import com.bkopysc.shortmind.repository.ShortNoteRepository;
import com.bkopysc.shortmind.repository.UserRepository;
import com.bkopysc.shortmind.service.shortnoteLike.IShortNoteLikeService;
import com.bkopysc.shortmind.service.user.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Primary
public class ShortNoteServiceImpl implements IShortNoteService{

    private final ShortNoteRepository shortNoteRepository;
    private final ShortNoteLikeRepository shortNoteLikeRepository;
    private final IShortNoteLikeService shortNoteLikeService;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Override
    public ShortNoteGetDTO createShortNote(ShortNotePostDTO shortNotePostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getAuthenticatedUser(authentication);

        ShortNote shortNote = ShortNote.builder()
                .title(shortNotePostDTO.getTitle())
                .content(shortNotePostDTO.getContent())
                .user(user)
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
    public ShortNoteGetDTO getShortNoteDTOById(Long id) {
        ShortNote shortNote = this.shortNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));
        Long likes = this.shortNoteLikeRepository.countByShortNoteId(id);

        ShortNoteGetDTO shortNoteGetDTO = ShortNoteGetDTO.builder()
                .id(shortNote.getId())
                .title(shortNote.getTitle())
                .content(shortNote.getContent())
                .createdAt(shortNote.getCreatedAt())
                .user(modelMapper.map(shortNote.getUser(), UserGetDTO.class))
                .likes(likes)
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

        shortNoteGetDTOs.stream()
            .forEach(shortNoteGetDTO -> {
                Long likes = this.shortNoteLikeRepository.countByShortNoteId(shortNoteGetDTO.getId());
                shortNoteGetDTO.setLikes(likes);
            });

        return shortNoteGetDTOs;
    }

    @Override
    public ShortNote getShortNoteById(Long id) {
        return this.shortNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));
    }

    @Override
    public Boolean likeShortNoteById(Long id) {
        ShortNote shortNote = this.shortNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));
        return this.shortNoteLikeService.like(shortNote);
    }

    @Override
    public Boolean unlikeShortNoteById(Long id) {
        ShortNote shortNote = this.shortNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));
        return this.shortNoteLikeService.unlike(shortNote);
    }
    
}
