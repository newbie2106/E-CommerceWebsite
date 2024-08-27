/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Category;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface CategoryService {

    public long countCate();

    public List<Category> getCates();

    public Category getCategoryById(int id);

    public void addOrUpdateCate(Category c);

    public void deleteCategory(int id);

}
