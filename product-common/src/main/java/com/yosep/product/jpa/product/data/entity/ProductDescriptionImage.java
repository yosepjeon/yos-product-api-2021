package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "yos_product_description_image")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PD")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class ProductDescriptionImage extends Image {
//    리팩토링 상속관계로
//    @Id
//    @Column
//    private String id;

//    무신사를 예를 들어 봤을때, 상품 정보를 텍스트는 없고 전부 이미지로 나타내는것 같음
//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "description_id")
//    private ProductDescription productDescription;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    리팩토링 상속관계로
//    @Column(name="url",length=300,nullable=false)
//    private String url;
}
