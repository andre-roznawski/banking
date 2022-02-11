package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Application;

public class Starter {

	public static void main(String[] args) {
		
        //Erzeugt ein neues Application Objekt, mit der Referenzvariablen application 
		Application application = new ApplicationImpl(); //ApplicationImpl, weil dort die Methode "run" steht.
		application.run(args); //Aufruf der Methode "run" in der ApplicationImpl, über die SST Datei Application. Dort muss die Methode ebenfalls stehen.
		// application.run (args); args könnte mit übergeben werden.
	}

}