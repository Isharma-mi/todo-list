package com.example.TodoList.archive;

import java.util.Scanner;

import com.example.TodoList.file.TodoFileReader;
import com.example.TodoList.file.TodoFileWriter;
import com.example.TodoList.logic.ListOfItems;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.database.ListDAO;

public class OptionsController {
	private Scanner scanner;
	
	public OptionsController() {
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Shows the name of all the lists made via the singelton instance.
	 */
	void viewAllLists() {
		System.out.println("Lists created:");
		System.out.println(ListOfLists.getInstance());
	}
	
	/**
	 * Creates a new list based off user input.
	 */
	void createList() {
		System.out.println("What do you want the name of the list to be?");
		String listName = this.scanner.nextLine().trim();
		
		// Done to make importing/exporting and viewing of lists more clean
		if (listName.startsWith("-") || listName.isEmpty()) {
			System.out.println("ERROR: Please enter a list name that does NOT begin with - and is NOT empty!");
			return;
		}
		
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
	void deleteList() {
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
	 * Creates an item for a specific list.
	 */
	void addItem() {
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
		
		if (list.getItem(itemName) != null) {
			// Stops trying to create item if item already exists
			System.out.println("ERROR: Item already exists!");
			return;
		} else if (itemName.startsWith("-") || itemName.isEmpty()) {
			// Done to make importing/exporting and viewing of items more clean
			System.out.println("ERROR: Please enter an item name that does NOT beign with - and is NOT empty!");
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
	 * Deletes an item from a specific list.
	 */
	void deleteItem() {
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
	void viewItemsOfList() {
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

	/**
	 * Exports lists and items to a file of with a specific format. 
	 */
	void exportToFile() {
		System.out.println("What do you want the file name to be called?");
		String fileName = this.scanner.nextLine();
		
		boolean wroteToFile = TodoFileWriter.writeToFile(fileName);
		
		if (wroteToFile) {
			System.out.println("Lists and items saved!");
		} else {
			System.out.println("ERROR: Unable to save to file!");
		}
	}
	
	/**
	 * Imports lists and items from a file. 
	 * NOTE: Formatting of file needs to be same as exported file.
	 */
	void importFromFile() {
		System.out.println("From what file do you want to import list from? (NOTE: Needs to be of same format file was exported as)");
		String fileName = this.scanner.nextLine();
		
		boolean readFromFile = TodoFileReader.importFile(fileName);
		
		if (readFromFile) {
			System.out.println("Lists and items imported!");
		} else {
			System.out.println("ERROR: Unable to import from the file!");
		}	
	}
	
	/**
	 * Exports lists and items to a SQL database and table(s).
	 */
	void exportToSQL() {
		System.out.println("What is the name of the server you would like to connect to?");
		String serverName = this.scanner.nextLine();
		System.out.println("What do you want the database to be called that will store the information? NOTE: It needs to be a new db.");
		String dbName = this.scanner.nextLine();
		System.out.println("What is the username of the SQL user? NOTE: This needs to be a user able to create a db and table");
		String userName = this.scanner.nextLine();
		System.out.println("What is the password of the SQL user?");
		String password = this.scanner.nextLine();
		
		ListDAO.exportToSQL(serverName, dbName, userName, password);
		
	}
}
