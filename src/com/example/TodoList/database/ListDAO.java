package com.example.TodoList.database;

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
			createDatabase(connection, dbName);;
			createTables(connection, dbName);
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
	 * @param dbName Name of the db that has been created.
	 * @throws SQLException If unable to craete tables within the database
	 */
	private static void createTables(Connection connection, String dbName) throws SQLException {
		System.out.println("Trying to add tables");
		// TODO: Use a String to write out sql cmd then send that cmd over via prepareStatement(...)}
		for (ListOfItems list: ListOfLists.getInstance().getLists()) {
			System.out.println("In a list");
			String sqlCmd = String.format(
					"USE %s " +
					"CREATE TABLE %s (" +
						"item_identity BIGINT IDENTITY (1,1), " + 
						"item_name varchar(100)," + 
						"item_description varchar(100)" +
					")"
					,dbName, list.getName());
			
			System.out.println(sqlCmd);
			
			try (PreparedStatement stmnt = connection.prepareStatement(sqlCmd)) {
				stmnt.execute();
				System.out.println("Table made");
			} catch (SQLException e) {
				throw new SQLException("Unable to create tables");
			}
		}
		System.out.println("Stopped trying to add tables.");
	}
	
	// TODO: Create method to add items to db (NOT FULLY IMPLEMENTED)
	private static boolean insertIntoTable() {
		boolean addedToDatabase = false;;
		// Loop thru items of each list
		List<ListOfItems> lists = ListOfLists.getInstance().getLists();
		
		// If there are no lists stop trying to add to db
		if (lists.isEmpty()) {
			return addedToDatabase = false;
		}
		
		return addedToDatabase;
	}
}
