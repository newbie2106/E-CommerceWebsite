/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.OrderDetail;
import com.tth.repositories.OrderDetailsRepository;
import org.hibernate.HibernateException;
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
public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrderDetail(OrderDetail o) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(o);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
