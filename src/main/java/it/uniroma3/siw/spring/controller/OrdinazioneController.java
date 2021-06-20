package it.uniroma3.siw.spring.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.OrdinazioneValidator;
import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.model.Cameriere;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Ordinazione;
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.service.BirraService;
import it.uniroma3.siw.spring.service.CameriereService;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.OrdinazioneService;
import it.uniroma3.siw.spring.service.SalaService;
import it.uniroma3.siw.spring.service.TavoloService;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class OrdinazioneController {
	
	/*private List<Birra> ordinazioneProvvisoria;
	private Tavolo tavoloSelezionato;*/
	
	/*private Long idCameriereLoggato;*/
	
	@Autowired
	private OrdinazioneService ordinazioneService;
	
	@Autowired
	private BirraService birraService;
	
	@Autowired
	private CameriereService cameriereService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private TavoloService tavoloService;
	
	@Autowired
	private OrdinazioneValidator ordinazioneValidator;
	
	/*@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private CameriereService cameriereService;*/
	
	
	
//	/*il cameriere entra nella home*/
//	@GetMapping(value = {"/cameriere/home"})
//	public String getHome(Model model) {
//		model.addAttribute("tavoli", this.tavoloService.tutti());
//		return "cameriere/home";
//	}
//	/*il cameriere seleziona un tavolo*/
//	@GetMapping("/cameriere/home/tavolo/{tavolo}")
//	public String getTavolo(@PathVariable("tavolo") Tavolo tavolo, Model model) {
//		this.tavoloSelezionato = tavolo;
//		model.addAttribute("birre", this.birraService.tutte());
//		model.addAttribute("tavoloDiOrdinazione", tavolo);
//		model.addAttribute("birra", new Birra());
//		return "cameriere/gestisciOrdinazioni";
//	}
//	/*il cameriere seleziona una birra*/
//	@PostMapping("/cameriere/gestisciOrdinazioni/add/birra")
//	public String addBirraToOrdinazione(@ModelAttribute("birra") Birra birra, Model model) {
//		ordinazioneProvvisoria.add(birra);
//		model.addAttribute("ordinazioneProvvisoria", this.ordinazioneProvvisoria);
//		return "cameriere/gestisciOrdinazioni";
//	}
//	/*il cameriere conferma l'ordinazione*/
//	@PostMapping("/cameriere/home/tavolo/gestisciOrdinazioni/conferma")
//	public String setOrdinazione(Model model) {
//		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Credentials credentials = credentialsService.getId(userDetails.getUsername()); //id cameriere
//		Cameriere cameriereLoggato = cameriereService.camerierePerId(credentials.getId());
//		/*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");*/
//		LocalDateTime now = LocalDateTime.now();
//		/*String dateTimeString = now.format(formatter);*/
//		Ordinazione ordinazione = new Ordinazione();
//		ordinazione.setCameriere(cameriereLoggato);
//		ordinazione.setBirre(ordinazioneProvvisoria);
//		ordinazione.setOrario(now);
//		ordinazione.setTavolo(tavoloSelezionato);
//		this.ordinazioneService.inserisci(ordinazione);
//		return "cameriere/home";
//	}
	
	

	@GetMapping(value = "/cameriere/gestisciOrdinazioni")
	public String getGestisciOrdinazioni(Model model) {
		model.addAttribute("ordinazioni", this.ordinazioneService.tutte());
		model.addAttribute("tavoli", this.tavoloService.tutti());
		model.addAttribute("camerieri", this.cameriereService.tutti());
		/*model.addAttribute("sale", this.salaService.tutte());*/
		model.addAttribute("birreDaMostrare", this.birraService.tutte());
		
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
    	
		/*System.out.println("ordinazione id (dopo click bottone): " + ordinazione.getId());*/
		
    	
    	/*this.ordinazioneValidator.validate(ordinazione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	LocalDateTime now = LocalDateTime.now();
        	ordinazione.setOrario(now);
        	this.ordinazioneService.inserisci(ordinazione);
        	model.addAttribute("ordinazioni", this.ordinazioneService.tutte());
            return "redirect:/cameriere/gestisciOrdinazioni";
        }
        return "cameriere/gestisciOrdinazioni";*/
		
		LocalDateTime now = LocalDateTime.now();
    	ordinazione.setOrario(now);
    	this.ordinazioneService.inserisci(ordinazione);
    	model.addAttribute("ordinazioni", this.ordinazioneService.tutte());
        return "redirect:/cameriere/gestisciOrdinazioni";
    }

}
