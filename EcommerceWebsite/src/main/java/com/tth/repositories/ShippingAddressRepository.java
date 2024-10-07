/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.ShippingAddress;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface ShippingAddressRepository {

    boolean addOrUpdateShippingAddress(ShippingAddress shippingAddress);

    List<ShippingAddress> getAllAddressesByUserName(String username);

    ShippingAddress getShippingAddressByUsernameAndId(String username, Long id);

}
