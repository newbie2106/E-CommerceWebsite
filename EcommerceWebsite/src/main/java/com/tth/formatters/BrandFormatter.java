/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Brand;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class BrandFormatter implements Formatter<Brand>{
    @Override
    public String print(Brand brand, Locale locale) {
        return String.valueOf(brand.getId());
    }

    @Override
    public Brand parse(String id, Locale locale) throws ParseException {
        Brand b = new Brand();
        b.setId(Integer.parseInt(id));
        return b;
    }
}
