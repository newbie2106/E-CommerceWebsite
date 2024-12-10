/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.Branch;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.SaleOrder;
import com.tth.pojo.Product;
import com.tth.pojo.Shipment;
import com.tth.pojo.User;
import com.tth.repositories.StatsRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rD = q.from(OrderDetail.class);
        Root rO = q.from(SaleOrder.class);
        Root rP = q.from(Product.class);
        Root rS = q.from(Shipment.class);

        q.multiselect(b.function(period, Integer.class, rO.get("createdDate")),
                b.sum(rO.get("totalAmount")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rD.get("saleOrderId"), rO.get("id")));
        predicates.add(b.equal(rD.get("productId"), rP.get("id")));
        predicates.add(b.equal(b.function("YEAR", Integer.class, rO.get("createdDate")), year));
        predicates.add(b.equal(rS.get("status"), ShipmentStatus.Delivered));
        predicates.add(b.equal(rS.get("saleOrder"), rO.get("id")));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function(period, Integer.class, rO.get("createdDate")));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByProduct() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rP = q.from(Product.class);
        Root rD = q.from(OrderDetail.class);
        Root rS = q.from(Shipment.class);
        Root rO = q.from(SaleOrder.class);

        q.multiselect(rP.get("id"), rP.get("name"), b.sum(b.prod(rD.get("quantity"), rD.get("unitPrice"))));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rD.get("productId"), rP.get("id")));
        predicates.add(b.equal(rS.get("status"), ShipmentStatus.Delivered));
        predicates.add(b.equal(rS.get("saleOrder"), rO.get("id")));
        predicates.add(b.equal(rO.get("id"), rD.get("saleOrderId")));
        q.where(predicates.toArray(Predicate[]::new));

        q.groupBy(rP.get("id"));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByProductBranch(String usernameBranch) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rP = q.from(Product.class);
        Root rD = q.from(OrderDetail.class);
        Root rS = q.from(Shipment.class);
        Root rO = q.from(SaleOrder.class);
        Root rB = q.from(Branch.class);
        q.multiselect(
                rP.get("id"), rP.get("name"),
                b.sum(b.prod(rD.get("quantity"), rD.get("unitPrice")))
        );
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rS.get("status"), ShipmentStatus.Delivered));
        predicates.add(b.equal(rB.get("adminUser").get("username"), usernameBranch));
        //predicates.add(b.equal(rS.get("saleOrder"), rD.get("id")));     
        predicates.add(b.equal(rO.get("id"), rD.get("saleOrderId")));
        predicates.add(b.equal(rP.get("id"), rD.get("productId")));

        q.where(predicates.toArray(Predicate[]::new));

        q.groupBy(rP.get("id"));

        Query<Object[]> query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsRevenueByPeriodBranch(int year, String period, String usernameBranch) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<SaleOrder> rO = q.from(SaleOrder.class);
        Root rS = q.from(Shipment.class);
        Root rB = q.from(Branch.class);

        q.multiselect(
                b.function(period, Integer.class, rO.get("createdDate")),
                b.sum(rO.get("totalAmount"))
        );

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("YEAR", Integer.class, rO.get("createdDate")), year));
        predicates.add(b.equal(rB.get("adminUser").get("username"), usernameBranch));
        predicates.add(b.equal(rS.get("status"), ShipmentStatus.Delivered));
        predicates.add(b.equal(rS.get("saleOrder"), rO.get("id")));

        q.where(predicates.toArray(Predicate[]::new));

        q.groupBy(b.function(period, Integer.class, rO.get("createdDate")));

        Query<Object[]> query = s.createQuery(q);

        return query.getResultList();
    }

}
