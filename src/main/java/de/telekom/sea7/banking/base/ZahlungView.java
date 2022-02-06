package de.telekom.sea7.banking.base;

public interface ZahlungView {

	void zahlungAnzeigen(Depot depot, Zahlung zahlung);

	Zahlung getZahlung();

	void setZahlung(Zahlung otherZahlung);
	
	

}

