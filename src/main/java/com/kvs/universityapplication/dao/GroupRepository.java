package com.kvs.universityapplication.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kvs.universityapplication.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,String> {
	
	@EntityGraph(attributePaths = {"department"})
	Page<Group> findAll(Pageable pageable);

}
