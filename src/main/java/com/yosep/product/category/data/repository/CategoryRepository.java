package com.yosep.product.category.data.repository;

import com.yosep.product.category.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
