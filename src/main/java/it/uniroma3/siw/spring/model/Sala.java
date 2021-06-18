package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public @Data class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String descrizione;
	
	@OneToMany(mappedBy = "sala", cascade = {CascadeType.REMOVE})
	private List<Tavolo> tavoli;

	
	/*public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	public Long getId() {return id;}

	public String getDescrizione() {return descrizione;}
	public void setDescrizione(String descrizione) {this.descrizione = descrizione;}*/
	
}
