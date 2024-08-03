package com.example.TodoList.database;

import com.example.TodoList.logic.Item;
import com.example.TodoList.logic.ListOfLists;
import com.example.TodoList.logic.ListOfItems;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ListDAO {
	// TODO: Let user delete the old db (when name exists already) if it exists
	
	/**
	 * Exports the lists and items in the program to a SQL Server database. 
	 * Uses helper methods to achieve this. 
	 * @param serverName Name of the server to create a connection to.
	 * @param dbName Name of the database that will be created containing the tables related to the lists and items.
	 * @param userName Part of user login info that is able to create dbs and modify them.
	 * @param password Part of user login info that is able to create dbs and modify them.
	 */
	public static void exportToSQL(String serverName, String dbName, String userName, String password) {
		String url = "jdbc:sqlserver://" + serverName + ";encrypt=true;trustServerCertificate=true";
		Connection conn = null;
		
		// First tries to make a connection to the server
		try {
			// Tries to create a connection
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Connection made.");
			
			if (checkIfDbExists(conn, dbName)) {
				System.out.println("ERROR: Database with same name already exists!");
				return;
			}
			
			// Tries to create a database
			createDatabase(conn, dbName);
			// Tries to create the tables in the database
			createTables(conn, dbName);
			// Tries to put the items of a list in the respective tables
			createRecordsForTables(conn, dbName);
						
		} catch(SQLException e) {
			System.out.println(e);
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
			throw new SQLException("ERROR: Unable to create database " + dbName + "!");
		}
	}
	
	/**
	 * Creates each individual list as a table in SQL.
	 * @param connection Object that will access the SQL server
	 * @param dbName Name of the database that has been created
	 * @return returns boolean letting caller know if database was created
	 * @throws SQLException If unable to create tables within the database
	 */
	private static boolean createTables(Connection connection, String dbName) throws SQLException {
		boolean createdTables = false;
		
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
					, dbName, list.getName());
			
			try (PreparedStatement stmnt = connection.prepareStatement(sqlCmd)) {
				stmnt.execute();
			} catch (SQLException e) {
				throw new SQLException("ERROR: Unable to create table \"" + list.getName() + "\"!");
			}
			
			System.out.println(list.getName() + " table made.");
		}
		
		System.out.println("Finished making tables!");
		return (createdTables = true);
	}
	
	/**
	 * Creates each item as a record in the respective table in SQL. 
	 * @param connection Object that will access the SQL server
	 * @param dbName Name of the database that has been created
	 * @return returns boolean letting caller know if records were successfuly made
	 * @throws SQLException If unable to create records in the database
	 */
	private static boolean createRecordsForTables(Connection connection, String dbName) throws SQLException {
		boolean addedRecords = false;
		List<ListOfItems> lists = ListOfLists.getInstance().getLists();
		
		// If there are no lists stop trying to add to db
		if (lists.isEmpty()) {
			return addedRecords;
		}
		
		// Add the items to SQL one list at a time
		for (ListOfItems list: lists) {
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
					throw new SQLException("ERROR: Unable to insert item \"" + item.getName() + " into \"" + list.getName() + "\"!");
				}
			}
			System.out.println("Added records to table " + list.getName() + ".");
		}
		
		System.out.println("Finished adding records to tables!");
		return (addedRecords = true);
	}

	/**
	 * Checks if a database already exists within the SQL server.
	 * @param conn Object that will access the SQL server
	 * @param dbName Name of the database that has been created
	 * @return boolean letting caller know if database exists 
	 * @throws SQLException If unable to check that database exists
	 */
	private static boolean checkIfDbExists(Connection conn, String dbName) throws SQLException {
		boolean dbExists = false;
		int dbExistsTracker = 0;
		
		String sqlCmd = "SELECT COUNT(*) FROM sys.databases WHERE name = (?)";
		try (PreparedStatement stmnt = conn.prepareStatement(sqlCmd)) {
			stmnt.setString(1, dbName);
			// Stores all the info related to the # of dbs that have the dbName as their name
			ResultSet rs = stmnt.executeQuery();
			
			// Gets the exact number of dbs with the dbName as their name (Should be either 1 or 0)
			if (rs.next()) {
				dbExistsTracker = rs.getInt(1);
			}
			
			rs.close();
		} catch (SQLException e) {
			throw new SQLException("ERROR: Unable to check if db exists!");
		}
		
		if (dbExistsTracker == 1) {
			dbExists = true;
		} else {
			dbExists = false;
		}
		
		return dbExists;
	}
}
