package com.yosep.product.jpa.product.data.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class ProductDescriptionImageDtoForCreation {
    private String id;
    private String url;
    private MultipartFile file;
}
