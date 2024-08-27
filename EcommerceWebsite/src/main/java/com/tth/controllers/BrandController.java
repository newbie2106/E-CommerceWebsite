/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Brand;
import com.tth.services.BrandService;
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

/**
 *
 * @author tongh
 */
@Controller
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private Environment env;

    @GetMapping("/brands")
    public String createView(Model model) {
        model.addAttribute("brand", new Brand());
        return "brands";
    }

    @PostMapping("/brands")
    public String createBrand(@ModelAttribute(value = "brand") @Valid Brand p,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                this.brandService.addOrUpdateBrand(p);
                return "redirect:/manage-brands";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "brands";
    }

    @GetMapping("/brands/{brandId}")
    public String updateView(Model model, @PathVariable(value = "brandId") int id) {
        model.addAttribute("brand", this.brandService.getBrandById(id));

        return "brands";
    }

    @RequestMapping("/manage-brands")
    public String BrandManagement(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("brands", this.brandService.getBrands());

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.brandService.countBrand();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageBrands";
    }
}
