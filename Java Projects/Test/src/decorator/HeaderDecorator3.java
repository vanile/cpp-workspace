package decorator;

public class HeaderDecorator3 extends TicketDecorator {

	  public HeaderDecorator3(Component c) {
	    super(c);
	  }

	  public void printTicket() {	    
		  this.printHeader();
	    super.printTicket();
	    //this.printHeader();
	  }

	  public void printHeader() {
	    System.out.println("@@ Header Three @@");
	  }
}
