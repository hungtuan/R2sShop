package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Address;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.repository.AddressRepository;
import com.buy.r2sshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Address> getAllAddressesByUserId(Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address addAddress(Integer userId, Address address) {
        User user = new User();
        user.setId(userId);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Integer userId, Integer addressId, Address updatedAddress) {
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));

        existingAddress.setAddress_line_1(updatedAddress.getAddress_line_1());
        existingAddress.setAddress_line_2(updatedAddress.getAddress_line_2());
        return addressRepository.save(existingAddress);

    }

    @Override
    public void deleteAddress(Integer userId, Integer addressId) {
        Address existingAddress  = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
        addressRepository.delete(existingAddress);
    }
}
