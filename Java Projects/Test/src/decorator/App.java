package decorator;

public class App {

	public static void main(String[] args) {
		SalesTicket salesTicket = new SalesTicket();
		//salesTicket.printTicket();
		
//		Component comp =
//	    	new HeaderDecorator3(
//		    				new FooterDecorator1(
//		    						new HeaderDecorator1(salesTicket) ));
		Component comp = new HeaderDecorator1(new HeaderDecorator2(new HeaderDecorator3(salesTicket)));
		comp.printTicket();

	}

}
