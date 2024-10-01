/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.ShippingAddress;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface ShippingAddressService {

    boolean addOrUpdateShippingAddress(ShippingAddress shippingAddress);

    List<ShippingAddress> getAllAddresses(String username);

    ShippingAddress updateAddress(Long id, ShippingAddress updatedAddress);
}
