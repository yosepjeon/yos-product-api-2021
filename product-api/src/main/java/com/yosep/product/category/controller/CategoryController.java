package com.yosep.product.category.controller;

import com.yosep.product.category.service.CategoryService;
import com.yosep.product.jpa.category.data.dto.request.CategoryDtoForCreation;
import com.yosep.product.jpa.category.data.dto.response.CreatedCategoryDto;
import com.yosep.product.jpa.category.data.dto.response.SelectedCategoryDto;
import com.yosep.product.jpa.category.data.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping(value = "/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{category-id}")
    public ResponseEntity readCategory(@PathVariable("category-id") String categoryId) {
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

    @GetMapping("/parent-categories")
    public ResponseEntity readCategoriesGroupByParent() {
        Optional<CollectionModel<EntityModel<SelectedCategoryDto>>> categories = categoryService.readCategoriesByParentIsNullForUpdate();

        return categories.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categories);
    }

    @GetMapping
    public void readAllCategories() {
        
    }

    @PostMapping
    public ResponseEntity<EntityModel<CreatedCategoryDto>> createCategory(@RequestBody CategoryDtoForCreation categoryDtoForCreation) {
        Optional<Category> optionalCreatedCategory = categoryService.createCategory(categoryDtoForCreation);

        CreatedCategoryDto createdCategoryDto = optionalCreatedCategory.isPresent() ? new CreatedCategoryDto(optionalCreatedCategory.get()) : new CreatedCategoryDto();
        EntityModel<CreatedCategoryDto> responseModel = EntityModel.of(createdCategoryDto);
        responseModel
                .add(linkTo(CategoryController.class).slash(createdCategoryDto.getId()).withSelfRel());

        ResponseEntity<EntityModel<CreatedCategoryDto>> response = ResponseEntity.created(URI.create("/categories")).body(responseModel);

        return response;
    }

    @PatchMapping
    public void updateCategory() {

    }

    @DeleteMapping
    public void deleteCategory() {

    }
}
