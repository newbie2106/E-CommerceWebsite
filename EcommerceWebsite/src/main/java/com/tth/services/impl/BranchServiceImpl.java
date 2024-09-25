/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Branch;
import com.tth.repositories.BranchRepository;
import com.tth.services.BranchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    @Override
    public Branch getBrandById(int id) {
        return this.branchRepo.getBrandById(id);
    }

    @Override
    public List<Branch> getBrands() {
        return this.branchRepo.getBrands();
    }

    @Override
    public String getUsernameByBranchId(int branchId) {
        return this.branchRepo.getUsernameByBranchId(branchId);
    }

    @Override
    public Branch getBrandByUserAdmin(String username) {
        return this.branchRepo.getBrandByUserAdmin(username);
    }

}
