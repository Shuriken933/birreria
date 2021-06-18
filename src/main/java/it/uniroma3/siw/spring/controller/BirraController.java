package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.spring.controller.validator.BirraValidator;
import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.service.BirraService;

@Controller
public class BirraController {
	
	@Autowired
	private BirraService birraService;
	
	@Autowired
	private BirraValidator birraValidator;
	
	
	@GetMapping(value = "/birre")
	public String getBirre(Model model) {
		model.addAttribute("birre", this.birraService.tutte());
		return "birre.html";
	}
	
	@GetMapping({"birra/{id}"})
	public String getBirra(@PathVariable("id") Long id, Model model) {
		model.addAttribute("opera", this.birraService.birraPerId(id));
		return "birra";
	}
	
	@PostMapping(value = "/admin/birra")
    public String addBirra(@ModelAttribute("birra") Birra birra, Model model, BindingResult bindingResult) {
    	this.birraValidator.validate(birra, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.birraService.inserisci(birra);
        	model.addAttribute("birre", this.birraService.tutte());
            return "redirect:/admin/gestisciBirre";
        }
        return "admin/gestisciBirre";
    }
	
	@PostMapping(value = {"/admin/modifica/birra"})
	public String modifyBirra(@ModelAttribute("birra") Birra birra, Model model, BindingResult bindingResult) {
		this.birraService.modificaBirra(birra);
		return "redirect:/admin/gestisciBirre";
	}
	
	@GetMapping("/admin/delete/birra/{id}")
	public String rimuoviBirra(@PathVariable("id") Long id, Model model) {
		this.birraService.rimuoviBirra(id);
		return "redirect:/admin/gestisciBirre"; 
	}
	

}
