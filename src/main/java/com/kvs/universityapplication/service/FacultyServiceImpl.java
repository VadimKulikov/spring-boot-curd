package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.FacultyRepository;
import com.kvs.universityapplication.entity.Faculty;

@Service
public class FacultyServiceImpl implements FacultyService {

	private FacultyRepository facultyRepository;
	
	public FacultyServiceImpl(FacultyRepository facultyRepo) {
		facultyRepository = facultyRepo;
	}
	
	@Override
	public List<Faculty> findAll() {
		return facultyRepository.findAllByOrderByNameAsc();
	}

	@Override
	public Faculty findById(int theId) {
		Optional<Faculty> result = facultyRepository.findById(theId);
		Faculty faculty = null;
		if(result.isPresent()) {
			faculty = result.get();
		} else {
			throw new RuntimeException("Didn't find faculty");
		}
		return faculty;
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
