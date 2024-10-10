/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Carrier;
import com.tth.repositories.CarrierRepository;
import com.tth.services.CarrierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class CarrierServiceImpl implements CarrierService {

    @Autowired
    private CarrierRepository carrierRepository;

    @Override
    public Carrier getCarrierById(int id) {
        return this.carrierRepository.getCarrierById(id);
    }

    @Override
    public List<Carrier> getListCarrier() {
        return this.carrierRepository.getListCarrier();
    }

}
