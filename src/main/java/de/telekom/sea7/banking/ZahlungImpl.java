package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Zahlung;

public class ZahlungImpl implements Zahlung {

	private String empfaenger;
	private String iban;
	private float betrag;
	private String verwendungszweck;
	private boolean echtzeitueberweisung;

	public String getEmpfaenger() {
		return empfaenger;
	}

	public void setEmpfaenger(String empfaenger) {
		// if(empfaenger)
		this.empfaenger = empfaenger;
	}

	public String getIban() {
		return iban;
	}

	public float getBetrag() {
		return betrag;
	}

	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}

	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}

	public boolean isEchtzeitueberweisung() {
		return echtzeitueberweisung;
	}

	public void setEchtzeitueberweisung(boolean echtzeitueberweisung) {
		this.echtzeitueberweisung = echtzeitueberweisung;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}
	public String toString() {
		return empfaenger+" "+iban+" "+betrag+"€ "+verwendungszweck+"  "+echtzeitueberweisung;
	}
}