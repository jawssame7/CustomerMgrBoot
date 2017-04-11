package com.kinoshita.springboot.utility;

public class PaginationUtility {

	/**
	 * 現在ページからPrevのURLを出す
	 * @param page
	 * @return
	 */
	public String prevUrl(int page) {
		return "/page/" + (page > 1 ? page - 1 : 1);
	}
	
	/**
	 * 現在ページからNextのURLを出す
	 * @param page
	 * @return
	 */
	public String nextUrl(int page) {
		return "/page/" + (page + 1);
	}
}
