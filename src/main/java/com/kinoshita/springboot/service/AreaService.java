package com.kinoshita.springboot.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.kinoshita.springboot.entity.Area;

@Service
public class AreaService {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 都道府県のみのデータが入ったAreaを取得するメソッド
	 * distinct=trueにより重複行を削除している
	 * @return
	 */
	public List<Area> getState() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Area> query = builder.createQuery(Area.class);
		Root<Area> root = query.from(Area.class);
		query.select(root).distinct(true);
		
		List<Area> list = null;
		list = (List<Area>)entityManager.createQuery(query).getResultList();
		return list;
	}
}
