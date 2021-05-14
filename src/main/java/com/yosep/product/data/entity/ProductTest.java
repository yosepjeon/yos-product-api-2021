package com.yosep.product.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "yos_product_test")
@Entity
public class ProductTest {
    @Id
    @Column(length = 100)
    private String id = "";

    @Column(length = 50)
    private String name = "";
}
