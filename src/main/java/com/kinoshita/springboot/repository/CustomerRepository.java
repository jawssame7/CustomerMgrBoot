package com.kinoshita.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
