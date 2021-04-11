package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.TeacherRepository;
import com.kvs.universityapplication.entity.Teacher;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	private final int PAGE_SIZE = 10;
	
	private TeacherRepository teacherRepository;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository; 
	}
	
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher findById(int theId) {
		Optional<Teacher> result = teacherRepository.findById(theId);
		Teacher teacher = null;
		if (result.isPresent()) {
			teacher = result.get();
		} else {
			throw new RuntimeException("Didnt't find teacher");
		}
		return teacher;
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
