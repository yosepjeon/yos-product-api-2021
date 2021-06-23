package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.jpa.category.data.entity.Category;
import com.yosep.product.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "productId")
@Table(name = "yos_product")
//public abstract class Product {
public class Product extends BaseEntity {
    @Id
    @Column(length = 100)
    @Setter(value = AccessLevel.PRIVATE)
    private String productId = "";

    @Column(length = 50, nullable = false)
    private String productName = "";

    @Column(nullable = false)
    private long productPrice = 0;

    @Column(nullable = false)
    private long stockQuantity = 0;

    @Column
    private String productDetail;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    // nullable false로 다시 바꾸기 테스트용으로 true한거임
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

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

//    public static Product create(
//            ProductDtoForCreation productDtoForCreation,
//            CategoryRepository categoryRepository,
//            ProductRepository productRepository
//    ) {
//        String productId = RandomIdGenerator.createId();
//        categoryRepository.findById(productDtoForCreation.getCategory());
//
//        while (true) {
//            if (productRepository.existsById(productId)) {
//                productId = RandomIdGenerator.createId();
//                continue;
//            } else {
//                Product product = Product.builder()
//                        .productId(productId)
//                        .productName(productDtoForCreation.getProductName())
//                        .productPrice(productDtoForCreation.getProductPrice())
//                        .stockQuantity(productDtoForCreation.getStockQuantity())
//                        .productDetail(productDtoForCreation.getProductDetail())
//                        .category(categoryRepository.findById(productDtoForCreation.getCategory()).get())
//                        .build();
//
//                return productRepository.save(product);
//            }
//        }
//    }

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
