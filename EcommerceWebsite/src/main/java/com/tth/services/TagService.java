/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Product;
import com.tth.pojo.Tag;
import com.tth.pojo.TagProduct;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface TagService {

    public long countTag();

    public Tag getTagById(int id);

    public List<Tag> getTags();

    public TagProduct getTagProductById(int id);

    public void addOrUpdateTag(Tag tag);

    public void deleteTag(int id);

    public void deleteTagProduct(int id);

    public TagProduct addOrUpdateTagProduct(int productId, int tagId);

    public List<TagProduct> getTagProduct();
}
