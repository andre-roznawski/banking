package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import de.telekom.sea7.banking.base.Application;
import de.telekom.sea7.banking.base.Depot;
import de.telekom.sea7.banking.base.DepotView;
import de.telekom.sea7.banking.base.Zahlung;
import de.telekom.sea7.banking.base.ZahlungView;
//import de.telekom.sea7.banking.implementation.DepotIteratorImplTh;
import java.sql.*;

import java.util.Properties;

public class ApplicationImpl implements Application {
	private Depot<Zahlung> depot1 = new DepotImpl<Zahlung>();
	private DepotView depotview = new DepotViewImpl();
	private Zahlung zahlung = new ZahlungImpl();
	private Zahlung zahlung1 = new ZahlungImpl();
	private Zahlung zahlung2 = new ZahlungImpl();
	private ZahlungDBImpl testzahlung; 

	// Variablen für die Connection
	final String URL = "jdbc:mariadb://localhost:3306/myfirstdb";
	final String user = "admin";
	final String password = "start123";

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
        //Connection zur DB
		try (Connection con = DriverManager.getConnection(URL, user, password)) {
			System.out.println("Verbindung erfolgreich hergestellt!");
			testzahlung = new ZahlungDBImpl(con);
			try (Statement stm = con.createStatement()) {

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
testzahlung.getZahlung(2);
testzahlung.getAll();
testzahlung.setZahlung("Helmut Meister", 612.14f, "Rechnung Heizung Brösel", true, 4); 
//System.out.println(testzahlung.getEmpfaenger(stm));
//System.out.println(testzahlung.getIban(stm));
//System.out.println(testzahlung.getBetrag(stm));
//System.out.println(testzahlung.getVerwendungszweck(stm));
//System.out.println(testzahlung.getEchtzeitueberweisung(stm));



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
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (SQLException e3) {
			e3.printStackTrace();
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