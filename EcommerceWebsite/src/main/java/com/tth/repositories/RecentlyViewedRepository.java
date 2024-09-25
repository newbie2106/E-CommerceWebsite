/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.RecentlyViewed;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface RecentlyViewedRepository {

    List<RecentlyViewed> findRecentViewsByUsername(String username, Date timeLimit);

    RecentlyViewed findByUsernameAndProductId(String username, Integer productId);

    void deleteAllByViewedAtBefore(Date timeLimit);
    
    void addRecentlyViewed(RecentlyViewed recentlyViewed);
}
