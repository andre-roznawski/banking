package de.telekom.sea7.banking.base;

public interface Depot <T> {

	T getContent(int index);

	T[] getListe();

	void setClear();

	boolean isEmpty();

	boolean isFull();

	int size();

	boolean contains(T object);

	void setListe(T element, int index);

	String toString();

	int indexOf(T object);

	boolean checkIndex(int index);
	
	String getMessage();
	
	void setMessage(String message);

}