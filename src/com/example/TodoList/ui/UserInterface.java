package com.example.TodoList.ui;

import java.util.Scanner;

public class UserInterface {
	public void start() {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			// Asks user what they want to do
			// TODO: Implement all options
			System.out.println("Welcome to the TodoList! What would you like to do?"
					+ "\n1: View name of all lists"
					+ "\n2: View items of a list"
					+ "\n3: Create a list"
					+ "\n4: Delete a list"
					+ "\n5: Create item for a list"
					+ "\n6: Exit program");
			
			// Gets user input
			String input = scanner.nextLine();

			if (!input.matches("[12345]")) {
				// If user input is incorrect 
				// Restart process of asking what user wants to do
				continue;
			} else if(input.equals("1")) {

			}
		}
	}
}
