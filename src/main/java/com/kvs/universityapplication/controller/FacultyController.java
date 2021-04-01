package com.kvs.universityapplication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kvs.universityapplication.entity.Faculty;
import com.kvs.universityapplication.service.FacultyService;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

	private FacultyService facultyService;

	public FacultyController(FacultyService facultyService) {
		this.facultyService = facultyService;
	}

	@GetMapping("/list")
	public String listFaculties(Model theModel) {

		List<Faculty> faculties = facultyService.findAll();

		theModel.addAttribute("faculties", faculties);

		return "faculty/list-faculties";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Faculty faculty = new Faculty();
		
		theModel.addAttribute("faculty", faculty);
		
		return "faculty/faculty-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("facultyId") int id, Model theModel) {
		
		Faculty faculty = facultyService.findById(id);
		
		theModel.addAttribute("faculty",faculty);
		
		return "faculty/faculty-form";
	}
	
	@PostMapping("/save")
	public String saveFaculty(@ModelAttribute("faculty") Faculty faculty) {
		facultyService.save(faculty);
		return "redirect:/faculty/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("facultyId") int id) {
		facultyService.deleteById(id);
		
		return "redirect:/faculty/list";
	}

}
