package com.juyss.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Cart
 * @Desc:  购物车对象
 * @package com.juyss.pojo
 * @project BookStore
 * @date 2020/8/21 19:23
 */
public class Cart {

    //总商品数量
//    private Integer totalCount;

    //总商品合集金额
//    private BigDecimal totalPrice;

    //商品集合
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    //最后一次添加的商品信息
    private CartItem lastAddedCartItem;

    public Integer getTotalCount() {
        Integer totalCount = 0;

        for(Map.Entry<Integer , CartItem> entry : items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal("0");
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public CartItem getLastAddedCartItem() {
        return lastAddedCartItem;
    }

    public void setLastAddedCartItem(CartItem lastAddedCartItem) {
        this.lastAddedCartItem = lastAddedCartItem;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                ", lastAddedCartItem=" + lastAddedCartItem +
                '}';
    }

    /**
     * 向购物车中添加商品
     * @param cartItem 要添加的商品对象
     */
    public void addItem(CartItem cartItem){
        if (items.containsKey(cartItem.getId())){
            //已经添加过此商品
            CartItem item = items.get(cartItem.getId());
            //更新数量和总价
            item.setCount(item.getCount()+1); //数量+1
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount()))); //总价=单价*数量


        }else{
            //未添加过此商品
            items.put(cartItem.getId(), cartItem);
        }
        //更新最后一次的添加记录
        lastAddedCartItem = cartItem;
    }

    /**
     * 删除购物车中的指定商品项
     * @param id 要删除的商品id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clearItem(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateItemCount(Integer id , Integer count){
        CartItem cartItem = items.get(id);
        if (cartItem!=null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }
}
