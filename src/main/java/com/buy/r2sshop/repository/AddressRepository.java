package com.buy.r2sshop.repository;

import com.buy.r2sshop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    //

    List<Address> findByUserId(Integer userId);
}
