package com.kvs.universityapplication.controller;

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

import com.kvs.universityapplication.entity.Department;
import com.kvs.universityapplication.entity.Faculty;
import com.kvs.universityapplication.service.DepartmentService;
import com.kvs.universityapplication.service.FacultyService;

@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	private DepartmentService departmentService;
	private FacultyService facultyService;
	
	public DepartmentController(DepartmentService departmentService, FacultyService facultyRepository) {
		this.departmentService = departmentService;
		this.facultyService = facultyRepository;
	}
	
	@GetMapping("/list")
	public String listDepartments(Model theModel) {
		
		return listByPage(theModel, 1);
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("departmentId") String abbr, Model theModel) {
		
		Department department = departmentService.findById(abbr);
		List<Faculty> faculties = facultyService.findAll();
		
		theModel.addAttribute("department", department);
		theModel.addAttribute("faculties",faculties);
		
		return "department/department-form";
	}
	
	@GetMapping("/list/{pageNumber}")
	public String listByPage(Model theModel,
			@PathVariable("pageNumber") int currentPageNum) {
		Page<Department> currentPage = departmentService.findPaginated(currentPageNum);
		long totalItems = currentPage.getTotalElements();
		int totalPages = currentPage.getTotalPages();
		
		theModel.addAttribute("departments",currentPage.getContent());
		theModel.addAttribute("totalPages",totalPages);
		theModel.addAttribute("totalItems",totalItems);
		
		return "department/list-departments";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Department department = new Department();
		List<Faculty> faculties = facultyService.findAll();
		
		theModel.addAttribute("faculties", faculties);
		
		theModel.addAttribute("department", department);
		
		return "department/department-form";
	}
	
	@PostMapping("/save")
	public String saveDepartment(@ModelAttribute("department") Department department) {
		departmentService.save(department);
		return "redirect:/department/list";
	}
	
	@GetMapping("/delete")
	public String deleteDepartment(@RequestParam("departmentId") String abbr) {
		departmentService.deleteById(abbr);
		
		return "redirect:/department/list";
	}
}
