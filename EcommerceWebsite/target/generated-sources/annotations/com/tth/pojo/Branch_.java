package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.Districts;
import com.tth.pojo.Inventory;
import com.tth.pojo.Provinces;
import com.tth.pojo.SaleOrder;
import com.tth.pojo.Wards;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-24T07:13:34")
@StaticMetamodel(Branch.class)
public class Branch_ { 

    public static volatile SingularAttribute<Branch, String> address;
    public static volatile SingularAttribute<Branch, Districts> districtId;
    public static volatile SingularAttribute<Branch, Admin> adminUser;
    public static volatile SetAttribute<Branch, SaleOrder> saleOrderSet;
    public static volatile SingularAttribute<Branch, Integer> id;
    public static volatile SingularAttribute<Branch, Wards> wardId;
    public static volatile SingularAttribute<Branch, Provinces> provinceId;
    public static volatile SetAttribute<Branch, Inventory> inventorySet;

}