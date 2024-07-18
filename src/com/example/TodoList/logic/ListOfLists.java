package com.example.TodoList.logic;

import java.util.List;
import java.util.ArrayList;

public class ListOfLists {
	// TODO: Implement method to delete from the ArrayList
	
	private static ListOfLists instance;
	private List<ListOfItems> listsOfItems;
	
	/**
	 * Private constructor to implement singleton pattern. 
	 * Also intializes the list that will contain all user made lists.
	 */
	private ListOfLists() {
		listsOfItems = new ArrayList<>();
	}
	
	/**
	 * Used to create a ListOfLists obj or return the obj via singleton pattern.
	 * @return returns the singelton instance
	 */
	public static synchronized ListOfLists getInstance() {
		if (instance == null) {
			instance = new ListOfLists();
		}
		return instance;
	}
	
	/**
	 * Used to get a ref to the object containing all the lists made. 
	 * @return returns the List obj containing all the lists of items
	 */
	public List<ListOfItems> getListsOfItems() {
		return this.listsOfItems;
	}
	
	/**
	 * Used to add a individual list
	 * @param list used as ref to list that needs to be added
	 */
	public void addList(ListOfItems list) {
		this.listsOfItems.add(list);
	}
}
