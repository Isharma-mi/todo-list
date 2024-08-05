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
 * Manages elements of the UI related to the lists.
 */
public class ListsManager {

	private static ListsManager instance;
	
	/*
	 * Private constructor to ensure singleton pattern.
	 */
	private ListsManager() {
	}
	
	/**
	 * Creates a ListsManager object or returns ref to the existing singleton.
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
	 * @return returns VBox containing all the list names and components relating to creation of a list.
	 */
	public Parent getView() {
		// TODO: Add formatting to "Lists:" text (center it and add padding)
		// TODO: Remove borders of buttons for + and lists
		// TODO: Update window to get bigger when lists added

		// Layout containing everything associated with the viewing of all the lists
		VBox layout = new VBox();

		// Components related to viewing of the lists
		HBox listsHeader = new HBox();
		Label label = new Label("Lists:");
		Button addListButton = new Button("+");
		VBox lists = new VBox();
		
		// Adds header related components together
		listsHeader.getChildren().addAll(label, addListButton);
		
		// When the user clicks the button to add a list
		addListButton.setOnAction(e -> {
			addListStage(lists);
		});
		
		// Adds the header and names of the lists together
		layout.getChildren().addAll(listsHeader, lists);
		
		// TESTING: Calling populateLists immediately for testing lists show up
		layout.getChildren().add(testingPopulateLists());
		
		return layout;
	}
	
	/**
	 * Creates a window that gets the name of a list that user wants to create.
	 * @param lists VBox containing the names of all the lists that will get updated if a new list is successfully made
	 */
	private void addListStage(VBox lists) {
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
	
	/**
	 * For testing purposes right now.
	 * Adds all the lists to the lists (Maybe be reworked in future for adding imported lists properly).
	 * Should modify this later to work for imported lists. Since listener above is for lists created in the moment.
	 * Could make that listener separate method.
	 * @return
	 */
	private VBox testingPopulateLists() {	
		VBox listsLayout = new VBox();

		// Loops thru all the lists and adds each to UI with listener
		for (int i = 0; i < ListOfLists.getInstance().getLists().size(); i++) {
			Button newListBtn = new Button(ListOfLists.getInstance().getLists().get(i).getName());
			listsLayout.getChildren().add(newListBtn);
			
			newListBtn.setOnAction(newListBtnClicked -> {
				System.out.println(newListBtn.getText());
				ItemsManager.getInstance().modifyItems(newListBtn.getText());
			});
		}
		
		return listsLayout;
	}
}
