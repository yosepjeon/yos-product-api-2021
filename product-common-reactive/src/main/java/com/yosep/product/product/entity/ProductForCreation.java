package com.yosep.product.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table(value = "yos_product")
@AllArgsConstructor
public class ProductForCreation implements Persistable<String> {
    @Id
    private String productId;

    private String productName;

    private long productPrice;

    private long stockQuantity;

    private String productDetail;

    private long categoryId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Override
    public String getId() {
        return this.productId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
