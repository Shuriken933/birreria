package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Birra;

public interface BirraRepository extends CrudRepository<Birra, Long> {
	
	public List<Birra> findByNome(String nome);

}
