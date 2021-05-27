package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yosep.product.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Table(name="yos_product_comment_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Entity
@ToString
public class ProductCommentImage extends BaseEntity {
    @Id
    @Column
    private String id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private ProductComment productComment;
}
