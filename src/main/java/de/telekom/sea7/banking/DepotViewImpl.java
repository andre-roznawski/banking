package de.telekom.sea7.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.telekom.sea7.banking.base.Depot;
import de.telekom.sea7.banking.base.DepotView;
import de.telekom.sea7.banking.base.Zahlung;

public class DepotViewImpl implements DepotView {

	/*
	 * public String toString() { String ausgabe = ""; for (int i = 0; i <
	 * liste.length; i++) { var element = liste[i];
	 * 
	 * if (element == null) ausgabe = ausgabe + "null, "; else ausgabe +=
	 * element.toString() + ", "; } return ausgabe; }
	 */

	public Zahlung zahlung = new ZahlungImpl();

	@Override
	public void depotAnzeige(Depot depot) {
		System.out.println();
		System.out.println("Sicht Depotkonto:"+depot.getMessage());
		for (int i = 0; i < depot.size(); i++) {
			if (depot.getContent(i) instanceof Zahlung) {
				System.out.println("Zahlung " + (i + 1) + " : " + ((Zahlung) depot.getContent(i)));
			}
		}
	}

	public void csvView(Depot depot) {
		System.out.println();
		System.out.println("CSV View Depot:"+depot.getMessage());
		System.out.println("Zahlungs-Nr.,Empfänger,IBAN,Betrag,Verwendungszweck,Echtzeitüberweisung");
		for (int i = 0; i < depot.size(); i++) {
			if (depot.getContent(i) instanceof Zahlung) {
				Zahlung zahlung = ((Zahlung) depot.getContent(i));
				System.out.println(String.format("%02d,%s,%s,%.2f,%s,%s", i+1, zahlung.getEmpfaenger(), zahlung.getIban(),
						zahlung.getBetrag(), zahlung.getVerwendungszweck(), zahlung.isEchtzeitueberweisung()));
			}
		}
	}

	public void csvFileWrite(Depot depot) {
			System.out.println();
		    System.out.println("Schreiben der CSV-Datei"+depot.getMessage());
		    String line = "Zahlungs-Nr.,Empfänger,IBAN,Betrag,Verwendungszweck,Echtzeitüberweisung";
		    System.out.println(line);
			// BufferedWriter bw = new BufferedWriter(new FileWriter("ausgabedatei.csv"));
			// f1 = new FileWriter("ausgabedatei.csv");
			try {
				try (FileWriter f1 = new FileWriter("ausgabedatei.csv")) //try with resources 
				{
					f1.write(line + "\n");
					for (int i = 0; i < depot.size(); i++) {
						if (depot.getContent(i) instanceof Zahlung) {
							Zahlung zahlung = ((Zahlung) depot.getContent(i));
							line = (String.format("%02d,%s,%s,%.2f,%s,%s", i+1, zahlung.getEmpfaenger(),
									zahlung.getIban(), zahlung.getBetrag(), zahlung.getVerwendungszweck(),
									zahlung.isEchtzeitueberweisung()));
							System.out.println(line);
							f1.write(line + "\n");
						}
					}
				}
				//f1.close();
			} catch (IOException e) {
				System.out.println("OException e:" + e.getMessage());
			}
		}
	
	public void csvFileReader(Depot depot , String inputName) {
		File file = new File(inputName);

        if (!file.canRead() || !file.isFile()) {
            System.out.println(inputName+" ist nicht vorhanden oder nicht lesbar!!!");
            return;
        }
            
            BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(inputName));
            String zeile = null;
            System.out.println();
            while ((zeile = in.readLine()) != null) {
            	System.out.println("Gelesene Zeile: " + zeile);
                if(!zeile.startsWith("Zahlung")) {
                	String[] zeilensplitter = zeile.split(",");
                    
                    Zahlung zahlungIn = new ZahlungImpl();
                    zahlungIn.setEmpfaenger(zeilensplitter[1]);
                    zahlungIn.setIban(zeilensplitter[2]);
                    zahlungIn.setBetrag(Float.parseFloat(zeilensplitter[3]));
                    zahlungIn.setVerwendungszweck(zeilensplitter[4]);
                    zahlungIn.setEchtzeitueberweisung(Boolean.parseBoolean(zeilensplitter[5]));
        
                    depot.setListe(zahlungIn,Integer.parseInt(zeilensplitter[0])-1); 	
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    } 
}

// Stunde,Montag,Dienstag,Mittwoch,Donnerstag,Freitag
// 1,Mathematik,Deutsch,Englisch,Erdkunde,Politik
// 2,Sport,Deutsch,Englisch,Sport,Geschichte
// 3,Sport,"Religion (ev., kath.)",Kunst,,Kunst

// System.out.println("Sicht Depotkonto: " +zahlung.getEmpfaenger()+ " " +
// zahlung.getIban()+" "+zahlung.getBetrag());
// System.out.println("Name: "+zahlung.getEmpfaenger());
// System.out.println("IBAN: "+zahlung.getIban());
// System.out.println("Betrag: "+zahlung.getBetrag());
// System.out.println("Verwendungszweck: "+zahlung.getVerwendungszweck());
// System.out.println("Echtzeitüberweisung? "+zahlung.isEchtzeitueberweisung());