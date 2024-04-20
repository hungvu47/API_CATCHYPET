package com.hungvu.webgym.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemRequest {
    private Long cartItemId;
    private Long productId;
    private int quantity;
}
