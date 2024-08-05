package com.example.TodoList.ui;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ItemsManager {
	private static ItemsManager instance;
	
	/**
	 * Private constructor to ensure singleton pattern
	 */
	private ItemsManager() {
	}
	
	/**
	 * Creates an ItemsManager obj or returns a ref to existing singelton.
	 * @return returns the singleton instance
	 */
	public static synchronized ItemsManager getInstance() {
		if (instance == null) {
			instance = new ItemsManager();
		}
		
		return instance;
	}

	public Parent getView() {
		VBox items = new VBox();
		
		return items;
	}
}