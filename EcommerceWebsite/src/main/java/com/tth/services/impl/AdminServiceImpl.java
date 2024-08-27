/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Admin;
import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.User;
import com.tth.pojo.Wards;
import com.tth.repositories.AdminRepository;
import com.tth.services.AdminService;
import com.tth.services.DistrictService;
import com.tth.services.ProvinceService;
import com.tth.services.WardService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private DistrictService districtService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private WardService wardService;

//    @Override
//    public boolean addOrUpdateUserAdmin(Map<String, String> params, User user) {
//        Admin admin = new Admin();
//        Provinces p = this.provinceService.getProvinceById(params.get("admin.provinceId"));
//        Districts d = this.districtService.getDistrictById(params.get("admin.districtId"));
//        Wards w = this.wardService.getWardById(params.get("admin.wardId"));
//
//        admin.setAddress(params.get("admin.address"));
//        admin.setPersonalId(params.get("admin.personalId"));
//        admin.setEmail(params.get("admin.email"));
//        admin.setPhone(params.get("admin.phone"));
//
//        admin.setUser(user);
//        admin.setUsername(params.get("username"));
//        admin.setProvinceId(p);
//        admin.setDistrictId(d);
//        admin.setWardId(w);
//        return this.adminRepo.addOrUpdateUserAdmin(admin);
//    }
    
    
    
    
    
    @Override
    public boolean addOrUpdateUserAdmin(Admin admin) {
        return this.adminRepo.addOrUpdateUserAdmin(admin);
    }

    @Override
    public boolean usernameAdminExists(String username) {
        return this.adminRepo.usernameAdminExists(username);
    }

}
