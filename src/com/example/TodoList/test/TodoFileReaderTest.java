package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.TodoList.file.TodoFileReader;
import com.example.TodoList.file.TodoFileWriter;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoFileReaderTest {

	/**
	 * Resets any refs singelton has before a test is done.
	 * Also helps with making sure helper methods do NOT have any lingering impact. 
	 */
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case testing that a file was able to read.
	 */
	@Test
	void readFileShouldReturnTrue() {
		createListsAndItems();
		// Creates a file with added lists and items
		String testFileName = "TestFile1";
		TodoFileWriter.writeToFile(testFileName );

		// Removes any lists and items stored to test if they can be imported
		ListOfLists.getInstance().reset();
		assertTrue(TodoFileReader.importFile(testFileName));
		
		/* 
		 * Delete file in method instead of using an @AfterEach.
		 * Since other test methods will test different scenarios (like creation of multiple lists).
		 */
		String relativePath = testFileName  + ".txt";
		Path filePath = Paths.get(relativePath);
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			System.out.println("ERROR: Unable to delete file!");
		}
	}
	
	/**
	 * Test case testing that a file that does NOT exist should NOT be read.
	 */
	@Test
	void readingNotExistingFileShouldReturnFalse() { 
		String fileName = "TestFile1";
		assertFalse(TodoFileReader.importFile(fileName));
	}
	
	/**
	 * Helper method creating lists and items to add to a file that will be later imported.
	 */
	private void createListsAndItems() {
		// Creates 1 list and items
		ListOfLists.getInstance().addList("Planets");
		ListOfLists.getInstance().getList("Planets").addItem("Mercury", "");
		ListOfLists.getInstance().getList("Planets").addItem("Venus", "Getting pretty close to our planet.");
		ListOfLists.getInstance().getList("Planets").addItem("Earth", "Our homeplanet!");
		
		// Creates another list and items
		ListOfLists.getInstance().addList("Lightsabers");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Starkiller's lightsaber", "");
		
		// Creates a 3rd list
		ListOfLists.getInstance().addList("Groceries");
		ListOfLists.getInstance().getList("Groceries").addItem("Black Beans", "");
		ListOfLists.getInstance().getList("Groceries").addItem("Tofu", "");
		ListOfLists.getInstance().getList("Groceries").addItem("Lentils", "");
		ListOfLists.getInstance().getList("Groceries").addItem("Bread", "");
		ListOfLists.getInstance().getList("Groceries").addItem("Milk", "");
	}

}
