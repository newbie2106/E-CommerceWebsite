/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;

/**
 *
 * @author tongh
 */
public interface InventoryRepository {

    boolean updateProductQuantity(Inventory i);

    Inventory getInventoryByProductAndBranch(Product product, Branch branch);
}
