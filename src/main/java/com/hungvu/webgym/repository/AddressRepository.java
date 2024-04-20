package com.hungvu.webgym.repository;

import com.hungvu.webgym.model.Address;
import com.hungvu.webgym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.email =:email")
    List<Address> findByUserEmail(String email);

    @Query("SELECT a FROM Address a WHERE a.user.email =:email AND a.isDefault = true")
    Address findDefaultAddressByEmail(String email);
}
