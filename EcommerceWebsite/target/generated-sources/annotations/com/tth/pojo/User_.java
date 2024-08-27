package com.tth.pojo;

import com.tth.pojo.Admin;
import com.tth.pojo.Customer;
import com.tth.pojo.ForgotPassword;
import com.tth.pojo.Role;
import com.tth.pojo.SaleOrder;
import com.tth.pojo.Wishlist;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-27T21:57:07")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> lastName;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Wishlist> wishlistSet;
    public static volatile SingularAttribute<User, Date> createdDate;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile SetAttribute<User, SaleOrder> saleOrderSet;
    public static volatile SetAttribute<User, ForgotPassword> forgotPasswordSet;
    public static volatile SingularAttribute<User, Admin> admin;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, Customer> customer;

}