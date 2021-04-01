package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.StudentRepository;
import com.kvs.universityapplication.entity.Group;
import com.kvs.universityapplication.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public List<Student> findStudentsGroup(String groupName) {
		return studentRepository.findStudentsGroup(groupName);
	}

	@Override
	public Student findById(int theId) {
		Optional<Student> result = studentRepository.findById(theId);
		Student student = null;
		if (result.isPresent()) {
			student = result.get();
		} else {
			throw new RuntimeException("Didnt't find student");
		}
		return student;
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
		
	}

	@Override
	public void deleteById(String theId) {
		studentRepository.deleteById(null);
		
	}

	
}
