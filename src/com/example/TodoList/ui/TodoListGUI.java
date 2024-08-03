package com.example.TodoList.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
/**
 * Manages all elements on the GUI.
 * Combines the ListGUI with the ItemsGUI.
 * Used by the Main class to set the overall scene.
 */
public class TodoListGUI {
	public Parent getView() {
		Parent listsManager = ListsManager.getInstance().getView();
		
		BorderPane layout = new BorderPane();
		layout.setLeft(listsManager);
		
		return layout;
	}
}
