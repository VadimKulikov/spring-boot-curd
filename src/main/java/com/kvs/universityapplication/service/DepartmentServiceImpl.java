package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.DepartmentRepository;
import com.kvs.universityapplication.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentRepository;
	private final int PAGE_SIZE = 10;
	
	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}
	
	@Override
	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Department findById(String theId) {
		Optional<Department> result = departmentRepository.findById(theId);
		Department department = null;
		if(result.isPresent()) {
			department = result.get();
		} else {
			throw new RuntimeException("Didn't find department");
		}
		return department;
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
