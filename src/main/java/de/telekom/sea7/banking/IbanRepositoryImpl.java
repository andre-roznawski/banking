package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IbanRepositoryImpl {
	
	private IbanImpl iban; 
   	private Connection con;
	//private PreparedStatement psForInsert;
	private PreparedStatement psForSelect;
	private PreparedStatement psForAll;
	//private PreparedStatement psForUpdate;
   	

	//String sqlForInsert = "INSERT INTO zahlungen (Empfaenger, Betrag, Verwendungszweck, Echtzeitueberweisung, iban_id) VALUES ( ?, ? ,?, ?, ?)";
	String sqlForSelect = "SELECT * FROM iban WHERE id = ?";
	String sqlForAll = "SELECT * FROM iban";
	//String sqlForUpdate = "UPDATE zahlungen set Empfaenger = ?, Betrag = ?, Verwendungszweck = ?, Echtzeitueberweisung = ?, iban_id = ? WHERE ZahlungID = ? ";
		
	//Konstruktor
	public IbanRepositoryImpl(Connection con) throws SQLException {
		this.con = con;

		//psForInsert = con.prepareStatement(sqlForInsert);
		psForSelect = con.prepareStatement(sqlForSelect);
		psForAll = con.prepareStatement(sqlForAll);
		//psForUpdate = con.prepareStatement(sqlForUpdate);
	}
	public IbanImpl getIban(int id) {
		IbanImpl iban = new IbanImpl();
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche Spalten .
			psForSelect.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (ResultSet rs = psForSelect.executeQuery()) {
			while (rs.next()) {
				System.out.println("ID   |IBAN-NR");
				System.out.println(String.format("%5s|%30s", rs.getString(1), rs.getString(2)));
				int id1 = rs.getInt(1);
				iban.setIban_id(id1);		
				String ibannr = rs.getString(2);
				iban.setIban(ibannr);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iban;
	}

	public void getAll() {
		try (ResultSet rs = psForAll.executeQuery()) {
			System.out.println("ID   |IBAN-NR");
			while (rs.next()) {
				System.out.println(String.format("%5s|%30s", rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
