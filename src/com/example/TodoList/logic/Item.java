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
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
