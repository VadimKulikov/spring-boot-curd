package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.TeacherProcRepository;
import com.kvs.universityapplication.entity.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final int PAGE_SIZE = 10;

	private TeacherProcRepository teacherRepository;

	public TeacherServiceImpl(TeacherProcRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<Teacher> findAll() {
		return null;
	}

	@Override
	public Teacher findById(int theId) {
		return teacherRepository.findById(theId);
	}

	@Override
	public void save(Teacher teacher) {
		teacherRepository.save(teacher);
	}

	@Override
	public void deleteById(int theId) {
		teacherRepository.deleteById(theId);

	}

	@Override
	public Page<Teacher> findPaginated(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return teacherRepository.findAll(pageable);
	}

}
