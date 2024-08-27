/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.formatters;

import com.tth.pojo.Role;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author tongh
 */
public class RoleFormatter implements Formatter<Role> {

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String id, Locale locale) throws ParseException {
        Role r = new Role();
        r.setId(Integer.parseInt(id));
        return r;
    }
}
