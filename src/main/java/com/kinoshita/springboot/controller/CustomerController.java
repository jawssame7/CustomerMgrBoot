package com.kinoshita.springboot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kinoshita.springboot.dto.CustomerDto;
import com.kinoshita.springboot.dto.CustomerSearchConditions;
import com.kinoshita.springboot.entity.Area;
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

	@Autowired
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
	 * 検索ページ初期表示
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
	 * 検索ページ「検索」押下時
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
	 * 名称サジェスト
	 * @param nameSearchData
	 * @return
	 */
	@RequestMapping(value = "/name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> nameSuggest(@RequestParam String nameSearchData) {
		
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
	 * 登録ページ初期表示
	 * @return
	 */
	@RequestMapping(value = "/customer/new/entry")
	public ModelAndView create(@ModelAttribute("customer") Customer customer, ModelAndView mav) {
		
		// entry.htmlを適用
		mav.setViewName("/customer/entry");
		
		// repositoryから都道府県を取得
		List<String> stateList =  areaRepository.getStates();
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", stateList);
		
		// 空の顧客オブジェクトを適用
		mav.addObject("customer", customer);
		
		// 郵便番号エラーチェックをスルー
		mav.addObject("postalError", false);
		
		return mav;
	}
	
	/**
	 * 住所自動入力
	 * @param searchPostal_code
	 * @return
	 */
	@RequestMapping(value = "/getAddress", method = RequestMethod.GET)
	@ResponseBody
	public Area getAddress(@RequestParam String postalCode) {
		
		Area resultArea = areaRepository.findByPostalCode(postalCode);
		return resultArea;
	}
	
	
	
	
	/**
	 * リダイレクト : 確認 or 登録 or 編集ページ
	 * @return
	 */
	@RequestMapping(value = "/customer/redirect/check", method = RequestMethod.POST)
	public ModelAndView check(@ModelAttribute("customer") @Validated Customer customer, BindingResult result, RedirectAttributes attributes) {
		
		ModelAndView mav = new ModelAndView();
		attributes.addFlashAttribute("customer", customer);
		
		String id = null;
		
		// 新規顧客登録の場合
		if (customer.getId() == null) {
			id = "new";
		} else {
			id = customer.getId().toString();
		}
		
		boolean postalError = postalCodeCheck(customer.getPostalCode());
		
		// バリデーションエラーがない場合
		if (!result.hasErrors() && postalError == false) {
			// check.htmlを適用
			mav.setViewName("redirect:/customer/" + id + "/check");

		// バリデーションエラーがある場合
		} else {
			
			// 新規顧客登録の場合
			if (customer.getId() == null) {
				mav.setViewName("redirect:/customer/" + id + "/entry/fix");
				
			// 既存顧客更新の場合
			} else {
				mav.setViewName("redirect:/customer/" + id + "/edit");
			}
			attributes.addFlashAttribute("postalError", postalError);
		}
		attributes.addFlashAttribute("postalError", postalError);
		
		return mav;
	}
	
	/**
	 * 郵便番号チェック
	 * @param postalCode
	 * @return
	 */
	public boolean postalCodeCheck(String postalCode) {
		
		// false判定 : 郵便番号がnullまたは空欄
		if (postalCode == null || postalCode.length() == 0) {
			return false;
		} else {

			Area exist = areaRepository.findByPostalCode(postalCode);
			
			// true判定 : 郵便番号が存在しない
			if (exist == null) {
				return true;
				
			// true判定 : 郵便番号が存在する
			} else {
				return false;
			}
		}
	}
	
	
	/**
	 * fix登録ページ
	 * @return
	 */
	@RequestMapping(value = "/customer/new/entry/fix", method = RequestMethod.GET)
	public ModelAndView redirectCreate(@ModelAttribute("customer") @Validated Customer customer, BindingResult result, ModelAndView mav) {
		
		// entry.htmlを適用
		mav.setViewName("/customer/entry");
		
		// repositoryから都道府県を取得
		List<String> stateList =  areaRepository.getStates();
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", stateList);

		return mav;
	}
	
	
	/**
	 * 確認ページ
	 * @return
	 */
	@RequestMapping(value = "/customer/{id}/check", method = RequestMethod.GET)
	public ModelAndView check(@ModelAttribute("customer") @Validated Customer customer, BindingResult result, @PathVariable String id, @ModelAttribute("postalError") boolean postalError, ModelAndView mav) {
		
		// BindingResultは必要なくても受け取るようにする
		// 参考 : http://qiita.com/orangeeeee/items/334cf4b7042efbce9265
		
		// 登録/編集ページに戻った際住所自動入力がerrorを吐かないようにpostalErrorも受け取っておく
		
		// check.htmlを適用
		mav.setViewName("/customer/check");
		
		return mav;
	}
	
	
	
	
	/**
	 * リダイレクト : 登録or編集の保存
	 * @param customer
	 * @return
	 */
	@RequestMapping(value = "/customer/save", method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public String save(@ModelAttribute("customer") Customer customer, ModelAndView mav, RedirectAttributes attributes) {
		
		if (customer.getId() == null) {
			customer.setCreated(new Date());
		}
		customer.setUpdated(new Date());
		customerRepository.saveAndFlush(customer);
		
		attributes.addAttribute("saved", true);
		
		return "redirect:/customer/" + customer.getId() + "/detail";
	}
	
	/**
	 * （登録・編集）保存後顧客詳細
	 * @return
	 */
	@RequestMapping(value="/customer/{id}/detail/saved={saved}", method = RequestMethod.GET)
	public ModelAndView saveLaterDetail(@PathVariable Long id, @RequestParam("saved") boolean saved, ModelAndView mav) {
		
		// detail.htmlを適用
		mav.setViewName("/customer/detail");
		
		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.findOne(id);
		
		// CustomerDtoオブジェクトに移す
		CustomerDto customerDto = new CustomerDto(customer); 
		
		// ビューに顧客データを反映
		mav.addObject("customer", customerDto);
		return mav;
	}
	
	/**
	 * 顧客詳細
	 * @return
	 */
	@RequestMapping("/customer/{id}/detail")
	public ModelAndView detail(@PathVariable Long id, ModelAndView mav) {
		
		// detail.htmlを適用
		mav.setViewName("/customer/detail");
		
		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.findOne(id);
		
		// CustomerDtoオブジェクトに移す
		CustomerDto customerDto = new CustomerDto(customer); 
		
		// ビューに顧客データを反映
		mav.addObject("customer", customerDto);
		return mav;
	}
	
	
	

	
	/**
	 * 編集ページ
	 * @return
	 */
	@RequestMapping("/customer/{id}/edit")
	public ModelAndView update(ModelAndView mav) {
		
		// edit.htmlを適用
		mav.setViewName("/customer/edit");
		
		return mav;
	}
	
	
	/**
	 * 都道府県一覧取得
	 * @return
	 */
	private List<String> getStateList() {
		
		return areaRepository.getStates();
	}
}
