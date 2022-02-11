package de.telekom.sea7.banking.base;

import de.telekom.sea7.banking.Entity;
import de.telekom.sea7.banking.Table;

@Entity
@Table(name ="iban")
public interface Iban {

	int getIban_id();

	void setIban_id(int iban_id);

	String getIban();

	void setIban(String iban);

}