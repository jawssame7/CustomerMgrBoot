package com.kinoshita.springboot.utility;

import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

public class PaginationDialect extends AbstractDialect implements IExpressionEnhancingDialect {
	
	public PaginationDialect() {
		
		super();
	}
	
	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		Map<String, Object> objects = new HashMap<>();
		objects.put("pageHelper", new PaginationUtility());
		return objects;
	}

	@Override
	public String getPrefix() {
		return null;
	}
}
