package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfLists;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

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
	/**
	 * Gets the layout relating to showing and creating new lists.
	 * @return Returns VBox containing all the list names and components relating to creation of a list.
	 */
	public Parent getView() {
		// TODO: Add formatting to "Lists:" text (center it and add padding)
		// TODO: Create + button to add lists use event handlers for this
		// TODO: Remove borders of buttons for + and lists

		// Populate lists when importing a file
		// Populate new lists one at a time

		// Layout containing everything associated with the viewing of all the lists
		VBox layout = new VBox();

		// Components related to header where it says Lists: and has button to add a list
		HBox listsHeader = new HBox();
		Label label = new Label("Lists:");
		Button addListButton = new Button("+");
		
		listsHeader.getChildren().addAll(label, addListButton);
		
		
		addListButton.setOnAction(e -> {
			// Box will have ok and cancel button
				// When click ok -> Add a new button with the name of the list to the vbox
			System.out.println("TESTING: Add a list button pressed");
			addListStage();
			
		});
		
		// TESTING: Calling populateLists immediately for testing lists show up
		layout.getChildren().addAll(listsHeader, populateLists());
		return layout;
	}
	
	private void addListStage() {
		// TODO: Center label and submitButton
		// Creates UI components
		Label label = new Label("What will the name of the list be?");
		TextField textField = new TextField();
		Button submitButton = new Button("Submit");
		Label submitStatusLabel = new Label();
		
		// Organizes UI components
		VBox layout = new VBox();
		layout.getChildren().addAll(label, textField, submitButton, submitStatusLabel);
	
		// Adds components for viewing
		Scene scene = new Scene(layout);
		
		// Creates window 
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
		
		// Whenever the submit button is pressed
		submitButton.setOnAction(e -> {
			// Let user know if nothing was put in text field
			if (textField.getText().trim().equals("")) {
				submitStatusLabel.setText("ERROR: Nothing put in text field!");
				return;
			}
			
			// Check that entered name does not exist in lists already
			System.out.println("TESTING: Trying to submit a new list name");
			stage.close();
		});
	}
	
	/**
	 * Adds all the lists to the lists
	 * @return
	 */
	private Parent populateLists() {
		VBox listsLayout = new VBox();
		
		for (int i = 0; i < ListOfLists.getInstance().getLists().size(); i++) {
			listsLayout.getChildren().add(new Button(ListOfLists.getInstance().getLists().get(i).getName()));
		}
		
		return listsLayout;
	}

	
}
