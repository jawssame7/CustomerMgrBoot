package com.kinoshita.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SlipController {

	/**
	 * 伝票検索
	 * @return
	 */
	@RequestMapping("/slip/search")
	public ModelAndView slipSearch(ModelAndView mav) {
		return mav;
	}
}
