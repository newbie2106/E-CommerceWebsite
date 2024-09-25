package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.Cart;
import com.tth.pojo.Customer;
import com.tth.pojo.ForgotPassword;
import com.tth.pojo.RecentlyViewed;
import com.tth.pojo.Role;
import com.tth.pojo.SaleOrder;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-24T07:13:34")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SetAttribute<User, SaleOrder> saleOrderSet;
    public static volatile SetAttribute<User, ForgotPassword> forgotPasswordSet;
    public static volatile SetAttribute<User, Cart> cartSet;
    public static volatile SingularAttribute<User, Admin> admin;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, RecentlyViewed> recentlyViewedSet;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> createdDate;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, Customer> customer;

}