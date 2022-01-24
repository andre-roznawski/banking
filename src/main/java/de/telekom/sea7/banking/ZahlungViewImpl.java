package de.telekom.sea7.banking;

public class ZahlungViewImpl implements ZahlungView {
	
	private Zahlung zahlung = new ZahlungImpl();

	public void zahlungAnzeigen(Zahlung zahlung) {
		System.out.println("Sicht Viewkonto: " +zahlung);
		System.out.println("Name: "+zahlung.getEmpfaenger());
		System.out.println("IBAN: "+zahlung.getIban());
		System.out.println("Betrag: "+zahlung.getBetrag());
		System.out.println("Verwendungszweck: "+zahlung.getVerwendungszweck());
		System.out.println("Echtzeitüberweisung? "+zahlung.isEchtzeitueberweisung());
		}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung otherZahlung) {
		this.zahlung = otherZahlung;
	}


		
	
}
