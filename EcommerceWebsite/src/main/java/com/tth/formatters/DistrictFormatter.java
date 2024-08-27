/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Districts;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class DistrictFormatter implements Formatter<Districts>{

    @Override
    public String print(Districts district, Locale locale) {
        return String.valueOf(district.getCode());
    }

    @Override
    public Districts parse(String code, Locale locale) throws ParseException {
        Districts d = new Districts();
        d.setCode(code);
        return d;
    }
    
}
