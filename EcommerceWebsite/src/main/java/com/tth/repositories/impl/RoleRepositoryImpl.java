/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Product;
import com.tth.pojo.Role;
import com.tth.repositories.RoleRepository;
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
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getRole() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Role.findAll");

        return q.getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Role.class, id);
    }

}
