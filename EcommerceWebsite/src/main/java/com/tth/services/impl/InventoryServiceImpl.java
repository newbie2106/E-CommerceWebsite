/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import com.tth.repositories.InventoryRepository;
import com.tth.services.BranchService;
import com.tth.services.InventoryService;
import com.tth.services.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    
    @Autowired
    private BranchService branchService;
    
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryRepository inventoryRepo;
    
    @Override
    public List<Inventory> getInventoryByBranch(String branch) {
        return this.inventoryRepo.getInventoryByBranch(branch);
    }
    
    @Override
    public boolean updateProductQuantity(Map<String, String> params) {
        String branchId = params.get("branchId");
        String productId = params.get("productId");
        int availableQuantity = Integer.parseInt(params.get("availableQuantity"));
        
        Branch branch = branchService.getBrandByUserAdmin(branchId);
        Product product = productService.getProductById(Integer.parseInt(productId));

        // Kiểm tra sản phẩm trong Inventory
        Inventory inventory = inventoryRepo.getInventoryByProductAndBranch(product, branch);
        
        if (inventory != null) {
            // Cập nhật số lượng nếu đã có
            inventory.setAvailableQuantity(availableQuantity);
            return inventoryRepo.updateProductQuantity(inventory);
        } else {
            // Tạo mới nếu chưa có
            Inventory newInventory = new Inventory();
            newInventory.setBranchId(branch);
            newInventory.setProductId(product);
            newInventory.setAvailableQuantity(availableQuantity);
            return inventoryRepo.updateProductQuantity(newInventory);
        }
    }
    
    @Override
    public boolean updateInventoryQuantity(int productId, int branchId, int quantityPurchased) {
        return this.inventoryRepo.updateInventoryQuantity(productId, branchId, quantityPurchased);
    }
    
    @Override
    public boolean updateProductQuantity(Inventory i) {
        return this.inventoryRepo.updateProductQuantity(i);
    }
    
}
