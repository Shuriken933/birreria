package it.uniroma3.siw.spring.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
public @Data class Ordinazione {
	
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
	
	/*@OneToMany(mappedBy = "ordinazione", cascade = {CascadeType.REMOVE})
	private List<BirraOrdinata> birreOrdinate;*/

	
}
