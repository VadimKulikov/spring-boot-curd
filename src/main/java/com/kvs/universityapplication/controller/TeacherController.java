package com.kvs.universityapplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kvs.universityapplication.entity.Department;
import com.kvs.universityapplication.entity.Teacher;
import com.kvs.universityapplication.service.DepartmentService;
import com.kvs.universityapplication.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private TeacherService teacherService;
	private DepartmentService departmentService;
	
	public TeacherController(TeacherService teacherService, DepartmentService departmentService) {
		this.teacherService = teacherService;
		this.departmentService = departmentService;
	}
	
	@GetMapping("/list")
	public String listGroups(Model theModel) {

		return listByPage(theModel, 1);
	}
	
	@GetMapping("/list/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable("pageNumber") int currentPageNum) {
		Page<Teacher> currentPage = teacherService.findPaginated(currentPageNum);
		long totalItems = currentPage.getTotalElements();
		int totalPages = currentPage.getTotalPages();

		theModel.addAttribute("teachers", currentPage.getContent());
		theModel.addAttribute("totalPages", totalPages);
		theModel.addAttribute("totalItems", totalItems);

		return "teacher/list-teachers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Teacher teacher = new Teacher();

		theModel.addAttribute("teacher", teacher);

		return "teacher/teacher-form";
	}
	
	@GetMapping("/departmentAutocomplete")
	@ResponseBody
	public List<String> departmentNamesAutocomplete(@RequestParam(value = "term", defaultValue = "") String term) {
		List<Department> departments = departmentService.findAll();
		List<String> departmentNames = new ArrayList<>();
		for (Department d : departments) {
			if (d.getName().contains(term)) {
				departmentNames.add(d.getName());
			}
		}
		return departmentNames;
	}
	
	@PostMapping("/save")
	public String saveGroup(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.save(teacher);
		return "redirect:/teacher/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("teacherId") int id, Model theModel) {
		
		Teacher teacher = teacherService.findById(id);
		theModel.addAttribute("teacher", teacher);
		
		return "teacher/teacher-form";
	}
	
	@GetMapping("/delete")
	public String deleteTeacher(@RequestParam("teacherId") int id) {
		teacherService.deleteById(id);
		
		return "redirect:/teacher/list";
	}
}
