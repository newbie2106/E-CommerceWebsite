/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.DTO.UserAdminDTO;
import com.tth.pojo.Admin;

/**
 *
 * @author tongh
 */
public interface AdminRepository {

    public boolean addOrUpdateUserAdmin(Admin admin);

    public boolean usernameAdminExists(String username);
    
}
