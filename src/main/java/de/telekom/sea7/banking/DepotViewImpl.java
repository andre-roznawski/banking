package de.telekom.sea7.banking;

public class DepotViewImpl {

	
	
	
	
	/*public String toString() {
		String ausgabe = "";
		for (int i = 0; i < liste.length; i++) {
			var element = liste[i];
			
			if (element == null)
				ausgabe = ausgabe + "null, ";
			else
				ausgabe += element.toString() + ", ";
		}
		return ausgabe;
	}*/
	
	public Zahlung zahlung = new ZahlungImpl();
	
	public void depotAnzeige(Depot depot) {
		//for (int i = 0; i < depot.size(); i++)
			
			
		System.out.println("Sicht Depotkonto:");	
		
	}
	
	//System.out.println("Sicht Depotkonto: " +zahlung.getEmpfaenger()+ " " + zahlung.getIban()+" "+zahlung.getBetrag());
	//System.out.println("Name: "+zahlung.getEmpfaenger());
	//System.out.println("IBAN: "+zahlung.getIban());
	//System.out.println("Betrag: "+zahlung.getBetrag());
	//System.out.println("Verwendungszweck: "+zahlung.getVerwendungszweck());
	//System.out.println("Echtzeitüberweisung? "+zahlung.isEchtzeitueberweisung());
	
	
	
	
}
