package com.kvs.universityapplication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvs.universityapplication.entity.Department;
import com.kvs.universityapplication.entity.Faculty;
import com.kvs.universityapplication.entity.SchoolDiploma;
import com.kvs.universityapplication.entity.Student;
import com.kvs.universityapplication.service.GroupService;
import com.kvs.universityapplication.service.SchoolDiplomaService;
import com.kvs.universityapplication.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private StudentService studentService;
	private GroupService groupService;
	private SchoolDiplomaService schoolDiplomaService;
	
	public StudentController(StudentService studentService, GroupService groupService,SchoolDiplomaService schoolDiplomaService) {
		this.studentService = studentService;
		this.groupService = groupService;
		this.schoolDiplomaService = schoolDiplomaService;
	}
	
	@GetMapping("/list/{groupName}")
	public String listSutentGroup(Model theModel, @PathVariable("groupName") String groupName) {
		
		List<Student> students = studentService.findStudentsGroup(groupName);
		
		theModel.addAttribute("students", students);
		
		return "student/list-students";
	}
	
	
	@GetMapping("/{groupName}/showFormForAdd")
	public String showFormForAdd(Model theModel, @PathVariable("groupName") String groupName) {
		Student student = new Student();
		student.setGroup(groupService.findById(groupName));
		SchoolDiploma diploma = new SchoolDiploma();
		student.setSchoolDiploma(diploma);
		theModel.addAttribute("student", student);
		theModel.addAttribute("groupName", groupName);
		theModel.addAttribute("diploma", diploma);
		
		return "student/student-form";
	}
	
	@PostMapping("/{groupName}/save")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.save(student);
		return "redirect:/student/list/{groupName}";
	}
}
