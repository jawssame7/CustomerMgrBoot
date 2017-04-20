package com.kinoshita.springboot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.kinoshita.springboot.validator.NullPermissionNumValidator;

@Documented
@Constraint(validatedBy = NullPermissionNumValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface NullPermissionNum {

	String message() default "{com.kinoshita.springboot.validator.NullPermissionNumValidator.message}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};

	int min();
	int max();
}
