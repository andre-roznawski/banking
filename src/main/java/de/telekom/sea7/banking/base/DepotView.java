package de.telekom.sea7.banking.base;

public interface DepotView {

	void depotAnzeige(Depot depot);
	
	void csvView(Depot depot );
	
	void csvFileWrite(Depot depot);
	
	void csvFileReader(Depot depot , String inputName);

}