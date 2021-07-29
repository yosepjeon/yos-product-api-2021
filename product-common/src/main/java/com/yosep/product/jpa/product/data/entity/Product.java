package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.common.exception.InvalidStockValueException;
import com.yosep.product.jpa.common.exception.NotEqualProductPrice;
import com.yosep.product.jpa.product.data.dto.request.OrderProductDtoForCreation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "productId")
@Table(name = "yos_product")
//public abstract class Product {
public class Product extends BaseEntity {
    @Id
    @Column(length = 100)
    private String productId = "";

    @Column(length = 50, nullable = false)
    private String productName = "";

    @Column(nullable = false)
    private long productPrice = 0;

    @Column(nullable = false)
    private long stockQuantity = 0;

    @Column
    private String productDetail = "";

    @Embedded
    private ProductDiscount productDiscount = new ProductDiscount();

    @JsonBackReference
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    // nullable false로 다시 바꾸기 테스트용으로 true한거임
    @JoinColumn(name = "category_id", nullable = true)
    private Category category = null;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages = new ArrayList<>();

//    무신사를 예를 들어 봤을때, 상품 정보를 텍스트는 없고 전부 이미지로 나타내는것 같음
//    @JsonManagedReference
//    @OneToMany(mappedBy="product",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//    private List<ProductDescription> productDescriptions = new ArrayList<ProductDescription>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDescriptionImage> productDescriptionImages = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productProfileImageURLs = new ArrayList<ProductImage>();

    public void increaseStock(long value) {
        validateStock(value);

        this.stockQuantity += value;
    }

    public void decreaseStock(long value) {
        validateStock(value);
        validateStock(this.stockQuantity - value);

        this.stockQuantity -= value;
    }

    public void validatePrice(long productPrice) {
        if(this.productPrice != caculateFinalPrice(productPrice)) {
            throw new NotEqualProductPrice("해당 상품의 가격과 요청 데이터의 가격 값이 일치하지 않습니다.");
        }
    }

    private long caculateFinalPrice(long productPrice) {
        return productDiscount.calculateProductPrice(productPrice);
    }

    private void validateStock(long value) {
        if(value < 0L) {
            throw new InvalidStockValueException("0이상의 결과값이어야합니다.");
        }
    }

    public void decreaseStock(OrderProductDtoForCreation orderProductDtoForCreation) {
        long value = orderProductDtoForCreation.getCount();

        validateStock(orderProductDtoForCreation);

        this.stockQuantity -= value;
    }

    public void increaseStock(OrderProductDtoForCreation orderProductDtoForCreation) {
        validateStock(orderProductDtoForCreation.getCount());

        this.stockQuantity += orderProductDtoForCreation.getCount();
    }

    public void validatePrice(OrderProductDtoForCreation orderProductDtoForCreation) {
        if(this.productPrice != orderProductDtoForCreation.getPrice()) {
            orderProductDtoForCreation.setState("NotEqualProductPrice");
            throw new NotEqualProductPrice("해당 상품의 가격과 요청 데이터의 가격 값이 일치하지 않습니다.");
        }
    }

    private void validateStock(OrderProductDtoForCreation orderProductDtoForCreation) {
        if(orderProductDtoForCreation.getCount() < 0L || this.stockQuantity - orderProductDtoForCreation.getCount() < 0L) {
            orderProductDtoForCreation.setState("InvalidStockValueException");
            throw new InvalidStockValueException("0이상의 결과값이어야합니다.");
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", stockQuantity=" + stockQuantity +
                ", productDetail='" + productDetail + '\'' +
                ", productImages=" + productImages +
                ", productDescriptionImages=" + productDescriptionImages +
                ", productProfileImageURLs=" + productProfileImageURLs +
                '}';
    }
}
