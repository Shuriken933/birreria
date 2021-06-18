package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.OrdinazioneValidator;
import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.model.Ordinazione;
import it.uniroma3.siw.spring.service.OrdinazioneService;

@Controller
public class OrdinazioneController {
	
	@Autowired
	private OrdinazioneService ordinazioneService;
	
	@Autowired
	private OrdinazioneValidator ordinazioneValidator;
	
	
	@GetMapping(value = "/ordinazioni")
	public String getOrdinazioni(Model model) {
		model.addAttribute("ordinazioni", this.ordinazioneService.tutte());
		return "ordinazioni.html";
	}
	
	@GetMapping({"ordinazione/{id}"})
	public String getOrdinazione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ordinazione", this.ordinazioneService.ordinazionePerId(id));
		return "ordinazione";
	}
	
	@PostMapping(value = "/cameriere/ordinazione")
    public String addOrdinazione(@ModelAttribute("ordinazione") Ordinazione ordinazione, Model model, BindingResult bindingResult) {
    	this.ordinazioneValidator.validate(ordinazione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.ordinazioneService.inserisci(ordinazione);
        	model.addAttribute("ordinazioni", this.ordinazioneService.tutte());
            return "redirect:/cameriere/gestisciOrdinazioni";
        }
        return "cameriere/gestisciOrdinazioni";
    }

}
