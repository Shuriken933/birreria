package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.BirraOrdinata;
import it.uniroma3.siw.spring.model.Ordinazione;

public interface BirraOrdinataRepository extends CrudRepository<BirraOrdinata, Long> {
	
	public List<BirraOrdinata> findByOrdinazione(Ordinazione ordinazione);

}
