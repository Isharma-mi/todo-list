package com.example.TodoList.file;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import com.example.TodoList.logic.ListOfLists;

public class TodoFileReader {
	// TODO: Create tests for importing and exporting
	public static boolean importFile(String fileName) {
		boolean fileRead = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"))) {
			boolean inAList = false;
			String line;
			String listName = null;
			String itemName = null;
			String description = "";
			
			while((line = reader.readLine()) != null) {
				if (!inAList) {
					// If the current line is a list name
					
					if (!line.startsWith("-") && !line.startsWith("\t") && !line.isEmpty()) {
						// Creates a ListofItems obj
						ListOfLists.getInstance().addList(line);
						listName = line;
						inAList = true;
						continue;
					}
				}

				if (inAList) {
					// If going thru a list
					if (line.startsWith("-")) {
						// If the line is an item
						if (itemName != null) {
							/* 
							 * Adds items from the second item to the last item
							 * Specifically once a new item name is read 
							 */
							ListOfLists.getInstance().getList(listName).addItem(itemName, description);
						}
						
						// Removes the - and space before the item's name
						itemName = line.substring(2);
						description = "";
					} else if (line.startsWith("\t")){
						// Removes the tab spacing before the description
						description = line.trim();
					} else {
						 // Adds the last item in the list
						ListOfLists.getInstance().getList(listName).addItem(itemName, description);

						// Resets item name so that last item of previous list does not get added in the next list
						itemName = null;
						inAList = false;
					}
				}
			}
			fileRead = true;
		} catch (IOException e) {
			System.out.println("ERROR: Unable to save lists and/or items from file!");
		}
		
		return fileRead;
	}		
}
