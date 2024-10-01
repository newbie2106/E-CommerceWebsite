
package com.tth.controllers;

import com.tth.pojo.ShippingAddress;
import com.tth.services.ShippingAddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin
public class ApiShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    // Hiển thị tất cả địa chỉ của người dùng
    @GetMapping("/{username}")
    public ResponseEntity<List<ShippingAddress>> getAllAddresses(@PathVariable String username) {
        List<ShippingAddress> addresses = this.shippingAddressService.getAllAddresses(username);
        return ResponseEntity.ok(addresses);
    }

    // Thêm địa chỉ mới
    @PostMapping("/")
    public ResponseEntity<ShippingAddress> addAddress(@RequestBody ShippingAddress address) {
        if (this.shippingAddressService.addOrUpdateShippingAddress(address)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(address);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Cập nhật địa chỉ
    @PutMapping("/{id}")
    public ResponseEntity<ShippingAddress> updateAddress(@PathVariable Long id, @RequestBody ShippingAddress updatedAddress) {
        ShippingAddress updated = this.shippingAddressService.updateAddress(id, updatedAddress);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
