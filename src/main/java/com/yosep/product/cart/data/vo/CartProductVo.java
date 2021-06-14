package com.yosep.product.cart.data.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.common.exception.InvalidStockValueException;
import com.yosep.product.product.data.entity.Product;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Getter
public class CartProductVo {
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private long amount;

    public CartProductVo(Product product, long amount) {
        this.product = product;
        this.amount = amount;
    }

    public void addAmount(long value) {
        validateStock(value);
        this.amount += value;
    }

    private static void validateStock(final long value) {
        if(value < 1L) {
            throw new InvalidStockValueException("[Cart] 재고 설정은 최소 1개 이상 해야합니다.");
        }
    }
}

//@Embeddable
//@Getter
//public class CartProductVo {
//    private String productId;
//
//    private long amount;
//
//    public CartProductVo(String productId, long amount) {
//        this.productId = productId;
//        this.amount = amount;
//    }
//
//    public void addAmount(long value) {
//        validateStock(value);
//        this.amount += value;
//    }
//
//    private static void validateStock(final long value) {
//        if(value < 1L) {
//            throw new InvalidStockValueException("[Cart] 재고 설정은 최소 1개 이상 해야합니다.");
//        }
//    }
//}
