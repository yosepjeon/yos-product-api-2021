package com.yosep.product.category.controller;

import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.category.data.dto.request.CreatedCategoryDto;
import com.yosep.product.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<EntityModel<Optional<SelectedCategoryDto>>> readCategory(@PathVariable("category-id") long categoryId) {
        Optional<SelectedCategoryDto> optionalReadedCategoryDto = categoryService.readCategoryById(categoryId);

        EntityModel<Optional<SelectedCategoryDto>> readedCategoryDto;
        ResponseEntity<EntityModel<Optional<SelectedCategoryDto>>> response;

        if(optionalReadedCategoryDto.isEmpty()) {
            response = ResponseEntity.notFound().build();
        }else {
            readedCategoryDto = EntityModel.of(optionalReadedCategoryDto);
            response = ResponseEntity.ok().body(readedCategoryDto);
        }

        return response;
    }

    @GetMapping
    public void readCategories() {

    }

    @PostMapping
    public ResponseEntity<EntityModel<CreatedCategoryDto>> createCategory(@RequestBody CategoryDto categoryDto) {
        Optional<Category> optionalCreatedCategory = categoryService.createCategory(categoryDto);

        CreatedCategoryDto createdCategoryDto = optionalCreatedCategory.isPresent() ? new CreatedCategoryDto(optionalCreatedCategory.get()) : new CreatedCategoryDto();
        EntityModel<CreatedCategoryDto> responseModel = EntityModel.of(createdCategoryDto);
//        responseModel
//                .add(linkTo(CategoryController.class).withSelfRel());

        ResponseEntity<EntityModel<CreatedCategoryDto>> response = ResponseEntity.created(URI.create("/categories")).body(responseModel);

        return response;
    }

    @PatchMapping
    public void updateCategory() {

    }
}
