package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.service.BirraService;

@Component
public class BirraValidator implements Validator {
	
	@Autowired
	private BirraService birraService;
	
	private static final Logger logger = LoggerFactory.getLogger(BirraValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dimensione", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.birraService.alreadyExists((Birra)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Birra.class.equals(aClass);
	}

}
