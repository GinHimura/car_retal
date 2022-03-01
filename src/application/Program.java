package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		Scanner sc = new Scanner(System.in);
		CarRental rental = null;
		RentalService service = null;
	
		System.out.println("Enter rentral data");
		System.out.print("Car model: ");
		String name = sc.nextLine();
		System.out.print("Pickup (dd/MM/yyyy hh:ss): ");
		Date dataPikup = sdf.parse(sc.nextLine());
		System.out.print("Return (dd/MM/yyyy hh:ss): ");
		Date dataReturn = sdf.parse(sc.nextLine());
		System.out.print("Enter price per hour: ");
		Double priceHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		Double priceDay = sc.nextDouble();
		rental = new CarRental(dataPikup, dataReturn, new Vehicle(name));
		service = new RentalService(priceHour, priceDay, new BrazilTaxService());
		
	    service.processInvoice(rental);
	    
	    System.out.println("INVOICE:");
	    System.out.println("Basic payment: " + String.format("%.2f", rental.getInvoice().getBasicPayment()));
	    System.out.println("Tax: " + String.format("%.2f", rental.getInvoice().getTax()));
	    System.out.println("Total payment: " + String.format("%.2f", rental.getInvoice().getTotalPayment()));
	    
	    sc.close();
	}			
}
