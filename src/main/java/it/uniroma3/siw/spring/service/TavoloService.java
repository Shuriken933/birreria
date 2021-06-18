package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Ordinazione;
import it.uniroma3.siw.spring.model.Tavolo;
import it.uniroma3.siw.spring.repository.TavoloRepository;

@Service
public class TavoloService {
	
	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Transactional
	public Tavolo inserisci(Tavolo tavolo) {
		return tavoloRepository.save(tavolo);
	}

	@Transactional
	public List<Tavolo> tutti() {
		return (List<Tavolo>) tavoloRepository.findAll();
	}
	
	@Transactional
	public void rimuoviTavolo(Long id) {
		tavoloRepository.deleteById(id);
	}
	
	@Transactional
	public void modificaTavolo(Tavolo tavolo) {
		Tavolo tavoloDaModificare = tavoloRepository.findById(tavolo.getId()).get();
		
		tavoloDaModificare.setNumeroTavolo(tavolo.getNumeroTavolo());
		tavoloDaModificare.setNumeroPosti(tavolo.getNumeroPosti());
		
	}
	
	@SuppressWarnings("static-access")
	@Transactional
	public boolean alreadyExists(Tavolo tavolo) {
		Optional<Tavolo> tavoloDaCercare = this.tavoloRepository.findById(tavolo.getId());
		if (tavoloDaCercare.empty() != null) {
			return true;
		} 
		return false;
		/*List<Tavolo> tavoli = this.tavoloRepository.findByNumero(tavolo.getNumeroTavolo());
		if (tavoli.size() > 0)
			return true;
		else 
			return false;*/
	}
	
	@Transactional
	public Tavolo tavoloPerId(Long id) {
		Optional<Tavolo> optional = tavoloRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			return null;
	}

}
