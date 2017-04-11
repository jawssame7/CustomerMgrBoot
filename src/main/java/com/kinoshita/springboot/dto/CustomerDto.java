package com.kinoshita.springboot.dto;

import java.text.SimpleDateFormat;

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
	public void convertCustomer(Customer origin) {
		// customerを格納
		customer = origin;
		
		// 郵便番号の先頭から3番目にハイフンを挿入したものを格納
		if (customer.getPostal_code() == null) {
			postal_code = null;
		} else {
			StringBuffer postal_codeSb = new StringBuffer(customer.getPostal_code());
			postal_codeSb.insert(3, "-");
			postal_code = new String(postal_codeSb);
		}
		
		// 住所1がnullの場合空白（""）を格納
		if (customer.getAddress1() == null) {
			address1 = "";
		} else {
			address1 = customer.getAddress1();
		}
		
		// 住所2がnullの場合空白（""）を格納
		if (customer.getAddress2() == null) {
			address2 = "";
		} else {
			address2 = customer.getAddress2();
		}
		
		// 税区分番号に対応した税区分の種類を格納
		StringBuffer tax_typeSb = new StringBuffer(customer.getTax_type());
		switch (customer.getTax_type()) {
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
		
		// 丸め方法番号に対応した税区分の種類を格納
		StringBuffer rounding_typeSb = new StringBuffer(customer.getRounding_type());
		switch (customer.getRounding_type()) {
			case 1:
				rounding_typeSb.append("明細行");
				break;
			case 2:
				rounding_typeSb.append("請求書");
				break;
		}
		rounding_type = new String(rounding_typeSb);
		
		// 登録日時をyyyy年MM月dd日 hh時mm分のフォーマットにして格納
		if (customer.getCreated() == null) {
			created = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分");
			created = sdf.format(customer.getCreated());
		}
		
		// 更新日時をyyyy年MM月dd日 hh時mm分のフォーマットにして格納
		if (customer.getUpdated() == null) {
			updated = null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分");
			updated = sdf.format(customer.getUpdated());
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
