/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.DTO.RecentlyViewedDTO;
import com.tth.pojo.RecentlyViewed;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface RecentlyViewedService {

    List<RecentlyViewedDTO> findRecentViewsByUsername(String username);

    void findByUsernameAndProductId(String username, Integer productId);

    void removeOldEntries();
}
