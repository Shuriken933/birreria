package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer numeroTavolo;
	private Integer numeroPosti;

	@ManyToOne
	private Sala sala;
	
	@OneToMany(mappedBy = "ordinazione", cascade = {CascadeType.REMOVE})
	private List<Ordinazione> ordinazioni;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(Integer posti) {
		this.numeroPosti = posti;
	}

	public Integer getNumeroTavolo() {
		return numeroTavolo;
	}

	public void setNumeroTavolo(Integer numeroTavolo) {
		this.numeroTavolo = numeroTavolo;
	}
}
