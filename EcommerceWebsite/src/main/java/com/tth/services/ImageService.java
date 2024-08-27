/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Image;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface ImageService {

    public void addImage(Image image);

    public Image getImageById(int id);

    public void deleteImageById(int id);

    public List<Image> getProductImage(int productId);

}
