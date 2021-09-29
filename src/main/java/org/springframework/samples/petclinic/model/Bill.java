package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;




@Audited
@Entity
public class Bill extends BaseEntity{		
	
	@OneToOne
	@NotAudited
	@JoinColumn(name="visit_id")
	Visit visit;
	
	@Positive
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
