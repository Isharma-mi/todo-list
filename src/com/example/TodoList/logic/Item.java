package com.example.TodoList.logic;

public class Item {
	private String name;
	private String description;
	
	public Item(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	@Override
	public String toString() {
		StringBuilder itemDetails = new StringBuilder(this.name);
		
		if (this.description != "") {
			itemDetails.append("\n\t");
			itemDetails.append(this.description);
		}
		
		return itemDetails.toString();
	}
	
	/**
	 * Gets the name of the item
	 * @return name of the item
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the description of the item
	 * @return returns description of the item
	 */
	public String getDescription() {
		return this.description;
	}
}
