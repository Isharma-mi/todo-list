package com.example.TodoList.logic;

import com.example.TodoList.ui.TodoListGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage window) {
		TodoListGUI todoListGUI = new TodoListGUI();
		Scene scene = new Scene(todoListGUI.getView());
		window.setScene(scene);
		window.setTitle("TodoList");
		window.show();
	}
	
	
	public static void main(String[] args) {
		// TODO: Implement javafx
		// TODO: Add documentation related to each class
		// TODO: Let user import from SQL
		// TODO: Let user delete old db and replace it
		launch(Main.class);
	}
}