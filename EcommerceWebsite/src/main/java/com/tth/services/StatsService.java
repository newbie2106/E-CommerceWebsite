/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import java.util.List;

/**
 *
 * @author tongh
 */
public interface StatsService {

    List<Object[]> StatsRevenueByPeriod(int year, String period);

    List<Object[]> StatsRevenueByProduct();
    
    List<Object[]> statsRevenueByProductBranch(String usernameBranch);
    
    List<Object[]> statsRevenueByPeriodBranch(int year, String period,String usernameBranch);

}
