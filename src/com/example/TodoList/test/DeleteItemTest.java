package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.TodoList.logic.ListOfLists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteItemTest {

	/**
	 * Reset any refs the singleton has before a test is done.
	 */
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case testing that an item can be deleted.
	 */
	@Test
	public void deleteOneItemShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!");
		assertTrue(ref.getList("Lightsabers").deleteItem("Anakin's lightsaber"));
	}


	/**
	 * Test case testing that a nonexisting item can NOT be deleted.
	 * Done to check that boolean returned is false.
	 */
	@Test
	public void deleteNotExistingItemShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertFalse(ref.getList("Lightsabers").deleteItem("Anakin's lightsaber"));		
	}
	
	/**
	 * Test case testing that multiple items can be deleted.
	 */
	@Test
	public void deletingMultipleItemsShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		
		assertTrue(ref.getList("Lightsabers").deleteItem("Anakin's lightsaber"));
		assertTrue(ref.getList("Lightsabers").deleteItem("Ahsoka's lightsabers"));
		assertTrue(ref.getList("Lightsabers").deleteItem("Palpatine's lightsaber"));
	}
	
	/**
	 * Test case testing that multiple items that exist can be deleted while those that do NOT exist are not "deleted"
	 * Done to check that boolean returned is correct.
	 */
	@Test
	public void deletingMultipleExistingAndNotExistingItemsShouldReturnTrueAndFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		
		assertTrue(ref.getList("Lightsabers").deleteItem("Palpatine's lightsaber"));
		
		assertFalse(ref.getList("Lightsabers").deleteItem("Starkiller's lightsaber"));
		assertFalse(ref.getList("Lightsabers").deleteItem("Sol's Lightsaber"));
		
		assertTrue(ref.getList("Lightsabers").deleteItem("Anakin's lightsaber"));
	}
}
