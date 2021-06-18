package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Cameriere;
import it.uniroma3.siw.spring.repository.CameriereRepository;

@Service
public class CameriereService {
	
	@Autowired
	private CameriereRepository cameriereRepository;
	
	@Transactional
	public Cameriere inserisci(Cameriere cameriere) {
		return cameriereRepository.save(cameriere);
	}

	@Transactional
	public List<Cameriere> tutti() {
		return (List<Cameriere>) cameriereRepository.findAll();
	}
	
	@Transactional
	public void rimuoviCameriere(Long id) {
		cameriereRepository.deleteById(id);
	}
	
	@Transactional
	public void modificaCameriere(Cameriere cameriere) {
		Cameriere cameriereDaModificare = cameriereRepository.findById(cameriere.getId()).get();
		
		cameriereDaModificare.setNome(cameriere.getNome());
		cameriereDaModificare.setCognome(cameriere.getCognome());
		cameriereDaModificare.setEtà(cameriere.getEtà());
		cameriereDaModificare.setAssunzione(cameriere.getAssunzione());
		cameriereDaModificare.setDurataContratto(cameriere.getDurataContratto());
		cameriereDaModificare.setNumeroDiTelefono(cameriere.getNumeroDiTelefono());
		
	}
	
	@Transactional
	public boolean alreadyExists(Cameriere cameriere) {
		List<Cameriere> camerieri = this.cameriereRepository.findByNome(cameriere.getNome());
		if (camerieri.size() > 0)
			return true;
		else 
			return false;
	}

	public Cameriere camerierePerId(Long id) {
		Optional<Cameriere> optional = cameriereRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

}
