package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Zahlung;

public class ZahlungImpl implements Zahlung {

	private int id;
	private String empfaenger;
	private float betrag;
	private String verwendungszweck;
	private boolean echtzeitueberweisung;
	private int ibanid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIbanid() {
		return ibanid;
	}

	public void setIbanid(int ibanid) {
		this.ibanid = ibanid;
	}

	public String getEmpfaenger() {
		return empfaenger;
	}

	public void setEmpfaenger(String empfaenger) {
		// if(empfaenger)
		this.empfaenger = empfaenger;
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

	public boolean getEchtzeitueberweisung() {
		return echtzeitueberweisung;
	}

	public void setEchtzeitueberweisung(boolean echtzeitueberweisung) {
		this.echtzeitueberweisung = echtzeitueberweisung;
	}

	public String toString() {
		return id +" "+ empfaenger +" "+ betrag + "€ "+" "+ verwendungszweck +"  "+ echtzeitueberweisung +" "+ ibanid;
	}

	
	
}