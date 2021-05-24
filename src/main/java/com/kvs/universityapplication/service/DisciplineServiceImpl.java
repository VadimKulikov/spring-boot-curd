package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.DisciplineProcRepository;
import com.kvs.universityapplication.entity.Discipline;

@Service
public class DisciplineServiceImpl implements DisciplineService {

	private DisciplineProcRepository disciplineRepository;
	private final int PAGE_SIZE = 10;

	public DisciplineServiceImpl(DisciplineProcRepository disciplineRepository) {
		this.disciplineRepository = disciplineRepository;
	}

	@Override
	public List<Discipline> findAll() {
		return null;
	}

	@Override
	public Discipline findById(String theId) {
		return disciplineRepository.findById(theId);
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
