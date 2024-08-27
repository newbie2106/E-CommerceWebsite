/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Role;
import com.tth.pojo.User;
import com.tth.repositories.RoleRepository;
import com.tth.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tongh
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException Ex) {
            return new User();
        }
    }

    public boolean usernameExists(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> q = s.createQuery("SELECT COUNT(*) FROM User WHERE username = :username", Long.class);
        q.setParameter("username", username);
        Long count = q.getSingleResult();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);
        return this.passEncoder.matches(password, u.getPassword());

    }

//    @Override
//    public User getUserById(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        return s.get(User.class, id);
//    }
    @Override
    public long countUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> p = s.createQuery("SELECT COUNT(u.username) FROM User u", Long.class);
        return p.uniqueResult();
    }

    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findAll");

        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        User existedUser = this.getUserByUsername(user.getUsername());
        System.out.println("HELLO:" + existedUser); 
        try {
            Role role = this.roleRepo.getRoleById(1);
            user.setRole(role);
            if (existedUser== null) {
                //User updateUser = getUserByUsername(user.getUsername());

                if (user.getAvatar() == null) {
                    user.setAvatar("https://res.cloudinary.com/dsbkju7j9/image/upload/v1719163511/bshktjhrrdzspkm7u301.png");
                }
                user.setCreatedDate(new Date());
                s.save(user);
                return true;
            } else {
                if (user.getAvatar() == null) {
                    user.setAvatar(existedUser.getAvatar());
                }
                user.setCreatedDate(existedUser.getCreatedDate());
                s.merge(user);
                return true;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    

    @Override
    public void deleteUser(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserByUsername(username);
        s.delete(u);
    }

    @Override
    public void changePassword(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
    }

}
