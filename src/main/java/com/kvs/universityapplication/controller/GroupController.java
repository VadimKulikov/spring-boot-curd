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
import com.kvs.universityapplication.entity.Group;
import com.kvs.universityapplication.service.DepartmentService;
import com.kvs.universityapplication.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {

	private GroupService groupService;
	private DepartmentService departmentService;

	public GroupController(GroupService groupService, DepartmentService departmentService) {
		this.groupService = groupService;
		this.departmentService = departmentService;
	}

	@GetMapping("/list")
	public String listGroups(Model theModel) {

		return listByPage(theModel, 1);
	}

	@GetMapping("/list/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable("pageNumber") int currentPageNum) {
		Page<Group> currentPage = groupService.findPaginated(currentPageNum);
		long totalItems = currentPage.getTotalElements();
		int totalPages = currentPage.getTotalPages();

		theModel.addAttribute("groups", currentPage.getContent());
		theModel.addAttribute("totalPages", totalPages);
		theModel.addAttribute("totalItems", totalItems);

		return "group/list-groups";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Group group = new Group();


		theModel.addAttribute("group", group);

		return "group/group-form";
	}
	
	@PostMapping("/save")
	public String saveGroup(@ModelAttribute("group") Group group) {
		groupService.save(group);
		return "redirect:/group/list";
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
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("groupId") String name, Model theModel) {
		
		Group group = groupService.findById(name);
		theModel.addAttribute("group", group);
		
		return "group/group-form";
	}
	
	@GetMapping("/delete")
	public String deleteGroup(@RequestParam("groupId") String name) {
		groupService.deleteById(name);
		
		return "redirect:/group/list";
	}

}
