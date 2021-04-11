package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kvs.universityapplication.entity.Teacher;


public interface TeacherService {
	public List<Teacher> findAll();

	public Teacher findById(int theId);

	public void save(Teacher teacher);

	public void deleteById(int theId);

	public Page<Teacher> findPaginated(int pageNumber);
}
