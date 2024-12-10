/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Branch;
import com.tth.pojo.Inventory;
import com.tth.pojo.Product;
import com.tth.repositories.InventoryRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public boolean updateInventoryQuantity(int productId, int branchId, int quantityPurchased) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Tạo truy vấn lấy Inventory của sản phẩm và chi nhánh cụ thể
        CriteriaQuery<Inventory> query = builder.createQuery(Inventory.class);
        Root<Inventory> root = query.from(Inventory.class);

        Predicate productPredicate = builder.equal(root.get("productId").get("id"), productId);
        Predicate branchPredicate = builder.equal(root.get("branchId").get("id"), branchId);
        query.where(builder.and(productPredicate, branchPredicate));

        Query<Inventory> q = session.createQuery(query);
        Inventory inventory = q.uniqueResult();

        if (inventory != null) {
            // Kiểm tra nếu số lượng tồn kho đủ để trừ đi
            int newQuantity = inventory.getAvailableQuantity() - quantityPurchased;
            if (newQuantity >= 0) {
                // Cập nhật số lượng tồn kho mới
                inventory.setAvailableQuantity(newQuantity);
                session.update(inventory);
                return true;
            } else {
                // Số lượng trong kho không đủ
                return false;
            }
        } else {
            // Không tìm thấy Inventory
            return false;
        }
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

    @Override
    public List<Inventory> getInventoryByBranch(String branch) {
        Session s = this.factory.getObject().getCurrentSession();

        Query<Inventory> query = s.createQuery("FROM Inventory i WHERE i.branchId.id = :id", Inventory.class);
        query.setParameter("id", branch);

        return query.getResultList();
    }

}
