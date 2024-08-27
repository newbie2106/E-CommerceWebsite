/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.repositories.ProvinceRepository;
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
public class ProvinceRepositoryImpl implements ProvinceRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Provinces getProvinceById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Provinces.class, id);
    }

    @Override
    public List<Provinces> getProvinces() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Provinces.findAll");

        return q.getResultList();
    }
}
