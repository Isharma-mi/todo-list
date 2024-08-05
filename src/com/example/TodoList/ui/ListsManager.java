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
	protected static synchronized ListsManager getInstance() {
		if (instance == null) {
			instance = new ListsManager();
		} 
		
		return instance;
	}
	
	/**
	 * Gets the layout relating to showing and creating new lists.
	 * @return returns VBox containing all the list names and components relating to creation of a list.
	 */
	protected Parent getView() {
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
			ListCreationManager.addListStage(lists);
		});
		
		// Adds the header and names of the lists together
		layout.getChildren().addAll(listsHeader, lists);
		
		// TESTING: Calling populateLists immediately for testing lists show up
		layout.getChildren().add(testingPopulateLists());
		
		return layout;
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
				ItemsManager.getInstance().setCurrentList(newListBtn.getText());
				ItemsManager.getInstance().modifyItems(newListBtn.getText());
			});
		}
		
		return listsLayout;
	}
}
