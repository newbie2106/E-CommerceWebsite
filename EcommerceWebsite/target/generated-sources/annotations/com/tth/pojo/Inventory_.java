package com.tth.pojo;

import com.tth.pojo.Branch;
import com.tth.pojo.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-08-28T22:05:15")
@StaticMetamodel(Inventory.class)
public class Inventory_ { 

    public static volatile SingularAttribute<Inventory, Integer> availableQuantity;
    public static volatile SingularAttribute<Inventory, Branch> branchId;
    public static volatile SingularAttribute<Inventory, Product> productId;
    public static volatile SingularAttribute<Inventory, Integer> id;

}