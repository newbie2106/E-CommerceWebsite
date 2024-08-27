/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Product;
import com.tth.pojo.Tag;
import com.tth.pojo.TagProduct;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface TagRepository {

    public long countTag();

    public Tag getTagById(int id);
    
    public TagProduct getTagProductById(int id);

    public List<Tag> getTags();

    public void addOrUpdateTag(Tag tag);

    public void deleteTag(int id);

    public void deleteTagProduct(int id);

    public boolean addOrUpdateTagProduct(TagProduct tagProduct);

    public List<TagProduct> getTagProduct();
}
