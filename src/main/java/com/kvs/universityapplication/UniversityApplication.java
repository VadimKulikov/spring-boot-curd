package com.kvs.universityapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kvs.universityapplication.converters.StringToDepartmentConverter;
import com.kvs.universityapplication.converters.StringToFacultyConverter;
import com.kvs.universityapplication.converters.StringToSetConverter;
import com.kvs.universityapplication.converters.StringtToGroupConverter;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class UniversityApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToFacultyConverter());
		registry.addConverter(new StringToDepartmentConverter());
		registry.addConverter(new StringtToGroupConverter());
		registry.addConverter(new StringToSetConverter());
	}

}
