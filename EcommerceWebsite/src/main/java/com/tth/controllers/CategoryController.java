/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Category;
import com.tth.services.CategoryService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tongh
 */

@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService cateService;
    
    @Autowired
    private Environment env;
    
    @GetMapping("/categories")
    public String createView(Model model) {
        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute(value = "category") @Valid Category p,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                //p.setCreatedDate(new Date());
                this.cateService.addOrUpdateCate(p);
                return "redirect:/manage-categories";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "categories";
    }

    @GetMapping("/categories/{categoryId}")
    public String updateView(Model model, @PathVariable(value = "categoryId") int id) {
        model.addAttribute("category", this.cateService.getCategoryById(id));

        return "categories";
    }
    
    @RequestMapping("/manage-categories")
    public String CategoryManagement(Model model,@RequestParam Map<String, String> params) {
        
        model.addAttribute("categories", this.cateService.getCates());
        
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.cateService.countCate();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageCategories";
    }
}
