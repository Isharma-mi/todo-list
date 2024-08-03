package com.example.TodoList.logic;

import com.example.TodoList.ui.UIController;
import com.example.TodoList.database.ListDAO;
import com.example.TodoList.file.TodoFileReader;

public class Main {
	
	public static void main(String[] args) {
		// TODO: Update documentation across all files to be more like ListDAO comments 
		// TODO: Update method names to be reflective of what they do better (ex: For booleans -> Use prefix like is)
		// TODO: Delete old db and let user know thats what happens when exporting to sql
		
		// Delete the lines below (only keep for testing right now)
		// Imports list information (so we do NOT need to create lists and items each time to test)
		TodoFileReader.importFile("TodoListTest");
		// Exports list information to SQL
		ListDAO.exportToSQL("ISHARMA", "test_db_from_TodoList_program", "testAccount", "test");
		
		/*
		 * These lines will not be deleted but instead uncommetned once testing is done.
		 */
		//UIController ui = new UIController();
		//ui.start();
	}
}