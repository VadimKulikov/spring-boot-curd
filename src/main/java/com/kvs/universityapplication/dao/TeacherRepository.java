package com.kvs.universityapplication.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kvs.universityapplication.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	
	@EntityGraph(attributePaths = {"department", "disciplines"})
	Page<Teacher> findAll(Pageable pageable);
}
