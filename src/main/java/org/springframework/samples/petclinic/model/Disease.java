package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "diseases")
public class Disease extends NamedEntity{
	@Size(min = 10, max = 1024)
	@Column(length=1024)     // Needed in some environments for strings longer than 255 characters
	private String description;
	
	@NotEmpty
	@ManyToMany
	Set<PetType> petTypeswithPrevalence;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<PetType> getPetTypeswithPrevalence() {
		return petTypeswithPrevalence;
	}

	public void setPetTypeswithPrevalence(Set<PetType> petTypeswithPrevalence) {
		this.petTypeswithPrevalence = petTypeswithPrevalence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((petTypeswithPrevalence == null) ? 0 : petTypeswithPrevalence.hashCode());
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
		Disease other = (Disease) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (petTypeswithPrevalence == null) {
			if (other.petTypeswithPrevalence != null)
				return false;
		} else if (!petTypeswithPrevalence.equals(other.petTypeswithPrevalence))
			return false;
		return true;
	}
	
	
}
