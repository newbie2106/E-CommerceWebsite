/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.DTO.CommentDTO;
import com.tth.pojo.Comment;
import com.tth.pojo.Product;
import com.tth.pojo.User;
import com.tth.repositories.CommentRepository;
import com.tth.repositories.ProductRepository;
import com.tth.repositories.UserRepository;
import com.tth.services.CommentService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository prodRepo;

    @Override
    public Comment addComment(Map<String, String> params, Principal user) {
        try {
            Comment comment = new Comment();
            String content = params.get("content");
            int productId = Integer.parseInt(params.get("productId"));
            User u = this.userRepository.getUserByUsername(user.getName());
            Product p = this.prodRepo.getProductById(productId);

            comment.setContent(content);
            comment.setProductId(p);
            comment.setUsername(u);
            comment.setCreatedDate(new Date());
            commentRepository.addComment(comment);
            return comment;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CommentDTO> getCommentByProductId(int productId) {
        return this.commentRepository.getCommentByProductId(productId);
    }
}
