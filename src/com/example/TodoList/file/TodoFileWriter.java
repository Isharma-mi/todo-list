package com.example.TodoList.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;

public class TodoFileWriter {
	
	/**
	 * Creates a file with a custom name containing all the lists and their items.
	 * @param fileName Name of file that will be made
	 * @return returns boolean letting caller know if file was created and written on successfully
	 */
	public static boolean writeToFile(String fileName) {
		boolean fileCreatedAndWritten = false;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
			
			// Writes out each list and item to the file
			for (ListOfItems l: ListOfLists.getInstance().getLists()) {
				writer.write(l.toString());
				writer.write("\n");
			}
			
			fileCreatedAndWritten = true;
			writer.close();
		} catch (IOException e) {
			fileCreatedAndWritten = false;
		}
		
		return fileCreatedAndWritten;
	}	
}
