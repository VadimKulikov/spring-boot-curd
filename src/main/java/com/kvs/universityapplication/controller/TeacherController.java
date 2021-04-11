package com.kvs.universityapplication.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvs.universityapplication.entity.Teacher;
import com.kvs.universityapplication.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	private TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
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
}
