/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Districts;
import com.tth.repositories.DistrictRepository;
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
public class DistrictRepositoryImpl implements DistrictRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Districts getDistrictById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Districts.class, id);
    }

    @Override
    public List<Districts> getDistricts() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Districts.findAll");

        return q.getResultList();
    }

    @Override
    public List<Districts> getDistrictsByProvineId(String provinceId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Districts WHERE provinceCode.code = :provinceId");
        q.setParameter("provinceId", provinceId);

        return q.getResultList();
    }
}
