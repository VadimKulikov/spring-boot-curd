package com.kvs.universityapplication.service;

import java.util.List;

import com.kvs.universityapplication.entity.Faculty;

public interface FacultyService {

	public List<Faculty> findAll();

	public Faculty findById(int theId);

	public void save(Faculty faculty);

	public void deleteById(int theId);

}
