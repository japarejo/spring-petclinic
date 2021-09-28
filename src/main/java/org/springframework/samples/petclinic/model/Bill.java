package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Bill extends BaseEntity {

	@Positive
	Integer total;
	
	@NotEmpty
	@Size(min = 3)	
	String concept;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	Owner owner;
}
