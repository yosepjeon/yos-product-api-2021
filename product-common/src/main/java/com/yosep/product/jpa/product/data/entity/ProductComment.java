package com.yosep.product.jpa.product.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.jpa.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="yos_product_comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

//    리팩토링 단방향으로
//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentProductComment", cascade = CascadeType.ALL)
//    private List<ProductComment> childs = new ArrayList<>();

//    리팩토링 단방향으로
//    이건 OneToMany 구조가 좀더 편할거 같은데.. 일단 좀더 고민
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productComment", cascade = CascadeType.ALL)
    private List<ProductCommentImage> commentImages = new ArrayList<>();

//    리팩토링 단방향으로
//    public void addChildComment(ProductComment child) {
//        this.childs.add(child);
//        child.setParentProductComment(this);
//    }
}
