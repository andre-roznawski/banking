package de.telekom.sea7.banking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class ZahlungDBImpl {

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

	private PreparedStatement psForInsert;
	private PreparedStatement psForSelect;
	private PreparedStatement psForAll;

	public ZahlungDBImpl(Connection con) throws SQLException {
		this.con = con;

		psForInsert = con.prepareStatement(sqlForInsert);
		psForSelect = con.prepareStatement(sqlForSelect);
		psForAll = con.prepareStatement(sqlForAll);
	}

//		public void setZahlung(Statement stm, String empfaenger, float betrag, String verwendungszweck, boolean echtzeitueberweisung, int iban_id) {
//			try (ResultSet rs = stm.executeQuery("insert into zahlungen (Empfaenger, Betrag, Verwendungszweck, Echtzeitueberweisung, iban_id) values ('empfaenger', 'betrag', 'verwendungszweck', 'echtzeitueberweisung', 'iban_id')"){ 
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//}

	public void setZahlung(String empfaenger, float betrag, String verwendungszweck, boolean echtzeitueberweisung, int iban_id) {
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
			//while (rs.next()) {
				System.out.println("\nDas ist ein erfolgreiches Update auf Zahlungen von: " + rows + "Datensaetzen");
				//System.out.println(String.format("%30s,%.2f,%30s,%8s,%2d"), empfaenger, betrag, verwendungszweck, echtzeitueberweisung, iban_id);
			//}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

		public void getZahlung(int id) {
		try {
			// 1 bedeutet das erste Fragezeichen, bei weiteren Fragezeichen, zusätzliche
			// Zeilen.
			psForSelect.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (ResultSet rs = psForSelect.executeQuery()) {
			while (rs.next()) {
				System.out.println(String.format("%2s,%30s,%2s,%30s,%8s", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getAll() {
		try (ResultSet rs = psForAll.executeQuery()) {
			while (rs.next()) {
				System.out.println(String.format("%2s,%30s,%2s,%30s,%8s", rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getEmpfaenger(Statement stm) {
		try (ResultSet rs = stm.executeQuery("select Empfaenger from zahlungen where ZahlungID=1")) {
			while (rs.next()) {
				empfaenger = rs.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return empfaenger;
	}

	public void setEmpfaenger(String empfaenger) {
		// if(empfaenger)
		this.empfaenger = empfaenger;
	}

	public String getIban(Statement stm) {
		try (ResultSet rs = stm.executeQuery("select IbanNR from iban where id=1")) {
			while (rs.next()) {
				iban = rs.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return iban;
	}

	public float getBetrag(Statement stm) {
		try (ResultSet rs = stm.executeQuery("select Betrag from zahlungen where ZahlungID=1")) {
			while (rs.next()) {
				betrag = rs.getFloat(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return betrag;
	}

	public void setBetrag(float betrag) {
		this.betrag = betrag;
	}

	public String getVerwendungszweck(Statement stm) {
		try (ResultSet rs = stm.executeQuery("select Verwendungszweck from zahlungen where ZahlungID=1")) {
			while (rs.next()) {
				verwendungszweck = rs.getString(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return verwendungszweck;
	}

	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}

	public boolean getEchtzeitueberweisung(Statement stm) {
		try (ResultSet rs = stm.executeQuery("select Echtzeitueberweisung from zahlungen where ZahlungID=1")) {
			while (rs.next()) {
				echtzeitueberweisung = rs.getBoolean(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return echtzeitueberweisung;
	}

	public void setEchtzeitueberweisung(boolean echtzeitueberweisung) {
		this.echtzeitueberweisung = echtzeitueberweisung;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String toString() {
		return empfaenger + " " + iban + " " + betrag + "€ " + verwendungszweck + "  " + echtzeitueberweisung;
	}
}
