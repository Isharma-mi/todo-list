package com.example.TodoList.ui;

import java.util.Scanner;

public class UIController {
	private Scanner scanner;
	private OptionsController oc;
	
	public UIController() {
		this.scanner = new Scanner(System.in);
		oc = new OptionsController();
	}
	
	/**
	 * Allows for user to interact with the program thru the console.
	 */
	public void start() {
		while (true) {
			// Asks user what they want to do
			// TODO: Implement all options
			System.out.println("---------------Welcome to the TodoList! What would you like to do?------------------"
					+ "\n1: View name of all lists"
					+ "\n2: Create a list"
					+ "\n3: Delete a list"
					+ "\n4: View all items from a list"
					+ "\n5: Add item to a list"
					+ "\n6: Delete item from a list"
					+ "\n7: Import from file"
					+ "\n8: Export to file"
					+ "\n9: Exit program");
			
			// Gets user input
			String input = this.scanner.nextLine();

			if (!input.matches("[123456789]")) {
				// If user input is incorrect  -> Restart process of asking what user wants to do
				System.out.println("ERROR: Please give a correct input!");
				continue;
			} else if(input.equals("1")) { 
				// Shows all created lists
				System.out.println();
				oc.viewAllLists();
			} else if (input.equals("2")) {
				// Creates list
				System.out.println();
				oc.createList();
			} else if (input.equals("3")) {
				// Deletes a list
				System.out.println();
				oc.deleteList();
			} else if (input.equals("4")) {
				// View all items 
				System.out.println();
				oc.viewItemsOfList();
			} else if (input.equals("5")) {
				// Creates item in a list
				System.out.println();
				oc.addItem();
			} else if (input.equals("6")) {
				// Deletes item from a list
				System.out.println();
				oc.deleteItem();
			} else if (input.equals("7")){
				// Imports lists and items from a file 
				System.out.println();
				oc.importFromFile();
			} else if (input.equals("8")) {
				// Saves lists and items to a file
				System.out.println();
				oc.exportToFile();
			} else if(input.equals("9")) {
				// Terminates program
				break;
			}
		}
	}
}