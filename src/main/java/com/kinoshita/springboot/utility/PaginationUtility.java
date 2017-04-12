package com.kinoshita.springboot.utility;

import com.kinoshita.springboot.service.CustomerService;

public class PaginationUtility {
	
	/**
	 * 現在ページからPrevのページ数を出す
	 * @param page
	 * @return
	 */
	public String prev(int page) {
		return "page=" + (page > 1 ? page - 1 : 1);
	}
	
	/**
	 * 現在ページからNextのページ数を出す
	 * @param page
	 * @return
	 */
	public String next(int page) {
		return "page=" + (page + 1);
	}
	
	/**
	 * 最大ページ数を出す
	 * @param page
	 * @param size
	 * @return
	 */
	public String last(int size) {
		return "page=" + (size / CustomerService.getPAGE_SIZE() + 1);
	}
}
