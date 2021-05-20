package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kvs.universityapplication.entity.Discipline;

public interface DisciplineService {

	public List<Discipline> findAll();

	public Discipline findById(String theId);

	public void save(Discipline department);

	public void deleteById(String theId);

	public Page<Discipline> findPaginated(int pageNumber);
}
