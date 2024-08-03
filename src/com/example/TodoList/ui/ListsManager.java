package com.example.TodoList.ui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 * Manages elements of the GUI related to the lists.
 */
public class ListsManager {
	
	private static ListsManager instance;
	
	/*
	 * Private constructor to ensure singleton pattern.
	 */
	private ListsManager() {
	}
	
	/**
	 * Creates a ListsManaager object or returns ref to an existing one.
	 * @return returns the singleton instance
	 */
	public static synchronized ListsManager getInstance() {
		if (instance == null) {
			instance = new ListsManager();
		} 
		
		return instance;
	}

	public Parent getView() {
		VBox layout = new VBox();
		Label label = new Label("Lists:");
		
		layout.getChildren().add(label);
		return layout;
	}
}
