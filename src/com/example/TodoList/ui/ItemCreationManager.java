package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfLists;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ItemCreationManager {
	
	/**
	 * Creates a window that gets the information of a new item that the user wants to add.
	 * @param currentList Name of the list user wants to add an item too
	 * @param items VBox containing all the items of the currentList
	 */
	protected static void addListStage(String currentList, VBox items) {
		// TODO: Center label and submitButton
		// Creates UI components
		Label itemNameLabel = new Label("What will the name of the item be?");
		TextField itemNameField = new TextField();
		Label itemDescriptionLabel = new Label("What will the description of the item be?");
		TextField itemDescriptionField = new TextField();
		Button submitButton = new Button("Submit");
		Label submitStatusLabel = new Label();
		
		// Organizes UI components
		VBox layout = new VBox();
		layout.getChildren().addAll(itemNameLabel, itemNameField, itemDescriptionLabel, itemDescriptionField, submitButton, submitStatusLabel);
	
		// Adds components for viewing
		Scene scene = new Scene(layout);
		
		// Creates window 
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
		
		// Whenever the submit button is pressed
		submitButton.setOnAction(submitBtnClicked -> {
			
			// Checks that a list was selected (Mainly in case someone tries to add an item the moment they launch the app)
			if (currentList == null) {
				submitStatusLabel.setText("ERROR: Please select a list first!");
				return;
			}
			
			// Gets the name and description user inputted
			String itemName = itemNameField.getText();
			String itemDescription = itemDescriptionField.getText();
			
			// Let user know if item was NOT given a name
			if (itemName.trim().equals("")) {
				submitStatusLabel.setText("ERROR: Nothing put in text field!");
				return;
			}

			// Tries to actually add the item
			boolean itemWasAdded = ListOfLists.getInstance().getList(currentList).addItem(itemName, itemDescription);

			if (itemWasAdded) {
				// If item was created -> Create textfields for the name and description
				TextField itemNameFieldForView = new TextField(itemName);
				TextField itemDescriptionFieldForView = new TextField(itemDescription);
					
				// Adds the fields to the view
				items.getChildren().addAll(itemNameFieldForView, itemDescriptionFieldForView);
			} else if (itemName.startsWith("-")) {
				submitStatusLabel.setText("ERROR: Item name can NOT begin with \"-\"!");
				return;
			} else {
				submitStatusLabel.setText("ERROR: Item with same name exists already!");
				return;
			}
			// If list was created successfully -> Close the popup window
			stage.close();
		});
	}
}
