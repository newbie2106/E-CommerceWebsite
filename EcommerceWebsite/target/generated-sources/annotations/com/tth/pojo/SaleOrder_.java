package com.tth.pojo;

import com.tth.pojo.OrderDetail;
import com.tth.pojo.Shipment;
import com.tth.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-27T21:57:07")
@StaticMetamodel(SaleOrder.class)
public class SaleOrder_ { 

    public static volatile SingularAttribute<SaleOrder, BigDecimal> totalAmount;
    public static volatile SingularAttribute<SaleOrder, Date> createdDate;
    public static volatile SingularAttribute<SaleOrder, Shipment> shipmentId;
    public static volatile SingularAttribute<SaleOrder, Boolean> paid;
    public static volatile SingularAttribute<SaleOrder, Integer> id;
    public static volatile SetAttribute<SaleOrder, OrderDetail> orderDetailSet;
    public static volatile SingularAttribute<SaleOrder, String> status;
    public static volatile SingularAttribute<SaleOrder, User> username;

}