package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Address;
import com.buy.r2sshop.entity.User;

import java.util.List;

public interface IAddressService {
    List<Address> getAllAddressesByUserId(Integer userId);

    Address addAddress(Integer userId, Address address);

    Address updateAddress(Integer userId, Integer addressId, Address updatedAddress);

    void deleteAddress(Integer userId, Integer addressId);


}
