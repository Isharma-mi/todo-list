package com.example.TodoList.ui;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 * Manages all elements on the GUI.
 * Combines the ListGUI with the ItemsGUI.
 * Used by the Main class to set the overall scene.
 */
public class TodoListGUI {

	/**
	 * Gets the lists and items view separately. 
	 * @return returns node containing all the UI elements
	 */
	public Parent getView() {		
		// Gets the lists and items view
		Parent listsManager = ListsManager.getInstance().getView();
		Parent itemsManager = ItemsManager.getInstance().getView();
		
		// Organizes the layout of the GUI
		BorderPane layout = new BorderPane();
		layout.setLeft(listsManager);
		layout.setCenter(itemsManager);
		
		// Returns the organized layout
		return layout;
	}
}
