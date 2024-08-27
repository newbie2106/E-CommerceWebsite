/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Cart;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.Product;
import com.tth.pojo.SaleOrder;
import com.tth.repositories.ProductRepository;
import com.tth.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tongh
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Environment env;

    public List<Product> getProducts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = b.createQuery(Product.class);
        Root r = q.from(Product.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }

        String fromPrice = params.get("fromPrice");
        if (fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(b.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }

        String toPrice = params.get("toPrice");
        if (toPrice != null && !toPrice.isEmpty()) {
            predicates.add(b.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }

        String cateId = params.get("cateId");
        if (cateId != null && !cateId.isEmpty()) {
            predicates.add(b.equal(r.get("categoryId"), Integer.parseInt(cateId)));
        }

        String brandId = params.get("brandId");
        if (brandId != null && !brandId.isEmpty()) {
            predicates.add(b.equal(r.get("brandId"), Integer.parseInt(brandId)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.asc(r.get("id")));

        Query query = s.createQuery(q);

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        List<Product> products = query.getResultList();
        for (Product product : products) {
            Hibernate.initialize(product.getImageSet());
        }
        return products;
    }

    @Override
    public long countProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Long> p = s.createQuery("SELECT COUNT(p.id) FROM Product p", Long.class);
        return p.uniqueResult();
    }
//

    @Override
    public boolean addOrUpdate(Product p) {
        Session s = this.factory.getObject().getCurrentSession();
        p.setCreatedDate(new Date());
        if (p.getId()!= null && p.getId() > 0) {
            s.update(p);

        } else {
            s.save(p);

        }
        return true;
    }

    @Override
    public Product getProductById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Product.class, id);
    }

    @Override
    public void deleteProduct(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Product p = this.getProductById(id);
        s.delete(p);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addReceipt(Map<String, Cart> cart) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            SaleOrder r = new SaleOrder();
            r.setUser(userRepository.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
            s.save(r);

            for (Cart c : cart.values()) {
                OrderDetail d = new OrderDetail();
                d.setQuantity(c.getQuantity());
                d.setSaleOrder(r);
                d.setProduct(this.getProductById(c.getId()));
                s.save(d);
            }
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }

}
