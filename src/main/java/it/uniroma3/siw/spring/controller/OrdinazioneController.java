package it.uniroma3.siw.spring.controller;

import java.util.List;

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
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.service.BirraService;
import it.uniroma3.siw.spring.service.OrdinazioneService;
import it.uniroma3.siw.spring.service.TavoloService;

@Controller
public class OrdinazioneController {
	
	private List<Birra> ordinazioneProvvisoria;
	
	@Autowired
	private OrdinazioneService ordinazioneService;
	
	@Autowired
	private BirraService birraService;
	
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private OrdinazioneValidator ordinazioneValidator;
	
	
	/*il cameriere entra nella home*/
	@GetMapping(value = {"/cameriere/home"})
	public String getHome(Model model) {
		model.addAttribute("tavoli", this.tavoloService.tutti());
		/*model.addAttribute("birre", this.birraService.tutte());*/
		return "cameriere/home";
	}
	
	/*il cameriere seleziona un tavolo*/
	@GetMapping("/cameriere/home/tavolo/{tavolo}")
	public String getTavolo(@PathVariable("tavolo") Tavolo tavolo, Model model) {
		
		model.addAttribute("birre", this.birraService.tutte());
		model.addAttribute("tavoloDiOrdinazione", tavolo);
		return "cameriere/home/tavolo/gestisciOrdinazioni";
	}
	
	/*il cameriere seleziona una birra*/
	@PostMapping("/cameriere/home/tavolo/gestisciOrdinazioni")
	public String getOrdinazione(@PathVariable("birra") Birra birra, Model model) {
		ordinazioneProvvisoria.add(birra);
		return "cameriere/home/tavolo/gestisciOrdinazioni";
	}
	
	/*il cameriere conferma l'ordinazione*/
	@PostMapping("/cameriere/home/tavolo/gestisciOrdinazioni/conferma")
	public String getOrdinazione(@PathVariable("ordinazione") Ordinazione ordinazione, Model model) {
		model.addAttribute("ordinazione", new Ordinazione());
		this.ordinazioneService.inserisci(ordinazione);
		return "cameriere/home";
	}
	
	
	
	
	
	
	/*@GetMapping(value = "/cameriere/gestisciOrdinazioni")
	public String getGestisciOrdinazioni(Model model) {
		model.addAttribute("tavoli", this.tavoloService.tutti());
		model.addAttribute("sale", this.salaService.tutte());
		model.addAttribute("ordinazione", new Ordinazione());
		return "cameriere/gestisciOrdinazioni";
	}
	
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
    }*/

}
