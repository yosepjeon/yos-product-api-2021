package com.yosep.product.category.service;

import com.yosep.product.category.data.dto.request.CategoryDto;
import com.yosep.product.category.data.dto.request.CategoryForUpdateDto;
import com.yosep.product.category.data.dto.response.ReadedCategoryDto;
import com.yosep.product.category.data.entity.Category;
import com.yosep.product.category.data.repository.CategoryRepository;
import com.yosep.product.category.data.repository.CategoryRepositoryQueryDsl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
     * 1. ModelMapper를 이용하여 CategoryDto를 category Entity로 변환
     * if-1. 생성하려는 category가 부모 카테고리라면 바로 생성
     * if-2. 생성하려는 category가 자식 카테고리지만 부모 카테고리가우 원래 없었거나, 알 수 없는 문제로 사라졌을 경우 바로 생성
     * if-3. 생성하려는 category가 자식 카테고리면서 부모 카테고리가 존재하는 경우
     *       1) 부모 카테고리를 가져옴.
     *       2) 자식 카테고리 저장.
     *       3) 저장한 자식 카테고리 엔티티를 부모 카테고리의 childs 리스트에 저장(자동 영속화 및 외래키 등록)
     */
    @Transactional(readOnly = false)
    public Optional<Category> createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        if (categoryDto.getParentId() == null) {
            return Optional.of(categoryRepository.save(category));
        } else if (categoryDto.getParentId() != null && categoryRepository.findById(categoryDto.getParentId()).isEmpty()) {
            return Optional.of(categoryRepository.save(category));
        } else {
            Category parent = categoryRepository.findById(categoryDto.getParentId()).get();
            Category child = categoryRepository.save(category);
            parent.addChildCategory(child);
            categoryRepository.save(parent);

            return Optional.of(category);
        }
    }

    /*
     * 부모 카테고리를 읽어오되, 자식 카테고리들도 부모별로 묶어서 읽어오기
     * Logic:
     * 1. parentId가 null인 카테고리들을 읽어온다.
     * 2. 각 카테고리의
     */
    public void readCategoriesByParentIsNull(Long categoryId) {

    }

    public void readAll() {

    }

    /*
     * 특정 카테고리를 읽어오되, 자식 카테고리들도 함께 읽어오기
     * Logic:
     * 1. 요청한 Id로 카테고리를 읽어온다.
     * 1-1. 만약 존재하지 않는다면 Optional.Empty 반환
     * 1-2. 존재한다면, 2로 진행
     * 2. 읽어온 Category Entity를 ReadedCategoryDto로 변환
     * 3. 반환
     */
    @Transactional(readOnly = false)
    public Optional<ReadedCategoryDto> readCategoryById(Long id) {
        Optional<Category> result = categoryRepository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        ReadedCategoryDto readedCategoryDto = new ReadedCategoryDto(result.get());
        return Optional.of(readedCategoryDto);
    }

    public void readCategoriesByName() {

    }

    /*
     * 특정 카테고리를 수정하기
     * Logic:
     * 1. 요청한 Id로 카테고리를 읽어온다.
     * 1-1. 만약 존재하지 않는다면 Optional.Empty 반환
     * 1-2. 존재한다면, 2로 진행
     * 2. 읽어온 Category Entity의 값들을 수정.
     * 3. 반환
     */
    public void updateCategory(CategoryForUpdateDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getId());

        if(optionalCategory.isEmpty()) {

        }else {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());

            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentId());
//            if(optionalParentCategory.isEmpty()) {
//
//            }else {
//
//            }
        }
    }

    public void deleteCategory() {

    }

    public void deleteCategories() {

    }

}
