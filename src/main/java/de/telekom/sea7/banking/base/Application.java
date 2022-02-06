package de.telekom.sea7.banking.base;

public interface Application {

	void run(String[] args);

	void initAll(Depot<Zahlung> depot);

}