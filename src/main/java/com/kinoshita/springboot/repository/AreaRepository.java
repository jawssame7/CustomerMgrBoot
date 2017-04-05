package com.kinoshita.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinoshita.springboot.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

	
	// 都道府県だけを取り出す
	// 落ちる
//	public List<String> findDistinctStateAll();
}
