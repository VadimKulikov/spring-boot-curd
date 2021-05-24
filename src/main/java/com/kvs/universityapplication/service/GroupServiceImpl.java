package com.kvs.universityapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.GroupProcRepository;
import com.kvs.universityapplication.entity.Group;

@Service
public class GroupServiceImpl implements GroupService {

	private final int PAGE_SIZE = 10;

	private GroupProcRepository groupRepository;

	public GroupServiceImpl(GroupProcRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<Group> findAll() {
//		return groupRepository.findAll();
		return null;
	}

	@Override
	public Group findById(String theId) {

		return groupRepository.findById(theId);
	}

	@Override
	public void save(Group group) {
		groupRepository.save(group);

	}

	@Override
	public void deleteById(String theId) {
		groupRepository.deleteById(theId);
	}

	@Override
	public Page<Group> findPaginated(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return groupRepository.findAll(pageable);
	}

}
