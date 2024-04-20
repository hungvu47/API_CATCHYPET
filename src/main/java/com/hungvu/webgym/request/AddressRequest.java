package com.hungvu.webgym.request;

import lombok.Data;

@Data
public class AddressRequest {
    String fullName;
    String phone;
    String streetAddress;
    String commune;
    String district;
    String city;
}
