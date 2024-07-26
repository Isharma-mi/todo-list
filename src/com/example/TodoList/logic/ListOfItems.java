package com.example.TodoList.logic;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ListOfItems {
	private String name;
	private List<Item> items;
	
	public ListOfItems(String name) {
		this.name = name;
		items = new ArrayList<>();
	}

	@Override
	public String toString() {
		Iterator<Item> it = this.items.iterator();
		StringBuilder listDetails = new StringBuilder(this.name);		
		listDetails.append("\n");
		
		while (it.hasNext()) {
			listDetails.append("- ");
			listDetails.append(it.next());
			listDetails.append("\n");
		}
		
		return listDetails.toString();
	}


	/**
	 * Getter accessing the name of the list.
	 * @return returns the name of the list
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter accessing the items in the list.
	 * @return returns thh items in the list
	 */
	public List<Item> getItems() {
		return this.items;
	}
	
	/**
	 * Used for testing purposes.
	 * Removes all the items stored in the list.
	 */
	public void reset() {
		this.items.clear();
	}
	
	/**
	 * Gets a ref to a specific item
	 * @param itemName used to find item
	 * @return returns the item (NOTE: Can return null)
	 */
	public Item getItem(String itemName) {
		Item item = null;
		
		for (Item i: this.items) {
			if (i.getName().equals(itemName)) {
				item = i;
			}
		}
		return item;
	}
	
	/**
	 * Adds an item to the list
	 * @param name used to set the name of the item
	 * @param description used to give additional info about the item
	 * @return returns boolean letting caller know if item was added or not
	 */
	public boolean addItem(String name, String description) {
		// Checks if an item with the same name does NOT exist already
		boolean canAdd = !this.items.stream().anyMatch(i -> (i.getName().equals(name)));

		if (canAdd) {
			this.items.add(new Item(name, description));
		}
		
		return canAdd;
	}
	
	/**
	 * Deletes an item from the list
	 * @param itemName used to find the item
	 * @return returns boolean letting caller know if item was deleted or not
	 */
	public boolean deleteItem(String itemName) {
		// Checks if an item with the same name exists already
		boolean canDelete = false;
		
		if (this.items.remove(this.getItem(itemName))) {
			canDelete = true;
		}
		
		return canDelete;
	}
}
