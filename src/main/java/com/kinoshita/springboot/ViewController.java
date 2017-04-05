package com.kinoshita.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

	/**
	 * ログイン後初期表示
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("/customer/customer_list");
	}
	
	/**
	 * 顧客検索
	 * @return
	 */
	@RequestMapping("/customer/search")
	public ModelAndView customerSearch() {
		return new ModelAndView("/customer/customer_list");
	}
	
	/**
	 * 顧客登録
	 * @return
	 */
	@RequestMapping("/customer/entry")
	public ModelAndView customerCreate() {
		return new ModelAndView("/customer/entry");
	}
	
	/**
	 * 顧客編集
	 * @return
	 */
	@RequestMapping("/customer/edit")
	public ModelAndView customerUpdate() {
		return new ModelAndView("/customer/edit");
	}
	
	/**
	 * 顧客登録・編集確認
	 * @return
	 */
	@RequestMapping("/customer/check")
	public ModelAndView customerCheck() {
		return new ModelAndView("/customer/check");
	}
	
	/**
	 * 顧客詳細
	 * @return
	 */
	@RequestMapping("/customer/detail")
	public ModelAndView customerDetail() {
		return new ModelAndView("/customer/detail");
	}
	
	/**
	 * 入金検索
	 * @return
	 */
	@RequestMapping("/payment/search")
	public ModelAndView paymentSearch() {
		return new ModelAndView("/payment/payment_list");
	}
	
	/**
	 * 伝票検索
	 * @return
	 */
	@RequestMapping("/slip/search")
	public ModelAndView slipSearch() {
		return new ModelAndView("/slip/slip_list");
	}
}
