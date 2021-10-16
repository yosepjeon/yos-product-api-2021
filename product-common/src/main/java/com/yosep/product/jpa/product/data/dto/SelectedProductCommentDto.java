package com.yosep.product.jpa.product.data.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
public class SelectedProductCommentDto {
    private final String id;
    private final String comment;
    private final List<SelectedProductCommentImageDto> commentImages;
    private final LocalDateTime createdTime;

    @QueryProjection
    public SelectedProductCommentDto(String id, String comment, List<SelectedProductCommentImageDto> commentImages, LocalDateTime createdTime) {
        this.id = id;
        this.comment = comment;
        this.commentImages = Collections.unmodifiableList(commentImages);
        this.createdTime = createdTime;
    }
}
