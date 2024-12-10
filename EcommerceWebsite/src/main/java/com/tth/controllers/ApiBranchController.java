/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.BranchDTO;
import com.tth.services.BranchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/branch/")
    public ResponseEntity<List<BranchDTO>> getBrands() {
        List<BranchDTO> branch = this.branchService.getBranchDTO();
        return new ResponseEntity<>(branch, HttpStatus.OK);
    }
}
