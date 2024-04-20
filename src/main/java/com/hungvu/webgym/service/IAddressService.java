package com.hungvu.webgym.service;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.request.AddressRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public interface IAddressService {
    List<Address> ListAddressOfUser(String email);

    Address addAddress(AddressRequest addressRequest, String email);

    Address updateAddress(Long addressId, AddressRequest req);

    void deleteAddress(Long addressId);

    Address getDefaultAddress(String email);
}
