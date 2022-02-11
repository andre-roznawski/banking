package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.SQLException;

import de.telekom.sea7.banking.base.Iban;
import de.telekom.sea7.banking.base.Zahlung;

import java.sql.*;

public class ZahlungRepositoryImpl {
	
	private ZahlungImpl zahlung;
	private Connection con;
	private IbanRepositoryImpl ibandbquest;
	
	private PreparedStatement psForInsert;
	private PreparedStatement psForSelect;
	private PreparedStatement psForAll;
	private PreparedStatement psForUpdate;

	String sqlForInsert = "INSERT INTO zahlungen (Empfaenger, Betrag, Verwendungszweck, Echtzeitueberweisung, iban_id) VALUES ( ?, ? ,?, ?, ?)";
	String sqlForSelect = "SELECT * FROM zahlungen WHERE ZahlungID = ?";
	String sqlForAll = "SELECT z.ZahlungID, z.Empfaenger, z.Betrag, z.Verwendungszweck, z.Echtzeitueberweisung, i.IbanNR  from zahlungen z left join iban i on z.iban_id = i.id";
	//String sqlForAll = "SELECT * FROM zahlungen";
	String sqlForUpdate = "UPDATE zahlungen set Empfaenger = ?, Betrag = ?, Verwendungszweck = ?, Echtzeitueberweisung = ?, iban_id = ? WHERE ZahlungID = ? ";
		
	public ZahlungRepositoryImpl(Connection con) throws SQLException {
		this.con = con;

		psForInsert = con.prepareStatement(sqlForInsert);
		psForSelect = con.prepareStatement(sqlForSelect);
		psForAll = con.prepareStatement(sqlForAll);
		psForUpdate = con.prepareStatement(sqlForUpdate);
	}

	public void saveZahlung(Zahlung zahlung) {

		if (zahlung.getZahlung_Id() == 0) {
			addZahlung(zahlung);
		} else {
			updateZahlung(zahlung);
		}
	}

	public void updateZahlung(Zahlung zahlung) {
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche Zeilen.
			psForUpdate.setString(1, zahlung.getEmpfaenger());
			psForUpdate.setFloat(2, zahlung.getBetrag());
			psForUpdate.setString(3, zahlung.getVerwendungszweck());
			psForUpdate.setBoolean(4, zahlung.getEchtzeitueberweisung());
			psForUpdate.setInt(5, zahlung.getIbanid());
			psForUpdate.setInt(6, zahlung.getZahlung_Id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int rows = psForUpdate.executeUpdate();
			System.out.println("\nDas ist ein erfolgreiches Update auf Zahlungen von: " + rows + " Datensaetzen");
			System.out.println(String.format("%30s,%7.2f,%30s,%8s,%2d", zahlung.getEmpfaenger(), zahlung.getBetrag(),
					zahlung.getVerwendungszweck(), zahlung.getEchtzeitueberweisung(), zahlung.getIbanid()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addZahlung(Zahlung zahlung) {
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
			// Zeilen.
			psForInsert.setString(1, zahlung.getEmpfaenger());
			psForInsert.setFloat(2, zahlung.getBetrag());
			psForInsert.setString(3, zahlung.getVerwendungszweck());
			psForInsert.setBoolean(4, zahlung.getEchtzeitueberweisung());
			psForInsert.setInt(5, zahlung.getIbanid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int rows = psForInsert.executeUpdate();
			System.out.println("\nDas ist ein erfolgreiches Update auf Zahlungen von: " + rows + "Datensaetzen");
			System.out.println(String.format("%30s,%7.2f,%30s,%8s,%2d", zahlung.getEmpfaenger(), zahlung.getBetrag(),
					zahlung.getVerwendungszweck(), zahlung.getEchtzeitueberweisung(), zahlung.getIbanid()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ZahlungImpl getZahlung(int id) {
		ZahlungImpl zahlung = new ZahlungImpl();
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
			// Spalten .
			psForSelect.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (ResultSet rs = psForSelect.executeQuery()) {
			while (rs.next()) {
				int id1 = rs.getInt(1);
				zahlung.setZahlung_Id(id1);
				String empfaenger = rs.getString(2);
				zahlung.setEmpfaenger(empfaenger);
				float betrag = rs.getFloat(3);
				zahlung.setBetrag(betrag);
				String verwendungszweck = rs.getString(4);
				zahlung.setVerwendungszweck(verwendungszweck);
				boolean echtzeitueberweisung = rs.getBoolean(5);
				zahlung.setEchtzeitueberweisung(echtzeitueberweisung);
				int ibanid = rs.getInt(6);
				zahlung.setIbanid(ibanid);
				Iban iban = null;
				iban = ibandbquest.getIban(ibanid);
				zahlung.setIban(iban);
				System.out.println("ID   |Empfänger                     |Betrag      |Verwendungszweck                        |Echtzeitüberweisung|IBAN-Nr");
				System.out.println(String.format("%5s|%-30s|%10s €|%-40s|%19s|%-15s", id1, empfaenger, betrag,
						verwendungszweck, echtzeitueberweisung, iban.getIban()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zahlung;
	}

	public void getAll() {
		try (ResultSet rs = psForAll.executeQuery()) {
			System.out.println("\nID   |Empfänger                     |Betrag      |Verwendungszweck                        |Echtzeitüberweisung|IBAN-NR");
			while (rs.next()) {
				System.out.println(String.format("%5s|%30s|%10s €|%40s|%19s|%17s", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setIbandbquest(IbanRepositoryImpl ibandbquest) {
		this.ibandbquest = ibandbquest;

	}
// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode bereits integriert.
//	public String getEmpfaenger(int id) {
//		try {
//			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
//			// Zeilen.
//			psForSelect.setInt(1, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try (ResultSet rs = psForSelect.executeQuery()) {
//			while (rs.next()) {
//				empfaenger = rs.getString(2);
//				System.out.println("\nDer Name des Empfänger ist: " + rs.getString(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return empfaenger;
//	}
//	
//	public void setEmpfaenger(String empfaenger, Zahlung zahlung) {
//		// if(empfaenger)
//		this.empfaenger = empfaenger;
//	}
//	// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode bereits integriert.
//	public int getIban_id(int id) {		
//		try {
//			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
//			// Zeilen.
//			psForSelect.setInt(1, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try (ResultSet rs = psForSelect.executeQuery()) {
//			while (rs.next()) {
//				iban_id = rs.getInt(6);
//				System.out.println("\nDie IBAN ID der Zahlung ist: " + rs.getString(6));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return iban_id;
//	}
	// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode
	// bereits integriert.
//	public float getBetrag(int id) {
//		try {
//			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
//			// Zeilen.
//			psForSelect.setInt(1, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try (ResultSet rs = psForSelect.executeQuery()) {
//			while (rs.next()) {
//				betrag = rs.getFloat(3);
//				System.out.println("\nDer Betrag der Überweisung ist: " + rs.getFloat(3)+" €");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return betrag;
//	}
//				
//	public void setBetrag(float betrag) {
//		this.betrag = betrag;
//	}
// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode bereits integriert.
//	public String getVerwendungszweck(int id) {
//		try {
//			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
//			// Zeilen.
//			psForSelect.setInt(1, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try (ResultSet rs = psForSelect.executeQuery()) {
//			while (rs.next()) {
//				verwendungszweck = rs.getString(4);
//				System.out.println("\nDer Verwendungszweck der Überweisung ist: " + rs.getString(4));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return verwendungszweck;
//	}
//		
//	public void setVerwendungszweck(String verwendungszweck) {
//		this.verwendungszweck = verwendungszweck;
//	}
// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode bereits integriert.
//	public boolean getEchtzeitueberweisung(int id) {
//		try {
//			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
//			// Zeilen.
//			psForSelect.setInt(1, id);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		try (ResultSet rs = psForSelect.executeQuery()) {
//			while (rs.next()) {
//				echtzeitueberweisung = rs.getBoolean(5);
//				System.out.println("\nEchtzeitüberweisung: " + rs.getBoolean(5));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return echtzeitueberweisung;
//	}

	public String toString() {
		return zahlung.getZahlung_Id() + " " + zahlung.getEmpfaenger() + " " + zahlung.getBetrag() + " € "
				+ zahlung.getVerwendungszweck() + " " + zahlung.getEchtzeitueberweisung() + " " + zahlung.getIbanid();
	}
}
