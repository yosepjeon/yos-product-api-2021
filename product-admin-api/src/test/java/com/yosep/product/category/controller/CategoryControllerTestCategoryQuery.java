package com.yosep.product.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosep.product.category.service.CategoryService;
import com.yosep.product.common.BaseCategoryIntegrationTest;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Slf4j
public class CategoryControllerTestCategoryQuery extends BaseCategoryIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoryControllerTestCategoryQuery(MockMvc mockMvc, ObjectMapper objectMapper, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Test
    @DisplayName("[Controller] Category 생성 테스트")
    public void createCategoryTest() throws Exception {
        log.info("카테고리 컨트롤러 생성 테스트");
        CategoryDtoForCreation categoryDtoForCreation = CategoryDtoForCreation.builder().name("create-test").build();

        String content = objectMapper.writeValueAsString(categoryDtoForCreation);

        mockMvc
                .perform(post("/categories", categoryDtoForCreation)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("[Controller] Category ID기준 조회 성공 테스트")
    public void readCategoryByIdSuccessTest() throws Exception {
        log.info("카테고리 컨트롤러 조회 테스트");

        mockMvc
                .perform(get("/categories/" + categoryId3)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("[Controller] Category ID기준 조회 실패 테스트")
    public void readCategoryByIdFailTest() throws Exception {
        log.info("카테고리 컨트롤러 조회 테스트");
        mockMvc
                .perform(get("/categories/-1")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
