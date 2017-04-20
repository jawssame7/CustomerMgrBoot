package com.kinoshita.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.kinoshita.springboot.annotation.PostalCode;
import com.kinoshita.springboot.entity.Area;
import com.kinoshita.springboot.repository.AreaRepository;

@EnableJpaRepositories("com.kinoshita.springboot.repository")
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
