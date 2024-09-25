/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import com.tth.services.BranchService;
import com.tth.services.BrandService;
import com.tth.services.CategoryService;
import com.tth.services.ProductService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ProductController {

    @Autowired
    private ProductService prodService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private Environment env;

    @GetMapping("/products")
    public String createView(Model model) {
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute(value = "product") @Valid Product p,
            BindingResult rs, @RequestParam List<MultipartFile> image) {
        if (!rs.hasErrors()) {
            try {
                //p.setCreatedDate(new Date());
                this.prodService.addOrUpdate(p, image);
                return "redirect:/manage-products";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

        return "products";
    }

    @GetMapping("/products/{productId}")
    public String updateView(Model model, @PathVariable(value = "productId") int id) {
        model.addAttribute("product", this.prodService.getProductById(id));

        return "products";
    }

    @RequestMapping("/manage-products")
    public String ProductManagement(Model model, @RequestParam Map<String, String> params,
            @RequestParam(value = "username", required = false) String branchAdmin) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Nếu không có tham số username được truyền vào, sử dụng username hiện tại
        if (branchAdmin == null || branchAdmin.isEmpty()) {
            branchAdmin = currentUsername;
        }
        model.addAttribute("productsWithInventory", this.prodService.getProductsWithInventory(params, branchAdmin));
        long pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.prodService.countProduct();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageProducts";
    }

    @RequestMapping("/admin-manage-products")
    public String ProductAdminManagement(Model model, @RequestParam Map<String, String> params) {

        model.addAttribute("products", this.prodService.getProducts(params));
        long pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.prodService.countProduct();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "adminManageProducts";
    }
    
}
