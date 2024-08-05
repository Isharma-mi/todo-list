package com.example.TodoList.ui;

import com.example.TodoList.logic.ListOfLists;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Manages the creation of a list.
 */
public class ListCreationManager {
	
	/**
	 * Creates a window that gets the name of a list that user wants to create.
	 * @param lists VBox containing the names of all the lists that will get updated if a new list is successfully made
	 */
	protected static void addListStage(VBox lists) {
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
		submitButton.setOnAction(submitBtnClicked -> {
			String listName = textField.getText();
			
			// Let user know if nothing was put in text field
			if (listName.trim().equals("")) {
				submitStatusLabel.setText("ERROR: Nothing put in text field!");
				return;
			}

			// Tries to actually make the list
			boolean listWasAdded = ListOfLists.getInstance().addList(listName);

			if (listWasAdded) {
				// If list was created -> Add a button for it and set events for when it is clicked
				Button newListBtn = new Button(listName);
				
				newListBtn.setOnAction(newListBtnClicked -> {
					ItemsManager.getInstance().setCurrentList(listName);
					ItemsManager.getInstance().modifyItems(newListBtn.getText());
				});
				
				// Adds the button to the view
				lists.getChildren().add(newListBtn);
			} else if (listName.startsWith("-")) {
				submitStatusLabel.setText("ERROR: List name can NOT begin with \"-\"!");
				return;
			} else {
				submitStatusLabel.setText("ERROR: List with same name exists already!");
				return;
			}
			// If list was created successfully -> Close the popup window
			stage.close();
		});
	}
}
