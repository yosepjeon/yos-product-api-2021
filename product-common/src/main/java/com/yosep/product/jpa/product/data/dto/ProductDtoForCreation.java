package com.yosep.product.jpa.product.data.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
public class ProductDtoForCreation {
    @NotBlank
    private String productName = "";

    @Min(0)
    private long productPrice = 0;

    @Min(0)
    private long stockQuantity = 0;

    private String productDetail;

    @NotBlank
    private Long category;

    private List<MultipartFile> productImages;
}
