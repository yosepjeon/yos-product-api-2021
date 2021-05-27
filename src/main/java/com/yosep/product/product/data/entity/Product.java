package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "DTYPE")
@Builder
@EqualsAndHashCode(of="productId")
@Table(name = "yos_product")
//public abstract class Product {
public class Product extends BaseEntity {
    @Id
    @Column(length = 100)
    @Setter(value = AccessLevel.PRIVATE)
    private String productId = "";

    @Column(length = 50)
    private String productName = "";

    @Column(nullable = false)
    private int productPrice = 0;

    @Column(nullable = false)
    private int stockQuantity = 0;

    @Column
    private String productDetail;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
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
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProductImage> productProfileImageURLs = new ArrayList<ProductImage>();
}
