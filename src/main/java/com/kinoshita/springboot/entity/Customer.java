package com.kinoshita.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", length = 40)
	private String name;
	
	@Column(name = "kana", length = 40)
	private String kana;
	
	@Column(name = "postal_code", length = 7)
	private String postal_code;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2", length = 100)
	private String address2;
	
	@Column(name = "tel", length = 14)
	private String tel;
	
	@Column(name = "fax", length = 14)
	private String fax;
	
	@Column(name = "tax_type")
	private Integer tax_type;
	
	@Column(name = "rounding_type")
	private Integer rounding_type;
	
	@Column(name = "closing_day")
	private Integer closing_day;

	@Column(name = "created")
	private Date created;
	
	@Column(name = "updated")
	private Date updated;
	
	@Column(name = "deleted")
	private Date deleted;

	
	/**
	 * getter and setter
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public Integer getTax_type() {
		return tax_type;
	}

	public void setTax_type(Integer tax_type) {
		this.tax_type = tax_type;
	}

	public Integer getRounding_type() {
		return rounding_type;
	}

	public void setRounding_type(Integer rounding_type) {
		this.rounding_type = rounding_type;
	}

	public Integer getClosing_day() {
		return closing_day;
	}

	public void setClosing_day(Integer closing_day) {
		this.closing_day = closing_day;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
}
