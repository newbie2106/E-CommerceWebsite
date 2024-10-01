package com.tth.pojo;

import com.tth.pojo.Shipment;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-28T20:14:39")
@StaticMetamodel(Carrier.class)
public class Carrier_ { 

    public static volatile SetAttribute<Carrier, Shipment> shipmentSet;
    public static volatile SingularAttribute<Carrier, String> name;
    public static volatile SingularAttribute<Carrier, String> description;
    public static volatile SingularAttribute<Carrier, Integer> id;

}