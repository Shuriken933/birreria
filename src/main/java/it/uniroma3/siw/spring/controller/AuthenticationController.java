package it.uniroma3.siw.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.controller.validator.UserValidator;
import it.uniroma3.siw.spring.model.Cameriere;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CameriereService;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.SalaService;
import it.uniroma3.siw.spring.service.TavoloService;

@Controller
public class AuthenticationController {
	
	@Autowired private CredentialsService credentialsService;
	@Autowired private CameriereService cameriereService;
	@Autowired private UserValidator userValidator;
	@Autowired private CredentialsValidator credentialsValidator;
	@Autowired SalaService salaService;
	@Autowired TavoloService tavoloService;
	
	
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) {
		return "loginForm";
	}
	
	/*@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}*/
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/index";
	}
	
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		/*model.addAttribute("user", new User());*/
    		/*model.addAttribute("credentials", new Credentials());*/
    		model.addAttribute("camerieri", this.cameriereService.tutti());
    		model.addAttribute("cameriere", new Cameriere());
    		model.addAttribute("sale", this.salaService.tutte());
    		model.addAttribute("sala", new Sala());
    		model.addAttribute("tavoli", this.tavoloService.tutti());
    		model.addAttribute("tavolo", new Tavolo());
            return "admin/dashboard";
        }else if(credentials.getRole().equals(Credentials.DEFAULT_ROLE)) {
        	return "redirect:/cameriere/gestisciOrdinazioni";
        }else {
        	/*model.addAttribute("user", new User());
    		model.addAttribute("credentials", new Credentials());*/
        	return "index";
        }
        /*return "cameriere/gestisciOrdinazioni";*/
    }
    
    
    
    /*@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    		return "admin/dashboard";
		}
    	else {
        	return "index";
        }
	}*/
	
    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, BindingResult userBindingResult,
    		@ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult, Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);
        
        /*System.out.println("USER");
        System.out.println("id: " + user.getId());
        System.out.println("nome: " + user.getNome());
        System.out.println("cognome: " + user.getCognome());
        System.out.println(" ");
        System.out.println("CREDENTIALS");
        System.out.println("id: " + credentials.getId());
        System.out.println("username: " + credentials.getUsername());
        System.out.println("ruolo: " + credentials.getRole());
        System.out.println("user: " + credentials.getUser());*/

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            
            return "index";
        }
        
        return "index";
    }
}
