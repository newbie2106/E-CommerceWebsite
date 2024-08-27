/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import java.util.List;

/**
 *
 * @author tongh
 */
public interface StatsRepository {

    List<Object[]> statsRevenueByPeriod(int year, String period);

    List<Object[]> statsRevenueByProduct();

}
