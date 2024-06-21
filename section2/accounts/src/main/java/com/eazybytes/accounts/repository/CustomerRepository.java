package com.eazybytes.accounts.repository;

import com.eazybytes.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    This method is a derived method which is provided by spring data jpa, and it follows
    a certain naming convention.
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);


}
