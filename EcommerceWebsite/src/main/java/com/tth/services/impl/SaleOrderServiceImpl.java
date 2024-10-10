/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.DTO.OrderDetailDTO;
import com.tth.DTO.SaleOrderDTO;
import com.tth.ENUM.ShipmentStatus;
import com.tth.pojo.Branch;
import com.tth.pojo.Carrier;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.Product;
import com.tth.pojo.SaleOrder;
import com.tth.pojo.Shipment;
import com.tth.pojo.ShippingAddress;
import com.tth.pojo.User;
import com.tth.repositories.OrderDetailsRepository;
import com.tth.repositories.SaleOrderRepository;
import com.tth.services.BranchService;
import com.tth.services.CarrierService;
import com.tth.services.ProductService;
import com.tth.services.SaleOrderService;
import com.tth.services.ShipmentService;
import com.tth.services.ShippingAddressService;
import com.tth.services.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    private SaleOrderRepository saleOrderRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService prodService;
    @Autowired
    private OrderDetailsRepository orderDetailRepo;
    @Autowired
    private BranchService branchService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private CarrierService carrierService;
    @Autowired
    private ShipmentService shipmentService;

    @Override
    public boolean AddSaleOrder(SaleOrderDTO saleOrder) {
        User u = this.userService.getUserByUsername(saleOrder.getUsername());
        try {
            Branch b = this.branchService.getBrandById(saleOrder.getBranchId());
            ShippingAddress shippingAddress = this.shippingAddressService.getShippingAddressByUsernameAndId(saleOrder.getUsername(),
                    Long.parseLong(saleOrder.getShippingAdressId().toString()));
            Carrier c = this.carrierService.getCarrierById(saleOrder.getCarrierId());

            SaleOrder newSaleOrder = new SaleOrder();
            newSaleOrder.setCreatedDate(new Date());
            newSaleOrder.setTotalAmount(saleOrder.getTotalAmount());
            newSaleOrder.setPaid(saleOrder.isIsPaid());
            newSaleOrder.setUser(u);
            newSaleOrder.setBranchId(b);
            newSaleOrder.setShippingAddress(shippingAddress);
            newSaleOrder.setNote(saleOrder.getNote());
            newSaleOrder.setCarrierId(c);

            if (this.saleOrderRepository.AddSaleOrder(newSaleOrder)) {
                Shipment shipment = new Shipment();
                shipment.setShipmentDate(new Date());
                shipment.setExpectedDelivery(new Date());
                shipment.setStatus(ShipmentStatus.Pending);
                shipment.setSaleOrderId(newSaleOrder);
                this.shipmentService.addShipment(shipment);
            }

            for (OrderDetailDTO detailDTO : saleOrder.getOrderDetails()) {
                Product p = this.prodService.getProductById(detailDTO.getProductId());
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setUnitPrice(p.getPrice());
                orderDetail.setProduct(p);
                orderDetail.setQuantity(detailDTO.getQuantity());
                orderDetail.setSaleOrder(newSaleOrder);
                this.orderDetailRepo.addOrderDetail(orderDetail);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
