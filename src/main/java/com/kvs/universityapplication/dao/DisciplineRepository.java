package com.kvs.universityapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kvs.universityapplication.entity.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline,String> {

}
