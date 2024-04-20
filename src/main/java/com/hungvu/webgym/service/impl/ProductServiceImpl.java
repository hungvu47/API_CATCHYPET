package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.Category;
import com.hungvu.webgym.model.Product;
import com.hungvu.webgym.repository.CategoryRepository;
import com.hungvu.webgym.repository.ProductRepository;
import com.hungvu.webgym.request.ProductRequest;
import com.hungvu.webgym.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        Product newProduct = new Product();

        newProduct.setProductName(request.getProductName());
        newProduct.setPrice(request.getPrice());
        newProduct.setDescription(request.getDescription());
        newProduct.setStockQuantity(request.getStockQuantity());
        newProduct.setProductImage(request.getProductImage());

        newProduct.setCategory(category);

        productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(Long productId, ProductRequest request) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy danh mục"));

        existingProduct.setProductName(request.getProductName());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setStockQuantity(request.getStockQuantity());
        existingProduct.setProductImage(request.getProductImage());
        existingProduct.setCategory(category);

        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
