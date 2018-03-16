\package com.kisti.osp.icesheet.portlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IceSheetDBConnector {
	private static final String DBMS_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	private static String strUserName = "root";

	private static String strPassword = "dpel+*db";

	private static String strConnectString = "jdbc:mysql://150.183.247.20/";

	private static String strDatabaseName = "edison2_prov";

	private static Connection _connection = null;

	private static Statement _statement = null;
	
	/*****
	 * 
	 * @param insertStmt insert statement
	 * @return
	 */
	public static void insertRecordIntoProvDB(String insertStmt) throws Exception{
//		boolean res = true;
//		try {
//			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
//				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
//				  insertStmt += ")"; 
			_statement.executeUpdate(insertStmt);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			//e.printStackTrace();
//			if(!e.getMessage().contains("duplicate")){
//				System.err.println(e.getMessage());
//				throw new Exception(e.getMessage());
//			}
//			res = false;
//		}
//		commit();
		commit();
//		return res;
	}
	
	/*****
	 * 
	 * @param insert statement
	 * @return
	 */
	public static String addToBatch(String insertStmt){
		String res = "";
		try {
//			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
//				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
//				  insertStmt += ")"; 
			_statement.addBatch(insertStmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.err.println(e.getMessage());
			//res = false;
			res = e.getMessage();
		}
		return res;
	}
	
	/*****
	 * Insert PROV doc batch into provenance database.
	 * @param insert statement
	 * @return
	 */
	public static int executeBatchForProvData() throws Exception{
//		boolean res = true;
		int dupCnt = 0;
		try {
//			String insertStmt = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES (";
//				  insertStmt += "'" + Constants.SERVICE_START_YEAR_MONTH + "'" ;
//				  insertStmt += ")"; 
			// execute batch
			int[] updateCntArr = _statement.executeBatch();
			for(int i= 0; i < updateCntArr.length;i++){
				dupCnt += updateCntArr[i];
			}
			// clear batch for next upate
			_statement.clearBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
//			System.err.println(e.getMessage());
			throw new Exception (e.getMessage());
		}
		return dupCnt;
	}
	
	/*****
	 * Insert PROV doc batch into provenance database.
	 * @param insert statement
	 * @return
	 */
	public static String executeBatch() {
//		boolean res = true;
		String res = "";
		try {
			_statement.executeBatch();
			_statement.clearBatch();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
//			System.err.println(e.getMessage());
//			throw new Exception (e.getMessage());
			res = ex.getMessage();
		}
		return res;
	}
	
//	public static ResultSet executeQueryOnProvDB(String query){
//		 ResultSet rs = null;
//		 try {
//			rs = _statement.executeQuery(query);
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

	/****
	 * Execute a query for simulation provenance DB
	 * @param query
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String query) throws Exception{
		 ResultSet rs = null;
		 try {
			rs = _statement.executeQuery(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new Exception (e.getMessage());
		}
		 return rs;
	}

	public static void createTable(String createStmt) throws Exception{
		// TODO Auto-generated method stub
		_statement.execute(createStmt);
	}
	
	public static void dropTable(String dropStmt) throws Exception{
		// TODO Auto-generated method stub
		_statement.execute(dropStmt);
	}

	public static void executeUpdate(String updateStmt) throws Exception{
		// TODO Auto-generated method stub
		_statement.executeUpdate(updateStmt);
		commit();
	}
}

