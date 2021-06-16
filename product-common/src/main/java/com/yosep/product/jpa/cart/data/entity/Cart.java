package com.yosep.product.jpa.cart.data.entity;

import com.yosep.product.jpa.cart.data.dto.CartProductDto;
import com.yosep.product.jpa.cart.data.vo.CartProductVo;
import com.yosep.product.jpa.common.exception.NotExistCartProductException;
import com.yosep.product.jpa.product.data.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="productId")
@Table(name = "yos_cart")
public class Cart {
    @Id
    @Column(length = 100)
    @Setter(value = AccessLevel.PRIVATE)
    private String cartId;

    @Column(length = 30, nullable = false)
    private String userId;

    @ElementCollection
    @CollectionTable(name = "yos_cart_product", joinColumns = @JoinColumn(name = "cart_id"))
    private Map<String, CartProductVo> cartProducts = new HashMap<>();

    public Cart(String cartId, String userId) {
        this.cartId = cartId;
        this.userId = userId;
    }

    public void addProductToCart(CartProductDto cartProductDto, Product product) {
        String productId = cartProductDto.getProductId();
        CartProductVo newCartProductVo = new CartProductVo(product, cartProductDto.getAmount());

        if(cartProducts.containsKey(productId)) {
            replaceCartProduct(productId, newCartProductVo);
        }else {
            cartProducts.put(productId, newCartProductVo);
        }
    }

    public void modifyOrderProductAmount(CartProductDto cartProductDto, Product product) {
        String productId = cartProductDto.getProductId();
        CartProductVo newCartProductVo = new CartProductVo(product, cartProductDto.getAmount());

        if(cartProducts.containsKey(productId)) {
            replaceCartProduct(productId, newCartProductVo);
        }else {
            throw new NotExistCartProductException("수정하려는 해당 상품이 존재하지 않습니다.");
        }
    }

    public void deleteCartProduct(String productId) {
        this.cartProducts.remove(productId);
    }

    private void replaceCartProduct(String productId, CartProductVo newCartProductVo) {
        CartProductVo prevCartProductVo = cartProducts.get(productId);
        prevCartProductVo.addAmount(newCartProductVo.getAmount());

        cartProducts.replace(productId, prevCartProductVo);
    }
}
