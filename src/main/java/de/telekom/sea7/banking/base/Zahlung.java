package de.telekom.sea7.banking.base;

public interface Zahlung {

	String getEmpfaenger();

	void setEmpfaenger(String empfaenger);

	float getBetrag();

	void setBetrag(float betrag);

	String getVerwendungszweck();

	void setVerwendungszweck(String verwendungszweck);

	boolean getEchtzeitueberweisung();

	void setEchtzeitueberweisung(boolean echtzeitueberweisung);

	String toString();
	
	int getZahlung_Id();
	
	void setZahlung_Id(int zahlung_id);
	
	int getIbanid();
	
	void setIbanid(int ibanid);
	
	

}