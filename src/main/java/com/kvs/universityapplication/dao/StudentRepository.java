package com.kvs.universityapplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kvs.universityapplication.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
//	@Query(value = "SELECT * FROM student WHERE group_name = ?1", nativeQuery = true)
	@Query("SELECT s FROM Student s WHERE s.group.name = ?1")
	@EntityGraph(attributePaths = {"group"})
	List<Student> findStudentsGroup(String groupName);
}
