package com.tth.pojo;

import com.tth.pojo.Branch;
import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.User;
import com.tth.pojo.Wards;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-09T06:11:41")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, String> personalId;
    public static volatile SingularAttribute<Admin, String> address;
    public static volatile SingularAttribute<Admin, Districts> districtId;
    public static volatile SingularAttribute<Admin, String> phone;
    public static volatile SingularAttribute<Admin, Wards> wardId;
    public static volatile SetAttribute<Admin, Branch> branchSet;
    public static volatile SingularAttribute<Admin, Provinces> provinceId;
    public static volatile SingularAttribute<Admin, User> user;
    public static volatile SingularAttribute<Admin, String> email;
    public static volatile SingularAttribute<Admin, String> username;

}