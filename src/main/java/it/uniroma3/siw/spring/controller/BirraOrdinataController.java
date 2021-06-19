package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.spring.service.BirraOrdinataService;
import it.uniroma3.siw.spring.service.BirraService;

@Controller
public class BirraOrdinataController {
	
	@Autowired
	private BirraOrdinataService birraOrdinataService;
	
	@Autowired
	private BirraService birraService;
	
	
	@GetMapping(value = "/cameriere/birraOrdinata")
	public String getBirraOrdinata(Model model) {
		//model.addAttribute("birre", this.birraOrdinataService.tutte());
		model.addAttribute("birre", this.birraService.tutte());
		return "cameriere/gestisciOrdinazioni";
	}

}
