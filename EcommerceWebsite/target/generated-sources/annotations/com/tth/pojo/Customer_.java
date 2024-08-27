package com.tth.pojo;

import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.User;
import com.tth.pojo.Wards;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-27T21:57:08")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, Districts> districtId;
    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, Wards> wardId;
    public static volatile SingularAttribute<Customer, Provinces> provinceId;
    public static volatile SingularAttribute<Customer, User> user;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile SingularAttribute<Customer, String> username;

}