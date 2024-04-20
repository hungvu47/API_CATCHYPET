package com.hungvu.webgym.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductRequest {

    private String productName;
    private String description;
    private double price;
    private int stockQuantity;
    private String productImage;
    private Long categoryId;
}
