package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.DepartmentSqlRepository;
import com.kvs.universityapplication.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentSqlRepository departmentRepository;
	private final int PAGE_SIZE = 10;

	public DepartmentServiceImpl(DepartmentSqlRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findById(String theId) {
		return departmentRepository.findById(theId);
	}

	@Override
	public void save(Department department) {
		departmentRepository.save(department);

	}

	@Override
	public void deleteById(String theId) {
		departmentRepository.deleteById(theId);

	}

	@Override
	public Page<Department> findPaginated(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return departmentRepository.findAll(pageable);
	}

}
