package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Category;
import com.hungvu.webgym.request.CategoryRequest;
import com.hungvu.webgym.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("/all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest request) {
        String categoryName = request.getCategoryName();
        if (categoryService.isCategoryNameExists(categoryName)) {
            return ResponseEntity.badRequest().body("Tên danh mục đã tồn tại");
        }
        Category category = categoryService.addCategory(request);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/remove/{categoryId}")
    public ResponseEntity<?> removeCategory(@PathVariable Long categoryId) {
        categoryService.removeCategory(categoryId);
        return ResponseEntity.ok("Xóa thành công");
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,
                                                 @RequestBody CategoryRequest request) {
        Category updateCategory = categoryService.updateCategory(categoryId, request);
        return ResponseEntity.ok(updateCategory);
    }

}
