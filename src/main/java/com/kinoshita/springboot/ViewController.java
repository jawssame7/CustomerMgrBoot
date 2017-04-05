package com.kinoshita.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kinoshita.springboot.repository.AreaRepository;
import com.kinoshita.springboot.repository.CustomerRepository;

@Controller
public class ViewController {

	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	/**
	 * ログイン後初期表示
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/customer/customer_list");
		
		// repositoryで全取得 落ちないけど読み込まれない
//		Iterable<Area> stateList =  areaRepository.findAll();
//		mav.addObject("area_list", stateList);
		
		// repositoryで単体取得 表示された
//		Area area = areaRepository.findOne(1L);
//		mav.addObject("area", area);
		
		// repositoryでdistinct 落ちる
//		List<String> stateList =  areaRepository.findDistinctStateAll();
//		mav.addObject("area_list", stateList);
		
		// serviceでdistinct　表示されたけど重複まで全部表示された すごくおもい
		Iterable<Area> stateList = areaService.getState();
		mav.addObject("area_list", stateList);
		
		Iterable<Customer> customerList = customerRepository.findAll();
		mav.addObject("customer_list", customerList);
		
		return mav;
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
