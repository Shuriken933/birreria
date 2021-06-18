package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.model.Ordinazione;
import it.uniroma3.siw.spring.repository.OrdinazioneRepository;

@Service
public class OrdinazioneService {
	
	@Autowired
	private OrdinazioneRepository ordinazioneRepository;
	
	@Transactional
	public Ordinazione inserisci(Ordinazione ordinazione) {
		return ordinazioneRepository.save(ordinazione);
	}

	@Transactional
	public List<Ordinazione> tutti() {
		return (List<Ordinazione>) ordinazioneRepository.findAll();
	}
	
	@Transactional
	public void rimuoviOrdinazione(Long id) {
		ordinazioneRepository.deleteById(id);
	}
	
	@Transactional
	public void modificaOrdinazione(Ordinazione ordinazione) {
		Ordinazione ordinazioneDaModificare = ordinazioneRepository.findById(ordinazione.getId()).get();
		
		ordinazioneDaModificare.setId(ordinazione.getId());
		ordinazioneDaModificare.setOrario(ordinazione.getOrario());
		
	}
	
	@Transactional
	public boolean alreadyExists(Ordinazione ordinazione) {
		List<Ordinazione> ordinazioni = this.ordinazioneRepository.findById(ordinazione.getId());
		if (ordinazioni.size() > 0)
			return true;
		else 
			return false;
	}

}
