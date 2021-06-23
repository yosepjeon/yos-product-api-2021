package com.yosep.product.jpa.product.data.dto.request;


import com.yosep.product.jpa.category.data.entity.Category;

public interface ProductTestDto {
    String getProductId();
    String getProductName();
    Category getCategory();
}
