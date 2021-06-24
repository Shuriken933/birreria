package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.TavoloValidator;
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.service.SalaService;
import it.uniroma3.siw.spring.service.TavoloService;

@Controller
public class TavoloController {
	
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private TavoloValidator tavoloValidator;
	
	@Autowired
	private SalaService salaService;
	
	
	/*@GetMapping(value = "/admin/gestisciTavoli")
	public String getGestisciTavoli(Model model) {
		model.addAttribute("tavoli", this.tavoloService.tutti());
		model.addAttribute("sale", this.salaService.tutte());
		model.addAttribute("tavolo", new Tavolo());
		return "admin/gestisciTavoli";
	}*/
	
	@PostMapping("/admin/tavolo")
    public String addTavolo(@ModelAttribute("tavolo") Tavolo tavolo, Model model, BindingResult bindingResult) {
    	/*this.tavoloValidator.validate(tavolo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.tavoloService.inserisci(tavolo);
        	model.addAttribute("tavoli", this.tavoloService.tutti());
            return "redirect:/admin/gestisciTavoli";
        }
        return "admin/gestisciTavoli";*/
		
		this.tavoloService.inserisci(tavolo);
    	model.addAttribute("tavoli", this.tavoloService.tutti());
        return "redirect:/admin/dashboard";
    }
	
	@GetMapping("tavolo/delete/{id}")
	public String rimuoviTavolo(@PathVariable("id") Long id, Model model) {
		this.tavoloService.rimuoviTavolo(id);
		return "redirect:/admin/gestisciTavoli"; 
	}
	
	@GetMapping("tavolo/{id}")
	public String mostraTavolo(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tavolo", this.tavoloService.tavoloPerId(id));
		return "collezione"; 
	}
	
	@PostMapping(value = {"/admin/modifica/tavolo"})
	public String modificaTavolo(@ModelAttribute("tavolo") Tavolo tavolo, Model model, BindingResult bindingResult) {
		this.tavoloService.modificaTavolo(tavolo);
		return "redirect:/admin/gestisciTavoli";
	}

}
