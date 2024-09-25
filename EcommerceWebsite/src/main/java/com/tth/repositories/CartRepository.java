/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.DTO.CartProductDTO;
import com.tth.pojo.Cart;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tongh
 */
public interface CartRepository {

    Cart findByUsernameAndProductId(String username, Integer productId);

    void addToCart(Cart cart);

    void deleteAllByUsername(String username);

    List<Cart> findCartByUsername(String username);

    void deleteCartItem(Cart cart);
}
