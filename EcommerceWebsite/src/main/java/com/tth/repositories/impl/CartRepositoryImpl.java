/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Cart;
import com.tth.repositories.CartRepository;
import java.util.List;
import javax.persistence.NoResultException;
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
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    
    @Override
    public List<Cart> findCartByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Cart c WHERE "
                + "c.username.username = :username");
        query.setParameter("username", username);

        return query.getResultList();
    }

    
    @Override
    public Cart findByUsernameAndProductId(String username, Integer productId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM Cart c WHERE c.username.username = :username AND c.productId.id = :productId");
        query.setParameter("username", username);
        query.setParameter("productId", productId);

        try {
            return (Cart) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void addToCart(Cart cart) {
        Session session = this.factory.getObject().getCurrentSession();
        if (cart.getId() == null) {
            session.save(cart);
        } else {
            session.update(cart);
        }
    }

    @Override
    public void deleteAllByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM Cart c WHERE c.username.username = :username");
        query.setParameter("username", username);
        query.executeUpdate();
    }

    @Override
    public void deleteCartItem(Cart cart) {
        Session session = this.factory.getObject().getCurrentSession();
        session.delete(cart);
    }
}
