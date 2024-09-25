/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.DTO.ProductDTO;
import com.tth.pojo.Branch;
import com.tth.pojo.Cart;
import com.tth.pojo.Image;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import com.tth.repositories.ProductRepository;
import com.tth.services.BranchService;
import com.tth.services.ImageService;
import com.tth.services.ProductService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tongh
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImageService imgService;
    @Autowired
    private BranchService branchService;

    @Override
    public long countProduct() {
        return this.productRepo.countProduct();
    }

    @Override
    public void addOrUpdate(Product p, List<MultipartFile> image) {
        this.productRepo.addOrUpdate(p);

        boolean hasValidImage = image != null && image.stream().anyMatch(img -> img != null && !img.isEmpty());
        if (hasValidImage) {

            List<Image> currentImages = this.imgService.getProductImage(p.getId());

            for (Image img : currentImages) {
                this.imgService.deleteImageById(img.getId());
            }

            for (MultipartFile img : image) {
                Image i = new Image();
                //i.setImage(img.getName());
                i.setProduct(p);
                i.setFile(img);
                this.imgService.addImage(i);
            }
        } else {
            System.out.println("TỆP RỖNG RỒI");
        }
    }

    @Override
    public Product getProductById(int id) {
        return this.productRepo.getProductById(id);
    }

    @Override
    public void deleteProduct(int id) {
        this.productRepo.deleteProduct(id);
    }

    @Override
    public boolean addReceipt(Map<String, Cart> cart) {
        if (cart != null) {
            return this.productRepo.addReceipt(cart);
        }
        return false;
    }

    @Override
    public List<Object[]> getProductsWithInventory(Map<String, String> params, String branchAdmin) {
        return this.productRepo.getProductsWithInventory(params, branchAdmin);
    }

    @Override
    public List<ProductDTO> getProducts(Map<String, String> params) {
        return this.productRepo.getProducts(params);
    }

//    @Override
//    public List<ProductDTO> getProducts(Map<String, String> params) {
//        
//    }
    @Override
    public ProductDTO getProductDTOById(int id) {
        return this.productRepo.getProductDTOById(id);
    }
}
