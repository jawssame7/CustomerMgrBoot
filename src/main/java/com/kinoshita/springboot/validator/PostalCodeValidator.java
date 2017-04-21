package com.kinoshita.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.kinoshita.springboot.annotation.PostalCode;
import com.kinoshita.springboot.entity.Area;
import com.kinoshita.springboot.repository.AreaRepository;

/**
 * 入力された郵便番号が存在するかを調べる
 * @author wizuser
 * 
 * true判定 : 入力されていない、または入力された郵便番号が存在する
 * false判定 : 入力された郵便番号が存在しない
 */
@ComponentScan("com.kinoshita.springboot.repository")
public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

	@Autowired
	AreaRepository repository;	
	
	@Override
	public void initialize(PostalCode postalCode) {
		
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		
		if (input == null || input.length() == 0) {
			return true;
		}

		Area result = repository.findByPostalCode(input);
		
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}
}
