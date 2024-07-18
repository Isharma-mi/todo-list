package com.example.TodoList.logic;

public class ListOfItems {
	private String name;
	
	public ListOfItems(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return this.name;
	}
}
