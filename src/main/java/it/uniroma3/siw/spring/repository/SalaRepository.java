package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Sala;

public interface SalaRepository extends CrudRepository<Sala, Long> {
	
	public List<Sala> findByNome(String nome);

}
