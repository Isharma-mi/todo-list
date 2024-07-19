package com.example.TodoList.logic;

import java.util.List;
import java.util.ArrayList;

public class ListOfItems {
	private String name;
	private List<Item> items;
	
	public ListOfItems(String name) {
		this.name = name;
		items = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
}
