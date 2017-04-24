package com.kinoshita.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kinoshita.springboot.annotation.NullPermissionNum;

/**
 * 文字数が規定値を満たしているか調べる
 * @author wizuser
 * 
 * true判定 : 入力されていない、または入力された文字数が規定値を満たしている
 * false判定 : 入力された文字列が規定値から外れている
 */
public class NullPermissionNumValidator implements ConstraintValidator<NullPermissionNum, String> {

	@Override
	public void initialize(NullPermissionNum num) {
		
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		
		if (input == null || input.length() == 0) {
			return true;
		}
		if (input.length() < 10) {
			return false;
		} else {
			return input.matches("[0-9]*");
		}
		
	}
}
