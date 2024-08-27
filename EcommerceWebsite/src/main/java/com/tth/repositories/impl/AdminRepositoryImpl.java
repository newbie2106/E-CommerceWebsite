/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.DTO.UserAdminDTO;
import com.tth.pojo.Admin;
import com.tth.pojo.User;
import com.tth.repositories.AdminRepository;
import com.tth.services.UserService;
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
@Repository
@Transactional
public class AdminRepositoryImpl implements AdminRepository {

    @Autowired
    private LocalSessionFactoryBean factory;


    public boolean usernameAdminExists(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> q = s.createQuery("SELECT COUNT(*) FROM Admin WHERE username = :username", Long.class);
        q.setParameter("username", username);
        Long count = q.getSingleResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addOrUpdateUserAdmin(Admin admin) {
        Session s = this.factory.getObject().getCurrentSession();
        boolean usernameAdminExisted = this.usernameAdminExists(admin.getUsername());
        try {
            if (usernameAdminExisted) {
                s.update(admin);
                return true;
            } else {
                s.save(admin);
                return true;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
