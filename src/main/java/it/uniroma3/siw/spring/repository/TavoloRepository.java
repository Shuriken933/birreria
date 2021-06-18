package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long> {

	/*public List<Tavolo> findByNumero(Integer numero);*/
}
