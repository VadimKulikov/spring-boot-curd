package com.kvs.universityapplication.entity;


public class Discipline {
	

	private String name;
	
	private int duration;
	
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
