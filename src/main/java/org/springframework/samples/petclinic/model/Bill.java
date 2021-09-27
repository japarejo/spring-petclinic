package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RevisionEntity;




@Audited
@Entity
public class Bill extends BaseEntity{		
	
	@OneToOne
	@NotAudited
	Visit visit;
	
	double amount;
	
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
