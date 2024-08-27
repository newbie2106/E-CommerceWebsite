/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tth.pojo.Image;
import com.tth.repositories.ImageRepository;
import com.tth.services.ImageService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private ImageRepository imgRepo;

    @Override
    public void addImage(Image image) {
        if (!image.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(image.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                image.setUrl(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ProductServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.imgRepo.addImage(image);
        }

    }

    @Override
    public Image getImageById(int id) {
        return this.imgRepo.getImageById(id);
    }

    @Override
    public void deleteImageById(int id) {
        this.imgRepo.deleteImageById(id);
    }

    @Override
    public List<Image> getProductImage(int productId) {
        return this.imgRepo.getProductImage(productId);
    }

}
