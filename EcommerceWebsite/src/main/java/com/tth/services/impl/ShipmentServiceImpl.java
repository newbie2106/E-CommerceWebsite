/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Shipment;
import com.tth.repositories.ShipmentRepository;
import com.tth.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {
    
    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Override
    public void addShipment(Shipment s) {
        this.shipmentRepository.addShipment(s);
    }
    
}
