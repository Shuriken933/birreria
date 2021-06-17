package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Sala;
import it.uniroma3.siw.spring.service.SalaService;

@Component
public class SalaValidator implements Validator {
	
	@Autowired
	private SalaService salaService;
	
	private static final Logger logger = LoggerFactory.getLogger(SalaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.salaService.alreadyExists((Sala)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Sala.class.equals(aClass);
	}
}
