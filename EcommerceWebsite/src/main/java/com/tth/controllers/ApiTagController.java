/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiTagController {
    
    @Autowired
    private TagService tagService;
    
    @DeleteMapping("/tags/{tagId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable(value = "tagId") int id) {
        this.tagService.deleteTag(id);
    }
    
    @DeleteMapping("/tagProducts/{tagProductId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTagProduct(@PathVariable(value = "tagProductId") int id) {
        this.tagService.deleteTagProduct(id);
    }
}
