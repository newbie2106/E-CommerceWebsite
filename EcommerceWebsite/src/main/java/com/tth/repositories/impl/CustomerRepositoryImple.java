/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Admin;
import com.tth.pojo.Customer;
import com.tth.repositories.CustomerRepository;
import com.tth.repositories.UserRepository;
import com.tth.services.UserService;
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
public class CustomerRepositoryImple implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Customer getCustomerByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Customer WHERE username = :username");
        q.setParameter("username", username);
        try {
            return (Customer) q.getSingleResult();
        } catch (NoResultException Ex) {
            return new Customer();
        }
    }
    
    public boolean usernameCustomerExists(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> q = s.createQuery("SELECT COUNT(*) FROM Customer WHERE username = :username", Long.class);
        q.setParameter("username", username);
        Long count = q.getSingleResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean addUserCustomer(Customer customer) {
        Session s = this.factory.getObject().getCurrentSession();
        boolean usernameExisted = this.usernameCustomerExists(customer.getUsername());
        try {
            if (usernameExisted) {
                s.update(customer);
                return true;
            } else {
                s.save(customer);
                return true;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
