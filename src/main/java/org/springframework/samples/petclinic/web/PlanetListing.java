package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.samples.petclinic.model.Planet;

public class PlanetListing {
	private int count;
	private String next;
	private String previous;
	private List<Planet> results;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public List<Planet> getResults() {
		return results;
	}
	public void setResults(List<Planet> results) {
		this.results = results;
	}
	
}
