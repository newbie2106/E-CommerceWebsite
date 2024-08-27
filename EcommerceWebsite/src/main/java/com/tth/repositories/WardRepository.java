/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Wards;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface WardRepository {

    public List<Wards> getWards();

    public Wards getWardById(String id);

    public List<Wards> getWardsByDistrictId(String districtId);

}
