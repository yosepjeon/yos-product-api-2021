package com.yosep.product.cart.data.entity;

import com.yosep.product.cart.data.dto.CartProductDto;
import com.yosep.product.cart.data.vo.CartProductVo;
import com.yosep.product.common.exception.NotExistCartProductException;
import com.yosep.product.product.data.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
