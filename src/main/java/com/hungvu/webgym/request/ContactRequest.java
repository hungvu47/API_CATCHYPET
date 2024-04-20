package com.hungvu.webgym.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ContactRequest {

    private String customerName;

    private String phoneCustomer;

    private String emailCustomer;

    private String notes;
}
