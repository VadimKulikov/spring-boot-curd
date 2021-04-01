package com.kvs.universityapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kvs.universityapplication.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	@EntityGraph(attributePaths = {"departments"})
	public List<Faculty> findAllByOrderByNameAsc();
	
}
