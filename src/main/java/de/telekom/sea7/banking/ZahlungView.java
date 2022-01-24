package de.telekom.sea7.banking;

public interface ZahlungView {

	void zahlungAnzeigen(Zahlung zahlung);

	Zahlung getZahlung();

	void setZahlung(Zahlung otherZahlung);
	
	

}