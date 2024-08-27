/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Provinces;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface ProvinceRepository {
    public List<Provinces> getProvinces();

    public Provinces getProvinceById(String id);
}
