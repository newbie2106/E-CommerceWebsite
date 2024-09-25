/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tongh
 */
public interface InventoryService {

    public boolean updateProductQuantity(@RequestParam Map<String, String> params);
    
}
