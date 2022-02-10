package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Iban;

public class IbanImpl implements Iban {
	
	private int iban_id;
	private String iban;
	
	public IbanImpl() {
		
	}
	
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
