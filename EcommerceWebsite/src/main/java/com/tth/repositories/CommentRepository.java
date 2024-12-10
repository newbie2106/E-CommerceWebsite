/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.DTO.CommentDTO;
import com.tth.pojo.Comment;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface CommentRepository {

    boolean addComment(Comment comment);

    List<CommentDTO> getCommentByProductId(int productId);
    
    CommentDTO convertToCommentDTO(Comment comment);
}
