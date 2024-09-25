/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.ProductDTO;
import com.tth.pojo.Image;
import com.tth.pojo.Product;
import com.tth.services.ImageService;
import com.tth.services.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiProductController {

    @Autowired
    private ProductService prodService;

    @DeleteMapping("/products/{productId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(value = "productId") int id) {
        this.prodService.deleteProduct(id);
    }

    @GetMapping("/products/")
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam Map<String, String> params) {
        List<ProductDTO> prod = this.prodService.getProducts(params);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping(path = "/product/{productId}/", produces = {
        MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductDTO> getCustomerPostById(@PathVariable(value = "productId") int id) {
        return new ResponseEntity<>(this.prodService.getProductDTOById(id), HttpStatus.OK);
    }

}
