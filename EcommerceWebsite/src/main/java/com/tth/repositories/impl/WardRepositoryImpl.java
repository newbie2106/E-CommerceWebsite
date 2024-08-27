/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Wards;
import com.tth.repositories.WardRepository;
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
public class WardRepositoryImpl implements WardRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Wards getWardById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Wards.class, id);
    }

    @Override
    public List<Wards> getWards() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Wards.findAll");

        return q.getResultList();
    }

    @Override
    public List<Wards> getWardsByDistrictId(String districtId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Wards WHERE districtCode.code = :districtId");
        q.setParameter("districtId", districtId);

        return q.getResultList();
    }
}
