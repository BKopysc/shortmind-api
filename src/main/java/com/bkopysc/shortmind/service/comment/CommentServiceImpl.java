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
import com.bkopysc.shortmind.exceptions.ObjectNotFoundException;
import com.bkopysc.shortmind.exceptions.UserNotOwnerException;
import com.bkopysc.shortmind.model.Comment;
import com.bkopysc.shortmind.model.User;
import com.bkopysc.shortmind.repository.CommentRepository;
import com.bkopysc.shortmind.service.shortnote.IShortNoteService;
import com.bkopysc.shortmind.service.user.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

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

        CommentGetDTO commentGetDTO = this.modelMapper.map(savedComment, CommentGetDTO.class);

        return commentGetDTO;
    }

    @Override
    public List<CommentGetDTO> getCommentsByShortnoteId(Long shortNoteId){
        List<Comment> comments = this.commentRepository.getCommentsByShortNoteId(shortNoteId);

        List<CommentGetDTO> commentGetDTOs = comments.stream()
                .map(comment -> this.modelMapper.map(comment, CommentGetDTO.class)).toList();
                
        return commentGetDTOs;
    }

    @Override
    public CommentGetDTO getCommentById(Long id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found"));
        return this.modelMapper.map(comment, CommentGetDTO.class);
    }

    @Override
    public Boolean deleteCommentById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = this.userService.getAuthenticatedUser(authentication);

        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found"));

        if (comment.getUser().getId() != user.getId()) {
            throw new UserNotOwnerException("Comment");
        }

        this.commentRepository.deleteById(id);
        return true;
    }

    @Override
    public List<CommentGetDTO> getAllComments() {
        List<Comment> comments = this.commentRepository.findAll();
        List<CommentGetDTO> commentGetDTOs = comments.stream()
                .map(comment -> this.modelMapper.map(comment, CommentGetDTO.class)).toList();
        return commentGetDTOs;
    }

}
