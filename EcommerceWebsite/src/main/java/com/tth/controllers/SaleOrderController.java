/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.SaleOrderDTO;
import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.SaleOrder;
import com.tth.services.BranchService;
import com.tth.services.SaleOrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tongh
 */
@Controller
public class SaleOrderController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private SaleOrderService saleOrderService;

    @GetMapping("/saleOrder")
    public String getSaleOrdersByBranchId(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        List<SaleOrderDTO> saleOrders = this.saleOrderService.getSaleOrderByBranchAdmin(currentUsername);

        model.addAttribute("saleOrders", saleOrders);
        return "saleOrderBranch";
    }

    @PostMapping("/updateStatus")
    public ModelAndView updateStatus(@RequestParam Integer orderId, @RequestParam ShipmentStatus selectedStatus) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        
        SaleOrder s = this.saleOrderService.getSaleOrderById(orderId);
        
        s.getShipment().setStatus(selectedStatus);
        
        this.saleOrderService.UpdateStatusSaleOrder(s);

        // Tải lại danh sách đơn hàng sau khi cập nhật
        List<SaleOrderDTO> saleOrders = this.saleOrderService.getSaleOrderByBranchAdmin(currentUsername);

        ModelAndView modelAndView = new ModelAndView("saleOrderBranch");
        modelAndView.addObject("saleOrders", saleOrders);

        return modelAndView;
    }
}
