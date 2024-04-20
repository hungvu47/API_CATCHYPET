package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.Category;
import com.hungvu.webgym.repository.CategoryRepository;
import com.hungvu.webgym.request.CategoryRequest;
import com.hungvu.webgym.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(CategoryRequest request) {

        Category category = new Category();
        category.setCategoryId(request.getCategoryId());
        category.setCategoryName(request.getCategoryName());
        category.setCategoryImage(request.getCategoryImage());
        category.setCategoryDes(request.getCategoryDes());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryRequest request) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            throw new IllegalStateException("Không tìm thấy danh mục");
        }

        Category category = optionalCategory.get();
        String newName = request.getCategoryName();
        if (!newName.equals(category.getCategoryName()) && categoryRepository.existsByCategoryName(newName)) {
            throw new IllegalArgumentException("Tên danh mục đã tồn tại");
        }

        category.setCategoryName(newName);
        category.setCategoryDes(request.getCategoryDes());
        category.setCategoryImage(request.getCategoryImage());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public void removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void getProductByCategory() {

    }

    @Override
    public boolean isCategoryNameExists(String categoryName) {
        return categoryRepository.existsByCategoryName(categoryName);
    }
}
