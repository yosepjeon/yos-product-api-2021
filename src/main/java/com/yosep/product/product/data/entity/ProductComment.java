package com.yosep.product.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="yos_product_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString
public class ProductComment extends BaseEntity {
    @Id
    @Column(name="product_comment_id")
    private String id;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductComment parentProductComment;

    @Column(name="description")
    private String comment;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentProductComment", cascade = CascadeType.ALL)
    private List<ProductComment> childs = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productComment", cascade = CascadeType.ALL)
    private List<ProductCommentImage> commentImages = new ArrayList<>();

    public void addChildComment(ProductComment child) {
        this.childs.add(child);
        child.setParentProductComment(this);
    }
}
