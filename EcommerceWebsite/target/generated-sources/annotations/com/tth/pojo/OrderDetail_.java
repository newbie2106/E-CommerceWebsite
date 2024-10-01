package com.tth.pojo;

import com.tth.pojo.Product;
import com.tth.pojo.SaleOrder;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-28T20:14:39")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, BigDecimal> unitPrice;
    public static volatile SingularAttribute<OrderDetail, Integer> quantity;
    public static volatile SingularAttribute<OrderDetail, Product> productId;
    public static volatile SingularAttribute<OrderDetail, SaleOrder> saleOrderId;
    public static volatile SingularAttribute<OrderDetail, Integer> id;

}