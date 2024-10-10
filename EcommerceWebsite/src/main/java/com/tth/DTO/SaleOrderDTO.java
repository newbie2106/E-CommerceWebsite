/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.DTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tongh
 */
public class SaleOrderDTO {

    private String username;
    private Date createdDate;
    private BigDecimal totalAmount;
    private boolean isPaid;
    private String note;
    private Integer branchId;
    private Integer shippingAdressId;
    private Integer carrierId;

    public SaleOrderDTO() {
    }

    public SaleOrderDTO(String username, Date createdDate, BigDecimal totalAmount, boolean isPaid, String note, Integer branchId, Integer shippingAdressId, Integer carrierId, List<OrderDetailDTO> orderDetails) {
        this.username = username;
        this.createdDate = createdDate;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
        this.note = note;
        this.branchId = branchId;
        this.shippingAdressId = shippingAdressId;
        this.carrierId = carrierId;
        this.orderDetails = orderDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getShippingAdressId() {
        return shippingAdressId;
    }

    public void setShippingAdressId(Integer shippingAdressId) {
        this.shippingAdressId = shippingAdressId;
    }

    public Integer getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    private List<OrderDetailDTO> orderDetails;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
