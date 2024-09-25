/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Branch;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface BranchService {
    public Branch getBrandById(int id);

    public List<Branch> getBrands();
    
    public String getUsernameByBranchId(int branchId);
    
    Branch getBrandByUserAdmin(String username);
}
