/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.DTO.CommentDTO;
import com.tth.pojo.Comment;
import com.tth.repositories.CommentRepository;
import java.util.List;
import java.util.stream.Collectors;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addComment(Comment comment) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            session.save(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CommentDTO> getCommentByProductId(int productId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root r = q.from(Comment.class);
        q.select(r);

        Predicate predicate = b.equal(r.get("productId").get("id"), productId);
        q.where(predicate);

        Query query = s.createQuery(q);

        List<Comment> saleOrderList = query.getResultList();

        List<CommentDTO> saleOrderDTO = saleOrderList.stream()
                .map(this::convertToCommentDTO)
                .collect(Collectors.toList());

        return saleOrderDTO;
    }

    @Override
    public CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        String fullName = comment.getUsername().getFirstName() + " " + comment.getUsername().getLastName();
        dto.setContent(comment.getContent());        
        dto.setCreatedDate(comment.getCreatedDate());
        dto.setFullName(fullName);
        dto.setAvatar(comment.getUsername().getAvatar());

        return dto;
    }
}
