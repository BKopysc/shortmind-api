package com.bkopysc.shortmind.controller.comment;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bkopysc.shortmind.dto.comment.CommentGetDTO;
import com.bkopysc.shortmind.dto.comment.CommentPostDTO;

@RequestMapping("/default")
public interface ICommentOperations {
    
    @GetMapping("/{id}")
    public ResponseEntity<CommentGetDTO> getCommentById(@PathVariable Long id);

    @GetMapping("/")
    public ResponseEntity<List<CommentGetDTO>> getAllCommentsForShortnote(@RequestParam(required = true, name = "shortNoteId") Long shortNoteId);

    @PostMapping("/")
    public ResponseEntity<CommentGetDTO> createComment(@RequestBody CommentPostDTO commentPostDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id);


}
