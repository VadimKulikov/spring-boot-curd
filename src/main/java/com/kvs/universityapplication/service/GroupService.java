package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kvs.universityapplication.entity.Group;

public interface GroupService {
	
	public List<Group> findAll();

	public Group findById(String theId);

	public void save(Group group);

	public void deleteById(String theId);

	public Page<Group> findPaginated(int pageNumber);

}
