package com.kinoshita.springboot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringControl {
	
	private Customer customer;
	private String postal_code;
	private String address1;
	private String address2;
	private String tax_type;
	private String rounding_type;
	private String created;
	private String updated;
	
	/**
	 * 郵便番号　先頭から三番目にハイフンを挿入して格納
	 * @param postal_code
	 */
	public void postal_codeConvert (String originPostal_code) {
		StringBuffer postal_codeSb = new StringBuffer(originPostal_code);
		postal_codeSb.insert(3, "-");
		postal_code = new String(postal_codeSb);
	}
	
	/**
	 * 住所1　nullの場合文字列なし（""）に変更
	 * @param originAddress1
	 */
	public void address1Convert (String originAddress1) {
		if (originAddress1 == null) {
			originAddress1 = "";
		}
		address1 = originAddress1;
	}
	
	/**
	 * 住所2　nullの場合文字列なし（""）に変更
	 * @param originAddress1
	 */
	public void address2Convert (String originAddress2) {
		if (originAddress2 == null) {
			originAddress2 = "";
		}
		address2 = originAddress2;
	}
	
	/**
	 * 税区分　番号に対応した税区分の種類を格納
	 * @param tax_type
	 */
	public void tax_typeConvert (int originTax_type) {
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
	
	/**
	 * 丸め方法　番号に対応した丸め方法の種類を格納
	 * @param rounding_type
	 */
	public void rounding_typeConvert (int originRounding_type) {
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
	
	/**
	 * 登録日時　yyyy年MM月dd日 hh時mm分のフォーマットに合わせ格納
	 * @param date
	 */
	public void createdConvert (Date originCreated) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分");
		created = sdf.format(originCreated);
	}
	
	/**
	 * 更新日時　yyyy年MM月dd日 hh時mm分のフォーマットに合わせ格納
	 * @param date
	 */
	public void updatedConvert (Date originCreated) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分");
		updated = sdf.format(originCreated);
	}

	
	/**
	 * customer setter
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
