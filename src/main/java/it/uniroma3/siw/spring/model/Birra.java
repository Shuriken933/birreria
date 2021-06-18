package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
public @Data class Birra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String birrificio;
	private String colore;
	private String descrizione;
	private Integer dimesione;
	private Integer prezzo;
	
	@ManyToMany
	private List<Ordinazione> ordinazioni;

}
