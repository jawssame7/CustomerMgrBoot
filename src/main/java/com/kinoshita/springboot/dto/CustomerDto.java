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
	private String postal_code;
	private String address1;
	private String address2;
	private String tax_type;
	private String rounding_type;
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
		this.setPostal_code(origin.getPostal_code());
		
		// 住所1がnullの場合空白（""）を格納
		this.setAddress1(origin.getAddress1());
		
		// 住所2がnullの場合空白（""）を格納
		this.setAddress2(origin.getAddress2());
		
		// 税区分番号に対応した税区分の種類を格納
		this.setTax_type(origin.getTax_type());
		
		// 丸め方法番号に対応した税区分の種類を格納
		this.setRounding_type(origin.getRounding_type());
		
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
	public void setPostal_code(String originPostal_code) {
		if (originPostal_code == null) {
			postal_code = null;
		} else {
			StringBuffer postal_codeSb = new StringBuffer(originPostal_code);
			postal_codeSb.insert(3, "-");
			postal_code = new String(postal_codeSb);
		}
	}
	
	
	
	public void setAddress1(String originAddress1) {
		if (originAddress1 == null) {
			address1 = "";
		} else {
			address1 = originAddress1;
		}
	}
	
	public void setAddress2(String originAddress2) {
		if (originAddress2 == null) {
			address2 = "";
		} else {
			address2 = originAddress2;
		}
	}
	
	public void setTax_type(Integer originTax_type) {
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
		tax_type = new String(tax_typeSb);
	}
	
	public void setRounding_type(Integer originRounding_type) {
		StringBuffer rounding_typeSb = new StringBuffer(originRounding_type);
		switch (originRounding_type) {
			case 1:
				rounding_typeSb.append("明細行");
				break;
			case 2:
				rounding_typeSb.append("請求書");
				break;
		}
		rounding_type = new String(rounding_typeSb);
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
	
	public String getPostal_code() {
		return postal_code;
	}
	
	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getTax_type() {
		return tax_type;
	}

	public String getRounding_type() {
		return rounding_type;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}
}
