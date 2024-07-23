package com.example.TodoList.test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.TodoList.logic.ListOfLists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class CreateItemTest {

	/**
	 * Reset any refs the singleton has before a test is done.
	 */
	@BeforeEach
	public void setup() {
		ListOfLists.getInstance().reset();
	}
	
	/**
	 * Test case testing that a single item can be added to a list.
	 */
	@Test
	public void addOneItemToListShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
	}
	
	/**
	 * Test case testing that only items with unique names can be added even if duplicate has no description.
	 */
	@Test
	public void addingDuplicateItemWithNoDescriptionShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!");
		assertFalse(ref.getList("Lightsabers").addItem("Anakin's lightsaber", ""));
	}
	
	/**
	 * Test case testing only items with unique names can be added even if duplicate has a different description.
	 */
	@Test
	public void addingDuplicateItemsWithDifferentDescriptionShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.getList("Lightsabers").addItem("Palpatine's Lightsaber", "Why use a lightsaber when have lightning?");
		assertFalse(ref.getList("Lightsabers").addItem("Palpatine's Lightsaber", "Random text"));
	}
	
	/**
	 * Test case testing that multiple unique items can be added.
	 */
	@Test
	public void addingMultipleNewItemsShouldReturnTrue() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
	}
	
	/**
	 * Test case testing that multiple unique items can be added with duplicates not being added.
	 */
	@Test
	void addingMultipleNewAndExistingItemsShouldReturnTrueAndFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.getList("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		
		assertFalse(ref.getList("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		assertTrue(ref.getList("Lightsabers").addItem("Starkiller's lightsaber", ""));
		assertFalse(ref.getList("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertFalse(ref.getList("Lightsabers").addItem("Starkiller's lightsaber", "Hmmmm, kind of like Ahsoka's style."));
	}
}
