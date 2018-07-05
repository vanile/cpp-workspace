import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Alexander Kimea
 *
 */
class Project3 {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		launch();
	}
	
	public static void launch() {
		UI ui = new UI();
		ui.menu();
	}
}