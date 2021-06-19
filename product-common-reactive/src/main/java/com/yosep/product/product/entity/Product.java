package com.yosep.product.product.entity;

import com.yosep.product.common.exception.InvalidStockValueException;
import com.yosep.product.product.repository.ReactiveProductRepository;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@Table(value = "yos_product")
public class Product implements Persistable<String> {
    @Id
    protected String productId;

    protected String productName;

    protected long productPrice;

    protected long stockQuantity;

    protected String productDetail;

    protected long categoryId;

    protected LocalDateTime createdDate;

    protected LocalDateTime lastModifiedDate;

    @Transient
    protected boolean isNew = false;

    public Product(String productId, String productName, long productPrice, long stockQuantity, String productDetail, int categoryId, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDetail = productDetail;
        this.categoryId = categoryId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.isNew = false;
    }

    public Product setAsNew() {
        this.isNew = true;

        return this;
    }

    public Mono<Product> increaseStock(long value, ReactiveProductRepository reactiveProductRepository) throws InvalidStockValueException{
        if (value < 0L) {
            throw new InvalidStockValueException("1 이상의 값이어야 합니다.");
        }

        stockQuantity += value;

        return reactiveProductRepository.save(this);
    }

    public Mono<Product> decreaseStock(long value, ReactiveProductRepository reactiveProductRepository) throws InvalidStockValueException {
        if (value < 0L) {
            throw new InvalidStockValueException("1 이상의 값이어야 합니다.");
        }

        stockQuantity -= value;

        if(stockQuantity < 0L) {
            throw new InvalidStockValueException("재고가 부족합니다.");
        }

        return reactiveProductRepository.save(this);
    }

    @Override
    public String getId() {
        return this.productId;
    }

    @Override
    @Transient
    public boolean isNew() {
        return isNew || productId == null;
    }

}
