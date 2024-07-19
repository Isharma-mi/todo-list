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
	
	@Override
	public String toString() {
		StringBuilder names = new StringBuilder();
		
		if (lists.size() == 0) {
			names.append("ERROR: No lists found!");
		} else {
			for (int i = 0; i < lists.size(); i++) {
				names.append(lists.get(i).getName());
				if (i < lists.size() - 1) {
					names.append("\n");
				}
			}
		}
		
		// Returns the names of each list
		return names.toString();
	}
	
	/**
	 * Gets a ref to the object containing all the lists. 
	 * @return returns the List obj containing all the lists of items
	 */
	public List<ListOfItems> getLists() {
		return this.lists;
	}
	
	/**
	 * Gets a ref to a specifc list
	 * @param listName used to find the list
	 * @return returns the list (NOTE: Can return null)
	 */
	public ListOfItems get(String listName) {
		ListOfItems listOfItems = null;
		
		/*
		 *  Loop thru each list of items in overall lists to find the one specifed
		 *  Use for-each loop since just want to get a ref to the obj if found
		 */
		for (ListOfItems l: this.lists) {
			if (l.getName().equals(listName)) {
				listOfItems = l;
			}
		}
		
		return listOfItems;
	}
		
	/**
	 * Adds an individual list.
	 * @param listName used to set the name of the list user wants to add.
	 * @return returns boolean letting caller know if list was added
	 */
	public boolean addList(String listName) {
		boolean canAdd = !this.lists.stream().anyMatch(l -> l.getName().equals(listName));
		
		if (canAdd) {
			this.lists.add(new ListOfItems(listName));
		}
		
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
		
		// Iterates thru all lists of items.
		while (it.hasNext()) {
			if (it.next().getName().equals(listName)) {
				it.remove();
				wasDeleted = true;
				break;
			}
		}
		
		return wasDeleted;
	}
}
