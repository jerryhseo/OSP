package com.kisti.osp.icesheet.portlet;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ProvDataRetriever {
	
	 public static Vector<ProvData> retrieveProvenance(Long subjectId, 
			 										   String subjectName, 
			 										   String subjectVersion, 
			 										   String searchDayStart, 
			 										   String topK) throws Exception{
		 Vector<ProvData> resVec = new Vector<ProvData>();
		 if(subjectId == null){
			 subjectId = ProvDataCollector.getScienceAppId(subjectName, subjectVersion);
		 }
		 
		 String queryStmt = " SELECT " + Constants.simUuidFN + ", " 
				 		+ Constants.simulationTitleFN + ", "
				 		+ Constants.simulationCreateDtFN + ", "
				 		+ Constants.provLocFN + " " 
				 		+ " FROM " + Constants.SIMULATION_PROV_TABLE_NAME 
				 		+ " WHERE subjectId = " + subjectId + " ";
		 if(searchDayStart != null){
			 String translatedSearchCond = "SUBDATE(NOW(), "+searchDayStart+")";
			 queryStmt += " and "+Constants.simulationCreateDtFN + " >= " + translatedSearchCond;
			 queryStmt += " order by " + Constants.simulationCreateDtFN + " desc";
		 }
		 
		 if(topK != null){
			 queryStmt += " LIMIT 0 , "+topK;
		 }else{
			 queryStmt += " LIMIT 0 , "+Constants.DEFAULT_TOP_K;
		 }
		 
		 if(IceSheetDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		 }
System.out.println(queryStmt);	
		 ResultSet rs = IceSheetDBConnector.executeQuery(queryStmt);
		 while(rs.next()){
			 String simUuid = rs.getString(1);
			 String simTitle = rs.getString(2);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			 String simCreateDt = (rs.getTimestamp(3)).toString();
			 String simCreateDt = sdf.format(rs.getTimestamp(3));
	    	 String outputDirPath = rs.getString(4);
	    	 
	    	 ProvData pd = new ProvData(simUuid, simTitle, simCreateDt, outputDirPath);
	    	 resVec.add(pd);
		 }
				 
		 IceSheetDBConnector.close();
		 return resVec;
	 }
	 
//	 SELECT * 
//	 FROM EDSIM_SimulationProv
//	 WHERE simulationCreateDt >= SUBDATE( NOW( ) , 60 ) 
//	 order by simulationCreateDt desc
//	 LIMIT 0 , 10
	
	/*****
	 * Retrieve all file names under a given directory
	 * @param outputPath
	 * @return
	 */
	public static Vector<String> getAllFileList(String outputPath){
		Vector<String> fileNameVec = new Vector<String>();
		
		//TODO: Access the provenance server and get all the files under the given output path
		try{
//			String sshCmd = "ssh "+Constants.PROV_SERVER_USER_ACCNT+"@"+Constants.PROV_SERVER_IP +" -p 22002 " + "'ls "+ outputPath +"'";
			String sshCmd = "ssh "+Constants.PROV_SERVER_USER_ACCNT+"@"+Constants.PROV_SERVER_IP +" -p 22002 ";
//System.out.println(sshCmd);
//System.out.println("process gets executed..");
			Process p = Runtime.getRuntime().exec(sshCmd);
//			PrintStream out = new PrintStream(p.getOutputStream());
//			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			while (in.ready()) {
//			  String s = in.readLine();
//			  System.out.println(s);
//			  fileNameVec.add(s);
//			}
//			out.println("exit");
//System.out.println("process executed..");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);
			out.println("ls "+outputPath);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String temp = "";
			Thread.sleep(3000);
			while (in.ready()) {
			    String s = in.readLine();
//			    System.out.println(s);
			    fileNameVec.add(s);
			}
//System.out.println("waiting for input...");
//			while ((temp = in.readLine()) != null) {
//System.out.println(temp);
//			  fileNameVec.add(temp);
//			}
//			do{
//				System.out.println("getting results");
//				temp = in.readLine();
//				if(temp != null){
//					 String s = temp;
//				System.out.println(s);
//				  fileNameVec.add(s);
//				}
//			}while(temp != null);
//System.out.println("before exit");
			out.println("exit");
			// Let's execute the command.
//			// Get any input stream.
//			InputStream instd = p.getInputStream();
//			// Let's get it through buffered reader.
//			BufferedReader buf_reader = new BufferedReader(
//					new InputStreamReader(instd));
//			String temp = "";
//			// System.out.println("new line executed command: " + command);
//			while ((temp = buf_reader.readLine()) != null) {
//				System.out.println(temp);
//				fileNameVec.add(temp);
//			}
			// Let's close buffered reader
//			buf_reader.close();
			//p.waitFor();
System.out.println("Command successfully executed.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return fileNameVec;
	}
	
	/*****
	 * Retrieve all file names under a given directory
	 * @param outputPath
	 * @return
	 */
	public static String readOutputFile(String fileName){
		StringBuffer sb = new StringBuffer();
		//TODO: Access the provenance server and get all the files under the given output path
		try{
//			String sshCmd = "ssh "+Constants.PROV_SERVER_USER_ACCNT+"@"+Constants.PROV_SERVER_IP +" -p 22002 " + "'ls "+ outputPath +"'";
			String sshCmd = "ssh "+Constants.PROV_SERVER_USER_ACCNT+"@"+Constants.PROV_SERVER_IP +" -p 22002 ";
//System.out.println(sshCmd);
//System.out.println("process gets executed..");
			Process p = Runtime.getRuntime().exec(sshCmd);
//			PrintStream out = new PrintStream(p.getOutputStream());
//			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			while (in.ready()) {
//			  String s = in.readLine();
//			  System.out.println(s);
//			  fileNameVec.add(s);
//			}
//			out.println("exit");
//System.out.println("process executed..");
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);
			out.println("cat "+fileName);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			String temp = "";
			Thread.sleep(1000);
			while (in.ready()) {
			    String s = in.readLine();
//			    System.out.println(s);
			    sb.append(s+"\n");
			}
//System.out.println("waiting for input...");
//			while ((temp = in.readLine()) != null) {
//System.out.println(temp);
//			  fileNameVec.add(temp);
//			}
//			do{
//				System.out.println("getting results");
//				temp = in.readLine();
//				if(temp != null){
//					 String s = temp;
//				System.out.println(s);
//				  fileNameVec.add(s);
//				}
//			}while(temp != null);
//System.out.println("before exit");
			out.println("exit");
			// Let's execute the command.
//			// Get any input stream.
//			InputStream instd = p.getInputStream();
//			// Let's get it through buffered reader.
//			BufferedReader buf_reader = new BufferedReader(
//					new InputStreamReader(instd));
//			String temp = "";
//			// System.out.println("new line executed command: " + command);
//			while ((temp = buf_reader.readLine()) != null) {
//				System.out.println(temp);
//				fileNameVec.add(temp);
//			}
			// Let's close buffered reader
//			buf_reader.close();
			//p.waitFor();
System.out.println("Command successfully executed.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return sb.toString();
	}
	/****
	 * Retrieve a PROV document
	 * @param  
	 * @return 
	 */
	public static Vector<ProvData> retrievePROVDoc(String serviceYearMon,
												 String subjectName,
												 String subjectVersion,
												 String startDateTime,
												 String endDateTime) throws Exception{
		//String resPROVDoc = "";
		Vector<ProvData> resProvDataVec = new Vector<ProvData>();
		
		if(IceSheetDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		}
		
//		String queryStmt = " SELECT " + Constants.PROV_LOC_FN
//						 + " FROM " + Constants.SIMULATION_PROV_TABLE_NAME 
//						 + " WHERE " + Constants.SERVICE_START_YEAR_MONTH_FN + " = '" + serviceYearMon +"' and "
//						 + Constants.simUuidFN + " = '" + simulationUuid + "'";
//		
//System.out.println(queryStmt);
		
		
//		String queryStmt = " SET @row_num = 0; ";
//		queryStmt += " SELECT @row_num := @row_num + 1 as row_number, ";
		String queryStmt = " SELECT ";
		queryStmt += " t1."+Constants.simUuidFN+", ";
		queryStmt += " t1."+Constants.simulationTitleFN+", ";
		queryStmt += " t1."+Constants.simulationCreateDtFN+", ";
		queryStmt += " t1."+Constants.provLocFN;
		queryStmt += " FROM " + Constants.SIMULATION_SUBJECT_TABLE_NAME + " t0, ";
		queryStmt += " " + Constants.SIMULATION_PROV_TABLE_NAME + " t1 ";
		queryStmt += " WHERE t1." + Constants.serviceStartYearMonthFN + " = " + serviceYearMon;
		queryStmt += " and t0." + Constants.subjectIdFN + " = t1." + Constants.subjectIdFN;
		queryStmt += " and t0." + Constants.subjectNameFN + " = " + subjectName;
		queryStmt += " and t0." + Constants.subjectVersionFN + " = " + subjectVersion;
		queryStmt += " and t0." + Constants.simulationCreateDtFN + " >= '" + startDateTime + "' and t0." + Constants.simulationCreateDtFN + " <= '" + endDateTime + "'";
		queryStmt += " order by t0."+ Constants.simulationCreateDtFN + ", t0."+Constants.simulationTitleFN;
System.out.println(queryStmt);	
		ResultSet rs = IceSheetDBConnector.executeQuery(queryStmt);		
		try {
			while(rs.next()){
//				resPROVDoc = rs.getString(1);
				String simUuid = rs.getString(1);
				String simTitle = rs.getString(2);
				
				Date temp = rs.getDate(3);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String simCreateDtStr = sdf.format(temp);
				String provLoc = rs.getString(4);
				
				ProvData provData = new ProvData(
										simUuid,
										simTitle,
										simCreateDtStr,
										provLoc);
				resProvDataVec.add(provData);
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IceSheetDBConnector.close();
		return resProvDataVec;
	}
}
