package de.telekom.sea7.banking;

import de.telekom.sea7.banking.base.Application;

public class Starter {

	public static void main(String[] args) {

		Application application = new ApplicationImpl(); // application = Referenzvariable

		application.run(args);
		// application.run (args); args könnte mit übergeben werden.
	}

}