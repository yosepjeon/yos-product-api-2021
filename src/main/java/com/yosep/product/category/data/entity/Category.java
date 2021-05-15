package com.yosep.product.category.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "yos_product_category")
public class Category {
    @Id
    @Column(name = "category_id", length = 30)
    private String id;

    @Column(name = "category_name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_parent_id", insertable = false, updatable = false)
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Category> childs;

    public void addChild(Category child) {
        this.childs.add(child);
        child.setParent(this);
    }

}
