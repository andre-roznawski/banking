package de.telekom.sea7.banking;

import java.util.Scanner;
import de.telekom.sea7.banking.base.Application;
import de.telekom.sea7.banking.base.Depot;

public class BankingMenu {

	// public void mainShow() {
	public String mainShow(Application appParent, Depot depot1) {
		/// ###Zu
		// ((Application) app).;
		// Demo appParent.initAll(depot1);
		// appParent.run(null);
		// appParent.depotview.depotAnzeige(depot1);
		Scanner scanner = new Scanner(System.in);

		System.out.println("DepotAnzeige : 1");
		System.out.println("Anzeige Zahlungen: 2");

		System.out.println("CSV-Datei-Anzeige: 3");
		System.out.println("CSV-Datei vom Depot Schreiben: 4");
		System.out.println("CSV-Datei Lesen: 5");
		System.out.println("Menu beenden: 9");
		String eingabe = scanner.next();
		// scanner.close();
		return eingabe;

	}

}
