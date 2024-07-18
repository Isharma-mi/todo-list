package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfItems;
import com.example.TodoList.logic.ListOfLists;
import java.util.Scanner;

public class UserInterface {
	private Scanner scanner;
	
	public UserInterface() {
		this.scanner = new Scanner(System.in);
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
					+ "\n2: View items of a list"
					+ "\n3: Create a list"
					+ "\n4: Delete a list"
					+ "\n5: Create item for a list"
					+ "\n6: Import from csv file"
					+ "\n7: Export to csv file"
					+ "\n6: Exit program");
			
			// Gets user input
			String input = this.scanner.nextLine();

			if (!input.matches("[123456]")) {
				// If user input is incorrect 
				// Restart process of asking what user wants to do
				System.out.println("ERROR: Please give a correct input!");
				continue;
			} else if(input.equals("1")) { 
				viewAllLists();
			}else if (input.equals("3")) {
				// Creates list
				createList();
			} else if(input.equals("6")) {
				// Terminates program
				break;
			}
		}
	}
	
	/**
	 * Used to make a new list based off user input.
	 */
	private void createList() {
		System.out.println("What do you want the name of the list to be?");
		String listName = this.scanner.nextLine();
		
		ListOfLists listOfListsRef = ListOfLists.getInstance();
		
		// Checks if the name already exists
		boolean listExists = listOfListsRef.getListsOfItems().stream().anyMatch(obj -> obj.getName().equals(listName));
		
		if (listExists) { 
			System.out.println("ERROR: List with same exists already!");
		} else {
			// Creates list and adds it to list of lists
			listOfListsRef.addList(new ListOfItems(listName));
		}
	}
	
	/**
	 * Used to see the name of all the lists made via the singelton instance.
	 */
	private void viewAllLists() {
		System.out.println("Lists created:");
		
		// Loop thru each individual list
		for (ListOfItems l: ListOfLists.getInstance().getListsOfItems()) {
			System.out.println("\t" + l);
		}
	}
}
