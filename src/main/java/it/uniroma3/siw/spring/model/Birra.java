package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	private Double dimensione;
	private Double prezzo;
	
	@Column(nullable = true, length = 64)
    private String immagine;
	
	@ManyToMany(mappedBy = "birre")
	private List<Ordinazione> ordinazioni;
	
	/*@OneToMany(mappedBy = "birra", cascade = {CascadeType.REMOVE})
	private List<BirraOrdinata> birreOrdinate;*/

	
}
