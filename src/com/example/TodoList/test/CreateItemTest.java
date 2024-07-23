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
	 * Test case checking that a single item can be added to a list.
	 */
	@Test
	public void addOneItemToList() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.get("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
	}
	
	/**
	 * Test case checking that only items with unique names can be added even if duplicate has no description.
	 */
	@Test
	public void addingDuplicateItemWithNoDescriptionShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.get("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!");
		assertFalse(ref.get("Lightsabers").addItem("Anakin's lightsaber", ""));
	}
	
	/**
	 * Test case checking only items with unique names can be added even if duplicate has a different description.
	 */
	@Test
	public void addingDuplicateItemsWithDifferentDescriptionShouldReturnFalse() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		ref.get("Lightsabers").addItem("Palpatine's Lightsaber", "Why use a lightsaber when have lightning?");
		assertFalse(ref.get("Lightsabers").addItem("Palpatine's Lightsaber", "Random text"));
	}
	
	/**
	 * Test case checking that multiple unique items can be added.
	 */
	@Test
	public void addingMultipleNewItems() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.get("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.get("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.get("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
	}
	
	/**
	 * Test case checking that multiple unique items can be added with duplicates not being added.
	 */
	@Test
	void addingMultipleNewAndExistingItems() {
		ListOfLists ref = ListOfLists.getInstance();
		ref.addList("Lightsabers");
		assertTrue(ref.get("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertTrue(ref.get("Lightsabers").addItem("Ahsoka's lightsabers", "Cool dual wielding w/double grip!"));
		assertTrue(ref.get("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		
		assertFalse(ref.get("Lightsabers").addItem("Palpatine's lightsaber", "Why use a lightsaber when have lightning?"));
		assertTrue(ref.get("Lightsabers").addItem("Starkiller's lightsaber", ""));
		assertFalse(ref.get("Lightsabers").addItem("Anakin's lightsaber", "Beware of friendly fire!"));
		assertFalse(ref.get("Lightsabers").addItem("Starkiller's lightsaber", "Hmmmm, kind of like Ahsoka's style."));
	}
}
