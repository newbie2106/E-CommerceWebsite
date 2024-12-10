/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.DTO;

/**
 *
 * @author tongh
 */
public class BranchDTO {

    private int branchId;
    private String address;
    private String province;
    private String district;
    private String wards;    

    public BranchDTO() {
    }

    
    public BranchDTO(int branchId, String address, String province, String district, String wards) {
        this.branchId = branchId;
        this.address = address;
        this.province = province;
        this.district = district;
        this.wards = wards;
    }

    public int getBranch() {
        return branchId;
    }

    public void setBranch(int branchId) {
        this.branchId = branchId;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }


}
