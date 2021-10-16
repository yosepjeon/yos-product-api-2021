package com.yosep.product.jpa.product.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class SelectedProductDescriptionImageDto extends RepresentationModel<SelectedProductDescriptionImageDto> {
    private final String url;

    @QueryProjection
    public SelectedProductDescriptionImageDto(String url) {
        this.url = url;
    }
}
