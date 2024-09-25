/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.DTO.CartProductDTO;
import com.tth.pojo.Cart;
import com.tth.pojo.Image;
import com.tth.pojo.Product;
import com.tth.pojo.User;
import com.tth.repositories.CartRepository;
import com.tth.services.CartService;
import com.tth.services.ImageService;
import com.tth.services.ProductService;
import com.tth.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageService imageService;

    @Override
    public void addOrUpdateCart(String username, Integer productId, Integer quantity) {
        Cart existingCart = cartRepository.findByUsernameAndProductId(username, productId);

        if (existingCart == null) {
            User user = this.userService.getUserByUsername(username);
            Product product = this.productService.getProductById(productId);

            Cart newCart = new Cart();
            newCart.setUsername(user);
            newCart.setProductId(product);
            newCart.setQuantity(quantity);

            cartRepository.addToCart(newCart);
        } else {
            // Cập nhật số lượng cho sản phẩm đã tồn tại
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            this.cartRepository.addToCart(existingCart);
        }
    }

    @Override
    public void updateCartQuantity(String username, Integer productId, Integer quantity) {
        Cart existingCart = cartRepository.findByUsernameAndProductId(username, productId);

        if (existingCart != null) {
            // Nếu số lượng <= 0, có thể xóa sản phẩm khỏi giỏ
            if (quantity <= 0) {
                this.cartRepository.deleteCartItem(existingCart);
            } else {

                existingCart.setQuantity(quantity);
                this.cartRepository.addToCart(existingCart);
            }
        }
    }

    @Override
    public void deleteCartItem(Cart cart) {
        this.cartRepository.deleteCartItem(cart);
    }

    @Override
    public void deleteAllCartItem(String username) {
        this.cartRepository.deleteAllByUsername(username);
    }

    @Override
    public List<CartProductDTO> getCartByUsername(String username) {
        List<Cart> cartItems = this.cartRepository.findCartByUsername(username);

        return (List<CartProductDTO>) cartItems.stream().map(c -> {
            // Lấy sản phẩm tương ứng từ RecentlyViewed
            Product product = c.getProductId();
            List<Image> allImages = this.imageService.getProductImage(product.getId());
            String firstImage = allImages.isEmpty() ? null : allImages.get(0).getUrl();
            System.out.println("4nh" + firstImage);
            return new CartProductDTO(
                    c.getProductId().getId(),
                    c.getProductId().getName(),
                    c.getQuantity(),
                    c.getProductId().getPrice(),
                    firstImage
            );
        }).collect(Collectors.toList());
    }
}
