package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Cameriere;

public interface CameriereRepository extends CrudRepository<Cameriere, Long> {
	
	public List<Cameriere> findByNome(String nome);

}
