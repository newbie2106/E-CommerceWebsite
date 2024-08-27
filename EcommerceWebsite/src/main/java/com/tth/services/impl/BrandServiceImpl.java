/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.pojo.Brand;
import com.tth.repositories.BrandRepository;
import com.tth.services.BrandService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepo;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public void addOrUpdateBrand(Brand b) {
        if (!b.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(b.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                b.setLogo(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.brandRepo.addOrUpdateBrand(b);
    }

    @Override
    public List<Brand> getBrands() {
        return this.brandRepo.getBrands();
    }

    @Override
    public Brand getBrandById(int id) {
        return this.brandRepo.getBrandById(id);
    }

    @Override
    public void deleteBrand(int id) {
        this.brandRepo.deleteBrand(id);
    }

    @Override
    public long countBrand() {
        return this.brandRepo.countBrand();
    }

}
