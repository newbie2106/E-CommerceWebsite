/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Provinces;
import com.tth.repositories.ProvinceRepository;
import com.tth.services.ProvinceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */

@Service
public class ProvinceServiceImpl implements ProvinceService{
    
    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public List<Provinces> getProvinces() {
        return this.provinceRepo.getProvinces();
    }

    @Override
    public Provinces getProvinceById(String id) {
        return this.provinceRepo.getProvinceById(id);
    }
}
