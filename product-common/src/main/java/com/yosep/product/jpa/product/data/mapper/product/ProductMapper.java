package com.yosep.product.jpa.product.data.mapper.product;

import com.yosep.product.jpa.product.data.dto.request.ProductDtoForCreation;
import com.yosep.product.jpa.product.data.dto.response.CreatedProductDto;
import com.yosep.product.jpa.product.data.dto.response.SelectedProductDto;
import com.yosep.product.jpa.product.data.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "category", ignore = true)
    Product productDtoForCreationToProduct(ProductDtoForCreation productDtoForCreation);

    @Mapping(target = "productId", ignore = false)
    CreatedProductDto productToCreatedProductDto(Product product);

    @Mapping(target = "productId", ignore = false)
    SelectedProductDto productToSelectedProductDto(Product product);
}