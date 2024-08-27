/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Admin;
import com.tth.pojo.User;
import java.util.Map;

/**
 *
 * @author tongh
 */
public interface AdminService {
//    public boolean addOrUpdateUserAdmin(Map<String, String> params,User user);    
    public boolean addOrUpdateUserAdmin(Admin admin);

    public boolean usernameAdminExists(String username);
}
