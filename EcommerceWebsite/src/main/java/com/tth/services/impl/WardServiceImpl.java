/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Wards;
import com.tth.repositories.WardRepository;
import com.tth.services.WardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */

@Service
public class WardServiceImpl implements WardService{

    @Autowired
    private WardRepository wardRepo;
    
    @Override
    public List<Wards> getWards() {
        return this.wardRepo.getWards();
    }

    @Override
    public Wards getWardById(String id) {
        return this.wardRepo.getWardById(id);
    }

    @Override
    public List<Wards> getWardsByDistrictId(String districtId) {
        return this.wardRepo.getWardsByDistrictId(districtId);
    }
    
}
