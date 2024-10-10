/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Carrier;
import com.tth.repositories.CarrierRepository;
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
public class CarrierRepositoryImpl implements CarrierRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Carrier getCarrierById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Carrier.class, id);
    }

    @Override
    public List<Carrier> getListCarrier() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Carrier.findAll");

        return q.getResultList();
    }

}
