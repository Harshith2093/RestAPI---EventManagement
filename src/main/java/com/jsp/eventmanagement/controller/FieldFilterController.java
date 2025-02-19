package com.jsp.eventmanagement.controller;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jsp.eventmanagement.model.FieldFiltering;

@RestController
public class FieldFilterController {
	
	@GetMapping("filter-1")
	public MappingJacksonValue method1() {
		
		FieldFiltering filter = new FieldFiltering("Jeevith", "San", "Meerai") ;
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filter) ;
		
		SimpleBeanPropertyFilter actFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2") ;
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("jsonfilter", actFilter) ;
		
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue ;
	}
}
