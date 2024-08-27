/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Product;
import com.tth.pojo.Tag;
import com.tth.pojo.TagProduct;
import com.tth.repositories.TagRepository;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tongh
 */
@Repository
@Transactional
public class TagRepositoryImpl implements TagRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public long countTag() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> p = s.createQuery("SELECT COUNT(c.id) FROM Tag c", Long.class);
        return p.uniqueResult();
    }

    @Override
    public Tag getTagById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Tag.class, id);
    }

    @Override
    public TagProduct getTagProductById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(TagProduct.class, id);
    }

    @Override
    public List<Tag> getTags() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Tag.findAll");

        return q.getResultList();
    }

    @Override
    public void addOrUpdateTag(Tag tag) {
        Session s = this.factory.getObject().getCurrentSession();
        if (tag.getId() != null) {
            s.update(tag);
        } else {
            s.save(tag);
        }
    }

    @Override
    public void deleteTag(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Tag tag = this.getTagById(id);
        s.delete(tag);
    }

    @Override
    public boolean addOrUpdateTagProduct(TagProduct tagProduct) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
           if (tagProduct.getId() != null) {
            session.update(tagProduct);
        } else {
            session.save(tagProduct);
        }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TagProduct> getTagProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT tp FROM TagProduct tp JOIN FETCH tp.productId JOIN FETCH tp.tagId");
        return q.getResultList();
    }

    @Override
    public void deleteTagProduct(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        TagProduct tagProduct = this.getTagProductById(id);
        s.delete(tagProduct);
    }

}
