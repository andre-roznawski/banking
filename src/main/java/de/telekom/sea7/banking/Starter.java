package de.telekom.sea7.banking;

public class Starter {

	public static void main(String[] args) {
		
		Application application = new ApplicationImpl();   //application = Referenzvariable
		
		application.run ();
		//application.run (args); args könnte mit übergeben werden.
	}

}
