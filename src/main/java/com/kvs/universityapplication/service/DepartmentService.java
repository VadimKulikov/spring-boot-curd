package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.kvs.universityapplication.entity.Department;


public interface DepartmentService {
	public List<Department> findAll();

	public Department findById(String theId);

	public void save(Department department);

	public void deleteById(String theId);

	public Page<Department> findPaginated(int pageNumber);
}
