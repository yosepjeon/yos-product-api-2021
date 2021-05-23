package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ProductCommentImage {
    @Id
    @Column
    private String id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private ProductComment productComment;

    @Column(name="rdate")
    private LocalDateTime rdate;

    @Column(name="udate")
    private LocalDateTime udate;
}
