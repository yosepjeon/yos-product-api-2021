package com.yosep.product.category.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "parentCategory")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "yos_product_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", length = 50, nullable = false)
    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory")
    private List<Category> childs = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.childs.add(child);
        child.setParentCategory(this);
    }
}
