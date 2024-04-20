package com.hungvu.webgym.service.impl;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.model.User;
import com.hungvu.webgym.repository.AddressRepository;
import com.hungvu.webgym.repository.UserRepository;
import com.hungvu.webgym.request.AddressRequest;
import com.hungvu.webgym.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Address> ListAddressOfUser(String email) {
        List<Address> addresses = addressRepository.findByUserEmail(email);
        if (addresses.isEmpty()) {
            throw new IllegalArgumentException("Danh sách địa chỉ trống");
        }
        return addresses;
    }

    @Override
    public Address addAddress(AddressRequest addressRequest, String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy user");
        }
        User user = userOptional.get();
        List<Address> addresses = addressRepository.findByUserEmail(email);
        boolean isDefault = addresses.isEmpty();

        Address address = new Address();
        address.setFullName(addressRequest.getFullName());
        address.setPhone(addressRequest.getPhone());
        address.setStreetAddress(addressRequest.getStreetAddress());
        address.setCommune(addressRequest.getCommune());
        address.setDistrict(addressRequest.getDistrict());
        address.setCity(addressRequest.getCity());
        address.setDefault(isDefault);

        address.setUser(user);
        addressRepository.save(address);

        return address;
    }

    @Override
    public Address updateAddress(Long addressId, AddressRequest req) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalStateException("Địa chỉ không tồn tại"));
        existingAddress.setFullName(req.getFullName());
        existingAddress.setPhone(req.getPhone());
        existingAddress.setStreetAddress(req.getStreetAddress());
        existingAddress.setCommune(req.getCommune());
        existingAddress.setDistrict(req.getDistrict());
        existingAddress.setCity(req.getCity());

        addressRepository.save(existingAddress);

        return existingAddress;
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public Address getDefaultAddress(String email) {
         return addressRepository.findDefaultAddressByEmail(email);
    }
}
