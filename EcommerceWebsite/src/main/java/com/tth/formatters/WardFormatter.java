/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Wards;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class WardFormatter implements Formatter<Wards>{

    @Override
    public String print(Wards ward, Locale locale) {
        return String.valueOf(ward.getCode());
    }

    @Override
    public Wards parse(String code, Locale locale) throws ParseException {
        Wards w = new Wards();
        w.setCode(code);
        return w;
    }
    
}
