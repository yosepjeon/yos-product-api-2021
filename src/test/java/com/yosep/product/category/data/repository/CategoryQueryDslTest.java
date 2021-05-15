package com.yosep.product.category.data.repository;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CategoryQueryDslTest {
    private final CategoryRepositoryQueryDsl queryDsl;

    @Autowired
    public CategoryQueryDslTest(CategoryRepositoryQueryDsl queryDsl) {
        this.queryDsl = queryDsl;
    }


}
