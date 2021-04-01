package com.kvs.universityapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kvs.universityapplication.entity.SchoolDiploma;

public interface SchoolDiplomaRepository extends JpaRepository<SchoolDiploma,Long> {
	
}
