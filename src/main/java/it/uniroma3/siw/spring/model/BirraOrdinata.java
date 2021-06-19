package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class BirraOrdinata {
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer quantità;
	
	@ManyToOne
	private Birra birra;
	
	@ManyToOne
	private Ordinazione ordinazione;*/
	

}
