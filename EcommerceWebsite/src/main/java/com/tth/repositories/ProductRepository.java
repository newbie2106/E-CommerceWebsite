/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Cart;
import com.tth.pojo.Product;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tongh
 */
public interface ProductRepository {

    public long countProduct();

    List<Object[]> getProductsWithInventory(Map<String, String> params, String branchAdmin);

    public boolean addOrUpdate(Product p);

    Product getProductById(int id);

    void deleteProduct(int id);

    boolean addReceipt(Map<String, Cart> cart);

}
