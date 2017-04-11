package com.kinoshita.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

	/**
	 * 入金検索
	 * @return
	 */
	@RequestMapping("/payment/search")
	public ModelAndView paymentSearch(ModelAndView mav) {
		mav.setViewName("/payment/payment_list");
		return mav;
	}
}
