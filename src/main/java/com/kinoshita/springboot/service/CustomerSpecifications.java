package com.kinoshita.springboot.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.kinoshita.springboot.entity.Customer;

public class CustomerSpecifications {

	/**
	 * 名称　部分一致検索
	 * @param name
	 * @return
	 */
	public static Specification<Customer> nameContains(String name) {
		return StringUtils.isEmpty(name) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("name"), "%" + name + "%");
			}
		};
	}
	
	/**
	 * かな　部分一致検索
	 * @param kana
	 * @return
	 */
	public static Specification<Customer> kanaContains(String kana) {
		return StringUtils.isEmpty(kana) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("kana"), "%" + kana + "%");
			}
		};
	}
	
	/**
	 * 郵便番号　完全一致検索
	 * @param postalCode
	 * @return
	 */
	public static Specification<Customer> postalCodeContains(String postalCode) {
		return StringUtils.isEmpty(postalCode) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("postalCode"), postalCode);
			}
		};
	}
	
	/**
	 * 都道府県　完全一致検索
	 * @param address1
	 * @return
	 */
	public static Specification<Customer> address1Contains(String address1) {
		return StringUtils.isEmpty(address1) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("address1"), address1);
			}
		};
	}
	
	/**
	 * 市区町村以下　部分一致検索
	 * @param address2
	 * @return
	 */
	public static Specification<Customer> address2Contains(String address2) {
		return StringUtils.isEmpty(address2) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get("address2"), "%" + address2 + "%");
			}
		};
	}
	
	/**
	 * 電話番号　完全一致検索
	 * @param tel
	 * @return
	 */
	public static Specification<Customer> telContains(String tel) {
		return StringUtils.isEmpty(tel) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("tel"), tel);
			}
		};
	}
	
	/**
	 * FAX番号　完全一致検索
	 * @param fax
	 * @return
	 */
	public static Specification<Customer> faxContains(String fax) {
		return StringUtils.isEmpty(fax) ? null : new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("fax"), fax);
			}
		};
	}
	
	/**
	 * 未削除（deleted == null）のデータ検索
	 * @return
	 */
	public static Specification<Customer> deletedIsNull() {
		return new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isNull(root.get("deleted"));
			}
		};
	}
}
