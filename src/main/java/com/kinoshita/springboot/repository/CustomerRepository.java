package com.kinoshita.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
	
	// 名前サジェスト - 10件
	@Query(value = "SELECT name FROM customer WHERE name LIKE :name% LIMIT 10", nativeQuery = true)
	public List<String> findByNameLikeLimit10(@Param("name") String name);
	
	// かなサジェスト - 10件
	@Query(value = "SELECT kana FROM customer WHERE kana LIKE :kana% LIMIT 10", nativeQuery = true)
	public List<String> findByKanaLikeLimit10(@Param("kana") String kana);
}
