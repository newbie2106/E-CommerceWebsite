/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class RecentlyViewedDTO {

    private Integer productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private List<String> imageUrls;

    private Date viewedAt;

    // Constructors, Getters và Setters
    public RecentlyViewedDTO() {
    }

    public RecentlyViewedDTO(Integer productId, String productName, String productDescription,
            BigDecimal productPrice, List<String> imageUrls, Date viewedAt) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.imageUrls = imageUrls;
        this.viewedAt = viewedAt;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(Date viewedAt) {
        this.viewedAt = viewedAt;
    }

    public RecentlyViewedDTO(Integer productId, String productName, List<String> imageUrls, Date viewedAt) {
        this.productId = productId;
        this.productName = productName;
        this.imageUrls = imageUrls;
        this.viewedAt = viewedAt;
    }
}
