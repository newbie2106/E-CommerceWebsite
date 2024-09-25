/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.ENUM.ShipmentStatus;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tongh
 */
public interface SaleOrderRepository {

    List<Object[]> getSaleOrder(Map<String, String> params, String branch, ShipmentStatus status);

}
