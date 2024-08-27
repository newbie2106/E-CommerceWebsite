/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Image;
import com.tth.pojo.Product;
import com.tth.repositories.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Image addImage(Image image) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(image);
            return image;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Image getImageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Image.class, id);
    }

    @Override
    public List<Image> getProductImage(int productId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Image> q = b.createQuery(Image.class);

        Root r = q.from(Image.class);
        //Root rP = q.from(Product.class);

        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(r.get("productId"), productId));
        //predicates.add(b.equal(r.get("productId"), rP.get("id")));
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);

        return query.getResultList();

    }   


    @Override
    public void deleteImageById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Image i = this.getImageById(id);
        s.delete(i);
    }

}
