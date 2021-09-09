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
	@Size(min = 10, max = 1024)
	@Column(length=1024)     // Needed in some environments for strings longer than 255 characters
	private String description;
	
	@OneToOne(optional = false)
	private Visit visit;
	

	@ManyToOne(optional = false)
	private Disease disease;
	
	@ManyToOne(optional = false)
	private Vet vet;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((disease == null) ? 0 : disease.hashCode());
		result = prime * result + ((vet == null) ? 0 : vet.hashCode());
		result = prime * result + ((visit == null) ? 0 : visit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diagnose other = (Diagnose) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (disease == null) {
			if (other.disease != null)
				return false;
		} else if (!disease.equals(other.disease))
			return false;
		if (vet == null) {
			if (other.vet != null)
				return false;
		} else if (!vet.equals(other.vet))
			return false;
		if (visit == null) {
			if (other.visit != null)
				return false;
		} else if (!visit.equals(other.visit))
			return false;
		return true;
	}

	
	
}
