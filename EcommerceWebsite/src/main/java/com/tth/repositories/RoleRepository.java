/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Role;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface RoleRepository {
    public List<Role> getRole();
    
    public Role getRoleById(int id);
}
