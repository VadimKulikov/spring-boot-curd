package com.kvs.universityapplication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kvs.universityapplication.entity.SchoolDiploma;
import com.kvs.universityapplication.entity.Student;
import com.kvs.universityapplication.service.GroupService;
//import com.kvs.universityapplication.service.SchoolDiplomaService;
import com.kvs.universityapplication.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	private StudentService studentService;
	private GroupService groupService;

	public StudentController(StudentService studentService, GroupService groupService) {
		this.studentService = studentService;
		this.groupService = groupService;
	}

	@GetMapping("/list/{groupName}")
	public String listStudentGroup(Model theModel, @PathVariable("groupName") String groupName) {

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

	@GetMapping("/{groupName}/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel,
			@PathVariable("groupName") String groupName) {

		Student student = studentService.findById(id);
		theModel.addAttribute("student", student);

		return "student/student-form";
	}

	@GetMapping("/{groupName}/delete")
	public String deleteFaculty(@RequestParam("studentId") int id) {
		studentService.deleteById(id);

		return "redirect:/student/list/{groupName}";
	}

}
