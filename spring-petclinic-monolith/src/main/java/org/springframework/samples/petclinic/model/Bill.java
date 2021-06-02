package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.petclinic.web.api.BaseEntityDeserializer;
import org.springframework.samples.petclinic.web.api.BaseEntitySerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Audited
@Entity
public class Bill extends BaseEntity{		
	
	@OneToOne
	@NotAudited
	@JsonSerialize(using = BaseEntitySerializer.class)
	@JsonDeserialize(using = BaseEntityDeserializer.class)
	Visit visit;
	
	@Min(0)
	double amount;
	
	@NotEmpty
	String concept;

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}
	
	
}
