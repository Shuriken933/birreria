package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Birra;
import it.uniroma3.siw.spring.repository.BirraRepository;

@Service
public class BirraService {
	
	@Autowired
	private BirraRepository birraRepository;
	
	@Transactional
	public Birra inserisci(Birra birra) {
		return birraRepository.save(birra);
	}

	@Transactional
	public List<Birra> tutte() {
		return (List<Birra>) birraRepository.findAll();
	}
	
	@Transactional
	public void rimuoviBirra(Long id) {
		birraRepository.deleteById(id);
	}
	
	@Transactional
	public void modificaBirra(Birra birra) {
		Birra birraDaModificare = birraRepository.findById(birra.getId()).get();
		
		birraDaModificare.setNome(birra.getNome());
		birraDaModificare.setBirrificio(birra.getBirrificio());
		birraDaModificare.setColore(birra.getColore());
		birraDaModificare.setDescrizione(birra.getDescrizione());
		birraDaModificare.setDimensione(birra.getDimensione());
		birraDaModificare.setPrezzo(birra.getPrezzo());
	}
	
	@Transactional
	public boolean alreadyExists(Birra birra) {
		List<Birra> birre = this.birraRepository.findByNome(birra.getNome());
		if (birre.size() > 0)
			return true;
		else 
			return false;
	}

	@Transactional
	public Birra birraPerId(Long id) {
		Optional<Birra> optional = birraRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}


}
