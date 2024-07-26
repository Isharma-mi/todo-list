package com.example.TodoList.database;

import com.example.TodoList.logic.Item;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ListDAO {
	// TODO: Implement way to delete old db if it exists
	
	/**
	 * Exports the lists and items in the program to a SQL Server database. Uses helper methods to achieve this. 
	 * @param serverName Name of the server to create a connection to.
	 * @param dbName Name of the database that will be created containing the tables related to the lists and items.
	 * @param userName Part of user login info that is able to create dbs and modify them.
	 * @param password Part of user login info that is able to create dbs and modify them.
	 */
	public static void exportToSQL(String serverName, String dbName, String userName, String password) {
		String url = "jdbc:sqlserver://" + serverName + ";encrypt=true;trustServerCertificate=true";
		try (Connection connection = DriverManager.getConnection(url, userName, password)) {
			System.out.println("Connection made");
			// Tries to create db -> Create tables -> Create records
			createDatabase(connection, dbName);;
			if (createTables(connection, dbName)) {
				insertIntoTables(connection, dbName);
			}
		} catch(SQLException e) {
			System.out.println("ERROR: Unable to export to SQL, SPECIFIC ERROR: " + e.getMessage());
		}
	}
	
	/**
	 * Creates a database that will contain all the lists and items from the program.
	 * @param connection Object that will access a SQL server
	 * @param dbName Name of the database that has been created.
	 * @throws SQLException If unable to create a database (ex: It exists already)
	 */
	private static void createDatabase(Connection connection, String dbName) throws SQLException {
		try (PreparedStatement stmnt = connection.prepareStatement("CREATE DATABASE " + dbName)) {
			stmnt.execute();
			System.out.println("Database made.");
		} catch (SQLException e) {
			throw new SQLException("Unable to create database");
		}
	}
	
	/**
	 * Creates each individual list as a table in SQL.
	 * @param connection Object that will access the SQL server.
	 * @param dbName Name of the database that has been created.
	 * @throws SQLException If unable to create tables within the database
	 */
	private static boolean createTables(Connection connection, String dbName) throws SQLException {
		boolean createdTables = false;
		System.out.println("Trying to add tables");
		
		if (ListOfLists.getInstance().getLists().isEmpty()) {
			System.out.println("ERROR: No lists were found!");
			return createdTables;
		}
		for (ListOfItems list: ListOfLists.getInstance().getLists()) {
			String sqlCmd = String.format(
					"USE %s " +
					"CREATE TABLE %s (" +
						"item_identity BIGINT IDENTITY (1,1), " + 
						"item_name varchar(100)," + 
						"item_description varchar(100)" +
					")"
					,dbName, list.getName());
			
			//System.out.println(sqlCmd);
			
			try (PreparedStatement stmnt = connection.prepareStatement(sqlCmd)) {
				stmnt.execute();
				System.out.println("Table made");
			} catch (SQLException e) {
				throw new SQLException("Unable to create tables");
			}
		}
		
		System.out.println("Finished adding tables!");
		return (createdTables = true);
	}
	
	/**
	 * Creates each item as a record in the respective table in SQL. 
	 * @param connection Object that will access the SQL server
	 * @param dbName Name of the database that has been created
	 * @return returns boolean letting caller know if records were successfuly made
	 * @throws SQLException If unable to create records in the db
	 */
	private static boolean insertIntoTables(Connection connection, String dbName) throws SQLException {
		boolean addedRecords = false;
		List<ListOfItems> lists = ListOfLists.getInstance().getLists();
		
		// If there are no lists stop trying to add to db
		if (lists.isEmpty()) {
			return addedRecords;
		}
		
		// Add the items to SQL one list at a time
		for (ListOfItems list: lists) {
			System.out.println("Trying to add records to table " + list.getName());
			// Loop thru each item in a list
			for (Item item: list.getItems()) {
				String sqlCmd = String.format(
						"USE %s " + 
						"INSERT INTO %s (item_name, item_description) " +
						"VALUES ((?), (?)) "
						, dbName, list.getName());
				
				// Tries to send over the sql cmd to add the specific item to the list
				try (PreparedStatement stmnt = connection.prepareStatement(sqlCmd)) {
					// setString helps avoid any issues that could occur with specific escape characters
					stmnt.setString(1, item.getName());
					stmnt.setString(2, item.getDescription());
					stmnt.executeUpdate();
				} catch (SQLException e) {
					throw new SQLException("Unable to create records in tables");
				}
			}
		}
		
		System.out.println("Finished adding records to tables!");
		return (addedRecords = true);
	}
}
