/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.RecentlyViewed;
import com.tth.pojo.ShippingAddress;
import com.tth.repositories.ShippingAddressRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
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
@Transactional
@Repository
public class ShippingAddressRepositoryImpl implements ShippingAddressRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrUpdateShippingAddress(ShippingAddress shippingAddress) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Date now = new Date();
            if (shippingAddress.getId() != null && shippingAddress.getId() > 0) {
                shippingAddress.setUpdatedAt(now);
                s.update(shippingAddress);

            } else {
                shippingAddress.setUpdatedAt(now);
                shippingAddress.setCreatedAt(now);
                s.save(shippingAddress);

            }
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

    @Override
    public List<ShippingAddress> getAllAddressesByUserName(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM ShippingAddress sp WHERE "
                + "sp.username.username = :username");
        query.setParameter("username", username);

        return query.getResultList();
    }

    @Override
    public ShippingAddress getShippingAddressByUsernameAndId(String username, Long id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM ShippingAddress sp WHERE sp.username.username = :username "
                + "AND sp.id = :id");
        query.setParameter("username", username);
        query.setParameter("id", id);

        try {
            return (ShippingAddress) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
