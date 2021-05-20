package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.StudentSqlRepository;
import com.kvs.universityapplication.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentSqlRepository studentRepository;

	public StudentServiceImpl(StudentSqlRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> findAll() {
		return null;
	}

	@Override
	public List<Student> findStudentsGroup(String groupName) {
		return studentRepository.findStudentsGroup(groupName);
	}

	@Override
	public Student findById(int theId) {
		return studentRepository.findById(theId);
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);

	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
	}

}
