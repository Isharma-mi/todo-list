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
				// If user input is incorrect  -> Restart process of asking what user wants to do
				System.out.println("ERROR: Please give a correct input!");
				continue;
			} else if(input.equals("1")) { 
				// Shows all created lists
				System.out.println();
				viewAllLists();
			} else if (input.equals("3")) {
				// Creates list
				System.out.println();
				createList();
			} else if (input.equals("4")){
				// Deletes a list
				System.out.println();
				deleteList();
			} else if (input.equals("5")) {
				// Creates item in a list
				System.out.println();
				createItem();
			} else if(input.equals("6")) {
				// Terminates program
				break;
			}
		}
	}
	
	/**
	 * Shows the name of all the lists made via the singelton instance.
	 */
	private void viewAllLists() {
		System.out.println("Lists created:");
		System.out.println(ListOfLists.getInstance());
	}
	
	/**
	 * Makes a new list based off user input.
	 */
	private void createList() {
		System.out.println("What do you want the name of the list to be?");
		String listName = this.scanner.nextLine().trim();
		boolean wasAdded = ListOfLists.getInstance().addList(listName);
		
		// Lets user know if list was made
		if (wasAdded) { 
			System.out.println("List successfully added");
		} else {
			System.out.println("ERROR: List already exists!");
		}
	}
	
	/**
	 * Deletes list based off user input.
	 */
	private void deleteList() {
		System.out.println("What list do you want to delete?");
		String listName = this.scanner.nextLine().trim();
		boolean wasDeleted = ListOfLists.getInstance().deleteList(listName);
		
		// Lets user know if list was deleted or not
		if (wasDeleted) {
			System.out.println("List successfully deleted!");
		} else {
			System.out.println("ERROR: List could NOT be found!");
		}

	}
	
	/** 
	 * Creates an item for a specific list
	 */
	private void createItem() {
		
		/*
		System.out.println("\nFor what list do you want to add the item to?");
		String listName = this.scanner.nextLine().trim();
		
		
		// Stops trying to make an item if list does NOT exist
		if (!ListOfLists.getInstance().checkListExists(listName)) {
			System.out.println("ERROR: Unable to find list!");
			return;
		}
		
		System.out.println("What is the name of the item?");
		String itemName = this.scanner.nextLine().trim();
		System.out.println("What is the descripiton of the item?");
		*/
		
	//	ListOfLists.getInstance().get("asd");
	
		// Create item (Call method in ListOfItems that will create a new Item w/the info given)
		// Add item to list
	}
}