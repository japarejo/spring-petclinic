package org.springframework.samples.petclinic.model.businessrulesexceptions;



public class ImpossibleDiseaseException extends Exception {
	
	private String disease;
	private String petType;
	
	public ImpossibleDiseaseException(String disease, String petType) {
		super("According to our vademencum a "+petType+" cannot develop "+disease);
		this.disease=disease;
		this.petType=petType;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}
}
