/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import com.tth.repositories.InventoryRepository;
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
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    public Inventory getInventoryByProductAndBranch(Product product, Branch branch) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<Inventory> query = session.createQuery("FROM Inventory WHERE productId = :productId AND branchId = :branchId", Inventory.class);
        query.setParameter("productId", product);
        query.setParameter("branchId", branch);

        return query.uniqueResult();
    }

    public boolean updateProductQuantity(Inventory inventory) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            session.saveOrUpdate(inventory);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
}
