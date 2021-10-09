package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.jpa.common.entity.BaseEntity;
import com.yosep.product.jpa.product.data.vo.ImageType;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Builder
@Table(name="yos_product_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@DiscriminatorValue("P")
@EqualsAndHashCode(of="id")
@Entity
@ToString
public class ProductImage extends Image {
//    리팩토링 상속관계로
//    @Id
//    @Column(length=300)
//    private String id;

    @JsonBackReference
    @ManyToOne(fetch= LAZY)
    @JoinColumn(name="product_id")
    private Product product;

//    리팩토링 상속관계로
//    @Column(name="url",length=300,nullable=false)
//    private String url;

//    리팩토링 상속관계로
//    @Column(name = "image_type")
//    @Enumerated(EnumType.STRING)
//    private ImageType imageType;
}
