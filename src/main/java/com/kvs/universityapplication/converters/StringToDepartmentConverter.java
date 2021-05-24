package com.kvs.universityapplication.converters;

import org.springframework.core.convert.converter.Converter;

import com.kvs.universityapplication.entity.Department;

public class StringToDepartmentConverter implements Converter<String, Department> {

	@Override
	public Department convert(String source) {
		Department dept = new Department();
		dept.setAbbr(source);
		return dept;
	}

}
