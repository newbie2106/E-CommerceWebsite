/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.GoogleAccount;
import com.tth.repositories.GoogleAccountRepository;
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
public class GoogleAccountRepositoryImpl implements GoogleAccountRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private GoogleAccountRepository googleAccountRepo;

    @Override
    public GoogleAccount getGoogleAccountByGoogleId(String googleId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM GoogleAccount WHERE googleId = :googleId");
        q.setParameter("googleId", googleId);
        try {
            return (GoogleAccount) q.getSingleResult();
        } catch (NoResultException Ex) {
            return null;  // Trả về null nếu không tìm thấy tài khoản
        }
    }

    @Override
    public void addOrUpdateGoogleAccount(GoogleAccount googleAccount) {
        Session s = this.factory.getObject().getCurrentSession();

        if (googleAccount.getId() != null) {
            // Cập nhật tài khoản nếu đã tồn tại
            s.update(googleAccount);
        } else {
            // Tạo mới tài khoản nếu chưa tồn tại
            s.save(googleAccount);
        }
    }

}
