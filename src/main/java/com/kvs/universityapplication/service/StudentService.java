package com.kvs.universityapplication.service;

import java.util.List;

import com.kvs.universityapplication.entity.Student;

public interface StudentService {

	public List<Student> findAll();

	public List<Student> findStudentsGroup(String groupName);

	public Student findById(int theId);

	public void save(Student student);

	public void deleteById(int theId);

}
