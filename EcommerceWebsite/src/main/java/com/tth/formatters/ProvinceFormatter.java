/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Provinces;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class ProvinceFormatter implements Formatter<Provinces>{

    @Override
    public String print(Provinces province, Locale locale) {
        return String.valueOf(province.getCode());
    }

    @Override
    public Provinces parse(String code, Locale locale) throws ParseException {
        Provinces p = new Provinces();
        p.setCode(code);
        return p;
    }
    
}
