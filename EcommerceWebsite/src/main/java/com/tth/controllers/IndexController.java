/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.services.BranchService;
import com.tth.services.BrandService;
import com.tth.services.CategoryService;
import com.tth.services.DistrictService;
import com.tth.services.ProductService;
import com.tth.services.ProvinceService;
import com.tth.services.RoleService;
import com.tth.services.TagService;
import com.tth.services.UserService;
import com.tth.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author tongh
 */
@Controller
@ControllerAdvice
public class IndexController {

    @Autowired
    private ProductService ProductService;

    @Autowired
    private CategoryService cateService;

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BranchService branchService;
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("role", this.roleService.getRole());
        model.addAttribute("categories", this.cateService.getCates());
        model.addAttribute("brands", this.brandService.getBrands());
        model.addAttribute("tags", this.tagService.getTags());
        model.addAttribute("branches", this.branchService.getBrands());
    }

    @RequestMapping("/")
    public String login(Model model) {
        return "redirect:/login";
    }
    
    @RequestMapping("/dashboard")
    public String index(Model model) {
        model.addAttribute("countProduct", this.ProductService.countProduct());
        model.addAttribute("countCate", this.cateService.countCate());
        model.addAttribute("countUser", this.userService.countUser());
        model.addAttribute("countBrand", this.brandService.countBrand());
        model.addAttribute("countTag", this.tagService.countTag());

        return "index";
    }
}
