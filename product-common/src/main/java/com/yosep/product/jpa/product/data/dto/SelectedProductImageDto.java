package com.yosep.product.jpa.product.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class SelectedProductImageDto {
    private final String url;

    @QueryProjection
    public SelectedProductImageDto(String url) {
        this.url = url;
    }
}
