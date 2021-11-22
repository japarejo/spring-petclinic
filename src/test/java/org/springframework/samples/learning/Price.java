package org.springframework.samples.learning;




public class Price {
    private int price = 0;

	public Price add(Price sumPrice) {
        return new Price(this.getPrice() + sumPrice.getPrice());
	}

	public int getAmount() {
		return price;
				
	}
	
	public Price() {}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Price(int price) {
		super();
		this.price = price;
	}
	
	
}
