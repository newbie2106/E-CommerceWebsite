package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.AdministrativeUnits;
import com.tth.pojo.Branch;
import com.tth.pojo.Customer;
import com.tth.pojo.Districts;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-27T21:57:07")
@StaticMetamodel(Wards.class)
public class Wards_ { 

    public static volatile SingularAttribute<Wards, AdministrativeUnits> administrativeUnitId;
    public static volatile SingularAttribute<Wards, Districts> districtCode;
    public static volatile SingularAttribute<Wards, String> code;
    public static volatile SetAttribute<Wards, Customer> customerSet;
    public static volatile SingularAttribute<Wards, String> fullName;
    public static volatile SetAttribute<Wards, Branch> branchSet;
    public static volatile SetAttribute<Wards, Admin> adminSet;

}