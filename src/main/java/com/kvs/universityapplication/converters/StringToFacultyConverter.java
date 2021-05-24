package com.kvs.universityapplication.converters;

import org.springframework.core.convert.converter.Converter;

import com.kvs.universityapplication.entity.Faculty;

public class StringToFacultyConverter implements Converter<String, Faculty> {

	@Override
	public Faculty convert(String source) {
		int id = Integer.parseInt(source);
		Faculty faculty = new Faculty();
		faculty.setId(id);
		return faculty;
	}

}
