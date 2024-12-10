/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.CommentDTO;
import com.tth.pojo.Comment;
import com.tth.services.CommentService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/")
    public ResponseEntity addComment(@RequestBody Map<String, String> params, Principal user) {
        Comment comment = commentService.addComment(params, user);
        if (comment != null) {
            return new ResponseEntity(comment, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
    
     @GetMapping("/comment/{productId}")
    public List<CommentDTO> getCommentByProductId(@PathVariable("productId") int productId) {
       return this.commentService.getCommentByProductId(productId);
    }

}
