package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;



public class SportEvent {
	Integer id;
	SportEventData data;
	List<Odd> odds;	
	
	public SportEvent() {
		odds=new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SportEventData getData() {
		return data;
	}

	public void setData(SportEventData data) {
		this.data = data;
	}

	public List<Odd> getOdds() {
		return odds;
	}

	public void setOdds(List<Odd> odds) {
		this.odds = odds;
	}
	
	
}
