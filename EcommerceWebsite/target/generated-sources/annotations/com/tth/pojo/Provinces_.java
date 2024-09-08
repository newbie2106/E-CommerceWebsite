package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.AdministrativeRegions;
import com.tth.pojo.AdministrativeUnits;
import com.tth.pojo.Branch;
import com.tth.pojo.Customer;
import com.tth.pojo.Districts;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-09T06:11:41")
@StaticMetamodel(Provinces.class)
public class Provinces_ { 

    public static volatile SingularAttribute<Provinces, AdministrativeUnits> administrativeUnitId;
    public static volatile SetAttribute<Provinces, Districts> districtsSet;
    public static volatile SingularAttribute<Provinces, String> code;
    public static volatile SetAttribute<Provinces, Customer> customerSet;
    public static volatile SingularAttribute<Provinces, AdministrativeRegions> administrativeRegionId;
    public static volatile SingularAttribute<Provinces, String> fullName;
    public static volatile SetAttribute<Provinces, Branch> branchSet;
    public static volatile SetAttribute<Provinces, Admin> adminSet;

}