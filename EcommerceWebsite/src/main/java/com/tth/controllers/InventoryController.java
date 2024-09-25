/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Inventory;
import com.tth.services.InventoryService;
import com.tth.services.ProductService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tongh
 */
@Controller
public class InventoryController {

    @Autowired
    private ProductService prodService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/update-product-quantity/{productId}")
    public String viewProductQuantity(Model model, @PathVariable(value = "productId") int id) {
        model.addAttribute("product", this.prodService.getProductById(id));
        return "updateProductQuantity";
    }

    @PostMapping("/update-product-quantity")
    public String updateProductQuantity(@RequestParam Map<String, String> params) {

        this.inventoryService.updateProductQuantity(params);
        return "redirect:/manage-products";
//        try {
//            this.inventoryService.updateProductQuantity(params);
//            return "redirect:/manage-products";
//        } catch (Exception ex) {
//            System.out.println("aala");
//            System.err.println(ex.getMessage());
//        }

        //return "updateProductQuantity";
    }
}
