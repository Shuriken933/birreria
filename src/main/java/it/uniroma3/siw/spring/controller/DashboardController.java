package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.CameriereValidator;
import it.uniroma3.siw.spring.model.Cameriere;
import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.service.CameriereService;
import it.uniroma3.siw.spring.service.SalaService;
import it.uniroma3.siw.spring.service.TavoloService;

@Controller
public class DashboardController {
	
	@Autowired CameriereValidator cameriereValidator;
	@Autowired CameriereService cameriereService;
	@Autowired SalaService salaService;
	@Autowired TavoloService tavoloService;
	
	
	@GetMapping(value = "/admin/dashboard")
	public String getDashboard(Model model) {
		model.addAttribute("camerieri", this.cameriereService.tutti());
		model.addAttribute("cameriere", new Cameriere());
		model.addAttribute("sale", this.salaService.tutte());
		model.addAttribute("sala", new Sala());
		model.addAttribute("tavoli", this.tavoloService.tutti());
		model.addAttribute("tavolo", new Tavolo());
		return "admin/dashboard";
	}
	
	@PostMapping(value = "/admin/registraCameriere")
	public String registerCameriere(@ModelAttribute("cameriere") Cameriere cameriere, Model model, BindingResult bindingResult) {
		this.cameriereValidator.validate(cameriere, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.cameriereService.inserisci(cameriere);
			/*model.addAttribute("camerieri", this.cameriereService.tutti());*/
			return "redirect:/admin/dashboard";
		}
		return "admin/dashboard";
	}
	
	

}
