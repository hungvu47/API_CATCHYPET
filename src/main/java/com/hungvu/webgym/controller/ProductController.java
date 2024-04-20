package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.model.Product;
import com.hungvu.webgym.request.ProductRequest;
import com.hungvu.webgym.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping("/collection")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest request) {
        Product newProduct = productService.addProduct(request);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,
                                                @RequestBody ProductRequest request) {
        try {
            Product updateProduct = productService.updateProduct(productId, request);
            return ResponseEntity.ok(updateProduct);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Xóa thành công");
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> productsByCategory = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(productsByCategory);
    }
}
