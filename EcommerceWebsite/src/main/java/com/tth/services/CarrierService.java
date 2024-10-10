/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.Carrier;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface CarrierService {

    Carrier getCarrierById(int id);

    List<Carrier> getListCarrier();
}
