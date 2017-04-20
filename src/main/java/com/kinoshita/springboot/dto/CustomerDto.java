package com.kinoshita.springboot.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.kinoshita.springboot.entity.Customer;

/**
 * 顧客データをビュー用に見やすくする
 * @author wizuser
 */
public class CustomerDto {
	
	private Customer customer;
	private String postalCode;
	private String address1;
	private String address2;
	private String taxType;
	private String roundingType;
	private String created;
	private String updated;
	
	
	/**
	 * 全データの変換、格納
	 * @param customer
	 * @return
	 */
	public CustomerDto(Customer origin) {
		// customerを格納
		this.setCustomer(origin);
		
		// 郵便番号の先頭から3番目にハイフンを挿入したものを格納
		this.setPostalCode(origin.getPostalCode());
		
		// 住所1がnullの場合空白（""）を格納
		this.setAddress1(origin.getAddress1());
		
		// 住所2がnullの場合空白（""）を格納
		this.setAddress2(origin.getAddress2());
		
		// 税区分番号に対応した税区分の種類を格納
		this.setTaxType(origin.getTaxType());
		
		// 丸め方法番号に対応した税区分の種類を格納
		this.setRoundingType(origin.getRoundingType());
		
		// 登録日時をyyyy年MM月dd日 hh時mm分のフォーマットにして格納
		this.setCreated(origin.getCreated());
		
		// 更新日時をyyyy年MM月dd日 hh時mm分のフォーマットにして格納
		this.setUpdated(origin.getUpdated());
	}
	
	/**
	 * customerに顧客オリジナルデータを格納
	 * @param originCustomer
	 */
	public void setCustomer(Customer originCustomer) {
		customer = originCustomer;
	}
	
	/**
	 * 郵便番号の先頭から3番目にハイフンを挿入したものを格納
	 * @param originPostal_code
	 */
	public void setPostalCode(String originPostal_code) {
		
		if (originPostal_code == null || originPostal_code.length() == 0) {
			postalCode = null;
		} else {
			StringBuffer postal_codeSb = new StringBuffer(originPostal_code);
			postal_codeSb.insert(3, "-");
			postalCode = new String(postal_codeSb);
		}
	}
	
	
	
	public void setAddress1(String originAddress1) {
		if (originAddress1 == null || originAddress1 == "") {
			address1 = "";
		} else {
			address1 = originAddress1;
		}
	}
	
	public void setAddress2(String originAddress2) {
		if (originAddress2 == null || originAddress2 == "") {
			address2 = "";
		} else {
			address2 = originAddress2;
		}
	}
	
	public void setTaxType(Integer originTax_type) {
		StringBuffer tax_typeSb = new StringBuffer(originTax_type);
		switch (originTax_type) {
			case 1:
				tax_typeSb.append("切捨て");
				break;
			case 2:
				tax_typeSb.append("四捨五入");
				break;
			case 3:
				tax_typeSb.append("切上げ");
				break;
		}
		taxType = new String(tax_typeSb);
	}
	
	public void setRoundingType(Integer originRounding_type) {
		StringBuffer rounding_typeSb = new StringBuffer(originRounding_type);
		switch (originRounding_type) {
			case 1:
				rounding_typeSb.append("明細行");
				break;
			case 2:
				rounding_typeSb.append("請求書");
				break;
		}
		roundingType = new String(rounding_typeSb);
	}
	
	public void setCreated(Date originCreated) {
		if (originCreated == null) {
			created = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 kk時mm分");
			created = sdf.format(originCreated);
		}
	}
	
	
	public void setUpdated(Date originUpdated) {
		if (originUpdated == null) {
			updated = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 kk時mm分");
			updated = sdf.format(originUpdated);
		}
	}
	
	/**
	 * getter
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getTaxType() {
		return taxType;
	}

	public String getRoundingType() {
		return roundingType;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}
}
