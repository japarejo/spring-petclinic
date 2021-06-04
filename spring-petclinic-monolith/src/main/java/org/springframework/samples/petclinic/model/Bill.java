package org.springframework.samples.petclinic.model;

import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.web.api.BaseEntityDeserializer;
import org.springframework.samples.petclinic.web.api.BaseEntitySerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Bill extends BaseEntity{		
		
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
