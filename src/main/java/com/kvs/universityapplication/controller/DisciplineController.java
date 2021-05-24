package com.kvs.universityapplication.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kvs.universityapplication.entity.Discipline;
import com.kvs.universityapplication.service.DisciplineService;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

	private DisciplineService disciplineService;

	public DisciplineController(DisciplineService disciplineService) {
		this.disciplineService = disciplineService;
	}

	@GetMapping("/list")
	public String listDisciplines(Model theModel) {
		return listByPage(theModel, 1);
	}

	@GetMapping("/list/{pageNumber}")
	public String listByPage(Model theModel, @PathVariable("pageNumber") int currentPageNum) {
		Page<Discipline> currentPage = disciplineService.findPaginated(currentPageNum);
		long totalItems = currentPage.getTotalElements();
		int totalPages = currentPage.getTotalPages();

		theModel.addAttribute("disciplines", currentPage.getContent());
		theModel.addAttribute("totalPages", totalPages);
		theModel.addAttribute("totalItems", totalItems);

		return "discipline/list-disciplines";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Discipline discipline = new Discipline();

		theModel.addAttribute("discipline", discipline);

		return "discipline/discipline-form";
	}

	@PostMapping("/save")
	public String saveDepartment(@ModelAttribute("discipline") Discipline discipline) {
		disciplineService.save(discipline);
		return "redirect:/discipline/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("disciplineId") String name, Model theModel) {

		Discipline discipline = disciplineService.findById(name);

		theModel.addAttribute("discipline", discipline);

		return "discipline/discipline-form";
	}

	@GetMapping("/delete")
	public String deleteDiscipline(@RequestParam("disciplineId") String name) {
		disciplineService.deleteById(name);

		return "redirect:/discipline/list";
	}

}
