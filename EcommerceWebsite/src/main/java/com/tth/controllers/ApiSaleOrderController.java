package com.tth.controllers;

import com.tth.DTO.SaleOrderDTO;
import com.tth.pojo.SaleOrder;
import com.tth.services.SaleOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiSaleOrderController {

    @Autowired
    private SaleOrderService saleOrderService;

    @PostMapping("/orders/")
    public ResponseEntity<String> createSaleOrder(@RequestBody SaleOrderDTO saleOrderDTO) {
        System.out.println("SaleOrderDTO: " + saleOrderDTO);
        System.out.println("SaleOrderDTO: " + saleOrderDTO.getShippingAdressId());

        boolean createdOrder = saleOrderService.AddSaleOrder(saleOrderDTO);

        if (createdOrder) {
            return ResponseEntity.ok("SaleOrder added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create SaleOrder due to invalid data.");
        }
    }
}
