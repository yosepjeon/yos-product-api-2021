package com.yosep.product.category.service;

import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.category.data.repository.CategoryRepositoryQueryDsl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryRepositoryQueryDsl categoryRepositoryQueryDsl;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryRepositoryQueryDsl categoryRepositoryQueryDsl, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryRepositoryQueryDsl = categoryRepositoryQueryDsl;
        this.modelMapper = modelMapper;
    }

    /*
    * 카테고리 생성
    * Logic:
    * 1. 년월일 + UUID
     */
    public void createCategory(CategoryDto categoryDto) {
        long parentId = categoryDto.getParentId();
        Category category = modelMapper.map(categoryDto, Category.class);

        if(categoryDto.getParentId() == null) {

//            categoryRepository.save()
        }else {
            categoryRepository.findById(parentId);
        }
    }

    public void readCategoryById() {

    }

    public void readCategoriesByName() {

    }

    public void updateCategory() {

    }

    public void deleteCategory() {

    }

    public void deleteCategories() {

    }

}
