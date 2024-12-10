/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.DTO.CommentDTO;
import com.tth.pojo.Comment;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tongh
 */
public interface CommentService {

    Comment addComment(Map<String, String> params, Principal user);

    List<CommentDTO> getCommentByProductId(int productId);
}
