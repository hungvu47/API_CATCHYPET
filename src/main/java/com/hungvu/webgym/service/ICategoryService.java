package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Category;
import com.hungvu.webgym.request.CategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {

    public List<Category> getAllCategories();

    public Category addCategory(CategoryRequest categoryRequest);

    public Category updateCategory(Long categoryId, CategoryRequest request);

    public void removeCategory(Long categoryId);

    public void getProductByCategory();

    public boolean isCategoryNameExists(String categoryName);
}
