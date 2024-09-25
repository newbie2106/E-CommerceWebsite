/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.DTO.CartProductDTO;
import com.tth.pojo.Cart;
import java.util.List;

/**
 *
 * @author tongh
 */
public interface CartService {

    void addOrUpdateCart(String username, Integer productId, Integer quantity);

    void updateCartQuantity(String username, Integer productId, Integer quantity);

    List<CartProductDTO> getCartByUsername(String username);

    void deleteCartItem(Cart cart);

    void deleteAllCartItem(String username);
}
