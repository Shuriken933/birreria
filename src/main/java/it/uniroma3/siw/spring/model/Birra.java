package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Birra {

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBirrificio() {
		return birrificio;
	}

	public void setBirrificio(String birrificio) {
		this.birrificio = birrificio;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getDimesione() {
		return dimesione;
	}

	public void setDimesione(Integer dimesione) {
		this.dimesione = dimesione;
	}
}
