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
					+ "\n2: Create a list"
					+ "\n3: Delete a list"
					+ "\n4: View all items from a list"
					+ "\n5: Add item to a list"
					+ "\n6: Delete item from a list"
					+ "\n7: Import from csv file"
					+ "\n8: Export to csv file"
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
				viewAllLists();
			} else if (input.equals("2")) {
				// Creates list
				System.out.println();
				createList();
			} else if (input.equals("3")) {
				// Deletes a list
				System.out.println();
				deleteList();
			} else if (input.equals("4")) {
				// View all items 
				System.out.println();
				viewItemsOfList();
			} else if (input.equals("5")) {
				// Creates item in a list
				System.out.println();
				addItem();
			} else if (input.equals("6")) {
				// Deletes item from a list
				System.out.println();
				deleteItem();
			} else if(input.equals("9")) {
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
	private void addItem() {
		System.out.println("For what list do you want to add the item to?");
		String listName = this.scanner.nextLine().trim();
		
		ListOfItems list = ListOfLists.getInstance().getList(listName);
		
		// Stops trying to create item if list does NOT exist
		if (list == null) {
			System.out.println("ERROR: List could NOT be found!");
			return;
		}
		
		System.out.printf("For list: %s, What is the name of the item?\n", list.getName());
		String itemName = this.scanner.nextLine().trim();
		
		// Stops trying to create item if item already exists
		if (list.getItem(itemName) != null) {
			System.out.println("ERROR: Item already exists!");
			return;
		}
		
		System.out.printf("For item: %s, What is the description of the item (Press enter for no description)?\n", itemName);
		String description = this.scanner.nextLine().trim();
		
		boolean wasAdded = list.addItem(itemName, description);
		
		if (wasAdded) {
			System.out.println("Item successfuly created and added to list");
		} else {
			System.out.printf("ERROR: %s could NOT be added to %s\n", itemName, list.getName());
		}
		
	}
	
	/**
	 * Deletes an item from a specific list
	 */
	private void deleteItem() {
		System.out.println("For what list do you want to delete the item from?");
		
		String listName = this.scanner.nextLine().trim();
		
		ListOfItems list = ListOfLists.getInstance().getList(listName);
		
		// Stop trying to delete item if list does NOT exist 
		if (list == null) {
			System.out.println("ERROR: List could NOT be found!");
			return;
		}
		
		System.out.printf("For list: %s, What is the name of the item?\n", listName);
		String itemName = this.scanner.nextLine().trim();
		boolean wasDeleted = list.deleteItem(itemName);
		
		if (wasDeleted) {
			System.out.println("Item successfully deleted from list!");
		} else {
			System.out.printf("ERROR: %s could not be deleted from %s\n", itemName, listName);
		}
	}

	/**
	 * Shows info on all the items in a list	
	 */
	private void viewItemsOfList() {
		System.out.println("For what list do you want to see the items for?");
		String listName = this.scanner.nextLine().trim();
		
		ListOfItems list = ListOfLists.getInstance().getList(listName);
		
		// Stop trying to view items if list does NOT exist
		if (list == null) {
			System.out.println("ERROR: List could NOT be found!");
			return;
		}
		
		System.out.println(list);
	}
}