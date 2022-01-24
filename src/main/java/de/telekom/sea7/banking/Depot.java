package de.telekom.sea7.banking;

public interface Depot {

	Object getContent(int index);

	Object[] getListe();

	void setClear();

	boolean isEmpty();

	boolean isFull();

	int size();

	boolean contains(Object object);

	void setListe(Object element, int index);

	String toString();

	int indexOf(Object object);
	
	boolean checkIndex (int index);

}