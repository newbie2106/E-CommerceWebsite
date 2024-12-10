package com.tth.controllers;

import com.tth.DTO.SaleOrderDTO;
import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.Branch;
import com.tth.pojo.SaleOrder;
import com.tth.services.BranchService;
import com.tth.services.SaleOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @Autowired
    private BranchService branchService;

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

    @GetMapping("/orders/{username}")
    public List<SaleOrderDTO> getSaleOrdersByUsername(@PathVariable("username") String username) {

        if (username != null) {
            // Gọi service để lấy danh sách SaleOrderDTO
            return this.saleOrderService.getSaleOrderByUsername(username);
        } else {
            
            return List.of();
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/orders/{id}/delivered")
    public ResponseEntity<String> markAsDelivered(@PathVariable("id") Integer orderId) {
        try {
            SaleOrder s = this.saleOrderService.getSaleOrderById(orderId);

            s.setPaid(Boolean.TRUE);
            s.getShipment().setStatus(ShipmentStatus.Delivered);

            this.saleOrderService.UpdateStatusSaleOrder(s);
            return ResponseEntity.ok("Đã nhận hàng thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/orders/{id}/cancel")
    public ResponseEntity<String> markAsCancel(@PathVariable("id") Integer orderId) {
        try {

            this.saleOrderService.cancelSaleOrder(orderId);
            return ResponseEntity.ok("Đã hủy đơn hàng thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
