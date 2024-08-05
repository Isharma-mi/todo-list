package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;
import com.example.TodoList.logic.Item;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class ItemsManager {
	public VBox items;
	
	private static ItemsManager instance;
	
	/**
	 * Private constructor to ensure singleton pattern
	 */
	private ItemsManager() {
		this.items = new VBox();
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

	/**
	 * Gets the items and descriptions of each list.
	 * @return returns VBox containing the items and descriptions
	 */
	public Parent getView() {
		return this.items;
	}
	
	/**
	 * Shows the items and their related descriptions in different TextFields.
	 * Using TextFields to allow for easier editing by the user.
	 * @param listName Name of list whose items will be shown
	 */
	public void modifyItems(String listName) {
		// TODO: Format textfields to not look so much like a textfield
		// TODO: Format textfields to grow and shrink according to size
		// TODO: Format textfields to be indented
		// TODO: Add listeners to allow items and descriptions to be modified
		// TODO: Deal with empty textfields
		this.items.getChildren().clear();
		
		// Gets the list
		ListOfItems list = ListOfLists.getInstance().getList(listName);
		
		// Gets the items in the list
		for (Item i: list.getItems()) {
			// Creates textfield for list and items
			TextField itemName = new TextField(i.getName());
			TextField itemDescription = new TextField(i.getDescription());

			// Adds textfields to Vbox containing all items and descriptions
			this.items.getChildren().addAll(itemName, itemDescription);
		}
	}
}