package com.yosep.product.cart.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yosep.product.product.data.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of="productId")
@Table(name = "yos_cart")
public class Cart {
    @Id
    @Column(length = 100)
    @Setter(value = AccessLevel.PRIVATE)
    private String cartId;

    @JsonManagedReference
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
}
