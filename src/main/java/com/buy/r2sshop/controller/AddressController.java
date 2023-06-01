package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.Address;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.service.AddressService;
import com.buy.r2sshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AddressController {
    private final AddressService addressService;

    private final UserService userService;

    @Autowired
    public AddressController(AddressService addressService, UserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/addresses")
    public ResponseEntity<List<Address>> getAllAddressesForUser(@PathVariable Integer userId) {
        List<Address> addresses = addressService.getAllAddressesByUserId(userId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("/{userId}/addresses")
    public ResponseEntity<Address> addAddressForUser(@PathVariable Integer userId, @RequestBody Address address) {
        Address addedAddress = addressService.addAddress(userId, address);
        return new ResponseEntity<>(addedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<Address> updateAddressForUser(
            @PathVariable Integer userId, @PathVariable Integer addressId, @RequestBody Address updatedAddress) {
        Address updated = addressService.updateAddress(userId, addressId, updatedAddress);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<Void> deleteAddressForUser(@PathVariable Integer userId, @PathVariable Integer addressId) {
        addressService.deleteAddress(userId, addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
