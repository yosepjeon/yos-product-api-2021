package com.yosep.product.jpa.common.entity;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public final class PageRequest {
    private int page;
    private int size;
    private Sort.Direction direction;

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 9;
        int MAX_SIZE = 45;
        this.size = size > MAX_SIZE ? MAX_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "createdDate");
    }
}
