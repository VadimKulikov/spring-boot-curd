package com.kvs.universityapplication.service;

import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.SchoolDiplomaRepository;
import com.kvs.universityapplication.entity.SchoolDiploma;

@Service
public class SchoolDiplomaServiceImpl implements SchoolDiplomaService {
	
	private SchoolDiplomaRepository schoolDiplomaRepository;

	public SchoolDiplomaServiceImpl(SchoolDiplomaRepository schoolDiplomaRepository) {
		this.schoolDiplomaRepository = schoolDiplomaRepository;
	}

	@Override
	public void save(SchoolDiploma diploma) {
		schoolDiplomaRepository.save(diploma);
		
	}
	
	
}
