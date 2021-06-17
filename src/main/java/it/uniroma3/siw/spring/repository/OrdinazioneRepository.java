package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Ordinazione;

public interface OrdinazioneRepository extends CrudRepository<Ordinazione, Long> {
	
	public List<Ordinazione> findById(Long id);
	
}
