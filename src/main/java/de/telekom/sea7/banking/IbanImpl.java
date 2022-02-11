package de.telekom.sea7.banking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

import de.telekom.sea7.banking.base.Iban;

@Entity
@Table(name ="iban")
public class IbanImpl implements Iban {

@Id
	private int iban_id;
	@Column(name ="IbanNR")
	private String iban;
	
	//Standartkonstruktor, muss eingefügt werden, da wir einen eigenen geschrieben haben.
	public IbanImpl() {
	}
	
	//Selbst geschriebener Konstruktor. 
	public IbanImpl(int iban_id, String iban) {
		this.iban_id = iban_id;
		this.iban = iban;
	}
	
	@Override
	public int getIban_id() {
		return iban_id;
	}
	
	@Override
	public void setIban_id(int iban_id) {
		this.iban_id = iban_id;
	}
	
	@Override
	public String getIban() {
		return iban;
	}
	
	@Override
	public void setIban(String iban) {
		this.iban = iban;
	}
	
}
