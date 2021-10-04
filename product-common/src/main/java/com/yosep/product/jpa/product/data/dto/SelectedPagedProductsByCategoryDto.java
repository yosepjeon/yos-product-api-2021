package com.yosep.product.jpa.product.data.dto;

import com.yosep.product.jpa.category.data.dto.response.CategoryDto;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public class SelectedPagedProductsByCategoryDto extends PagedModel<SelectedPagedProductsByCategoryDto> {
    List<SelectedProductDto> selectedProductDtos;

}
