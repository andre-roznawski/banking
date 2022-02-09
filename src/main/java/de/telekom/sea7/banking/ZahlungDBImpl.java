package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class ZahlungDBImpl {
    private ZahlungImpl zahlung; 
    //Wird nicht mehr benötigt, da in der ZahlungImpl.java vorhanden.
	private String empfaenger;
	private String iban;
	private float betrag;
	private String verwendungszweck;
	private boolean echtzeitueberweisung;
	private int iban_id;
	private Connection con;

	String sqlForInsert = "INSERT INTO zahlungen (Empfaenger, Betrag, Verwendungszweck, Echtzeitueberweisung, iban_id) VALUES ( ?, ? ,?, ?, ?)";
	String sqlForSelect = "SELECT * FROM zahlungen WHERE ZahlungID = ?";
	String sqlForAll = "SELECT * FROM zahlungen";
	String sqlForUpdate = "UPDATE  zahlungen set ? = ? WHERE ZahlungID = ? ";
	
	private PreparedStatement psForInsert;
	private PreparedStatement psForSelect;
	private PreparedStatement psForAll;
	private PreparedStatement psForUpdate;

	public ZahlungDBImpl(Connection con) throws SQLException {
		this.con = con;

		psForInsert = con.prepareStatement(sqlForInsert);
		psForSelect = con.prepareStatement(sqlForSelect);
		psForAll = con.prepareStatement(sqlForAll);
		psForUpdate = con.prepareStatement(sqlForUpdate);
	}

	public void setZahlung(String empfaenger, float betrag, String verwendungszweck, boolean echtzeitueberweisung,
			int iban_id) {
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
			// Zeilen.
			psForInsert.setString(1, empfaenger);
			psForInsert.setFloat(2, betrag);
			psForInsert.setString(3, verwendungszweck);
			psForInsert.setBoolean(4, echtzeitueberweisung);
			psForInsert.setInt(5, iban_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int rows = psForInsert.executeUpdate();
			System.out.println("\nDas ist ein erfolgreiches Update auf Zahlungen von: " + rows + "Datensaetzen");
			System.out.println(String.format("%30s,%7.2f,%30s,%8s,%2d", empfaenger, betrag, verwendungszweck,
					echtzeitueberweisung, iban_id));
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
				System.out.println("ID   |Empfänger                     |Betrag      |Verwendungszweck                        |Echtzeitüberweisung");
				System.out.println(String.format("%5s|%30s|%10s €|%40s|%8s", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5)));
				int id1 = rs.getInt(1);
				zahlung.setId(id1);
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
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zahlung;
	}

	public void getAll() {
		try (ResultSet rs = psForAll.executeQuery()) {
			System.out.println("\nID   |Empfänger                     |Betrag      |Verwendungszweck                        |Echtzeitüberweisung");
			while (rs.next()) {
				System.out.println(String.format("%5s|%30s|%10s €|%40s|%8s", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	// Wird nicht mehr benötigt, da weiter oben über die getZahlungen Methode bereits integriert.	
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
	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}
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
		
	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}
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
		return zahlung.getId() + " " + zahlung.getEmpfaenger() + " " + zahlung.getBetrag() + " € " + zahlung.getVerwendungszweck()+ " " + zahlung.getEchtzeitueberweisung() + " " + zahlung.getIbanid();
					}
}
