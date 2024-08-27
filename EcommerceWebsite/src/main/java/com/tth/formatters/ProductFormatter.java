/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Product;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class ProductFormatter implements Formatter<Product>{
     @Override
    public String print(Product product, Locale locale) {
        return String.valueOf(product.getId());
    }

    @Override
    public Product parse(String id, Locale locale) throws ParseException {
        Product b = new Product();
        b.setId(Integer.parseInt(id));
        return b;
    }
}
