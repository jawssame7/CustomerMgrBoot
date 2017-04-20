package com.kinoshita.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kinoshita.springboot.annotation.NullPermissionNum;

public class NullPermissionNumValidator implements ConstraintValidator<NullPermissionNum, String> {

	@Override
	public void initialize(NullPermissionNum num) {
		
	}
	
	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if (input == null || input == "") {
			return true;
		}
		if (input.length() < 10) {
			return false;
		} else {
			return true;
		}
	}
}
