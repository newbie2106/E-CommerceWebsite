/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Brand;
import com.tth.services.BrandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBrandController {

    @Autowired
    private BrandService brandService;

    @DeleteMapping("/brands/{brandId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable(value = "brandId") int id) {
        this.brandService.deleteBrand(id);
    }
    
    @GetMapping("/brands/")
    public ResponseEntity<List<Brand>> getProvinces() {
        List<Brand> brand = this.brandService.getBrands();
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }
}
