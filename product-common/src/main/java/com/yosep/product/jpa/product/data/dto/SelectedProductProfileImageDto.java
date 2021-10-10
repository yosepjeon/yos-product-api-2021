package com.yosep.product.jpa.product.data.dto;

import lombok.Data;

@Data
public class SelectedProductProfileImageDto {
    private String id;
    private String url;

    public SelectedProductProfileImageDto(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
