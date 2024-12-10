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
import com.tth.services.InventoryService;
import com.tth.services.ProductService;
import com.tth.services.SaleOrderService;
import com.tth.services.ShipmentService;
import com.tth.services.ShippingAddressService;
import com.tth.services.UserService;
import java.util.Date;
import java.util.List;
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

    @Autowired
    private InventoryService inventoryService;

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
                shipment.setSaleOrder(newSaleOrder);
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

                boolean isUpdated = this.inventoryService.updateInventoryQuantity(
                        detailDTO.getProductId(),
                        saleOrder.getBranchId(),
                        detailDTO.getQuantity()
                );

                if (!isUpdated) {
                    throw new Exception("Sản phẩm không đủ số lượng trong kho" + detailDTO.getProductId());
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelSaleOrder(int orderId) {
        SaleOrder saleOrder = this.saleOrderRepository.getSaleOrderById(orderId);
        if (saleOrder != null) {
            // Hoàn lại số lượng sản phẩm vào kho          
            Branch branch = this.branchService.getBrandById(saleOrder.getBranchId().getId());

            for (OrderDetail detail : saleOrder.getOrderDetailSet()) {
                Product p = this.prodService.getProductById(detail.getProduct().getId());
                int quantityPurchased = -detail.getQuantity();

                boolean isUpdated = this.inventoryService.updateInventoryQuantity(p.getId(), branch.getId(), quantityPurchased);
                if (!isUpdated) {
                    return false; // Không thể cập nhật kho cho sản phẩm này
                }
            }

            saleOrder.getShipment().setStatus(ShipmentStatus.Cancelled);
            this.saleOrderRepository.UpdateStatusSaleOrder(saleOrder);
        }
        return false; // Không tìm thấy đơn hàng
    }

    @Override
    public List<SaleOrderDTO> getSaleOrderByBranchAdmin(String branchAdmin) {
        return this.saleOrderRepository.getSaleOrderByBranchAdmin(branchAdmin);
    }

    @Override
    public SaleOrderDTO convertToSaleOrderDTO(SaleOrder saleOrder) {
        return this.saleOrderRepository.convertToSaleOrderDTO(saleOrder);
    }

    @Override
    public List<SaleOrderDTO> getSaleOrderByUsername(String username) {
        return this.saleOrderRepository.getSaleOrderByUsername(username);
    }

    @Override
    public void UpdateStatusSaleOrder(SaleOrder saleOrder) {
        this.saleOrderRepository.UpdateStatusSaleOrder(saleOrder);
    }

    @Override
    public SaleOrder getSaleOrderById(int id) {
        return this.saleOrderRepository.getSaleOrderById(id);
    }

}
