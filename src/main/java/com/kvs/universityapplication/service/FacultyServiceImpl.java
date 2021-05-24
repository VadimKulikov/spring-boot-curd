package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.FacultyProcRepository;
import com.kvs.universityapplication.entity.Faculty;

@Service
public class FacultyServiceImpl implements FacultyService {

	private FacultyProcRepository facultyRepository;

	public FacultyServiceImpl(FacultyProcRepository facultyRepo) {
		facultyRepository = facultyRepo;
	}

	@Override
	public List<Faculty> findAll() {
		return facultyRepository.findAll();
	}

	@Override
	public Faculty findById(int theId) {
		return facultyRepository.findById(theId);
	}

	@Override
	public void save(Faculty faculty) {
		facultyRepository.save(faculty);

	}

	@Override
	public void deleteById(int theId) {
		facultyRepository.deleteById(theId);

	}

}
