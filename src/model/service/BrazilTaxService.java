package model.service;

public class BrazilTaxService implements TaxtService{

	
	public double tax(Double amount) {
		if(amount <= 100) {
			return amount * 0.2;
		}
		return amount * 0.15;
	}
}
