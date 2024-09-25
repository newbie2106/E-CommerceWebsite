/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.RecentlyViewed;
import com.tth.repositories.RecentlyViewedRepository;
import java.time.LocalDateTime;
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
@Repository
@Transactional
public class RecentlyViewedRepositoryImpl implements RecentlyViewedRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<RecentlyViewed> findRecentViewsByUsername(String username, Date timeLimit) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM RecentlyViewed rv WHERE "
                + "rv.username.username = :username AND rv.viewedAt >= :timeLimit ORDER BY rv.viewedAt DESC");
        query.setParameter("username", username);
        query.setParameter("timeLimit", timeLimit);

        return query.getResultList();
    }

    @Override
    public RecentlyViewed findByUsernameAndProductId(String username, Integer productId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM RecentlyViewed rv WHERE rv.username.username = :username "
                + "AND rv.productId.id = :productId");
        query.setParameter("username", username);
        query.setParameter("productId", productId);

        try {
            return (RecentlyViewed) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void deleteAllByViewedAtBefore(Date timeLimit) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("DELETE FROM RecentlyViewed rv WHERE rv.viewedAt < :timeLimit");
        query.setParameter("timeLimit", timeLimit);
        query.executeUpdate();
    }

    @Override
    public void addRecentlyViewed(RecentlyViewed recentlyViewed) {
        Session s = this.factory.getObject().getCurrentSession();
        if (recentlyViewed.getId() == null) {
            s.save(recentlyViewed);
        } else {
            s.update(recentlyViewed);
        }
    }
}
