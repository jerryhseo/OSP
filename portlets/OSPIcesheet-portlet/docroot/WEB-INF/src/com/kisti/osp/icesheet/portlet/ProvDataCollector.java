package com.kisti.osp.icesheet.portlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;

import com.kisti.osp.icesheet.PROV.Simulation;
import com.kisti.osp.icesheet.PROV.SimulationJob;
import com.kisti.osp.icesheet.PROV.SimulationJobData;
import com.kisti.osp.icesheet.PROV.SimulationSubject;
import com.kisti.osp.icesheet.PROV.UserInfo;

//import com.kisti.science.platform.provenance.portlet.superman.Constants;
//import com.kisti.science.platform.provenance.portlet.superman.model.Simulation;
//import com.kisti.science.platform.provenance.portlet.superman.model.SimulationJob;
//import com.kisti.science.platform.provenance.portlet.superman.model.SimulationJobData;
//import com.kisti.science.platform.provenance.portlet.superman.model.UserInfo;

public class ProvDataCollector {
	
	private static SimulationSubject targetSimSubject = null;
	
	private static Vector<Simulation> sim_rec_vector = new Vector<Simulation>();
	private static Vector<SimulationJob> simjob_rec_vector = new Vector<SimulationJob>();
	private static Vector<SimulationJobData> simjobdata_rec_vector = new Vector<SimulationJobData>();
	private static Vector<UserInfo> userinfo_rec_vector = new Vector<UserInfo>();
	
	/****
	 * Flush provenance data for next fetch
	 */
	public static void flushProvenanceData(){
//		sim_rec_vector = new Vector<Simulation>();
//		simjob_rec_vector = new Vector<SimulationJob>();
//		simjobdata_rec_vector = new Vector<SimulationJobData>();
//		userinfo_rec_vector = new Vector<UserInfo>();
	}
	
	/****
	 * Get jobData matching a given simulationUuid.
	 * @param simulationUuid
	 * @return
	 */
	public static HashMap<String, String> getJobData(String subjectName,
													 String subjectVersion,
													 String simulationUuid) {
		HashMap<String, String> resMap = new HashMap<String, String>();
		
		if(EdisonDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		}

		String getJobInfoQuery = "SELECT t2.jobUuid, "
							  + "t3.jobData "
							  + "FROM "+Constants.SCIENCAPP_TABLE_NAME+" t0, "
							  + Constants.SIMULATION_TABLE_NAME +" t1, "
							  + Constants.SIMULATION_JOB_TABLE_NAME +" t2, "
							  + Constants.SIMULATION_JOB_DATA_TABLE_NAME +" t3 "
							  + "WHERE t0.name = '"+subjectName+"' "
							  + "AND t0.version = '"+subjectVersion+"' "
							  + "AND t0.scienceAppId = t1.scienceAppId "
							  + "AND t1.simulationUuid = t2.simulationUuid "
							  + "AND t1.simulationUuid = '"+simulationUuid+"' "
					  		  + "AND t2.jobUuid = t3.jobUuid";
				
System.out.println(getJobInfoQuery);		
		ResultSet rs = EdisonDBConnector.executeQuery(getJobInfoQuery);		
		try {
			while(rs.next()){
				String jobUuid = rs.getString(1);
				String jobData = rs.getString(2);
				resMap.put(jobUuid, jobData);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EdisonDBConnector.close();
		return resMap;
	}
	

	/****
	 * Return science app ID
	 * @param scienceAppName
	 * @param scienceAppVersion
	 * @return
	 */
	public static long getScienceAppId(String subjectName, String subjectVersion) throws Exception{
		// TODO Auto-generated method stub
		long appId = 0;
		if(EdisonDBConnector.open(false)){
//			System.out.println("EDISON DB successfully opened!");
		}
		
		String getAppIdQuery = "SELECT t0.scienceAppId "
				  + "FROM "+Constants.SCIENCAPP_TABLE_NAME+" t0 "
				  + "WHERE t0.name = '"+subjectName+"' "
				  + "AND t0.version = '"+subjectVersion+"' ";
if(!Constants.PAPER_EXP)	{
	System.out.println(getAppIdQuery);		
	System.out.println("<<< Trying to obtain the matching science app id with "+subjectName+"/"+subjectVersion+" from EDISON DB");
}
		ResultSet rs = EdisonDBConnector.executeQuery(getAppIdQuery);		
		try {
			while(rs.next()){
				appId = rs.getLong(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		EdisonDBConnector.close();
		return appId;
	}
	
	/*****
	 * Obtain all simulation records matching subjectName + subjectVersion
	 * @param subjectName app name
	 * @param subjectVersion app version
	 * @return <userName, <simulation uuid - <jobuuid, jobData>>>
	 * @throws Exception
	 */
	public static HashMap<String, HashMap<String, HashMap<String, String>>> getAllSimulations(String subjectName,
																			 				  String subjectVersion) throws Exception{
		// 
		// <userName, <simulation uuid - <jobuuid, jobData>>>
		HashMap<String, HashMap<String, HashMap<String, String>>> resMap = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}
		String getJobInfoQuery = "SELECT t4.screenName, "
							  + "t1.simulationUuid, "
							  + "t2.jobUuid, "
							  + "t3.jobData, "
							  + " TO_SECONDS(t2.jobStartDt)-TO_SECONDS(t2.jobSubmitDt) as jobQueuingTime, "
							  + " TO_SECONDS(t2.jobEndDt)-TO_SECONDS(t2.jobStartDt) as jobElapsedTime, "
							  + " t2.jobStatus, "
							  + " t1."+Constants.simulationCreateDtFN+", "
							  + " t1."+Constants.simulationTitleFN+" "
							  + "FROM "+Constants.SCIENCAPP_TABLE_NAME+" t0, "
							  + Constants.SIMULATION_TABLE_NAME +" t1, "
							  + Constants.SIMULATION_JOB_TABLE_NAME +" t2, "
							  + Constants.SIMULATION_JOB_DATA_TABLE_NAME +" t3, "
							  + Constants.USER_TABLE_NAME +" t4 "
							  + "WHERE t0.name = '"+subjectName+"' "
							  + "AND t0.version = '"+subjectVersion+"' "
							  + "AND t0.scienceAppId = t1.scienceAppId "
							  + "AND t2.jobStatus = " + Constants.JOB_SUCCESS_STATUS_CODE + " "
							  + "AND t1.userId = t4.userId   "
							  + "AND t1.simulationUuid = t2.simulationUuid "
					  		  + "AND t2.jobUuid = t3.jobUuid";
				
System.out.println(getJobInfoQuery);	
		ResultSet rs = EdisonDBConnector.executeQuery(getJobInfoQuery);		
		try {
			System.out.println("<<< Trying to obtain all the simulations on "+subjectName+"/"+subjectVersion+" from EDISON DB");
			while(rs.next()){
				String userName = rs.getString(1);
				String simUuid = rs.getString(2);
				String jobUuid = rs.getString(3);
				String jobData = rs.getString(4);
				Long jobQueingTime = rs.getLong(5);
				Long jobElapsedTime = rs.getLong(6);
				String statusCode = rs.getString(7);
				Timestamp temp = rs.getTimestamp(8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String simCreateDt = sdf.format(temp);
				String simulationTitle = rs.getString(9);
				
				// first, seek for user from the map to be returned
				HashMap<String, HashMap<String, String>> simDataMap = resMap.get(userName);
				if(simDataMap == null){
					simDataMap = new HashMap<String, HashMap<String, String>>();
				}
				
				// second, seek for a simulation from the map
				HashMap<String, String> jobDataMap = simDataMap.get(simUuid);
				if(jobDataMap == null){
					jobDataMap =  new HashMap<String, String>();
				}
				
				// update jobDataMap
				jobDataMap.put(jobUuid, jobData);
				// queuing time
				jobDataMap.put(Constants.PARAM_VAL_SET_QUEUING_TIME_ATT_NAME, Long.toString(jobQueingTime));
				// elapsed time
				jobDataMap.put(Constants.PARAM_VAL_SET_ELAPSED_TIME_ATT_NAME, Long.toString(jobElapsedTime));
				// status code
				jobDataMap.put(Constants.PARAM_VAL_SET_STATUS_ATT_NAME, statusCode);
				// simulation create date
				jobDataMap.put(Constants.PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME, simCreateDt);
				// simulation title 
				jobDataMap.put(Constants.simulationTitleFN, simulationTitle);
				
				// update simDataMap
				simDataMap.put(simUuid, jobDataMap);
				
				// update username
				resMap.put(userName, simDataMap);
			}
			System.out.println(">>> Just got all the simulations.");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		EdisonDBConnector.close();
		return resMap;
	}
	
	/*****
	 * Collection simulation provenance data
	 * @param scienceAppId science app ID
	 * @param scienceAppName science app name
	 * @param scienceAppVersion science app version
	 * @param simulationUuid simulation uuid
	 * @param jobDataMap jobuuid - jobData map
	 * @param jobResultMap jobuuid - job success or fail map
	 */
	public static void collectSimulationProvenance(String scienceAppId,
												   String scienceAppName,
												   String scienceAppVersion,
												   String simulationUuid,
												   HashMap<String, String> jobDataMap,
												   HashMap<String, Boolean> jobResultMap
												   ){
		
	}
	
	/*****
	 * 
	 */
	public static void collectJobData(String solvName){
		String query = "select sim.groupid, sim.simulationuuid, sov.scienceappid, sov.name, sov.version, sj.jobuuid, TO_SECONDS(sj.jobEndDt)-TO_SECONDS(sj.jobStartDt) as jobElapsedTime, jobdata "
				+ "	from EDAPP_ScienceApp sov, EDSIM_Simulation sim, EDSIM_SimulationJob sj, EDSIM_SimulationJobData sjd "
				+ " where sov.scienceappid = sim.scienceappid and sim.simulationuuid = sj.simulationuuid and sj.jobuuid = sjd.jobuuid and scienceAppName = "
				//+ " '"+solvName+"' and sim.simulationuuid = '00026e7a-af31-4e92-a2fa-0bf2198a808c' "
				+ " '"+solvName+"' "
				+ " order by groupid, name, version, simulationuuid, jobuuid";
//System.out.println(query);
		if(EdisonDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		}
//		Path currentRelativePath = Paths.get("");
//		String s = currentRelativePath.toAbsolutePath().toString();
//		System.out.println("Current relative path is: " + s);

		ResultSet rs = EdisonDBConnector.executeQuery(query);
		try {
			while(rs.next()){
				String simUuid = rs.getString(2);
				String jobuuid = rs.getString(6);
				String jobData = rs.getString(8);
				
				String jsonFileName = "../"+Constants.ICESHEET_MAIN_AXIS + File.separator + simUuid + "_" + jobuuid + ".json";
				//String jsonFileName = Constants.SUPERMAN_JSON_DIR_NAME + File.separator + simUuid + "_" + jobuuid + ".json";
				File f = new File(jsonFileName);
				OutputStream os = new FileOutputStream(f);
				os.write(jobData.getBytes());
				os.close();
//				try {
//			         byte bWrite [] = {11,21,3,40,5};
//			         OutputStream os = new FileOutputStream("test.txt");
//			         for(int x = 0; x < bWrite.length ; x++) {
//			            os.write( bWrite[x] );   // writes the bytes
//			         }
//			         os.close();
//			     
//			         InputStream is = new FileInputStream("test.txt");
//			         int size = is.available();
//
//			         for(int i = 0; i < size; i++) {
//			            System.out.print((char)is.read() + "  ");
//			         }
//			         is.close();
//			      }catch(IOException e) {
//			         System.out.print("Exception");
//			      }	
				
			}
			rs.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	/****
	 * Collect a simulation provenance data on a given simulation uuid
	 * @param simUuid
	 */
	public static HashMap<String, Vector<Object>> collectProvenanceDataByApp(String scienceAppName, 
												  String scienceAppVersion,
												  String reqStartDateTime,
												  String reqEndDateTime){
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		}
				
		//String query = "SELECT count(*) FROM EDAPP_Solver";
		//String query = "SELECT count(*) FROM EDSIM_Simulation";
		//String query = "SELECT count(*) FROM EDSIM_SimulationJob";
				
		//int numJobCnt = 5;
//				String query = "SELECT "+simUuidFN+", count("+jobSeqNoFN+") as numJobs"
//							+ " FROM " + simJobTblName
//							+ " WHERE "+jobEndDtFN+" - "+jobStartDtFN+" >= "+jobCompletionTimeThreshold+" and " + jobExecPathFN + " like '%"+solverNameVal+"%' "
//							+ " group by "+simUuidFN 
//							+ " having count(*) >= "+numJobCnt
//							+ " order by numJobs asc";
		//System.out.println(query);
//		System.out.println("Requested uuid: " + simUuid);	
//		String startDateTime = "2017-04-09 00:00:00";
//		String endDateTime = "2017-04-15 00:00:00";
		String query = 
				"SELECT t0."+Constants.simUuidFN+"," + 
				" t0."+Constants.groupIdFN+","+
			    " t0."+Constants.userIdFN+","+
			    " t0."+Constants.scienceAppIdFN+","+
			    " t0."+Constants.simScienceAppNameFN+","+
			    " t1."+Constants.jobUuidFN+","+
			    " t1."+Constants.jobSeqNoFN+","+
			    " t1."+Constants.jobStatusFN+","+
			    " t1."+Constants.jobStartDtFN+","+
			    " t1."+Constants.jobEndDtFN+","+"\n"+ 
			    " TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+") as elapsedTime,"+ 
			    //" SEC_TO_TIME(TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+")) as elapsedTime,"+ 
				" t1."+Constants.jobExecPathFN+","+ 
			    " t1."+Constants.jobUniversityFieldFN+","+ 
			    " t1."+Constants.jobInputDeckYnFN+","+
			    " t1."+Constants.jobInputDeckNameFN+","+
			    " t2."+Constants.jobDataFN+","+
			    " t3."+Constants.screenNameFN+","+
			    " t3."+Constants.firstNameFN+","+
			    " t0."+Constants.simulationTitleFN+","+//19
			    " t0."+Constants.simulationCreateDtFN+//20
			    ","+" t1."+Constants.jobSubmitDtFN+//21
			    "\n"+
			" FROM "+Constants.SCIENCAPP_TABLE_NAME+" t4,"
				+ Constants.SIMULATION_TABLE_NAME+" t0,"+
			" " + Constants.SIMULATION_JOB_TABLE_NAME+" t1,"+
			" " + Constants.USER_TABLE_NAME+" t3,"+
			" " + Constants.SIMULATION_JOB_DATA_TABLE_NAME+" t2\n"+
			" WHERE "
//			+ "t0."+Constants.simUuidFN+" = \'"+simUuid+"\'\n" +
//			" and "  
		  	+ "t0."+Constants.simUuidFN+" = t1."+Constants.simUuidFN+"\n"+
			" and t1."+Constants.jobUuidFN+" = t2."+Constants.jobUuidFN+"\n"+
			" and t0."+Constants.userIdFN +" = t3."+Constants.userIdFN+"\n"+
			" and t0."+Constants.scienceAppIdFN+"= t4."+Constants.scienceAppIdFN+"\n"+
			" and t4."+Constants.appNameFN+"='"+scienceAppName+"'" + "\n"+
			" and t4."+Constants.appVersionFN+"='"+scienceAppVersion+"'" + "\n"+
			" and t1."+Constants.jobSubmitDtFN+" >= " + "'"+reqStartDateTime+"'" + " and t1."+Constants.jobSubmitDtFN+" <= "+ "'"+reqEndDateTime+"'" +"\n"+
			" order by "+
			"t4."+Constants.appNameFN+" asc, "+"t4."+Constants.appVersionFN+" asc, t0."+Constants.simUuidFN+" asc, t1."+Constants.jobSubmitDtFN+" asc, "+
			Constants.jobSeqNoFN+" asc" + "\n";
System.out.println(query);	
//		SELECT t0.simulationUuid, t0.groupId, t0.userId, t0.scienceAppId, t0.scienceAppName, t1.jobUuid, t1.jobSeqNo, t1.jobStatus, t1.jobStartDt, t1.jobEndDt,
//		 SEC_TO_TIME(TO_SECONDS(t1.jobEndDt) - TO_SECONDS(t1.jobStartDt)) as elapsedTime, t1.jobExecPath, t1.jobUniversityField, t1.jobInputDeckYn, t1.jobInputDeckName, t2.jobData, t3.screenName, t3.firstName
//		 FROM EDSIM_Simulation t0, EDSIM_SimulationJob t1, User_ t3, EDSIM_SimulationJobData t2
//		 WHERE t0.simulationUuid = 'e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2'
//		 and t0.simulationUuid = t1.simulationUuid
//		 and t1.jobUuid = t2.jobUuid
//		 and t0.userId = t3.userId
//		 order by jobSeqNo asc
		HashMap<String, Vector<Object>> resultMap = new HashMap<String, Vector<Object>>();

		String tempSimUuid = "";
		Vector<Simulation> simulationVec = new Vector<Simulation>();
		Vector<SimulationJob> simulationJobVec = new Vector<SimulationJob>();
		Vector<SimulationJobData> simulationJobDataVec = new Vector<SimulationJobData>();
		Vector<UserInfo> userInfoVec = new Vector<UserInfo>();
		ResultSet rs = EdisonDBConnector.executeQuery(query);
		try {
			while(rs.next()){
				
				if(!tempSimUuid.equalsIgnoreCase(rs.getString(1))){
					if(!tempSimUuid.equalsIgnoreCase("")){
						Vector<Object> resVec = new Vector<Object>();
						resVec.add(simulationVec);
						resVec.add(simulationJobVec);
						resVec.add(simulationJobDataVec);
						resVec.add(userInfoVec);
						resultMap.put(tempSimUuid, resVec);
						
						// initialize the data vectors
						simulationVec = new Vector<Simulation>();
						simulationJobVec = new Vector<SimulationJob>();
						simulationJobDataVec = new Vector<SimulationJobData>();
						userInfoVec = new Vector<UserInfo>();
					}
					Simulation sim_rec = new Simulation();
					sim_rec.simUuidVal = rs.getString(1);
					sim_rec.groupIdVal = rs.getLong(2);
					sim_rec.userIdVal = rs.getLong(3);
					
					if(targetSimSubject == null){
						targetSimSubject = new SimulationSubject();
						targetSimSubject.subjectId = rs.getLong(4);
						targetSimSubject.subjectName = rs.getString(5);
						targetSimSubject.subjectVersion = scienceAppVersion;
					}
					
					sim_rec.scienceAppIdVal = rs.getLong(4);
					sim_rec.scienceAppNameVal = rs.getString(5);
					sim_rec.scienceAppVersionVal = scienceAppVersion;
					sim_rec.simulationTitleVal = rs.getString(19);
					Date temp = rs.getDate(20);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sim_rec.simulationCreateDtVal = sdf.format(temp);
					
					// save this simulationuuid
					tempSimUuid = rs.getString(1);
					sim_rec.printContents();
					simulationVec.add(sim_rec);
					
					UserInfo user_rec = new UserInfo();
					user_rec.userIdVal =  rs.getLong(3);
					user_rec.screenNameVal = rs.getString(17);
					user_rec.firstNameVal = rs.getString(18);
					user_rec.printContents();
					userInfoVec.add(user_rec);
				}
					
				SimulationJob simjob_rec = new SimulationJob();
				simjob_rec.jobUuidVal = rs.getString(6);
				simjob_rec.jobSeqNoVal =  rs.getLong(7);
				simjob_rec.jobStatusVal = rs.getLong(8);
				simjob_rec.jobStartDtVal = rs.getString(9);
				simjob_rec.jobEndDtVal = rs.getString(10);
//				simjob_rec.jobCompletionTimeVal = rs.getLong(11);
				simjob_rec.jobCompletionTimeVal = rs.getString(11);
				simjob_rec.jobExecPathVal = rs.getString(12);
				simjob_rec.jobUniversityFieldVal = rs.getInt(13);
				simjob_rec.jobInputDeckYnVal= rs.getShort(14);
				simjob_rec.jobInputDeckNameVal= rs.getString(15);
				simjob_rec.jobSubmitDtVal = rs.getString(21);
				simjob_rec.printContents();
				simulationJobVec.add(simjob_rec);
				
				SimulationJobData simjobdata_rec = new SimulationJobData();
				simjobdata_rec.jobDataVal = rs.getString(16);
				simjobdata_rec.printContents();
				simulationJobDataVec.add(simjobdata_rec);
				
				System.out.println("========================");
				//break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!tempSimUuid.equalsIgnoreCase("")){
			Vector<Object> resVec = new Vector<Object>();
			resVec.add(simulationVec);
			resVec.add(simulationJobVec);
			resVec.add(simulationJobDataVec);
			resVec.add(userInfoVec);
			resultMap.put(tempSimUuid, resVec);	
		}
		System.out.println("# of simulations: " + resultMap.size());
		EdisonDBConnector.close();
		return resultMap;
	}
	

	/*****
	 * Get science app information
	 * @param appId science app id
	 * @return a map of information: app name, app version
	 */
	public static HashMap<String, String> getScienceAppInfo(Long appId) {
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}
		
		// resMap: carries a variety of simulation information associated with simUuid
		HashMap<String, String> resMap = new HashMap<String, String>();
System.out.println("Requested app id: " + appId);		
		String query = "SELECT t4."+Constants.appNameFN+","+
						" t4."+Constants.appVersionFN+"\n"+
						" FROM "+Constants.SCIENCAPP_TABLE_NAME+" t4 " +
						" WHERE t4."+Constants.scienceAppIdFN+"="+appId + "\n";
System.out.println(query);	
		ResultSet rs = EdisonDBConnector.executeQuery(query);		
		try {
			System.out.println("<<< Trying to obtain a science app on "+appId+" from EDISON DB");
			while(rs.next()){
				// get app name
				resMap.put(Constants.scienceAppNameFN, rs.getString(1));
				// get app version
				resMap.put(Constants.scienceAppVersionFN, rs.getString(2));
			}
			rs.close();
		}catch(Exception ex){
			new Exception(ex.getMessage());
		}
		
		EdisonDBConnector.close();
		return resMap;
	}
	
	/******
	 * Get simulation information associated with simUuid
	 * @param simUuid simulation uuid
	 * @return simulation information map
	 */
	public static HashMap<String, Object> getNewSimulationInfo(String simUuid) throws Exception{
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}

		// resMap: carries a variety of simulation information associated with simUuid
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		String getSimInfoQuery = "SELECT "
				  + "t0."+Constants.screenNameFN + ", "
				  + "t4."+Constants.scienceAppIdFN +", "
				  + "t4."+Constants.scienceAppNameFN +", "
				  + "t4."+Constants.scienceAppVersionFN +", "
				  + "t1."+Constants.simulationTitleFN+", "
				  + "t1."+Constants.simulationCreateDtFN+", "
				  + "t2."+Constants.jobUuidFN+", "
				  + "t3."+Constants.jobDataFN+", "
				  + "t2."+Constants.jobStatusFN+", "
				  + "t2."+Constants.jobStartDtFN+", "
				  + "t2."+Constants.jobEndDtFN+" "
				  + "FROM " + Constants.USER_TABLE_NAME +" t0, "
				  + Constants.SIMULATION_TABLE_NAME +" t1, "
				  + Constants.SIMULATION_JOB_TABLE_NAME +" t2, "
				  + Constants.SIMULATION_JOB_DATA_TABLE_NAME +" t3, "
				  + Constants.SCIENCAPP_TABLE_NAME +" t4 "
				  + "WHERE t1."+Constants.simUuidFN +" = '" + simUuid + "'" 
				  + " and t1."+Constants.simUuidFN +" = t2."+Constants.simUuidFN 
				  + " and t2."+Constants.jobUuidFN +" = t3."+Constants.jobUuidFN 
				  + " and t1."+Constants.scienceAppIdFN +" = t4."+Constants.scienceAppIdFN 
				  + " and t1."+Constants.userIdFN + " = t0." + Constants.userIdFN;
System.out.println(getSimInfoQuery);	
		ResultSet rs = EdisonDBConnector.executeQuery(getSimInfoQuery);		
		try {
			System.out.println("<<< Trying to obtain a simulation on "+simUuid+" from EDISON DB");
			while(rs.next()){
				String screenName = rs.getString(1);
				Long scienceAppId = rs.getLong(2);
				String scienceAppName = rs.getString(3);
				String scienceAppVersion = rs.getString(4);
				String simulationTitle = rs.getString(5);
				Timestamp temp = rs.getTimestamp(6);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String simCreateDtStr = sdf.format(temp);
				String jobUuid = rs.getString(7);
				String jobData = rs.getString(8);
				String jobStatus = rs.getString(9);
				temp = rs.getTimestamp(10);
				String jobStartDt = sdf.format(temp);
				temp = rs.getTimestamp(11);
				String jobEndDt = sdf.format(temp);
				
				// now collect this information into a map
				// get simulation job uuid 
				resMap.put(Constants.simUuidFN, simUuid);
				// get screen name
				resMap.put(Constants.screenNameFN, screenName);
				// get science app id
				resMap.put(Constants.scienceAppIdFN, scienceAppId);
				// get science app name
				resMap.put(Constants.scienceAppNameFN, scienceAppName);
				// get science app version
				resMap.put(Constants.scienceAppVersionFN, scienceAppVersion);
				// get simulation title
				resMap.put(Constants.simulationTitleFN, simulationTitle);
				// get simulation create date
				resMap.put(Constants.simulationCreateDtFN, simCreateDtStr);
				// get simulation job uuid 
				resMap.put(Constants.jobUuidFN, jobUuid);
				// get simulation job data 
				resMap.put(Constants.jobDataFN, jobData);
				// get simulation job status
				resMap.put(Constants.jobStatusFN, jobStatus);
				// get simulation job start date
				resMap.put(Constants.jobStartDtFN, jobStartDt);
				// get simulation job end date
				resMap.put(Constants.jobEndDtFN, jobEndDt);
			}
			rs.close();
		}catch(Exception ex){
			new Exception(ex.getMessage());
		}
		
		EdisonDBConnector.close();
		return resMap;
	}
	
	/******
	 * Get simulation information associated with simUuid
	 * @param simUuid simulation uuid
	 * @return simulation information map
	 */
	public static HashMap<String, Object> getSimulationInfo(String simUuid) throws Exception{
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}
		
		// resMap: carries a variety of simulation information associated with simUuid
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		String getSimInfoQuery = "SELECT "
				  + "t1.userId, "
				  + "t1.scienceAppId, "
				  + "t1.simulationTitle, "
				  + "t1.simulationCreateDt, "
				  + "t0."+Constants.screenNameFN + " "
				  + "FROM " + Constants.USER_TABLE_NAME +" t0, "
				  + Constants.SIMULATION_TABLE_NAME +" t1 "
				  + "WHERE t1.simulationUuid = '" + simUuid + "'" 
				  + " and t1."+Constants.userIdFN + " = t0." + Constants.userIdFN;
System.out.println(getSimInfoQuery);	
		ResultSet rs = EdisonDBConnector.executeQuery(getSimInfoQuery);		
		try {
			System.out.println("<<< Trying to obtain a simulation on "+simUuid+" from EDISON DB");
			while(rs.next()){
				String screenName = rs.getString(5);
				Long userId = rs.getLong(1);
				Long scienceAppId = rs.getLong(2);
				String simTitle = rs.getString(3);
				Date temp = rs.getDate(4);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String simCreateDtStr = sdf.format(temp);
				// get user id
				resMap.put(Constants.userIdFN, userId);
				// get app id
				resMap.put(Constants.scienceAppIdFN, scienceAppId);
				// get simulation title
				resMap.put(Constants.simulationTitleFN, simTitle);
				// get simulation create date
				resMap.put(Constants.simulationCreateDtFN, simCreateDtStr);
				// get screen name
				resMap.put(Constants.screenNameFN, screenName);
			}
			rs.close();
		}catch(Exception ex){
			new Exception(ex.getMessage());
		}
		
		EdisonDBConnector.close();
		return resMap;
	}
	
	/*****
	 * Get a pair of simulation uuid and job uuid on jobData
	 * @param jobData
	 * @return
	 */
	public static HashMap<String, String> getUserSimJobInfo(String jobData) {
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}
		 
		// resMap: carries a variety of simulation information associated with simUuid
		HashMap<String, String> resMap = new HashMap<String, String>();
		String getSimInfoQuery = "SELECT "
				  + " t2." + Constants.screenNameFN+ ", "
				  + " t1." + Constants.simUuidFN+ ", "
				  + " t1." + Constants.jobUuidFN
				  + "FROM " + Constants.SIMULATION_JOB_TABLE_NAME +" t1, " 
				  + Constants.SIMULATION_TABLE_NAME +" t2 "
				  + "WHERE t1."+Constants.jobDataFN+" = '" + jobData + "' "
				  + " and t2."+Constants.simUuidFN + " = t1."+Constants.simUuidFN;
System.out.println(getSimInfoQuery);	
		ResultSet rs = EdisonDBConnector.executeQuery(getSimInfoQuery);		
		try {
			System.out.println("<<< Trying to obtain job uuid/simuuid from EDISON DB");
			while(rs.next()){
//						// get queuing time
//						resMap.put(Constants.jobCompletionTimeFN, );
				String userName = rs.getString(1);
				String simUuid = rs.getString(2);
				String jobUuid = rs.getString(3);
//				// get elapsed time
//				resMap.put(Constants.simUuidFN, simUuid);
//				// get status
//				resMap.put(Constants.jobUuidFN, jobUuid);
				resMap.put(Constants.screenNameFN, userName);
				resMap.put(jobUuid, simUuid);
			}
			rs.close();
		}catch(Exception ex){
			new Exception(ex.getMessage());
		}
		
		EdisonDBConnector.close();
		return resMap;
	}

	
	/****
	 * Get job detailed information
	 * @param jobUuid
	 * @return elapsed time, job status
	 */
	public static HashMap<String, String> getJobDetailedData(String jobUuid) {
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("EDISON Database successfully opened!");
		}
		 
		// resMap: carries a variety of simulation information associated with simUuid
		HashMap<String, String> resMap = new HashMap<String, String>();
		String getSimInfoQuery = "SELECT "
				  + " TO_SECONDS(t1.jobEndDt)-TO_SECONDS(t1.jobStartDt) as "+ Constants.jobCompletionTimeFN + ", "
				  + "t1."+Constants.jobStatusFN+ " "
				  + "FROM " + Constants.SIMULATION_JOB_TABLE_NAME +" t1 "
				  + "WHERE t1."+Constants.jobUuidFN+" = '" + jobUuid + "'";
System.out.println(getSimInfoQuery);	
		ResultSet rs = EdisonDBConnector.executeQuery(getSimInfoQuery);		
		try {
			System.out.println("<<< Trying to obtain a jobDetailed on "+jobUuid+" from EDISON DB");
			while(rs.next()){
//				// get queuing time
//				resMap.put(Constants.jobCompletionTimeFN, );
				long elapsedTime = rs.getLong(1);
				long jobStatusCode = rs.getLong(2);
				// get elapsed time
				resMap.put(Constants.jobCompletionTimeFN, Long.toString(elapsedTime));
				// get status
				resMap.put(Constants.jobStatusFN, Long.toString(jobStatusCode));
			}
			rs.close();
		}catch(Exception ex){
			new Exception(ex.getMessage());
		}
		
		EdisonDBConnector.close();
		return resMap;
	}

	/****
	 * Collect a simulation provenance data on a given simulation uuid
	 * @param simUuid
	 */
	public static void collectProvenanceData(String simUuid){
		// TODO Auto-generated method stub
		if(EdisonDBConnector.open(false)){
			System.out.println("Database successfully opened!");
		}
				
		//String query = "SELECT count(*) FROM EDAPP_Solver";
		//String query = "SELECT count(*) FROM EDSIM_Simulation";
		//String query = "SELECT count(*) FROM EDSIM_SimulationJob";
				
		//int numJobCnt = 5;
//				String query = "SELECT "+simUuidFN+", count("+jobSeqNoFN+") as numJobs"
//							+ " FROM " + simJobTblName
//							+ " WHERE "+jobEndDtFN+" - "+jobStartDtFN+" >= "+jobCompletionTimeThreshold+" and " + jobExecPathFN + " like '%"+solverNameVal+"%' "
//							+ " group by "+simUuidFN 
//							+ " having count(*) >= "+numJobCnt
//							+ " order by numJobs asc";
		//System.out.println(query);
		System.out.println("Requested uuid: " + simUuid);		
		String query = 
				"SELECT t0."+Constants.simUuidFN+"," + 
				" t0."+Constants.groupIdFN+","+
			    " t0."+Constants.userIdFN+","+
			    " t0."+Constants.scienceAppIdFN+","+
			    " t0."+Constants.simScienceAppNameFN+","+
			    " t1."+Constants.jobUuidFN+","+
			    " t1."+Constants.jobSeqNoFN+","+
			    " t1."+Constants.jobStatusFN+","+
			    " t1."+Constants.jobStartDtFN+","+
			    " t1."+Constants.jobEndDtFN+","+"\n"+ 
			    " TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+") as elapsedTime,"+ 
			    //" SEC_TO_TIME(TO_SECONDS(t1."+Constants.jobEndDtFN+") - TO_SECONDS(t1."+Constants.jobStartDtFN+")) as elapsedTime,"+ 
				" t1."+Constants.jobExecPathFN+","+ 
			    " t1."+Constants.jobUniversityFieldFN+","+ 
			    " t1."+Constants.jobInputDeckYnFN+","+
			    " t1."+Constants.jobInputDeckNameFN+","+
			    " t2."+Constants.jobDataFN+","+
			    " t3."+Constants.screenNameFN+","+
			    " t3."+Constants.firstNameFN+
			    ","+
			    " t0."+Constants.simulationTitleFN+","+//19
			    " t0."+Constants.simulationCreateDtFN+//20
			    "\n"+
			" FROM "+Constants.SIMULATION_TABLE_NAME+" t0,"+
			" " + Constants.SIMULATION_JOB_TABLE_NAME+" t1,"+
			" " + Constants.USER_TABLE_NAME+" t3,"+
			" " + Constants.SIMULATION_JOB_DATA_TABLE_NAME+" t2\n"+
			" WHERE "+ "t0."+Constants.simUuidFN+" = \'"+simUuid+"\'\n" +
			" and t0."+Constants.simUuidFN+" = t1."+Constants.simUuidFN+"\n"+
			" and t1."+Constants.jobUuidFN+" = t2."+Constants.jobUuidFN+"\n"+
			" and t0."+Constants.userIdFN +" = t3."+Constants.userIdFN+"\n"+
			" order by t1."+Constants.jobSeqNoFN+" asc";
System.out.println(query);	
//		SELECT t0.simulationUuid, t0.groupId, t0.userId, t0.scienceAppId, t0.scienceAppName, t1.jobUuid, t1.jobSeqNo, t1.jobStatus, t1.jobStartDt, t1.jobEndDt,
//		 SEC_TO_TIME(TO_SECONDS(t1.jobEndDt) - TO_SECONDS(t1.jobStartDt)) as elapsedTime, t1.jobExecPath, t1.jobUniversityField, t1.jobInputDeckYn, t1.jobInputDeckName, t2.jobData, t3.screenName, t3.firstName
//		 FROM EDSIM_Simulation t0, EDSIM_SimulationJob t1, User_ t3, EDSIM_SimulationJobData t2
//		 WHERE t0.simulationUuid = 'e00b6168-cd84-4c65-bbdc-2ed7dbee8ab2'
//		 and t0.simulationUuid = t1.simulationUuid
//		 and t1.jobUuid = t2.jobUuid
//		 and t0.userId = t3.userId
//		 order by jobSeqNo asc
		sim_rec_vector = new Vector<Simulation>();
		ResultSet rs = EdisonDBConnector.executeQuery(query);
		try {
			int simCount = 0;
			while(rs.next()){
				if(simCount == 0){
					Simulation sim_rec = new Simulation();
					sim_rec.simUuidVal = rs.getString(1);
					sim_rec.groupIdVal = rs.getLong(2);
					sim_rec.userIdVal = rs.getLong(3);
					sim_rec.scienceAppIdVal = rs.getLong(4);
					sim_rec.scienceAppNameVal = rs.getString(5);
					sim_rec.simulationTitleVal = rs.getString(19);
					Date temp = rs.getDate(20);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					sim_rec.simulationCreateDtVal = sdf.format(temp);
					sim_rec.printContents();
					if(sim_rec_vector.indexOf(sim_rec_vector) < 0)
						sim_rec_vector.add(sim_rec);
				}
				simCount++;
				
				SimulationJob simjob_rec = new SimulationJob();
				simjob_rec.jobUuidVal = rs.getString(6);
				simjob_rec.jobSeqNoVal =  rs.getLong(7);
				simjob_rec.jobStatusVal = rs.getLong(8);
				simjob_rec.jobStartDtVal = rs.getString(9);
				simjob_rec.jobEndDtVal = rs.getString(10);
				simjob_rec.jobCompletionTimeVal = rs.getString(11);
				simjob_rec.jobExecPathVal = rs.getString(12);
				simjob_rec.jobUniversityFieldVal = rs.getInt(13);
				simjob_rec.jobInputDeckYnVal= rs.getShort(14);
				simjob_rec.jobInputDeckNameVal= rs.getString(15);
				simjob_rec.printContents();
				simjob_rec_vector.add(simjob_rec);
				
				SimulationJobData simjobdata_rec = new SimulationJobData();
				simjobdata_rec.jobDataVal = rs.getString(16);
				simjobdata_rec.printContents();
				simjobdata_rec_vector.add(simjobdata_rec);
				

				UserInfo user_rec = new UserInfo();
				user_rec.userIdVal =  rs.getLong(3);
				user_rec.screenNameVal = rs.getString(17);
				user_rec.firstNameVal = rs.getString(18);
				user_rec.printContents();
				if(userinfo_rec_vector.indexOf(user_rec) < 0)
					userinfo_rec_vector.add(user_rec);
				
				System.out.println("========================");
				//break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println("# of solvers: " + Integer.toString(cnt));
		EdisonDBConnector.close();
	}
	
	public static Vector<Simulation> getSimulationRecords() {
		// TODO Auto-generated method stub
		return sim_rec_vector;
	}

	public static Vector<SimulationJob> getSimulationJobRecords() {
		// TODO Auto-generated method stub
		return simjob_rec_vector;
	}

	public static Vector<SimulationJobData> getSimulationJobDataRecords() {
		// TODO Auto-generated method stub
		return simjobdata_rec_vector;
	}
	
	public static Vector<UserInfo> getUserInfoRecords() {
		// TODO Auto-generated method stub
		return userinfo_rec_vector;
	}

	public static String getSimulationUuid() {
		// TODO Auto-generated method stub
		return sim_rec_vector.get(0).simUuidVal;
	}

	public static SimulationSubject getTargetSimulationSubject() {
		// TODO Auto-generated method stub
		return targetSimSubject;
	}

	public static String getJobInputFileDir(String jobUuid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
