package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.TodoList.logic.ListOfLists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class DeleteListTest {

	/**
	 * Reset any refs the singleton has before a test is done.
	 */
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case testing that a list can be deleted.
	 */
	@Test
	void deleteExistingListShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("List");
		assertTrue(ref.deleteList("List"));
	}
	
	/**
	 * Test case testing that a nonexistent list will NOT be deleted.
	 * Done to check that boolean returned is false.
	 */
	@Test
	void deleteNotExistingListShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		assertFalse(ref.deleteList("List"));
	}
	
	/**
	 * Test case testing that multiple lists can be deleted.
	 */
	@Test
	void deleteMultipleExistingListsShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.addList("Blasters");
		ref.addList("Jedi");
		assertTrue(ref.deleteList("Lightsabers"));
		assertTrue(ref.deleteList("Blasters"));
		assertTrue(ref.deleteList("Jedi"));
	}
}
