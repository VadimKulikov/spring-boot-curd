package com.kvs.universityapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="discipline")
public class Discipline {
	
	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="semester")
	private int semester;
	
	public Discipline() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Override
	public String toString() {
		return name;
	}
	
	

}
