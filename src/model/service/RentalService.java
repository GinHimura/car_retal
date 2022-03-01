package model.service;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	
	private TaxtService taxService;
	
	public RentalService(Double pricePerHour, Double pricePerDay, TaxtService taxService) {
		super();
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;
		
		double basicPayament;
		if(hours <= 12.0) {
			basicPayament = Math.ceil(hours) * pricePerHour;
		} else {
			basicPayament = Math.ceil(hours / 24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayament);
		
		carRental.setInvoice(new Invoice(basicPayament, tax));
	}
}
