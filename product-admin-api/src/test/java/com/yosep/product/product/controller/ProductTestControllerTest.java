package com.yosep.product.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Slf4j
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ProductTestControllerTest {
    private MockMvc mockMvc;

    @Autowired
    public ProductTestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Rest-Docs 잘 만들어지는지 Test")
    public void restDocTest() throws Exception {
        mockMvc
                .perform(get("/products-test/rest-doc-test"))
                .andDo(print())
                .andDo(document(
                        "test-restdoc",
                        links(
//                                linkWithRel("self").description("자기 자신을 가리키는 링크로 get-product와 일맥상통합니다."),
//                                linkWithRel("get-products").description("물건 리스트를 가져오는 링크"),
//                                linkWithRel("get-product").description("특정 물건을 가져오는 링크"),
//                                linkWithRel("patch-product").description("특정 물건의 일부 프로퍼티를 수정하는 링크"),
//                                linkWithRel("profile").description("profile 링크")
                        ),
                        requestHeaders(
//						headerWithName(HttpHeaders.CONTENT_TYPE).description("application/json;charset=UTF-8"),
//						headerWithName(HttpHeaders.AUTHORIZATION)
//								.description("Bearer token-value MSA 모든 서비스를 이용하기 위해서는 해당 인증값을 반드시 넣어야 합니다."),
//						headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("Content type")),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                        )
                ));
    }
}
