package com.tth.pojo;

import com.tth.pojo.Product;
import com.tth.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-27T21:57:07")
@StaticMetamodel(Wishlist.class)
public class Wishlist_ { 

    public static volatile SingularAttribute<Wishlist, Product> productId;
    public static volatile SingularAttribute<Wishlist, Integer> id;
    public static volatile SingularAttribute<Wishlist, User> username;

}