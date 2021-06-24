package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.SalaService;
import it.uniroma3.siw.spring.service.TavoloService;

@Controller
public class MainController {
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private TavoloService tavoloService;
	
	
	
	
	@GetMapping(value = "/admin/gestisciPagamenti")
	public String getGestisciPagamenti(Model model) {
		model.addAttribute("sale", this.salaService.tutte());
		model.addAttribute("tavoli", this.tavoloService.tutti());
		return "admin/gestisciPagamenti";
	}
	
	
	
}