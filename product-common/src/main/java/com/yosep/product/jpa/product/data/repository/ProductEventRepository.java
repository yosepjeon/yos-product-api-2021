package com.yosep.product.jpa.product.data.repository;

import com.yosep.product.jpa.product.data.entity.ProductEvent;
import com.yosep.product.jpa.product.data.vo.EventId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEventRepository extends JpaRepository<ProductEvent, EventId> {
}
