package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
public @Data class Cameriere {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String cognome;
	private Integer eta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate assunzione;
	private Integer durataContratto;
	private Long numeroDiTelefono;
	
	@OneToMany(mappedBy = "cameriere", cascade = {CascadeType.REMOVE})
	private List<Ordinazione> ordinazioni;
	
	
	/*public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public String getCognome() {return cognome;}
	public void setCognome(String cognome) {this.cognome = cognome;}
	
	public Integer getEtà() {return età;}
	public void setEtà(Integer età) {this.età = età;}
	
	public LocalDate getAssunzione() {return assunzione;}
	public void setAssunzione(LocalDate assunzione) {this.assunzione = assunzione;}
	
	public Integer getDurataContratto() {return durataContratto;}
	public void setDurataContratto(Integer durataContratto) {this.durataContratto = durataContratto;}
	
	public Long getNumeroDiTelefono() {return numeroDiTelefono;}
	public void setNumeroDiTelefono(Long numeroDiTelefono) {this.numeroDiTelefono = numeroDiTelefono;}*/

	
}
