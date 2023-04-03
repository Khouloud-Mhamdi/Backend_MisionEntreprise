package com.example.gazelec.sport.models;




import java.sql.Date;
import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Entraîneur")
public class Entraîneur {
	@Id 
	@Column(name="Id_Entraîneur")
	@GeneratedValue(strategy = GenerationType.AUTO )
    private Long id ; 
	private String nom;
	private String prénom;
	private String email;
	private String téléphone;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date naissance   ;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="Id_Discipline", referencedColumnName="Id_Discipline")
	private Discipline discipline ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrénom() {
		return prénom;
	}
	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTéléphone() {
		return téléphone;
	}
	public void setTéléphone(String téléphone) {
		this.téléphone = téléphone;
	}
	
	public Discipline getDiscipline() {
		return discipline;
	}
	
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	
	
	
	public Entraîneur(String nom, String prénom, String email, String téléphone, Date naissance) {
		super();
		this.nom = nom;
		this.prénom = prénom;
		this.email = email;
		this.téléphone = téléphone;
		this.naissance = naissance;
	}
	public Entraîneur(String nom, String prénom, String email, String téléphone, Date naissance,
			Discipline discipline) {
		super();
		this.nom = nom;
		this.prénom = prénom;
		this.email = email;
		this.téléphone = téléphone;
		this.naissance = naissance;
		this.discipline = discipline;
	}
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	
	public Entraîneur() {
		super();
		
	}
	
}
