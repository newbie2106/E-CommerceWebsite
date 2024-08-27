/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Category;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface CategoryRepository {

    public long countCate();

    public Category getCategoryById(int id);

    public List<Category> getCates();

    public void addOrUpdateCate(Category cate);

    public void deleteCategory(int id);

}
