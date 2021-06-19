package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.SalaValidator;
import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.service.SalaService;

@Controller
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private SalaValidator salaValidator;
	
	@GetMapping(value = {"/admin/gestisciSale"})
	public String getGestisciSale(Model model) {
		model.addAttribute("sale", this.salaService.tutte());
		model.addAttribute("sala", new Sala());
		return "admin/gestisciSale";
	}
	
	@GetMapping("sala/{id}")
	public String getSala(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sala", this.salaService.salaPerId(id));
		return "sala";
	}
	
	@PostMapping(value = {"/admin/sala"})
	public String addSala(@ModelAttribute("sala") Sala sala, Model model, BindingResult bindingResult) {
		this.salaValidator.validate(sala, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.salaService.inserisci(sala);
			model.addAttribute("sale", this.salaService.tutte());
			return "redirect:/admin/gestisciSale";
		}
		return "admin/gestisciSale";
	}
	
	@PostMapping(value = {"/admin/modifica/sala"})
	public String modifySala(@ModelAttribute("sala") Sala sala, Model model, BindingResult bindingResult) {
		this.salaService.modificaSala(sala);
		return "redirect:/admin/gestisciSale";
	}

	@GetMapping("sala/delete/{id}")
	public String rimuoviSala(@PathVariable("id") Long id, Model model) {
		this.salaService.rimuoviSala(id);
		return "redirect:/admin/gestisciSale"; 
	}

}
