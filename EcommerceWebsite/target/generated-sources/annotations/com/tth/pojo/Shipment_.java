package com.tth.pojo;

import com.tth.pojo.Carrier;
import com.tth.pojo.SaleOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-09T06:11:41")
@StaticMetamodel(Shipment.class)
public class Shipment_ { 

    public static volatile SingularAttribute<Shipment, Date> expectedDelivery;
    public static volatile SetAttribute<Shipment, SaleOrder> saleOrderSet;
    public static volatile SingularAttribute<Shipment, Integer> id;
    public static volatile SingularAttribute<Shipment, Date> shipmentDate;
    public static volatile SingularAttribute<Shipment, Carrier> carrierId;
    public static volatile SingularAttribute<Shipment, String> status;

}