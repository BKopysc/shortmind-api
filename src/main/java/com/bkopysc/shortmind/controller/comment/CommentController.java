package com.bkopysc.shortmind.controller.comment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkopysc.shortmind.dto.comment.CommentGetDTO;
import com.bkopysc.shortmind.dto.comment.CommentPostDTO;
import com.bkopysc.shortmind.service.comment.ICommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController implements ICommentOperations{
    
    private final ICommentService commentService;

    @Override
    public ResponseEntity<CommentGetDTO> getCommentById(Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @Override
    public ResponseEntity<List<CommentGetDTO>> getAllCommentsForShortnote(Long shortNoteId) {
        return ResponseEntity.ok(commentService.getCommentsByShortnoteId(shortNoteId));
    }

    @Override
    public ResponseEntity<CommentGetDTO> createComment(CommentPostDTO commentPostDTO) {
        return ResponseEntity.ok(commentService.createComment(commentPostDTO));
    }

    @Override
    public ResponseEntity<Boolean> deleteComment(Long id) {
        return ResponseEntity.ok(commentService.deleteCommentById(id));
    }
    


}
