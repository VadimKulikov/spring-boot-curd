package com.kvs.universityapplication.converters;

import org.springframework.core.convert.converter.Converter;

import com.kvs.universityapplication.entity.Group;

public class StringtToGroupConverter implements Converter<String, Group> {

	@Override
	public Group convert(String source) {
		Group group = new Group();
		group.setName(source);
		return group;
	}

}
