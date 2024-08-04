package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.TodoList.logic.ListOfLists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CreateListTest {
	/**
	 * Reset any refs the singleton has before a test is done.
	 */
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case checking that a list can be made and added to list of lists.
	 */
	@Test
	void addListShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		assertTrue(ref.addList("List"));
	}
	
	/**
	 * Test case checking that multiple unique lists can made and added to list of lists.
	 */
	@Test
	void addDifferentListsShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		assertTrue(ref.addList("List"));
		assertTrue(ref.addList("List1"));
	}
	
	/**
	 * Test case checking that only unique lists can be made and added to list of lists.
	 */
	@Test
	void addListWithSameNameAsOneAlreadyAddedShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("List");
		assertFalse(ref.addList("List"));
	}
	
	/**
	 * Test case checking that list names beginning with "-" can NOT be added
	 */
	@Test
	void addListBeginningWithDashShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		assertFalse(ref.addList("-List"));
	}
	
	/**
	 * Test case checking that list names that are empty can NOT be added
	 */
	@Test
	void addListWithNoNameShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		assertFalse(ref.addList(""));
	}
}
