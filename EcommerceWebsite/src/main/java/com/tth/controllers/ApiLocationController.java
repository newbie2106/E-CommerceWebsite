/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.Wards;
import com.tth.services.DistrictService;
import com.tth.services.ProvinceService;
import com.tth.services.WardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiLocationController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private WardService wardService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces/")
    public ResponseEntity<List<Provinces>> getProvinces() {
        List<Provinces> provinces = this.provinceService.getProvinces();
        return new ResponseEntity<>(provinces, HttpStatus.OK);
    }

    @GetMapping("/province/{code}/districts/")
    public ResponseEntity<List<Districts>> getDistrictByProvincesCode(@PathVariable(value = "code") String code) { // value tren param link
        List<Districts> districts = this.districtService.getDistrictsByProvineId(code);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/district/{code}/wards/")
    public ResponseEntity<List<Wards>> getWardsByDistrictCode(@PathVariable(value = "code") String code) { // value tren param link
        List<Wards> wards = this.wardService.getWardsByDistrictId(code);
        return new ResponseEntity<>(wards, HttpStatus.OK);

    }
}
