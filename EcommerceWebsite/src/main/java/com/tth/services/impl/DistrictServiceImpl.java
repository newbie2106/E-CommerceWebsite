/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Districts;
import com.tth.repositories.DistrictRepository;
import com.tth.services.DistrictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */

@Service
public class DistrictServiceImpl implements DistrictService{

    @Autowired
    private DistrictRepository districtRepo;
    
    @Override
    public List<Districts> getDistricts() {
        return this.districtRepo.getDistricts();
    }

    @Override
    public Districts getDistrictById(String id) {
        return this.districtRepo.getDistrictById(id);
    }

    @Override
    public List<Districts> getDistrictsByProvineId(String provinceId) {
        return this.districtRepo.getDistrictsByProvineId(provinceId);
    }
    
    
    
}
