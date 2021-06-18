package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.service.TavoloService;

@Component
public class TavoloValidator implements Validator {

	@Autowired
	private TavoloService tavoloService;
	
	private static final Logger logger = LoggerFactory.getLogger(TavoloValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroTavolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroPosti", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.tavoloService.alreadyExists((Tavolo)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Tavolo.class.equals(aClass);
	}
}
