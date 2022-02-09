package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Depot;
import de.telekom.sea7.banking.base.Zahlung;
import de.telekom.sea7.banking.base.ZahlungView;

public class ZahlungViewImpl implements ZahlungView {

	private Zahlung zahlung = new ZahlungImpl();

	public void zahlungAnzeigen(Depot depot , Zahlung zahlung) {
		System.out.println();
		System.out.println("Sicht Viewkonto: Zahlung "+(depot.indexOf(zahlung)+1));
		System.out.println("Name: " + zahlung.getEmpfaenger());
		//System.out.println("IBAN: " + zahlung.getIban());
		System.out.println("Betrag: " + zahlung.getBetrag());
		System.out.println("Verwendungszweck: " + zahlung.getVerwendungszweck());
		System.out.println("Echtzeitüberweisung? " + zahlung.getEchtzeitueberweisung());
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung otherZahlung) {
		this.zahlung = otherZahlung;
	}
}
