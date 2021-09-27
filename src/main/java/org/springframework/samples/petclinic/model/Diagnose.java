package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.service.businessrules.ValidatePossibleDisease;


@Entity
@Table(name="diagnoses")
@ValidatePossibleDisease
public class Diagnose extends BaseEntity{
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Vet getVet() {
		return vet;
	}

	public void setVet(Vet vet) {
		this.vet = vet;
	}

	@Size(min = 10, max = 1024)
	@Column(length=1024)     // Needed in some environments for strings longer than 255 characters
	private String description;
	
	@OneToOne(optional = false)
	private Visit visit;
	
	@ManyToOne(optional = false)
	private Disease disease;
	
	@ManyToOne(optional = false)
	private Vet vet;
}
