package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.DisciplineRepository;
import com.kvs.universityapplication.entity.Discipline;

@Service
public class DisciplineServiceImpl implements DisciplineService {

	private DisciplineRepository disciplineRepository;
	private final int PAGE_SIZE = 10;
	
	public DisciplineServiceImpl(DisciplineRepository disciplineRepository) {
		this.disciplineRepository = disciplineRepository;
	}
	
	@Override
	public List<Discipline> findAll() {
		return disciplineRepository.findAll();
	}

	@Override
	public Discipline findById(String theId) {
		Optional<Discipline> result = disciplineRepository.findById(theId);
		Discipline discipline = null;
		if(result.isPresent()) {
			discipline = result.get();
		} else {
			throw new RuntimeException("Didn't find discipline");
		}
		return discipline;
	}

	@Override
	public void save(Discipline discipline) {
		disciplineRepository.save(discipline);
	}

	@Override
	public void deleteById(String theId) {
		disciplineRepository.deleteById(theId);
	}

	@Override
	public Page<Discipline> findPaginated(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return disciplineRepository.findAll(pageable);
	}

}
