package de.telekom.sea7.banking;

import java.util.Scanner;
import de.telekom.sea7.banking.base.Application;
import de.telekom.sea7.banking.base.Depot;

public class BankingMenu {
	
	//Erzeugen eines Obkjekts vom Typ Scanner
	Scanner scanner = new Scanner(System.in);
	
	public String mainShow(Application appParent, Depot depot1) {
		
		System.out.println("Anzeige aller Zahlungen: 1");
		System.out.println("Anzeige der Zahlung mit der ID 6: 2");
		System.out.println("CSV-Datei-Anzeige: 3");
		System.out.println("CSV-Datei vom Depot Schreiben: 4");
		System.out.println("CSV-Datei Lesen: 5");
		System.out.println("Menu beenden: 9");
		String eingabe = scanner.next();
		// scanner.close();
		return eingabe;

	}

}
