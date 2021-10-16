package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@Table(name = "yos_product_comment_image")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PC")
@Getter
@EqualsAndHashCode
@Entity
@ToString
public class ProductCommentImage extends Image {
//    리팩토링 상속관계로
//    @Id
//    @Column
//    private String id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private ProductComment productComment;

//    리팩토링 상속관계로
//    @Column(name="url", length = 300, nullable = false)
//    private String url;
}
