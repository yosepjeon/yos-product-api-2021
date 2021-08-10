package com.yosep.product.jpa.category.data.repository;

import com.yosep.product.jpa.category.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String>, CategoryRepositoryQueryDsl {
}
