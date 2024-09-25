/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Branch;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class BranchFormatter implements Formatter<Branch>{
    @Override
    public String print(Branch branch, Locale locale) {
        return String.valueOf(branch.getId());
    }

    @Override
    public Branch parse(String id, Locale locale) throws ParseException {
        Branch b = new Branch();
        b.setId(Integer.parseInt(id));
        return b;
    }
}
