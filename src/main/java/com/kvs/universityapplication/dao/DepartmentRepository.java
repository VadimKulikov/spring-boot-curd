package com.kvs.universityapplication.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kvs.universityapplication.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {

	@Override
//	@EntityGraph(attributePaths = {"faculty"})
	List<Department> findAll();
	
	@EntityGraph(attributePaths = {"faculty"})
	Page<Department> findAll(Pageable pageable);
}
