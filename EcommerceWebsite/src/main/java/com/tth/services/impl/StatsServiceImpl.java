/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.repositories.StatsRepository;
import com.tth.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    private StatsRepository statsRepo;
    
    @Override
    public List<Object[]> StatsRevenueByPeriod(int year, String period) {
        return this.statsRepo.statsRevenueByPeriod(year, period);
    }

    @Override
    public List<Object[]> StatsRevenueByProduct() {
        return this.statsRepo.statsRevenueByProduct();
    }

}
