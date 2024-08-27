/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Category;
import com.tth.repositories.CategoryRepository;
import java.util.List;
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
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public long countCate() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> p = s.createQuery("SELECT COUNT(c.id) FROM Category c", Long.class);
        return p.uniqueResult();
    }

    @Override
    public List<Category> getCates() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Category.findAll");

        return q.getResultList();
    }

    @Override
    public Category getCategoryById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Category.class, id);
    }

    public void addOrUpdateCate(Category c) {
        Session s = this.factory.getObject().getCurrentSession();
        if (c.getId()!= null) {
            s.update(c);
        } else {
            s.save(c);
        }
    }

    @Override
    public void deleteCategory(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Category c = this.getCategoryById(id);
        s.delete(c);
    }
}
