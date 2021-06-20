package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.BirraOrdinata;
import it.uniroma3.siw.spring.model.Ordinazione;
import it.uniroma3.siw.spring.repository.BirraOrdinataRepository;

@Service
public class BirraOrdinataService {
	
	/*@Autowired
	private BirraOrdinataRepository birraOrdinataRepository;
	
	@Transactional
	public List<BirraOrdinata> tutte(Ordinazione ordinazione){
		return (List<BirraOrdinata>) birraOrdinataRepository.findByOrdinazione(ordinazione);
	}*/

}
