package com.tth.pojo;

import com.tth.pojo.Brand;
import com.tth.pojo.Cart;
import com.tth.pojo.Category;
import com.tth.pojo.Image;
import com.tth.pojo.Inventory;
import com.tth.pojo.OrderDetail;
import com.tth.pojo.RecentlyViewed;
import com.tth.pojo.TagProduct;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-09-26T18:27:48")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SetAttribute<Product, Image> imageSet;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SetAttribute<Product, Cart> cartSet;
    public static volatile SetAttribute<Product, TagProduct> tagProductSet;
    public static volatile SetAttribute<Product, OrderDetail> orderDetailSet;
    public static volatile SetAttribute<Product, RecentlyViewed> recentlyViewedSet;
    public static volatile SingularAttribute<Product, Date> createdDate;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, Brand> brandId;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Category> categoryId;
    public static volatile SetAttribute<Product, Inventory> inventorySet;

}