/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.DTO.ProductDTO;
import com.tth.pojo.Cart;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tongh
 */
public interface ProductService {

    public long countProduct();

    List<Object[]> getProductsWithInventory(Map<String, String> params, String branchAdmin);

    public void addOrUpdate(Product p, List<MultipartFile> image);

//    List<Product> getProducts(Map<String, String> params);
    ProductDTO getProductDTOById(int id);

    List<ProductDTO> getProducts(Map<String, String> params);

    Product getProductById(int id);

    void deleteProduct(int id);

    boolean addReceipt(Map<String, Cart> cart);
}
