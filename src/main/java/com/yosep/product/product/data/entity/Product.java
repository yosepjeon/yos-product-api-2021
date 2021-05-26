package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.category.data.entity.Category;
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
public class Product {
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
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private LocalDateTime productRdate;

    @Column
    private LocalDateTime productUdate;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProductDescription> productDescriptions = new ArrayList<ProductDescription>();

    @JsonManagedReference
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProductImage> productProfileImageURLs = new ArrayList<ProductImage>();
}
