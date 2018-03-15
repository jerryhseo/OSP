package com.kisti.osp.icesheet.portlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EdisonDBConnector {
	private static final String DBMS_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	private static String strUserName = "root";

	private static String strPassword = "dpel+*db";

	private static String strConnectString = "jdbc:mysql://150.183.247.20/";

	private static String strDatabaseName = "edison2_release";

	private static Connection _connection = null;

	private static Statement _statement = null;
	
//	/****
//	 * Young added the following statements. 
//	 */
//	private static String strPROVDatabaseName = "edison2_prov";
//	
//	private static Connection _prov_connection = null;
//
//	private static Statement _prov_statement = null;

//	/**
//	 * Opens the connection to the DBMS.
//	 * 
//	 * @throws DBMSInvalidConnectionParameterException
//	 */
//	public static boolean open_prov_db(boolean auto_commit) throws Exception{
//		boolean res = false;
//		try {
//			Class.forName(DBMS_DRIVER_CLASS_NAME);
//			_prov_connection = DriverManager.getConnection(strConnectString+strPROVDatabaseName,
//					strUserName, strPassword);
//			// turn off auto-commit. If this is turned on there will be a
//			// huge performance hit for inserting tuples
//			// into the DBMS.
//			_prov_connection.setAutoCommit(auto_commit);
//			_prov_statement = _prov_connection.createStatement(
//					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
//			res = true;
//		} catch (SQLException sqlex) {
//			String msgErr ="login failed - details: " + strConnectString + ", "
//					+ strUserName + ", " + strPassword;
////			System.err.println();
//			throw new Exception(msgErr);
//			
//		} catch (ClassNotFoundException e) {
//			//e.printStackTrace();
//			//System.exit(1); // programemer/dbsm error
//			throw new Exception(e.getMessage());
//		}
//		return res;
//	}
	
//	/**
//	 * Closes the DBMS connection that was opened by the open call.
//	 */
//	public static void close_prov_db() throws Exception{
//		try {
//			if (_prov_connection != null)
//				_prov_connection.commit();
//			if (_prov_statement != null)
//				_prov_statement.close();
//			if (_prov_connection != null)
//				_prov_connection.close();
//		} catch (SQLException e) {
////			e.printStackTrace();
//			throw new Exception(e.getMessage());
//		}
//		_prov_connection = null;
//	}
	
//	/*****
//	 * 
//	 * @param insertStmt insert statement
//	 * @return
//	 */
//	public static boolean insertRecordIntoProvDB(String insertStmt){
//		boolean res = true;
//		try {
////			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
////				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
////				  insertStmt += ")"; 
//			_prov_statement.executeUpdate(insertStmt);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			if(!e.getMessage().contains("duplicate"))
//			System.err.println(e.getMessage());
//			res = false;
//		}
//		return res;
//	}
	
//	/*****
//	 * 
//	 * @param insert statement
//	 * @return
//	 */
//	public static String addPROVDocToBatch(String insertStmt){
//		String res = "";
//		try {
////			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
////				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
////				  insertStmt += ")"; 
//			_prov_statement.addBatch(insertStmt);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			//System.err.println(e.getMessage());
//			//res = false;
//			res = e.getMessage();
//		}
//		return res;
//	}
	
//	/*****
//	 * Insert PROV doc batch into provenance database.
//	 * @param insert statement
//	 * @return
//	 */
//	public static String insertPROVDocBatchIntoProvDB(){
////		boolean res = true;
//		String res = "";
//		try {
////			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
////				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
////				  insertStmt += ")"; 
//			// execute batch
//			_prov_statement.executeBatch();
//			// clear batch for next upate
//			_prov_statement.clearBatch();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
////			System.err.println(e.getMessage());
//			res = e.getMessage();
//		}
//		return res;
//	}
	
//	/****
//	 * Execute a query for simulation provenance DB
//	 * @param query
//	 * @return ResultSet
//	 */
//	public static ResultSet executeQueryOnProvDB(String query){
//		 ResultSet rs = null;
//		 try {
//			rs = _prov_statement.executeQuery(query);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 return rs;
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/***
	 * Portal DB
	 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Closes the DBMS connection that was opened by the open call.
	 */
	public static void close() {
		try {
			if (_connection != null)
				_connection.commit();
			if (_statement != null)
				_statement.close();
			if (_connection != null)
				_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		_connection = null;
	}

	/**
	 * Commits all update operations made to the dbms. This must be called for
	 * inserts statements to be seen.
	 */
	public static void commit() {
		try {
			if (_connection != null && !_connection.isClosed())
				_connection.commit();
		} catch (SQLException e) {
			System.err.println("Commit failed");
			e.printStackTrace();
		}
	}

	/**
	 * Opens the connection to the DBMS.
	 * 
	 * @throws DBMSInvalidConnectionParameterException
	 */
	public static boolean open(boolean auto_commit) {
		boolean res = false;
		try {
			Class.forName(DBMS_DRIVER_CLASS_NAME);
			_connection = DriverManager.getConnection(strConnectString+strDatabaseName,
					strUserName, strPassword);
			// turn off auto-commit. If this is turned on there will be a
			// huge performance hit for inserting tuples
			// into the DBMS.
			_connection.setAutoCommit(auto_commit);
			_statement = _connection.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			res = true;
		} catch (SQLException sqlex) {
			System.err.println("login details: " + strConnectString + ", "
					+ strUserName + ", " + strPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1); // programemer/dbsm error
		}
		return res;
	}

	public static ResultSet executeQuery(String query){
		 ResultSet rs = null;
		 try {
			rs = _statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return rs;
	}
}
