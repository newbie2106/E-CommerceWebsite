/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Shipment;
import com.tth.repositories.ShipmentRepository;
import org.hibernate.Session;
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
public class ShipmentRepositoryImpl implements ShipmentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addShipment(Shipment s) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(s);
    }

}
