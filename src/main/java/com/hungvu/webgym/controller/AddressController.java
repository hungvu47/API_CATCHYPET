package com.hungvu.webgym.controller;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.request.AddressRequest;
import com.hungvu.webgym.service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;

    @GetMapping("/addressOfUser")
    public ResponseEntity<List<Address>> getListAddressOfUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            List<Address> addresses = addressService.ListAddressOfUser(email);
            return ResponseEntity.ok(addresses);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addAddressUser")
    public ResponseEntity<?> addAddressUser(@RequestBody AddressRequest addressRequest,
                                            Authentication authentication) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                String email = ((UserDetails) authentication.getPrincipal()).getUsername();
                Address addressed = addressService.addAddress(addressRequest,email);
            return ResponseEntity.ok(addressed);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/updateAddressUser/{addressId}")
    public ResponseEntity<?> updateAddressUser(@RequestBody AddressRequest addressRequest,
                                               @PathVariable Long addressId){
        try {
            Address updateAddress = addressService.updateAddress(addressId, addressRequest);
            return ResponseEntity.ok(updateAddress);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteAddress/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Xóa địa chỉ thành công");
    }

    @GetMapping("/defaultAddress")
    public Address getDefaultAddress(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            Address addresses = addressService.getDefaultAddress(email);
            return addresses;
        }
        return null;
    }
}
