package com.bkopysc.shortmind.service.comment;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.bkopysc.shortmind.dto.comment.CommentGetDTO;
import com.bkopysc.shortmind.dto.comment.CommentPostDTO;
import com.bkopysc.shortmind.dto.user.UserGetDTO;
import com.bkopysc.shortmind.model.Comment;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.CommentRepository;
import com.bkopysc.shortmind.service.shortnote.IShortNoteService;
import com.bkopysc.shortmind.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService{

    private final CommentRepository commentRepository;
    private final IUserService userService;
    private final IShortNoteService shortNoteService;
    private final ModelMapper modelMapper;

    @Override
    public CommentGetDTO createComment(CommentPostDTO commentPostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = this.userService.getAuthenticatedUser(authentication);
        var shortNote = this.shortNoteService.getShortNoteById(commentPostDTO.getShortNoteId());

        Comment newComment = Comment.builder()
                .user(user)
                .shortNote(shortNote)
                .content(commentPostDTO.getContent())
                .build();

        Comment savedComment = this.commentRepository.save(newComment);

        CommentGetDTO commentGetDTO = CommentGetDTO.builder()
                .id(savedComment.getId())
                .content(savedComment.getContent())
                .shortNoteId(commentPostDTO.getShortNoteId())
                .user(this.modelMapper.map(user, UserGetDTO.class))
                .build();
                
        return commentGetDTO;
    }

    @Override
    public CommentGetDTO getCommentById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCommentById'");
    }

    @Override
    public Boolean deleteCommentById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCommentById'");
    }

    @Override
    public List<CommentGetDTO> getAllComments() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllComments'");
    }
    
}
