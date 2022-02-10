package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Iban;
import de.telekom.sea7.banking.base.Zahlung;

public class ZahlungImpl implements Zahlung {

	private int zahlung_id;
	private String empfaenger;
	private float betrag;
	private String verwendungszweck;
	private boolean echtzeitueberweisung;
	private int ibanid;
	private Iban iban;
	
	/* Muss ich machen, da wir uns einen eigenen Konstruktor gebaut haben. */
	public ZahlungImpl() {
    	
	}
	
	public ZahlungImpl(int zahlung_id, String empfaenger, float betrag, String verwendungszweck,
			boolean echtzeitueberweisung, int ibanid) {
		this.zahlung_id = zahlung_id;
		this.empfaenger = empfaenger;
		this.betrag = betrag;
		this.verwendungszweck = verwendungszweck;
		this.echtzeitueberweisung = echtzeitueberweisung;
		this.ibanid = ibanid;
		}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}
	
	
	
	public int getZahlung_Id() {
		return zahlung_id;
	}

	public void setZahlung_Id(int zahlung_id) {
		this.zahlung_id = zahlung_id;
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
		return zahlung_id +" " + empfaenger +" "+ betrag + "€ "+" "+ verwendungszweck +"  "+ echtzeitueberweisung +" "+ ibanid;
	}
}