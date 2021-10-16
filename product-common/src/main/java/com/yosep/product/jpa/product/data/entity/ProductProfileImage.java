package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Builder
@Table(name="yos_product_profile_image")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PP")
@Getter
@EqualsAndHashCode(of="id",callSuper = true)
@Entity
@ToString
public class ProductProfileImage extends Image{
//    리팩토링 상속관계로
//    @Id
//    @Column
//    private String id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

//    리팩토링 상속관계로
//    @Column(name="url", length = 300, nullable = false)
//    private String url;
}
