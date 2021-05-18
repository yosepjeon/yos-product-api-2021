package com.yosep.product.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.common.BaseIntegrationTest;
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
public class CategoryControllerTest extends BaseIntegrationTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public CategoryControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("[Controller] Category 생성 테스트")
    public void createCategoryTest() throws Exception {
        log.info("카테코리 컨트롤러 생성 테스트");
        CategoryDto categoryDto = CategoryDto.builder().name("create-test").build();
//        CategoryDto categoryDto = new CategoryDto("create-test", null);

        String content = objectMapper.writeValueAsString(categoryDto);

        mockMvc
                .perform(post("/categories", categoryDto)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }
}
