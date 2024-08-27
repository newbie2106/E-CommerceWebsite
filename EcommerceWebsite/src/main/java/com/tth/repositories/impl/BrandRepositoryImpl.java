/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Brand;
import com.tth.pojo.Category;
import com.tth.repositories.BrandRepository;
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
public class BrandRepositoryImpl implements BrandRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Brand getBrandById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Brand.class, id);
    }

    @Override
    public List<Brand> getBrands() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Brand.findAll");

        return q.getResultList();
    }

    @Override
    public void addOrUpdateBrand(Brand b) {
        Session s = this.factory.getObject().getCurrentSession();
        if (b.getId()!= null) {
            s.update(b);
        } else {
            s.save(b);
        }
    }

    @Override
    public void deleteBrand(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Brand b = this.getBrandById(id);
        s.delete(b);
    }

    @Override
    public long countBrand() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> p = s.createQuery("SELECT COUNT(c.id) FROM Brand c", Long.class);
        return p.uniqueResult();
    }

}
