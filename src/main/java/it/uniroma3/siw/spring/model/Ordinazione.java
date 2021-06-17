package it.uniroma3.siw.spring.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ordinazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalTime orario;
	
	@ManyToOne
	private Cameriere cameriere;
	
	@ManyToOne
	private Tavolo tavolo;
	
	@ManyToMany(mappedBy = "ordinazioni")
	private List<Birra> birre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalTime getOrario() {
		return orario;
	}
	
	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
}
