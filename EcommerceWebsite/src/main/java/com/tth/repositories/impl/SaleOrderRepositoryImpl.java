/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.Product;
import com.tth.pojo.SaleOrder;
import com.tth.repositories.SaleOrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
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
public class SaleOrderRepositoryImpl implements SaleOrderRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Object[]> getSaleOrder(Map<String, String> params, String branch, ShipmentStatus status) {
//          Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder b = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//
//        Root<Product> rP = q.from(Product.class);
//        Root<SaleOrder> rSO= q.from(SaleOrder.class);
//        Root<OrderDetail> rOD = q.from(OrderDetail.class);
//
//        q.multiselect(rP.get("name"));
//        
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rP.get("id"), rInventory.get("productId")));
//        predicates.add(b.equal(rBranch.get("adminUser").get("username"), branchAdmin));
//        String kw = params.get("kw");
//        if (kw != null && !kw.isEmpty()) {
//            predicates.add(b.like(rProduct.get("name"), String.format("%%%s%%", kw)));
//        }
//
//        String fromPrice = params.get("fromPrice");
//        if (fromPrice != null && !fromPrice.isEmpty()) {
//            predicates.add(b.greaterThanOrEqualTo(rProduct.get("price"), Double.parseDouble(fromPrice)));
//        }
//
//        String toPrice = params.get("toPrice");
//        if (toPrice != null && !toPrice.isEmpty()) {
//            predicates.add(b.lessThanOrEqualTo(rProduct.get("price"), Double.parseDouble(toPrice)));
//        }
//
//        String cateId = params.get("cateId");
//        if (cateId != null && !cateId.isEmpty()) {
//            predicates.add(b.equal(rProduct.get("categoryId"), Integer.parseInt(cateId)));
//        }
//
//        String brandId = params.get("brandId");
//        if (brandId != null && !brandId.isEmpty()) {
//            predicates.add(b.equal(rProduct.get("brandId"), Integer.parseInt(brandId)));
//        }
//
//        q.where(predicates.toArray(Predicate[]::new));
//
//        q.groupBy(rProduct.get("id"), rInventory.get("availableQuantity"));
//
//        q.orderBy(b.asc(rProduct.get("id")));
//
//        Query query = s.createQuery(q);

//        String p = params.get("page");
//        if (p != null && !p.isEmpty()) {
//            int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
//            int start = (Integer.parseInt(p) - 1) * pageSize;
//            query.setFirstResult(start);
//            query.setMaxResults(pageSize);
//        }
//
//        List<Object[]> productsWithInventory = query.getResultList();
//
//        for (Object[] result : productsWithInventory) {
//            Product product = (Product) result[0];
//            Hibernate.initialize(product.getImageSet());
//        }

        return null;
    }
    
}
