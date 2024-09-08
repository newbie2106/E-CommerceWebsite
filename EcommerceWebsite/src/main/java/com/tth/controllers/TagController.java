/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Category;
import com.tth.pojo.Tag;
import com.tth.pojo.TagProduct;
import com.tth.services.CategoryService;
import com.tth.services.ProductService;
import com.tth.services.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private Environment env;

    @Autowired
    private ProductService productService;

    //     --- TAG ---
    @GetMapping("/tags")
    public String createView(Model model) {
        model.addAttribute("tag", new Tag());

        return "tags";
    }

    @PostMapping("/tags")
    public String createTag(@ModelAttribute(value = "tag") @Valid Tag p,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            try {
                //p.setCreatedDate(new Date());
                this.tagService.addOrUpdateTag(p);
                return "redirect:/manage-tags";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "tags";
    }

    @GetMapping("/tags/{tagId}")
    public String updateView(Model model, @PathVariable(value = "tagId") int id) {
        model.addAttribute("tag", this.tagService.getTagById(id));

        return "tags";
    }

    @RequestMapping("/manage-tags")
    public String tagManagement(Model model) {

        model.addAttribute("tags", this.tagService.getTags());

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.tagService.countTag();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageTags";
    }

//     --- TAG PRODUCT ---
    @GetMapping("/tagProducts")
    public String createTagProductView(Model model, Map<String, String> params,  @RequestParam("username") String branchAdmin) {
        model.addAttribute("tagProduct", new TagProduct());

        model.addAttribute("products", this.productService.getProductsWithInventory(params, branchAdmin));
        return "tagProducts";
    }

    @PostMapping("/tagProducts")
    public String createTagProduct(@ModelAttribute(value = "tagProduct") @Valid TagProduct p,
            Model model, Map<String, String> params) {
        try {

            this.tagService.addOrUpdateTagProduct(p.getProductId().getId(), p.getTagId().getId());
            return "redirect:/manage-tagProducts";
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        // model.addAttribute("products", this.productService.getProducts(params));
        return "products";
    }

    @GetMapping("/tagProducts/{tagProductId}")
    public String updateTagProductView(Model model, @PathVariable(value = "tagProductId") int id,
            Map<String, String> params, @RequestParam("username") String branchAdmin) {
        model.addAttribute("tagProduct", this.tagService.getTagProductById(id));
        model.addAttribute("products", this.productService.getProductsWithInventory(params, branchAdmin));

        return "tagProducts";
    }

    @RequestMapping("/manage-tagProducts")
    public String tagProductManagement(Model model) {

        model.addAttribute("tagProducts", this.tagService.getTagProduct());

        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.tagService.countTag();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageTagProducts";
    }
}
