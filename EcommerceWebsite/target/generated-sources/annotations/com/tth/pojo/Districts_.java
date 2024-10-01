package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.AdministrativeUnits;
import com.tth.pojo.Branch;
import com.tth.pojo.Customer;
import com.tth.pojo.Provinces;
import com.tth.pojo.ShippingAddress;
import com.tth.pojo.Wards;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-28T20:14:39")
@StaticMetamodel(Districts.class)
public class Districts_ { 

    public static volatile SetAttribute<Districts, ShippingAddress> shippingAddressSet;
    public static volatile SingularAttribute<Districts, AdministrativeUnits> administrativeUnitId;
    public static volatile SingularAttribute<Districts, String> code;
    public static volatile SetAttribute<Districts, Customer> customerSet;
    public static volatile SingularAttribute<Districts, Provinces> provinceCode;
    public static volatile SingularAttribute<Districts, String> fullName;
    public static volatile SetAttribute<Districts, Wards> wardsSet;
    public static volatile SetAttribute<Districts, Branch> branchSet;
    public static volatile SetAttribute<Districts, Admin> adminSet;

}