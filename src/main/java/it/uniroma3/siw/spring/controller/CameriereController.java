package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.CameriereValidator;
import it.uniroma3.siw.spring.model.Cameriere;
import it.uniroma3.siw.spring.service.CameriereService;

@Controller
public class CameriereController {
	
	@Autowired
	private CameriereService cameriereService;
	
	@Autowired
	private CameriereValidator cameriereValidator;
	
	
	@GetMapping(value = {"/admin/gestisciCamerieri"})
	public String getGestisciCamerieri(Model model) {
		model.addAttribute("camerieri", this.cameriereService.tutti());
		model.addAttribute("cameriere", new Cameriere());
		return "admin/gestisciCamerieri";
	}
	
	@GetMapping(value = {"/camerieri"})
	public String getCamerieri(Model model) {
		model.addAttribute("camerieri", this.cameriereService.tutti());
		return "camerieri";
	}
	
	@GetMapping("/cameriere/{id}")
	public String getCameriere(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cameriere", this.cameriereService.camerierePerId(id));
		return "artista";
	}
	
	@PostMapping(value = {"/admin/cameriere"})
	public String addCameriere(@ModelAttribute("cameriere") Cameriere cameriere, Model model, BindingResult bindingResult) {
		this.cameriereValidator.validate(cameriere, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.cameriereService.inserisci(cameriere);
			model.addAttribute("camerieri", this.cameriereService.tutti());
			return "redirect:/admin/gestisciCamerieri";
		}
		return "admin/cameriereForm";
	}
	
	@PostMapping(value = {"/admin/modifica/cameriere"})
	public String modifyCameriere(@ModelAttribute("cameriere") Cameriere cameriere, Model model, BindingResult bindingResult) {
		this.cameriereService.modificaCameriere(cameriere);
		return "redirect:/admin/gestisciCamerieri";
	}

	@GetMapping("cameriere/delete/{id}")
	public String rimuoviCameriere(@PathVariable("id") Long id, Model model) {
		this.cameriereService.rimuoviCameriere(id);
		return "redirect:/admin/gestisciCamerieri"; 
	}

}
