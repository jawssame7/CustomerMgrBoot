package com.kinoshita.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.kinoshita.springboot.repository.CustomerRepository;

@Service
public class CustomerService {

	/**
	 * 1ページに表示するエンティティ数
	 */
	private static final int PAGE_SIZE = 20;
	
	@Autowired
	CustomerRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * ページ番号
	 * @param pageNumber
	 * @return
	 */
	public Page<Customer> getCustomerInPage(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber - 1, PAGE_SIZE);
		return repository.findAll(pageRequest);
	}
	
	/**
	 * 顧客検索　動的クエリ
	 * @param name
	 * @param kana
	 * @param postal_code
	 * @param address1
	 * @param address2
	 * @param tel
	 * @param fax
	 * @return
	 */
	public List<Customer> findCustomers(String name, String kana, String postal_code, 
			String address1, String address2, String tel, String fax) {
		List<Customer> results = repository.findAll(Specifications
				.where(CustomerSpecifications.nameContains(name))
				.and(CustomerSpecifications.kanaContains(kana))
				.and(CustomerSpecifications.postal_codeContains(postal_code))
				.and(CustomerSpecifications.address1Contains(address1))
				.and(CustomerSpecifications.address2Contains(address2))
				.and(CustomerSpecifications.telContains(tel))
				.and(CustomerSpecifications.faxContains(fax)));
		return results;
	}
	
//	public List<Customer> searchCustomers(String name, String kana, String postal_code, 
//			String address1, String address2, String tel, String fax) {
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
//		Root<Customer> root = query.from(Customer.class);
//		
//		query.select(root);
//		
//		List<Predicate> creteria = new ArrayList<Predicate>();
//		
//		if (name != null) {
//			creteria.add(builder.equal(root.<String>get("name"), name));
//		}
//		
//		if (kana != null) {
//			creteria.add(builder.like(root.<String>get("kana"), "%" + kana + "%"));
//		}
//		
//		if (postal_code != null) {
//			creteria.add(builder.equal(root.<String>get("postal_code"), postal_code));
//		}
//		
//		if (address1 != null) {
//			creteria.add(builder.equal(root.<String>get("address1"), address1));
//		}
//		
//		if (address2 != null) {
//			creteria.add(builder.like(root.<String>get("address2"), "%" + address2 + "%"));
//		}
//		
//		if (tel != null) {
//			creteria.add(builder.equal(root.<String>get("tel"), tel));
//		}
//		
//		if (fax != null) {
//			creteria.add(builder.equal(root.<String>get("fax"), fax));
//		}
//		
//		creteria.add(builder.equal(root.<String>get("deleted"), null));
//		
//		return entityManager.createQuery(query).getResultList();
//	}
}
