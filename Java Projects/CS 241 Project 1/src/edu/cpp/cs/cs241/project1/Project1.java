package edu.cpp.cs.cs241.project1;

class Project1 {

	public static void main(String[] args) {
		UI ui = new UI();
		ui.addSequenceToTree(ui.getInitialSequence());
		ui.menu();
	}

}
