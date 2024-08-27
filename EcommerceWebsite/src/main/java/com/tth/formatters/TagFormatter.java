package com.tth.formatters;

import com.tth.pojo.Tag;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tongh
 */
public class TagFormatter implements Formatter<Tag>{
    @Override
    public String print(Tag tag, Locale locale) {
        return String.valueOf(tag.getId());
    }

    @Override
    public Tag parse(String id, Locale locale) throws ParseException {
        Tag b = new Tag();
        b.setId(Integer.parseInt(id));
        return b;
    }
}
