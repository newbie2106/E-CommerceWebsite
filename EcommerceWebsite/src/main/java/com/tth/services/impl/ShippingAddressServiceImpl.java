/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.ShippingAddress;
import com.tth.repositories.ShippingAddressRepository;
import com.tth.services.ShippingAddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Autowired
    private ShippingAddressRepository ShippingAddressRepo;

    @Override
    public ShippingAddress getShippingAddressByUsernameAndId(String username, Long id) {
        return this.ShippingAddressRepo.getShippingAddressByUsernameAndId(username, id);
    }

    @Override
    public boolean addOrUpdateShippingAddress(ShippingAddress shippingAddress) {
        return this.ShippingAddressRepo.addOrUpdateShippingAddress(shippingAddress);
    }

    @Override
    public List<ShippingAddress> getAllAddresses(String username) {
        return this.ShippingAddressRepo.getAllAddressesByUserName(username);
    }

    // Cập nhật địa chỉ
    @Override
    public ShippingAddress updateAddress(Long id, ShippingAddress updatedAddress) {
        ShippingAddress existingAddress = this.ShippingAddressRepo.
                getShippingAddressByUsernameAndId(updatedAddress.getUsername().getUsername(), id);
        if (existingAddress != null) {
            existingAddress.setFullName(updatedAddress.getFullName());
            existingAddress.setPhone(updatedAddress.getPhone());
            existingAddress.setAddress(updatedAddress.getAddress());
            existingAddress.setProvinceCode(updatedAddress.getProvinceCode());
            existingAddress.setDistrictCode(updatedAddress.getDistrictCode());
            existingAddress.setWardCode(updatedAddress.getWardCode());
            existingAddress.setIsDefault(updatedAddress.getIsDefault());
            this.addOrUpdateShippingAddress(existingAddress);
            return existingAddress;
        }
        return null; // Trả về null nếu không tìm thấy địa chỉ
    }

    @Override
    public void updateDefaultAddress(Long id, String username) {
        List<ShippingAddress> currentDefaultAddress = this.ShippingAddressRepo.getAllAddressesByUserName(username);
        for (ShippingAddress s : currentDefaultAddress) {
            s.setIsDefault(false);
            this.ShippingAddressRepo.addOrUpdateShippingAddress(s);
        }
        ShippingAddress newDefaultAddress = this.ShippingAddressRepo.getShippingAddressByUsernameAndId(username, id);
        newDefaultAddress.setIsDefault(Boolean.TRUE);
        this.ShippingAddressRepo.addOrUpdateShippingAddress(newDefaultAddress);
    }

}
