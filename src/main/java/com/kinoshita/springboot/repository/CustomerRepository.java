package com.kinoshita.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	// 都道府県だけを取り出す
	@Query(value = "SELECT name FROM customer", nativeQuery = true)
	public List<String> getNames();
}
