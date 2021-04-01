package com.kvs.universityapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kvs.universityapplication.dao.GroupRepository;
import com.kvs.universityapplication.entity.Group;

@Service
public class GroupServiceImpl implements GroupService {

	private final int PAGE_SIZE = 10;

	private GroupRepository groupRepository;

	public GroupServiceImpl(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public List<Group> findAll() {
		return groupRepository.findAll();
	}

	@Override
	public Group findById(String theId) {
		Optional<Group> result = groupRepository.findById(theId);
		Group group = null;
		if (result.isPresent()) {
			group = result.get();
		} else {
			throw new RuntimeException("Didnt't find group");
		}
		return group;
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
