/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.DTO.RecentlyViewedDTO;
import com.tth.pojo.Image;
import com.tth.pojo.Product;
import com.tth.pojo.RecentlyViewed;
import com.tth.pojo.User;
import com.tth.repositories.RecentlyViewedRepository;
import com.tth.services.ImageService;
import com.tth.services.ProductService;
import com.tth.services.RecentlyViewedService;
import com.tth.services.UserService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class RecentlyViewedServiceImpl implements RecentlyViewedService {

    @Autowired
    private RecentlyViewedRepository recentlyViewedRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService prodService;
    @Autowired
    private ImageService imageService;

    @Override
    public List<RecentlyViewedDTO> findRecentViewsByUsername(String username) {
        Date timeLimit = new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000);
        List<RecentlyViewed> recentViews = this.recentlyViewedRepository.findRecentViewsByUsername(username, timeLimit);

        return (List<RecentlyViewedDTO>) recentViews.stream().map(rv -> {
            // Lấy sản phẩm tương ứng từ RecentlyViewed
            Product product = rv.getProductId();
            List<Image> allImages = this.imageService.getProductImage(product.getId());

            // Lấy danh sách URL hình ảnh từ imageSet
            List<String> imageUrls = allImages.stream()
                    .map(Image::getUrl)
                    .collect(Collectors.toList());

            // Trả về RecentlyViewedDTO
            return new RecentlyViewedDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    imageUrls, 
                    rv.getViewedAt()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public void findByUsernameAndProductId(String username, Integer productId) {

        RecentlyViewed existingEntry = recentlyViewedRepository.findByUsernameAndProductId(username, productId);

        if (existingEntry == null) {
            User user = this.userService.getUserByUsername(username);
            Product prod = this.prodService.getProductById(productId);

            RecentlyViewed newEntry = new RecentlyViewed();
            newEntry.setUsername(user);
            newEntry.setProductId(prod);
            newEntry.setViewedAt(new Date());

            this.recentlyViewedRepository.addRecentlyViewed(newEntry);
        } else {
            // Cập nhật thời gian xem cho bản ghi đã tồn tại
            existingEntry.setViewedAt(new Date());
            this.recentlyViewedRepository.addRecentlyViewed(existingEntry);
        }
    }

    @Scheduled(cron = "0 0 * * * *") 
    public void removeOldEntries() {
        //Date timeLimit = new Date(System.currentTimeMillis() - 48 * 60 * 60 * 1000); // 48 giờ trước
        Date timeLimit = new Date(System.currentTimeMillis() - 10 * 60 * 1000);
        System.out.println("THUCTHI");
        recentlyViewedRepository.deleteAllByViewedAtBefore(timeLimit);
    }

}
