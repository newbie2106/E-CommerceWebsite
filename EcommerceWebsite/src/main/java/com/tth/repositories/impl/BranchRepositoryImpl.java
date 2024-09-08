/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.pojo.Branch;
import com.tth.repositories.BranchRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class BranchRepositoryImpl implements BranchRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Branch getBrandById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Branch.class, id);
    }

    @Override
    public List<Branch> getBrands() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Branch> q = b.createQuery(Branch.class);

        Root<Branch> r = q.from(Branch.class);

        q.select(r);
        
        Query query = s.createQuery(q);
        
        List<Branch> branches = query.getResultList();
        for (Branch branch : branches) {
            Hibernate.initialize(branch.getAdminUser());
        }

        return branches;
    }
    
     public String getUsernameByBranchId(int branchId) {
        Branch branch = this.getBrandById(branchId);
        if (branch != null && branch.getAdminUser() != null) {
            return branch.getAdminUser().getUsername();
        }
        return null;
    }

}
