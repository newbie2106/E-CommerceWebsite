/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories.impl;

import com.tth.DTO.OrderDetailDTO;
import com.tth.DTO.SaleOrderDTO;
import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.Branch;
import com.tth.pojo.Image;
import com.tth.pojo.Inventory;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.Product;
import com.tth.pojo.SaleOrder;
import com.tth.repositories.SaleOrderRepository;
import com.tth.services.ImageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Hibernate;
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
public class SaleOrderRepositoryImpl implements SaleOrderRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private ImageService imageService;

    @Override
    public SaleOrder getSaleOrderById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(SaleOrder.class, id);
    }

    @Override
    public boolean AddSaleOrder(SaleOrder saleOrder) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.save(saleOrder);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SaleOrderDTO> getSaleOrderByBranchAdmin(String branchAdmin) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<SaleOrder> q = b.createQuery(SaleOrder.class);
        Root r = q.from(SaleOrder.class);
        q.select(r);

        Predicate predicate = b.equal(r.get("branchId").get("adminUser").get("username"),
                branchAdmin);
        q.where(predicate);

        Query query = s.createQuery(q);

        List<SaleOrder> saleOrderList = query.getResultList();

        List<SaleOrderDTO> saleOrderDTO = saleOrderList.stream()
                .map(this::convertToSaleOrderDTO)
                .collect(Collectors.toList());

        return saleOrderDTO;
    }

    @Override
    public List<SaleOrderDTO> getSaleOrderByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<SaleOrder> q = b.createQuery(SaleOrder.class);
        Root r = q.from(SaleOrder.class);
        q.select(r);

        // trong saleorder goi user(dat la username) va user goi username
        Predicate predicate = b.equal(r.get("username").get("username"), username);
        q.where(predicate);

        Query query = s.createQuery(q);

        List<SaleOrder> saleOrderList = query.getResultList();

        List<SaleOrderDTO> saleOrderDTO = saleOrderList.stream()
                .map(this::convertToSaleOrderDTO)
                .collect(Collectors.toList());

        return saleOrderDTO;
    }

    @Override
    public SaleOrderDTO convertToSaleOrderDTO(SaleOrder saleOrder) {
        SaleOrderDTO dto = new SaleOrderDTO();
        String shippingAddressId = (saleOrder.getShippingAddress().getId()).toString();

        dto.setId(saleOrder.getId());
        dto.setUsername(saleOrder.getUsername().getUsername());
        dto.setCreatedDate(saleOrder.getCreatedDate());
        dto.setBranchId(saleOrder.getBranchId().getId());
        dto.setNote(saleOrder.getNote());
        dto.setTotalAmount(saleOrder.getTotalAmount());
        dto.setCarrierId(saleOrder.getCarrierId().getId());
        dto.setShippingAdressId(Integer.parseInt(shippingAddressId));
        dto.setIsPaid(saleOrder.getPaid());
        dto.setCurrentStatus(saleOrder.getShipment().getStatus());
        List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : saleOrder.getOrderDetailSet()) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

            List<Image> allImages = this.imageService.getProductImage(orderDetail.getProduct().getId());
            String firstImage = allImages.isEmpty() ? null : allImages.get(0).getUrl();

            orderDetailDTO.setProductImage(firstImage);
            orderDetailDTO.setProductId(orderDetail.getProduct().getId());
            orderDetailDTO.setProductName(orderDetail.getProduct().getName());
            orderDetailDTO.setProductId(orderDetail.getProduct().getId());
            orderDetailDTO.setQuantity(orderDetail.getQuantity());
            orderDetailDTO.setUnitPrice(orderDetail.getUnitPrice());

            orderDetailDTOList.add(orderDetailDTO);
        }

        dto.setOrderDetails(orderDetailDTOList);

        return dto;
    }

    @Override
    public void UpdateStatusSaleOrder(SaleOrder saleOrder) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(saleOrder);
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

}
