package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;
import com.example.TodoList.logic.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemsManager {
	public VBox items;
	
	private static ItemsManager instance;
	private static String currentList;
	
	/**
	 * Private constructor to ensure singleton pattern
	 */
	private ItemsManager() {
		// Initialize items to be empty, because launching program will have no list selected -> No items to show
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
	 * Sets the current list the items manager is focused on.
	 * @param listName Name of list the items manager is focused on
	 */
	protected void setCurrentList(String listName) {
		currentList = listName;
	}
	
	/**
	 * Gets the items and descriptions of each list.
	 * @return returns VBox containing the items and descriptions
	 */
	protected Parent getView() {
		// TODO: Add button to allow user to add new items/descriptions
		// TODO: Create sep class that deals with window for new items and descriptions
		// Creates items header components
		Label label = new Label("Items: ");
		Button addItemButton = new Button("+");
		HBox itemsHeader = new HBox();
		itemsHeader.getChildren().addAll(label, addItemButton);
		
		// Creates overall layout of items view
		BorderPane layout = new BorderPane();
		layout.setTop(itemsHeader);
		layout.setCenter(this.items);
		
		addItemButton.setOnAction(e -> {
			ItemCreationManager.addListStage(currentList, this.items);
		});
		
		return layout;
	}
	
	/**
	 * Shows the items and their related descriptions in different TextFields.
	 * Using TextFields to allow for easier editing by the user.
	 * @param listName Name of list whose items will be shown
	 */
	protected void modifyItems(String listName) {
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
			TextField itemNameField = new TextField(i.getName());
			TextField itemDescriptionField = new TextField(i.getDescription());
			
			itemNameField.setOnAction( e ->  {
				System.out.println("Modifying item name");
			});
			
			itemDescriptionField.setOnAction(e -> {
				System.out.println("Modifying item description");
			});

			// Adds textfields to Vbox containing all items and descriptions
			this.items.getChildren().addAll(itemNameField, itemDescriptionField);
		}
	}
}