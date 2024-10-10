/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.DTO.BranchDTO;
import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.repositories.BranchRepository;
import com.tth.services.BranchService;
import com.tth.services.InventoryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepo;

    @Autowired
    private InventoryService inventoryService;
    
    @Override
    public Branch getBrandById(int id) {
        return this.branchRepo.getBrandById(id);
    }

    @Override
    public List<Branch> getBrands() {
        return this.branchRepo.getBrands();
    }

    @Override
    public String getUsernameByBranchId(int branchId) {
        return this.branchRepo.getUsernameByBranchId(branchId);
    }

    @Override
    public Branch getBrandByUserAdmin(String username) {
        return this.branchRepo.getBrandByUserAdmin(username);
    }

//    Date timeLimit = new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000);
//        List<RecentlyViewed> recentViews = this.recentlyViewedRepository.findRecentViewsByUsername(username, timeLimit);
//
//        return (List<RecentlyViewedDTO>) recentViews.stream().map(rv -> {
//            // Lấy sản phẩm tương ứng từ RecentlyViewed
//            Product product = rv.getProductId();
//            List<Image> allImages = this.imageService.getProductImage(product.getId());
//
//            // Lấy danh sách URL hình ảnh từ imageSet
//            List<String> imageUrls = allImages.stream()
//                    .map(Image::getUrl)
//                    .collect(Collectors.toList());
//
//            // Trả về RecentlyViewedDTO
//            return new RecentlyViewedDTO(
//                    product.getId(),
//                    product.getName(),
//                    product.getDescription(),
//                    product.getPrice(),
//                    imageUrls, 
//                    rv.getViewedAt()
//            );
//        }).collect(Collectors.toList());
//    public List<BranchDTO> getAllBranchDTOs() {
//        List<Branch> branches = this.branchRepo.getBrands();
//
//        return (List<BranchDTO>) branches.stream().map(b -> {
//                List<Inventory> inv = this.inventoryService.getInventoryByBranch(b.getId().toString());
//                return new BranchDTO();
//                }).collect(Collectors.toList());
//    }
}
