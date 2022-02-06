package de.telekom.sea7.banking;

import java.util.Iterator;

import de.telekom.sea7.banking.base.Application;
import de.telekom.sea7.banking.base.Depot;
import de.telekom.sea7.banking.base.DepotView;
import de.telekom.sea7.banking.base.Zahlung;
import de.telekom.sea7.banking.base.ZahlungView;
//import de.telekom.sea7.banking.implementation.DepotIteratorImplTh;

public class ApplicationImpl implements Application {
	private Depot<Zahlung> depot1 = new DepotImpl<Zahlung>();
	private DepotView depotview = new DepotViewImpl();
	private Zahlung zahlung = new ZahlungImpl();
	private Zahlung zahlung1 = new ZahlungImpl();
	private Zahlung zahlung2 = new ZahlungImpl();

	public void initAll(Depot<Zahlung> depot) {

		zahlung.setEmpfaenger("Harry Mueller");
		zahlung.setIban("DE123456789");
		zahlung.setBetrag(1000);
		zahlung.setVerwendungszweck("Rechnung xyz");
		zahlung.setEchtzeitueberweisung(false);
		zahlung1.setEmpfaenger("Eva Maier");
		zahlung1.setIban("SE3456789");
		zahlung1.setBetrag(2000);
		zahlung1.setVerwendungszweck("Mitgliedsbeitrag Reitverein");
		zahlung1.setEchtzeitueberweisung(true);
		zahlung2.setEmpfaenger("Georg Kanz");
		zahlung2.setIban("DE45456789");
		zahlung2.setBetrag(33.33f);
		zahlung2.setVerwendungszweck("ebay Kauf vom 11.01.22");
		zahlung2.setEchtzeitueberweisung(false);

		depot.setListe(zahlung, 0);
		depot.setListe(zahlung1, 1);
		depot.setListe(zahlung2, 2);
	}

	public void run(String[] args) {
		ZahlungView zahlungView = new ZahlungViewImpl();

		Depot<Zahlung> depot = new DepotImpl<Zahlung>();
		depot.setMessage("depot-Out");
		// weg###Depot<Zahlung> depot1 = new DepotImpl<Zahlung>();
		depot1.setMessage("depot-In");
		// weg###DepotView depotview = new DepotViewImpl();

		this.initAll(depot);
		zahlungView.zahlungAnzeigen(depot, zahlung);
		zahlungView.zahlungAnzeigen(depot, zahlung1);
		// ###zahlungView.zahlungAnzeigen(depot, zahlung2);

		depotview.depotAnzeige(depot);
		// ###depotview.csvView(depot);
		// ###depotview.csvFileWrite(depot);

		// ###depotview.csvFileReader(depot1,"eingabedatei.csv");

		depotview.depotAnzeige(depot1);

		BankingMenu unserMenu = new BankingMenu();

		while (true) {
			switch (unserMenu.mainShow(this, depot1)) {
			case "1":
				depotview.depotAnzeige(depot);
				break;
			case "2":
				zahlungView.zahlungAnzeigen(depot, zahlung2);
				break;
			case "3":
				depotview.csvView(depot);
				break;
			case "4":
				depotview.csvFileWrite(depot);
				break;
			case "5":
				depotview.csvFileReader(depot1, "eingabedatei.csv");
				break;
			case "9":
				System.out.println("Danke das wars");
				return;
			default:
				System.out.println("Bitte Zahlen von 1 - 5 eingeben");

			}
			System.out.println();

		}

		// System.out.println("Depotanzeige:" +depot.toString());
		// System.out.println(((Zahlung)depot.getContent(0)).getEmpfaenger());
		// System.out.println(((Zahlung)depot.getContent(0)));

		// System.out.println(depot.getContent(8));
		// try {
		// depot.checkIndex(10);
		// } catch (Exception e) {
		// System.out.println("Hier ist das Problem: " +e.getMessage());
		// }

		// System.out.println("Erstes objektorientiertes Programm");
	}

}