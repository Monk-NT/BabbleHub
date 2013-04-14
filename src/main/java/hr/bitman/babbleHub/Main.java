package hr.bitman.babbleHub;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	
	public static void main(String[] args) {
		PropertyConfigurator.configure(Main.class.getClassLoader().getResourceAsStream("log4j.properties"));
		BabbleHub babel = new BabbleHub();
		babel.run();
	}

}
