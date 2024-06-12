package com.bkopysc.shortmind.service.shortnoteLike;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bkopysc.shortmind.dto.shortnoteLike.ShortNoteLikeGetDTO;
import com.bkopysc.shortmind.dto.shortnoteLike.ShortNoteLikePostDTO;
import com.bkopysc.shortmind.exceptions.ObjectExistedException;
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.model.ShortNote;
import com.bkopysc.shortmind.model.ShortNoteLike;
import com.bkopysc.shortmind.repository.ShortNoteLikeRepository;
import com.bkopysc.shortmind.repository.ShortNoteRepository;
import com.bkopysc.shortmind.repository.UserRepository;
import com.bkopysc.shortmind.service.shortnote.IShortNoteService;
import com.bkopysc.shortmind.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class ShortNoteLikesServiceImpl implements IShortNoteLikeService{

    private final ShortNoteLikeRepository shortNoteLikeRepository;
    private final ShortNoteRepository shortNoteRepository;
    private final IUserService userService;

    @Override
    public Boolean like(ShortNote shortNote) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = this.userService.getAuthenticatedUser(authentication);

        Optional<ShortNoteLike> isShortNoteLike = this.shortNoteLikeRepository.getLikeByShortNoteIdAndUserId(shortNote.getId(), user.getId());
        if(isShortNoteLike.isPresent()){
            throw new ObjectExistedException("ShortNoteLike");
        }

        ShortNoteLike shortNoteLike = ShortNoteLike.builder()
                .user(user)
                .shortNote(shortNote)
                .build();

        this.shortNoteLikeRepository.save(shortNoteLike);
        return true;
    }

    @Override
    public Boolean unlike(ShortNote shortNote) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = this.userService.getAuthenticatedUser(authentication);

        Optional<ShortNoteLike> shortNoteLike = this.shortNoteLikeRepository.getLikeByShortNoteIdAndUserId(shortNote.getId(), user.getId());
        if(!shortNoteLike.isPresent()){
            throw new ObjectNotFoundException("ShortNoteLike");
        }

        this.shortNoteLikeRepository.delete(shortNoteLike.get());
        return true;
    }

    @Override
    public Long getCountOfLikes(Long shortNoteId) {
        ShortNote shortNote = this.shortNoteRepository.findById(shortNoteId).orElseThrow(() -> new ObjectNotFoundException("ShortNote"));
        Long count = this.shortNoteLikeRepository.countByShortNoteId(shortNote.getId());
        return count;
    }

    @Override
    public Boolean ifLikeByShortNoteId(Long shortNoteId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = this.userService.getAuthenticatedUser(authentication);
        Optional<ShortNoteLike> shortNoteLike = this.shortNoteLikeRepository
            .getLikeByShortNoteIdAndUserId(shortNoteId, user.getId());
        if(!shortNoteLike.isPresent()){
            return false;
        }
        return true;
    }

    
    
}
