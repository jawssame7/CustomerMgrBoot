package com.kinoshita.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kinoshita.springboot.dto.CustomerDto;
import com.kinoshita.springboot.dto.CustomerSearchConditions;
import com.kinoshita.springboot.entity.Customer;
import com.kinoshita.springboot.repository.AreaRepository;
import com.kinoshita.springboot.repository.CustomerRepository;
import com.kinoshita.springboot.service.AreaService;
import com.kinoshita.springboot.service.CustomerService;
import com.kinoshita.springboot.utility.PaginationUtility;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AreaRepository areaRepository;
	@Autowired
	AreaService areaService;


	PaginationUtility pagination;
	
	/**
	 * ログイン後顧客検索ページへ
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/customer/search/page=1";
	}
	
	/**
	 * 顧客検索ページ初期表示
	 * @param mav
	 * @return
	 */
	@RequestMapping("/customer/search/page={pagenumber}")
	public ModelAndView searchTop(@PathVariable Integer pagenumber, ModelAndView mav) {
		// customer_list.htmlを適用
		mav.setViewName("/customer/customer_list");
		
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", this.getStateList());
		
		// 検索条件null
		CustomerSearchConditions condition = null;
		// 顧客全取得
		Page<Customer> page = customerService.findCustomers(condition, pagenumber);
		List<CustomerDto> customerList = this.getSearchResult(page);
		
		// 顧客リストを反映
		mav.addObject("customer_list", customerList);
		
		// ページネーションを反映
		mav.addObject("now_page", pagenumber);
		mav.addObject("last_page", page.getTotalPages() == 0 ? 1 : page.getTotalElements());
		
		return mav;
	}
	
	/**
	 * 顧客検索ページ「検索」押下時
	 * @param condition
	 * @param mav
	 * @return
	 */
	@RequestMapping(value = "/customer/search/page={pagenumber}", method = RequestMethod.GET)
	public ModelAndView searchResult(@ModelAttribute CustomerSearchConditions condition, @PathVariable Integer pagenumber, ModelAndView mav) {
		// customer_list.htmlを適用
		mav.setViewName("/customer/customer_list");
		
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", this.getStateList());
		
		// 検索条件を反映
		mav.addObject("condition", condition);
		
		// 顧客全取得
		Page<Customer> page = customerService.findCustomers(condition, pagenumber);
		List<CustomerDto> customerList = this.getSearchResult(page);
		
		// 顧客リストを反映
		mav.addObject("customer_list", customerList);
		
		// ページネーションを反映
		mav.addObject("now_page", pagenumber);
		mav.addObject("last_page", page.getTotalPages() == 0 ? 1 : page.getTotalPages());
		
		return mav;
	}
	
	/**
	 * 名称サジェスト
	 * @param nameSearchData
	 * @return
	 */
	@RequestMapping(value = "/name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> nameSuggest(@RequestParam String nameSearchData) {
		
		System.out.println("aaa");
		List<String> nameList = customerRepository.findByNameLikeLimit10(nameSearchData);
		return nameList;
	}
	
	/**
	 * かなサジェスト
	 * @param kanaSearchData
	 * @return
	 */
	@RequestMapping(value = "/kana/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> kanaSuggest(@RequestParam String kanaSearchData) {
		
		List<String> kanaList = customerRepository.findByKanaLikeLimit10(kanaSearchData);
		return kanaList;
	}
	
	
	/** 
	 * 顧客登録
	 * @return
	 */
	@RequestMapping("/customer/entry")
	public ModelAndView create(ModelAndView mav) {
		// entry.htmlを適用
		mav.setViewName("/customer/entry");
		
		// repositoryから都道府県を取得
		List<String> stateList =  areaRepository.getStates();
		
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", stateList);
		
		return mav;
	}
	
	/**
	 * 顧客編集
	 * @return
	 */
	@RequestMapping("/customer/edit")
	public ModelAndView update(ModelAndView mav) {
		// edit.htmlを適用
		mav.setViewName("/customer/edit");
		return mav;
	}
	
	/**
	 * 顧客（登録・編集）確認
	 * @return
	 */
	@RequestMapping(value = "/customer/check", method = RequestMethod.POST)
	public ModelAndView check(@ModelAttribute("customer") Customer customer, ModelAndView mav) {
		// check.htmlを適用
		mav.setViewName("/customer/check");
		return mav;
	}
	
	/**
	 * 顧客詳細
	 * @return
	 */
	@RequestMapping("/customer/detail/id={id}")
	public ModelAndView detail(ModelAndView mav, @PathVariable Long id) {
		// detail.htmlを適用
		mav.setViewName("/customer/detail");
		
		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.findOne(id);
		
		CustomerDto customerDto = new CustomerDto(customer); 
		
		// ビューに顧客データを反映
		mav.addObject("customer", customerDto);
		return mav;
	}
	
	
	/**
	 * 顧客検索
	 * @param condition
	 * @return
	 */
	private List<CustomerDto> getSearchResult(Page<Customer> page) {
		
		List<CustomerDto> convertCustomerList = new ArrayList<CustomerDto>();
		for (Customer customer: page) {
			CustomerDto customerDto = new CustomerDto(customer);
			
			// 文字列変換したデータをリストに格納
			convertCustomerList.add(customerDto);
		}
		return convertCustomerList;
	}
	
	/**
	 * 都道府県一覧取得
	 * @return
	 */
	private List<String> getStateList() {
		return areaRepository.getStates();
	}
}
