/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Category;
import com.tth.repositories.CategoryRepository;
import com.tth.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository cateRepo;

    @Override
    public long countCate() {
        return this.cateRepo.countCate();
    }

    @Override
    public List<Category> getCates() {
        return this.cateRepo.getCates();
    }

    @Override
    public Category getCategoryById(int id) {
        return this.cateRepo.getCategoryById(id);
    }

    @Override
    public void addOrUpdateCate(Category c) {
        this.cateRepo.addOrUpdateCate(c);
    }

    @Override
    public void deleteCategory(int id) {
        this.cateRepo.deleteCategory(id);
    }

}
