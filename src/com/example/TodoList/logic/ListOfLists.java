package com.example.TodoList.logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ListOfLists {
	private static ListOfLists instance;
	private List<ListOfItems> lists;
	
	/**
	 * Private constructor to implement singleton pattern. 
	 * Also intializes the list that will contain all user made lists.
	 */
	private ListOfLists() {
		lists = new ArrayList<>();
	}
	
	/**
	 * Creates a ListOfLists obj or return the obj via singleton pattern.
	 * @return returns the singelton instance
	 */
	public static synchronized ListOfLists getInstance() {
		if (instance == null) {
			instance = new ListOfLists();
		}
		return instance;
	}
	
	/**
	 * Gets a ref to the object containing all the lists. 
	 * @return returns the List obj containing all the lists of items
	 */
	public List<ListOfItems> getLists() {
		return this.lists;
	}

	/**
	 * Adds an individual list.
	 * @param listName used to set the name of the list user wants to add.
	 * @return returns boolean letting caller know if list was added
	 */
	public boolean addList(String listName) {
		Iterator<ListOfItems> it = this.lists.iterator();
		boolean canAdd = true;
		
		// Loops thru all the lists of items in overall list
		while (it.hasNext()) {
			if (it.next().getName().equals(listName)) {
				canAdd = false;
				break;
			}
		}
		
		// Adds list and lets user know
		if (canAdd) {
			this.lists.add(new ListOfItems(listName));
		}
		
		// Lets caller know if list was added
		return canAdd;
	}
	
	/**
	 * Deletes an individual list.
	 * @param listName String used to find the list user wants to delete.
	 * @return returns boolean letting caller know if list was deleted.
	 */
	public boolean deleteList(String listName) {
		Iterator<ListOfItems> it = this.lists.iterator();
		boolean wasDeleted = false;
		
		// Loops thru all the lists of items in the overall list
		while (it.hasNext()) {
			if (it.next().getName().equals(listName)) {
				it.remove();
				wasDeleted = true;
				break;
			}
		}
		
		// Lets caller know if list was deleted
		return wasDeleted;
	}
}
