package com.kvs.universityapplication.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.kvs.universityapplication.entity.Discipline;

public class StringToSetConverter implements Converter<String, Set<Discipline>> {

	@Override
	public Set<Discipline> convert(String source) {
		Set<Discipline> result = new HashSet<>();
		source = source.substring(1, source.length() - 1);
		String[] sourcearr = source.split(", ");
		for (String string : sourcearr) {
			Discipline disc = new Discipline();
			disc.setName(string);
			result.add(disc);
		}
		return result;
	}

}
