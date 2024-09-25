/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Category;
import com.tth.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiCategoryController {

    @Autowired
    private CategoryService cateService;

    @DeleteMapping("/categories/{categoryId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable(value = "categoryId") int id) {
        this.cateService.deleteCategory(id);
    }
    @GetMapping("/categories/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> cate = this.cateService.getCates();
        return new ResponseEntity<>(cate, HttpStatus.OK);
    }

    

}
