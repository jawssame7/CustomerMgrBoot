package com.kinoshita.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.kinoshita.springboot.annotation.NullPermissionNum;
import com.kinoshita.springboot.annotation.PostalCode;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name", length = 40)
	@NotEmpty
	private String name;
	
	@Column(name = "kana", length = 40)
	@NotEmpty
	private String kana;
	
	@Column(name = "postal_code")
	@PostalCode
	private String postalCode;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2", length = 100)
	private String address2;
	
	@Column(name = "tel", length = 14)
	@NullPermissionNum(min = 10, max = 14)
	private String tel;
	
	@Column(name = "fax", length = 14)
	@NullPermissionNum(min = 10, max = 14)
	private String fax;
	
	@Column(name = "tax_type")
	private Integer taxType;
	
	@Column(name = "rounding_type")
	private Integer roundingType;
	
	@Column(name = "closing_day")
	private Integer closingDay;

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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postal_code) {
		this.postalCode = postal_code;
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
	
	public Integer getTaxType() {
		return taxType;
	}

	public void setTaxType(Integer tax_type) {
		this.taxType = tax_type;
	}

	public Integer getRoundingType() {
		return roundingType;
	}

	public void setRoundingType(Integer rounding_type) {
		this.roundingType = rounding_type;
	}

	public Integer getClosingDay() {
		return closingDay;
	}

	public void setClosingDay(Integer closing_day) {
		this.closingDay = closing_day;
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
