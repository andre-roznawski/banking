package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import de.telekom.sea7.banking.base.Application;
import de.telekom.sea7.banking.base.Depot;
import de.telekom.sea7.banking.base.DepotView;
import de.telekom.sea7.banking.base.Iban;
import de.telekom.sea7.banking.base.Zahlung;
import de.telekom.sea7.banking.base.ZahlungView;
//import de.telekom.sea7.banking.implementation.DepotIteratorImplTh;
import java.sql.*;
import java.util.Properties;

public class ApplicationImpl implements Application {

	// Erzeugen eines Objektes vom Typ Zahlung. Quasi erzeugen einer Zahlung
	private Zahlung zahlung = new ZahlungImpl();
	private Zahlung zahlung1 = new ZahlungImpl();
	private Zahlung zahlung2 = new ZahlungImpl();

	// Erzeugen einer ZahlungView
	ZahlungView zahlungView = new ZahlungViewImpl();
	
	// Erzeugen eines Depot's
	private Depot<Zahlung> depot = new DepotImpl<Zahlung>();
	private Depot<Zahlung> depot1 = new DepotImpl<Zahlung>();
	
	// Erzeugen einer Depotsicht
	private DepotView depotview = new DepotViewImpl();

	private ZahlungRepositoryImpl zahlungdbquest;
	private IbanRepositoryImpl ibandbquest;
    
	//Erezugen eines Objekts vom Typ Iban
	private Iban iban1 = new IbanImpl();

	// Variablendeklaration für die Connection
	final String URL = "jdbc:mariadb://localhost:3306/myfirstdb";
	final String user = "admin";
	final String password = "start123";

	// "initAll" Methode, setzen einer Zahlung ins Depot
	public void initAll(Depot<Zahlung> depot) {

		 depot.setListe(zahlung, 0); // setzen der Zahlung ins Depot am index 0
		 depot.setListe(zahlung1, 1); // setzen der Zahlung1 ins Depot am index 1
		 depot.setListe(zahlung2, 2); // setzen der Zahlung2 ins Depot am index 2
	}

	// "run" Methode, wird durch die Starter.java aufgerufen.
	public void run(String[] args) {

		// Connection zur DB
		try (Connection con = DriverManager.getConnection(URL, user, password)) {
			System.out.println("Verbindung erfolgreich hergestellt!");
			try (Statement stm = con.createStatement()) {

				ibandbquest = new IbanRepositoryImpl(con);
				zahlungdbquest = new ZahlungRepositoryImpl(con);
				zahlungdbquest.setIbandbquest(ibandbquest);
				
				
				zahlung = zahlungdbquest.getZahlung(6);
				System.out.println("\nEmpfänger :" + zahlung.getEmpfaenger());
				zahlung1 = zahlungdbquest.getZahlung(7);
				System.out.println("\nBetrag: " + zahlung1.getBetrag());
				zahlungdbquest.getAll();
				zahlung = zahlungdbquest.getZahlung(8);
				zahlung.setEmpfaenger("Erika Berger");
				zahlungdbquest.saveZahlung(zahlung);
				zahlung1 = zahlungdbquest.getZahlung(3);
				// iban1 = ibandbquest.getIban(zahlung1.getIbanid());
				// ibandbquest.getAll();
				zahlung1.getIban().getIban_id();

//				Zahlung zahlung3 = new ZahlungImpl(0, "Katrin Roznawski", 111.50f, "Aldi Einkauf", false, 4); 
//                zahlungdbquest.saveZahlung(zahlung3); 
//                zahlungdbquest.getZahlung(9);

				
				//Erzeugung eines Objekts vom Typ Bankingmenü
				BankingMenu unserMenu = new BankingMenu();
				System.out.println();
				System.out.println();
				while (true) {
					switch (unserMenu.mainShow(this, depot1)) {
					case "1":
						zahlungdbquest.getAll();
						break;
					case "2":
						zahlungdbquest.getZahlung(6);
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
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		// depot.setMessage("depot-Out");
		// weg###Depot<Zahlung> depot1 = new DepotImpl<Zahlung>();
		// depot1.setMessage("depot-In");
		// weg###DepotView depotview = new DepotViewImpl();

		// this.initAll(depot);
		// zahlungView.zahlungAnzeigen(depot, zahlung);
		// zahlungView.zahlungAnzeigen(depot, zahlung1);
		// ###zahlungView.zahlungAnzeigen(depot, zahlung2);

		// depotview.depotAnzeige(depot);
		// ###depotview.csvView(depot);
		// ###depotview.csvFileWrite(depot);

		// ###depotview.csvFileReader(depot1,"eingabedatei.csv");
		// depotview.depotAnzeige(depot1);
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