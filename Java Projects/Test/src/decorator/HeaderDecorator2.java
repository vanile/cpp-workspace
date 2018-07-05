package decorator;

public class HeaderDecorator2 extends TicketDecorator {

	  public HeaderDecorator2(Component c) {
	    super(c);
	  }

	  public void printTicket() {
	    this.printHeader();
	    super.printTicket();
	  }

	  public void printHeader() {
	    System.out.println("@@ Header Two @@");
	  }
}
