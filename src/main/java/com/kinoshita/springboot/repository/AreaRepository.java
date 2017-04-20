package com.kinoshita.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.entity.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
	
	// 郵便番号から地域を検索
	public Area findByPostalCode(String postalCode);
	
	// 都道府県だけを取り出す
	@Query(value = "SELECT DISTINCT state FROM area", nativeQuery = true)
	public List<String> getStates();
}
