package com.juyss.test;

import com.juyss.pojo.Cart;
import com.juyss.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: CartTest
 * @Desc:  购物车测试类
 * @package com.juyss.test
 * @project BookStore
 * @date 2020/8/22 17:09
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        CartItem first = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem first1 = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem second = new CartItem(2, "second", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        cart.addItem(first);
        cart.addItem(first1);
        cart.addItem(second);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        CartItem first = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem first1 = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem second = new CartItem(2, "second", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        cart.addItem(first);
        cart.addItem(first1);
        cart.addItem(second);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clearItem() {
        Cart cart = new Cart();
        CartItem first = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem first1 = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem second = new CartItem(2, "second", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        cart.addItem(first);
        cart.addItem(first1);
        cart.addItem(second);
        cart.clearItem();
        System.out.println(cart);
    }

    @Test
    public void updateItemCount() {
        Cart cart = new Cart();
        CartItem first = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem first1 = new CartItem(1, "first", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        CartItem second = new CartItem(2, "second", 1, new BigDecimal("12.4"), new BigDecimal("12.4"));
        cart.addItem(first);
        cart.addItem(first1);
        cart.addItem(second);
        cart.updateItemCount(2, 4);
        System.out.println(cart);
    }
}