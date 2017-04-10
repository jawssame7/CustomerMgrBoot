package com.kinoshita.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	// deleted == nullのデータ全取得
	public List<Customer> findByDeletedIsNull();
	
	// 名前サジェスト - 10件
	@Query(value = "SELECT name FROM customer WHERE name LIKE %:name% LIMIT 10", nativeQuery = true)
	public List<String> suggestName(@Param("name") String name);
}
