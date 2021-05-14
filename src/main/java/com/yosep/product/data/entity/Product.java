package com.yosep.product.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Product {
    @Id
    @Column(length = 100)
    private String id;

    @Column(length = 50)
    private String name;

    private int price;
    private int stockQuantity;

    
}
