package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="yos_product_description")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class ProductDescription {
    @Id
    @Column(name="product_description_id")
    private String id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="product_description_rdate")
    private LocalDateTime productDescriptionRdate;

    @Column(name="product_description_udate")
    private LocalDateTime productDescriptionUdate;

    @Column(name="description")
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "productDescription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDescriptionImage> descriptionImages;
}
