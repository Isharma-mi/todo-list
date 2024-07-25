package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.file.TodoFileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TodoFileWriterTest {
	
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case for testing creation of a file.
	 */
	@Test
	public void createFile() {	
		String testFile1Name = "TestFile1";
		
		ListOfLists.getInstance().addList("Planets");
		ListOfLists.getInstance().getList("Planets").addItem("Mercury", "");
		ListOfLists.getInstance().getList("Planets").addItem("Venus", "Getting pretty close to our planet.");
		ListOfLists.getInstance().getList("Planets").addItem("Earth", "Our homeplanet!");
				
		// Tests custom method for creating file
		assertTrue(TodoFileWriter.writeToFile(testFile1Name), "Custom method for creating file should work.");
		
		/* 
		 * Delete file in method instead of using an @AfterEach.
		 * Since other test methods will test different scenarios (like creation of multiple lists).
		 */
		String relativePath = testFile1Name + ".txt";
		Path filePath = Paths.get(relativePath);
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			System.out.println("ERROR: Unable to delete file!");
		}
	}
	
	/**
	 * Test case for testing creation of multiple unique files.
	 */
	@Test
	public void createMultipleFilesShouldReturnTrue() {
		// Creates 1 list and items
		String testFile1Name = "TestFile1";
		ListOfLists.getInstance().addList("Planets");
		ListOfLists.getInstance().getList("Planets").addItem("Mercury", "");
		ListOfLists.getInstance().getList("Planets").addItem("Venus", "Getting pretty close to our planet.");
		ListOfLists.getInstance().getList("Planets").addItem("Earth", "Our homeplanet!");
		
		assertTrue(TodoFileWriter.writeToFile(testFile1Name), "File should be created containing only a List Planets");
		
		// Creates another list and items
		String testFile2Name = "TestFile2";
		ListOfLists.getInstance().addList("Lightsabers");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?");
		ListOfLists.getInstance().getList("Lightsabers").addItem("Starkiller's lightsaber", "");
		assertTrue(TodoFileWriter.writeToFile(testFile2Name), "2nd file should be created containing prev list and new list");
		
		/* 
		 * Delete file in method instead of using an @AfterEach.
		 * Since other test methods will test different scenarios (like creation of single list).
		 */
		String file1RelativePath = testFile1Name + ".txt";
		String file2RelativePath = testFile2Name + ".txt";
		Path file1Path = Paths.get(file1RelativePath);
		Path file2Path = Paths.get(file2RelativePath);
		try {
			Files.delete(file1Path);
			Files.delete(file2Path);
		} catch (IOException e) {
			System.out.println("ERROR: Unable to delete files!");
		}
	}
}
