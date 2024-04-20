package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Product;
import com.hungvu.webgym.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    List<Product> getAllProducts();

    Product addProduct(ProductRequest request);

    Product updateProduct(Long productId, ProductRequest request);

    void deleteProduct(Long productId);

    List<Product> searchProducts(String keyword);

    List<Product> getProductsByCategoryId(Long categoryId);

    Product getProductById(Long productId);

}
