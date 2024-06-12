package com.bkopysc.shortmind.repository;

import java.util.Optional;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bkopysc.shortmind.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    @Query("SELECT c FROM Comment c WHERE c.commentId = :commentId")
    public Optional<Comment> findByCommentId(Long commentId);
    
    @Query("SELECT c FROM Comment c WHERE c.shortNote.shortNoteId = :shortNoteId")
    public List<Comment> getCommentsByShortNoteId(Long shortNoteId);

    @Query("SELECT c FROM Comment c WHERE c.user.userId = :userId")
    public List<Comment> getCommentsByUserId(Long userId);


}
