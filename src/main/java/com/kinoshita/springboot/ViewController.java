package com.kinoshita.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kinoshita.springboot.repository.AreaRepository;
import com.kinoshita.springboot.repository.CustomerRepository;

@Controller
public class ViewController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AreaRepository areaRepository;
	@Autowired
	AreaService areaService;

	
	/**
	 * ログイン後顧客検索ページへ
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "forward:/customer/search";
	}
	
	/**
	 * 顧客検索初期表示
	 * @param mav
	 * @return
	 */
	@RequestMapping("/customer/search")
	public ModelAndView customerSearch(ModelAndView mav) {
		mav.setViewName("/customer/customer_list");
		
		// repositoryから都道府県を取得
		List<String> stateList =  areaRepository.getStates();
		
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", stateList);
		
		// 顧客全取得
		Iterable<Customer> customerList = customerRepository.findByDeletedIsNull();
		
		List<StringControl> convertCustomerList = new ArrayList<StringControl>();
		for (Customer customer: customerList) {
			StringControl convertCustomer = new StringControl();
			convertCustomer.setCustomer(customer);
			
			// 郵便番号にハイフンを挿入
			if (customer.getPostal_code() != null) {
				convertCustomer.postal_codeConvert(customer.getPostal_code());
			}
			// 住所1がnullの場合文字列なし（""）に変更
			convertCustomer.address1Convert(customer.getAddress1());
			// 住所2がnullの場合文字列なし（""）二変更
			convertCustomer.address2Convert(customer.getAddress2());
			// 税区分を番号に合った方法の文字列に変換
			convertCustomer.tax_typeConvert(customer.getTax_type());
			// 丸め方法を番号に合った方法の文字列に変換
			convertCustomer.rounding_typeConvert(customer.getRounding_type());
			// 登録日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
			convertCustomer.createdConvert(customer.getCreated());
			// 更新日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
			convertCustomer.updatedConvert(customer.getUpdated());
			convertCustomerList.add(convertCustomer);
		} 
		
		// ビューに顧客リストを反映
		mav.addObject("customer_list", convertCustomerList);
		
		return mav;
	}
	
	/**
	 * 名称サジェスト
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/name/search", method = RequestMethod.GET)
	@ResponseBody
	public List<String> nameSuggest(String name) {
		List<String> nameList = customerRepository.suggestName(name);
		
		return nameList;
	}
	
	/**
	 * 顧客検索
	 * @return
	 */
	@RequestMapping(value = "/customer/search/", produces="text/plain;charset=UTF-8")
	public ModelAndView customerSearch(@RequestParam String name, @RequestParam String kana, 
			@RequestParam String postal_code, @RequestParam String address1, 
			@RequestParam String address2, @RequestParam String tel, 
			@RequestParam String fax, ModelAndView mav) {
		mav.setViewName("/customer/customer_list");
		
		// repositoryから都道府県を取得
		List<String> stateList =  areaRepository.getStates();
		
		// 検索条件セレクトボックスに都道府県を格納
		mav.addObject("area_list", stateList);
		
		// serviceから検索結果を取得
		List<Customer> result = customerService.findCustomers(name, kana, postal_code, address1, address2, tel, fax);
		
		System.out.println(result.size());
		
		List<StringControl> convertCustomerList = new ArrayList<StringControl>();
		for (Customer customer: result) {
			StringControl convertCustomer = new StringControl();
			convertCustomer.setCustomer(customer);
			
			// 郵便番号にハイフンを挿入
			if (customer.getPostal_code() != null) {
				convertCustomer.postal_codeConvert(customer.getPostal_code());
			}
			// 住所1がnullの場合文字列なし（""）に変更
			convertCustomer.address1Convert(customer.getAddress1());
			// 住所2がnullの場合文字列なし（""）二変更
			convertCustomer.address2Convert(customer.getAddress2());
			// 税区分を番号に合った方法の文字列に変換
			convertCustomer.tax_typeConvert(customer.getTax_type());
			// 丸め方法を番号に合った方法の文字列に変換
			convertCustomer.rounding_typeConvert(customer.getRounding_type());
			// 登録日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
			convertCustomer.createdConvert(customer.getCreated());
			// 更新日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
			convertCustomer.updatedConvert(customer.getUpdated());
			convertCustomerList.add(convertCustomer);
		} 
		
		// ビューに検索結果を反映
		mav.addObject("customer_list", convertCustomerList);
		
		return mav;
	}
	
	/**
	 * 顧客登録
	 * @return
	 */
	@RequestMapping("/customer/entry")
	public ModelAndView customerCreate(ModelAndView mav) {
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
	public ModelAndView customerUpdate(ModelAndView mav) {
		mav.setViewName("/customer/edit");
		return mav;
	}
	
	/**
	 * 顧客（登録・編集）確認
	 * @return
	 */
	@RequestMapping(value = "/customer/check", method = RequestMethod.POST)
	public ModelAndView customerCheck(@ModelAttribute("customer") Customer customer, ModelAndView mav) {
		mav.setViewName("/customer/check");
		return mav;
	}
	
	/**
	 * 顧客詳細
	 * @return
	 */
	@RequestMapping("/customer/detail/id={id}")
	public ModelAndView customerDetail(ModelAndView mav, @PathVariable Long id) {
		mav.setViewName("/customer/detail");
		
		// 顧客IDから顧客データを取得
		Customer customer = customerRepository.findOne(id);
		
		StringControl convertCustomer = new StringControl(); 
		convertCustomer.setCustomer(customer);
		
		// 郵便番号にハイフンを挿入
		if (customer.getPostal_code() != null) {
			convertCustomer.postal_codeConvert(customer.getPostal_code());
		}
		// 住所1がnullの場合文字列なし（""）に変更
		convertCustomer.address1Convert(customer.getAddress1());
		// 住所2がnullの場合文字列なし（""）二変更
		convertCustomer.address2Convert(customer.getAddress2());
		// 税区分を番号に合った方法の文字列に変換
		convertCustomer.tax_typeConvert(customer.getTax_type());
		// 丸め方法を番号に合った方法の文字列に変換
		convertCustomer.rounding_typeConvert(customer.getRounding_type());
		// 登録日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
		convertCustomer.createdConvert(customer.getCreated());
		// 更新日時をyyyy年MM月dd日 hh時mm分のフォーマットに変換
		convertCustomer.updatedConvert(customer.getUpdated());
		
		// ビューに顧客データを反映
		mav.addObject("customer", convertCustomer);
		return mav;
	}
	
	/**
	 * 入金検索
	 * @return
	 */
	@RequestMapping("/payment/search")
	public ModelAndView paymentSearch(ModelAndView mav) {
		mav.setViewName("/payment/payment_list");
		return mav;
	}
	
	/**
	 * 伝票検索
	 * @return
	 */
	@RequestMapping("/slip/search")
	public ModelAndView slipSearch(ModelAndView mav) {
		return mav;
	}
}
