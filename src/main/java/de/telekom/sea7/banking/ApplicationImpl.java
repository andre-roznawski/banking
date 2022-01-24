package de.telekom.sea7.banking;

public class ApplicationImpl implements Application {

	public void run() {
		ZahlungView zahlungView = new ZahlungViewImpl();
		Zahlung zahlung = new ZahlungImpl();
		Zahlung zahlung1 = new ZahlungImpl();
		
		Depot depot = new DepotImpl();
		
		zahlung.setEmpfaenger("Harry Mueller");
		zahlung.setIban("DE123456789");
		zahlung.setBetrag (1000);
		zahlung.setVerwendungszweck ("Rechnung xyz");
		zahlung.setEchtzeitueberweisung(false);
		zahlung1.setEmpfaenger("Eva Maier");
		zahlung1.setIban("SE3456789");
		zahlung1.setBetrag(2000);
		zahlung1.setVerwendungszweck ("Mitgliedsbeitrag Reitverein");
		zahlung1.setEchtzeitueberweisung(true);
		
		depot.setListe(zahlung, 0);
		depot.setListe(zahlung1, 1);
		System.out.println("Depotanzeige:" +depot.toString());
		
		System.out.println(depot.getContent(8));
		//try {
			depot.checkIndex(10);
		//} catch (Exception e) {
		//	System.out.println("Hier ist das Problem: " +e.getMessage());
		//}
		
		//zahlungView.zahlungAnzeigen(zahlung);
		//zahlungView.zahlungAnzeigen(zahlung1);
		
		
		//System.out.println("Erstes objektorientiertes Programm");
	}


	
	
	
	
}
