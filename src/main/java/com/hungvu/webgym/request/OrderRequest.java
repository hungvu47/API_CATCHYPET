package com.hungvu.webgym.request;

import com.hungvu.webgym.model.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private String note;
    private String paymentMethod;

    private Long addressId;
}
