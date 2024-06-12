package com.bkopysc.shortmind.service.comment;

import java.util.List;

import com.bkopysc.shortmind.dto.comment.CommentGetDTO;
import com.bkopysc.shortmind.dto.comment.CommentPostDTO;

public interface ICommentService {
    
    public CommentGetDTO createComment(CommentPostDTO commentPostDTO);
    public CommentGetDTO getCommentById(Long id);
    public Boolean deleteCommentById(Long id);
    public List<CommentGetDTO> getAllComments();

}
