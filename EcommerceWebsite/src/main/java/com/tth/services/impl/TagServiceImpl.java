/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Product;
import com.tth.pojo.Tag;
import com.tth.pojo.TagProduct;
import com.tth.repositories.ProductRepository;
import com.tth.repositories.TagRepository;
import com.tth.services.TagService;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class TagServiceImpl implements TagService {
    
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private TagRepository tagRepo;
    
    @Autowired
    private ProductRepository productRepo;
    
    @Override
    public long countTag() {
        return this.tagRepo.countTag();
    }
    
    @Override
    public Tag getTagById(int id) {
        return this.tagRepo.getTagById(id);
    }
    
    @Override
    public List<Tag> getTags() {
        return this.tagRepo.getTags();
    }
    
    @Override
    public void addOrUpdateTag(Tag tag) {
        this.tagRepo.addOrUpdateTag(tag);
    }
    
    @Override
    public void deleteTag(int id) {
        this.tagRepo.deleteTag(id);
    }
    
    @Override
    public TagProduct addOrUpdateTagProduct(int productId, int tagId) {
        try {
            TagProduct tagProduct = new TagProduct();
            Product product = productRepo.getProductById(productId);
            Tag tag = tagRepo.getTagById(tagId);
            tagProduct.setProductId(product);
            tagProduct.setTagId(tag);
            
            this.tagRepo.addOrUpdateTagProduct(tagProduct);
            
            return tagProduct;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<TagProduct> getTagProduct() {
        return this.tagRepo.getTagProduct();
    }
    
    @Override
    public TagProduct getTagProductById(int id) {
        return this.tagRepo.getTagProductById(id);
    }
    
    @Override
    public void deleteTagProduct(int id) {
        this.tagRepo.deleteTagProduct(id);
    }
    
}
