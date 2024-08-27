/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Brand;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface BrandService {

    public List<Brand> getBrands();

    public Brand getBrandById(int id);

    public void addOrUpdateBrand(Brand b);

    public void deleteBrand(int id);

    public long countBrand();

}
