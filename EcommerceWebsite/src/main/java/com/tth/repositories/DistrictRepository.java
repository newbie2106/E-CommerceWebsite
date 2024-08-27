/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Districts;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface DistrictRepository {

    public List<Districts> getDistricts();

    public Districts getDistrictById(String id);
    
    public List<Districts> getDistrictsByProvineId(String provinceId);

}
