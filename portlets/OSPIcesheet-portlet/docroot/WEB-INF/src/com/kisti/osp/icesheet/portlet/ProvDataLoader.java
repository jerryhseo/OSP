package com.kisti.osp.icesheet.portlet;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.kisti.osp.icesheet.PROV.PROVDocManager;
import com.kisti.osp.icesheet.PROV.Simulation;

/*****
 * PROV document repository manager
 * @author yksuh
 *
 */
public class ProvDataLoader {
	/*****
	 * Load a provenance instance into IceSheet database.
	 * IceSheetDB must be open and closed right here, but not in any other place.
	 * @param simulationUuid simulation uuid
	 * @param userName user name (screen name)
	 * @param subjectId app id 
	 * @param subjectName app name
	 * @param subjectVersion app version
	 * @param simulationTitle simulation title
	 * @param simCreateDate simulation create date
	 * @param jobUuid job uuid 
	 * @param jobInputFileDir job input file directory if existing
	 * @param jobData job data
	 * @param statusCode job status
	 * @param jobStartDate job start date
	 * @param jobEndDate job end date
	 * @return output directory path
	 * @throws Exception
	 */
	public static String loadSimulationProvenance(String simulationUuid, // simulation uuid
												  String userName, // user name
												  Long subjectId, // app id 
												  String subjectName,//app name
												  String subjectVersion,// app version
												  String simulationTitle,// simulation title
												  String simCreateDate,// simulation create date
												  String jobUuid,// job uuid 
												  String jobInputFileDir,// job input file directory if existing
												  String jobData,// job data
												  String statusCode,// job status
												  String jobStartDate, // job start date
												  String jobEndDate // job end date
												) throws Exception{
		// open IceSheet DB
		if(IceSheetDBConnector.open(false)){
			//System.out.println("IceSheet Database successfully opened!");
		}
						
		if(subjectId == null){
	    	subjectId = ProvDataCollector.getScienceAppId(subjectName, subjectVersion);
	    }
		
		 // insert simulation subject info
		try{
			loadSimulationSubjectInfo(subjectId, subjectName,subjectVersion);
		}catch(Exception ex){
			String message = ex.getMessage();
			if(message.contains("Duplicate")){
				// it's definitely fine with this.
			}else{
				throw new Exception();
			}
		}
	    
	    // queuing time string
	    String queuingTime = "";
	    // elapsed time string
	    String elapsedTime = "";
	    try{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date inQueueDate = sdf.parse(simCreateDate);
	    	Date startDate = sdf.parse(jobStartDate);
			Date endDate = sdf.parse(jobEndDate);
			// let's convert it to sec
			elapsedTime = Long.toString((endDate.getTime()-startDate.getTime())/1000);
			queuingTime = Long.toString((startDate.getTime()-inQueueDate.getTime())/1000);
			if(Long.parseLong(queuingTime) < 0) queuingTime = "0";
	    }catch(Exception ex){
	    	throw new Exception(ex.getMessage());
	    }
	    
	    // Overall simulation provenance information
		String provFileLoc = PROVDocManager.getProvLoc(simulationUuid);
		ProvDataLoader.loadOverallProvenanceInfo(Constants.SERVICE_START_YEAR_MONTH, 
								    subjectId, 
								    simulationUuid, 
								    simulationTitle, 
								    simCreateDate, 
								    provFileLoc);
		
//		// extract parameter info
		LinkedHashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromNewPortData(subjectId, jobData);
		//LinkedHashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromOldPortData(subjectId, jobData);
		insertParametricValues(subjectId, paramMap, simCreateDate,queuingTime,elapsedTime, statusCode);
		
		Long inputDataHashKey = null;
		Long inputFileHashKey = null;
		
		// get input file hash key
		if(jobInputFileDir != null){
			String inputPath = "/EDISON/LDAP/DATA/"+userName+"/repository/";  // inputpath add 
			inputFileHashKey = IceSheetUtil.generateInputFileHashKey(subjectId, jobInputFileDir, inputPath);
		}else{
			inputFileHashKey = Constants.DEFAULT_INPUTFILE_HASH_KEY;
		}
		
		// get input data hash key
		if(jobData != null){
			inputDataHashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
		}
		
		String resDirPath = null;
		// examine if there's any matching item from this table on the generated keys 
//long startTime = System.currentTimeMillis();
		resDirPath = ProvDataMatcher.findOutputDataDirectory(subjectId, inputDataHashKey, inputFileHashKey);
//long endTime = System.currentTimeMillis();
//System.out.println("search time: " + (endTime-startTime) + " (ms)");
		if(resDirPath == null){
		// first move the outcome directory to our provenance server
//			HashMap<String, String> userSimJobInfoMap = ProvDataCollector.getUserSimJobInfo(jobData);
//			
//			String userName = null;
//			String simulationuuid = null;
//			jobUuid = null;
//			for(Map.Entry<String, String> entry : userSimJobInfoMap.entrySet()){
////				String simulationuuid = simInfoMap.get(jobUuid);
//				if(entry.getKey().contains(Constants.screenNameFN)){
//					userName = entry.getValue();
//				}else{
//					jobUuid = entry.getKey();
//					simulationuuid = entry.getValue();
//				}
//			}
//			resDirPath = IceSheetUtil.moveSimOutputDataintoProvServer(userName, subjectName, subjectVersion, simulationUuid, jobUuid);
			String inputHashKey = Long.toString(inputDataHashKey);
			if(inputFileHashKey != null){
				inputHashKey += "_" + inputFileHashKey;
			}
			resDirPath = "/EDISON/LDAP/"+subjectName+"/"+subjectVersion+"/"+inputHashKey+"/result";
			String insertStmt = 
					" INSERT INTO " + Constants.INPUTDATA_HT_TABLE + " VALUES (" + 
							subjectId + "," +
							inputDataHashKey + "," +
							inputFileHashKey + ",'" +
							resDirPath + "')";
			IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);	
		}
		
		// close IceSheet DB
		IceSheetDBConnector.close();
		return resDirPath;
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/****
	 * Load a simulation subject into database
	 * @param subjectId subject id
	 * @param subjectName subject name
	 * @param subjectVersion subject version
	 */
	public static void loadSimulationSubjectInfo(Long subjectId,
												String subjectName,
												String subjectVersion) throws Exception{
		if(IceSheetDBConnector.open(false)){
//			System.out.println("Database successfully opened!");
		}
			
		String insertStmt  = "INSERT INTO " + Constants.SIMULATION_SUBJECT_TABLE_NAME + " VALUES (";
				insertStmt +=	subjectId +",'";
				insertStmt +=	subjectName +"','";
				insertStmt +=	subjectVersion +"'";
				insertStmt += ")";
if(!Constants.PAPER_EXP)
	System.out.println(insertStmt);		

		IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);		
		IceSheetDBConnector.close();
	}
	
	/****
	 * Insert the location of PROV doc into database
	 * @param serviceYearMon
	 * @param subjectId 
	 * @param simUuid
	 * @param simTitle
	 * @param simCreateDtStr
	 * @param provLoc
	 */
	public static void loadOverallProvenanceInfo(String serviceYearMon,
			 					   Long subjectId,
			 					   String simUuid,
			   					   String simTitle,
								   String simCreateDtStr,
								   String provLoc) throws Exception{
		//open IceSheet DB
		if(IceSheetDBConnector.open(false)){
//			System.out.println("IceSheet Database successfully opened!");
		}
				
		// load prov doc into database
//		 = Constants.SERVICE_START_YEAR_MONTH;
		
//		String insertStmt  = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES ('";
//				insertStmt +=	serviceYearMon+"','";
//			   	insertStmt +=	uuid +"','";
//				insertStmt +=	provLoc + "'";
//				insertStmt += ")";
		
		String insertStmt  = "INSERT INTO " + Constants.SIMULATION_PROV_TABLE_NAME + " VALUES ('";
			insertStmt +=	serviceYearMon+"',";
			insertStmt +=	subjectId +",'";
			insertStmt +=	simUuid +"','";
			simTitle = simTitle.replaceAll("'", "");
			insertStmt +=	simTitle +"','";
			insertStmt +=	simCreateDtStr;
			insertStmt += "',";
		if(provLoc != null){
			insertStmt += "'" + provLoc + "'";
		}else{
			insertStmt += provLoc;
		}
			
		insertStmt += ")";
if(!Constants.PAPER_EXP)
	System.out.println(insertStmt);
//if(provLoc.equalsIgnoreCase("0a3a44d0-e067-47ec-9766-75018389b9ff.json")){
//	System.out.println(insertStmt);
//}
//		String res = "";
//		res = IceSheetDBConnector.addToBatch(insertStmt);		
//		if(!res.equalsIgnoreCase("")){
//			//System.err.println("cannot insert prov doc:" + simUuid);
//			throw new Exception(res);
//		}

		try{
			IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);	
		}catch(Exception ex){
			String message = ex.getMessage();
			if(message.contains("Duplicate")){
				
			}
		}
		
//		// close IceSheet DB
//		IceSheetDBConnector.close();
	}

	/****
	 * Insert a PROV batch into database.
	 */
	public static void insertPROVBatchIntoProvDB() throws Exception{
//		boolean res = false;
		String msgStr = IceSheetDBConnector.executeBatch();
		if(!msgStr.equalsIgnoreCase("") && !msgStr.contains("Duplicate")){
			System.err.println(msgStr);
			throw new Exception(msgStr);
		}
	}
	
	/****
	 * Provenance records are loaded in bulk into database 
	 * @param provDC
	 */
	public static void loadPROVDocInBulk(HashMap<String, Vector<Object>> provDC) throws Exception{
		try{
			// open provenance database
			if(IceSheetDBConnector.open(false)){
				System.out.println("Database successfully opened!");
			}
			
			Set<String> simUuidSet = provDC.keySet();
			Iterator<String> simIter = simUuidSet.iterator();
			while(simIter.hasNext()){
				String simUuid = simIter.next();
				Vector<Object> detailedObjVec = provDC.get(simUuid);
				if(detailedObjVec != null && detailedObjVec.size() == 4){
					@SuppressWarnings("unchecked")
					Vector<Simulation> simVec = (Vector<Simulation>)detailedObjVec.get(0);
					if(simVec != null){
						for(int i = 0; i < simVec.size();i++){
							Simulation simObj = simVec.get(i);
							Long subjectId = simObj.scienceAppIdVal;
							String simulationUuid = simObj.simUuidVal;
							if(!simulationUuid.equalsIgnoreCase(simUuid)){
								// shouldn't happen
								System.err.println("a given key: " + simUuid + ", sim object: " + simulationUuid);
							}
							String simTitle = simObj.simulationTitleVal;
							String simCreateDtStr = simObj.simulationCreateDtVal;
							
							// Write a PROV-JSON file.
							String provFileLoc = PROVDocManager.getProvLoc(simulationUuid);
							// add prov record to batch
							ProvDataLoader.loadOverallProvenanceInfo(Constants.SERVICE_START_YEAR_MONTH, 
													    subjectId, 
													    simUuid, 
													    simTitle, 
													    simCreateDtStr, 
													    provFileLoc);
						}
					}
				}
			}
			
			// insert the batch into database.
			ProvDataLoader.insertPROVBatchIntoProvDB();
			
			// close provenance database
			IceSheetDBConnector.close();
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	
//	/****
//	 * Build hash table for the current subject
//	 * @param subjectName subject name 
//	 * @param subjectVersion subject name 
//	 */
//	public static void buildInputDataHashTable(String subjectName,
//											   String subjectVersion) throws Exception{
//		// now construct input data hash table for a given science app
//		if(IceSheetDBConnector.open(false)){
//			System.out.println("EDISON Database successfully opened!");
//		}
//		if(EdisonDBConnector.open(false)){
//			System.out.println("IceSheet Database successfully opened!");
//		}
//		String getJobInfoQuery = "SELECT t0.scienceAppId, t1.simulationuuid,"
//				  + "t2.jobUuid, "
//				  + "t3.jobData "
//				  + "FROM "+Constants.SCIENCAPP_TABLE_NAME+" t0, "
//				  + Constants.SIMULATION_TABLE_NAME +" t1, "
//				  + Constants.SIMULATION_JOB_TABLE_NAME +" t2, "
//				  + Constants.SIMULATION_JOB_DATA_TABLE_NAME +" t3 "
//				  + "WHERE t0.name = '"+subjectName+"' "
//				  + "AND t0.version = '"+subjectVersion+"' "
//				  + "AND t0.scienceAppId = t1.scienceAppId "
//				  + "AND t1.simulationUuid = t2.simulationUuid "
//		  		  + "AND t2.jobUuid = t3.jobUuid";
//		
//System.out.println(getJobInfoQuery);		
//		
//		int totalCount = 0;
//		int dupCount = 0;
//		
//		String inputFileName = "test.msh";
//		ResultSet rs = EdisonDBConnector.executeQuery(getJobInfoQuery);		
//		try {
//			while(rs.next()){
//				totalCount++;
//				Long subjectId = rs.getLong(1);
//				String simulationuuid = rs.getString(2);
//				String jobUuid = rs.getString(3);
//				String jobData = rs.getString(4);
//				
//					
//			}
//			rs.close();
//			// close EDISON portal DB
//			EdisonDBConnector.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		double dupRatio = 0;
//		if(totalCount > 0)
//		 dupRatio =  ((double)dupCount/(double)totalCount);
//System.out.println(subjectName + "=> total simulation Counts: " + totalCount + ", duplicates: " + dupCount + ", dup rate: " + dupRatio  + "%");
//		// close IceSheet DB
//		IceSheetDBConnector.close();
//	}
	
	/****
	 * Load a Hash Table matching given subjectName and version
	 */
	public static HashMap<String, String> loadProvenanceMain(String userName, 
										 Long subjectId, 
										 String subjectName, 
										 String subjectVersion, 
										 String simUuid, 
										 String jobUuid, 
										 String jobData) throws Exception{
		// get hashKey for inputData of this subject
		long hashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
		
		HashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromOldPortData(subjectId, jobData);
//		HashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromNewPortData(subjectId, jobData);
		
		// copy output directory into provenance server
/*		String outputPath = IceSheetUtil.moveSimOutputDataintoProvServer(userName, 
															subjectName, 
															subjectVersion, 
															simUuid,
															jobUuid);
*/
		
		// output data copy to EDISON Server(Internal)  
		HashMap<String, String> outputPath = IceSheetUtil.copySimOutputDataintoEDISON(userName, 
				subjectName, 
				subjectVersion, 
				simUuid,
				jobUuid);
		
		// have access to Provenance DB to store these data
		
		
		return outputPath;
		
	}
	
	/****
	 * @author majin
	 * @param userName
	 * @param subjectId
	 * @param subjectName
	 * @param subjectVersion
	 * @param simUuid
	 * @param jobUuid
	 * @param jobData
	 * @throws Exception
	 */
	public static HashMap<String, String> inputFileloadProv(String userName, Long subjectId, String subjectName, String subjectVersion, String simUuid, String jobUuid, String jobData) throws Exception{

		String inputPath = "/EDISON/LDAP/DATA/"+userName+"/repository/";
		
		long hashkey = IceSheetUtil.generateInputFileHashKey(subjectId, jobData, inputPath);	
		HashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromOldPortData(subjectId, jobData);
		
//		String outputPath = IceSheetUtil.copySimOutputDataintoEDISON(userName, 
//				subjectName, 
//				subjectVersion, 
//				simUuid,
//				jobUuid);
		HashMap<String, String> outputPath = IceSheetUtil.copySimOutputDataintoEDISON(userName, 
				subjectName, 
				subjectVersion, 
				simUuid,
				jobUuid);
		
		return outputPath;
				
	}
	
	private static void loadSimulationProvenance_old(String simulationUuid)  throws Exception{
//		HashMap<String, String> simInfoMap = IceSheetUtil.getSimulation(simUuid);
//		
//		long subjectId = IceSheetUtil.getScienceAppId(subjectName, subjectVersion);
//		
//		// get hashKey for inputData of this subject
//		long hashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
//		
////		HashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromOldPortData(subjectId, jobData);
////		
////		// copy output directory into provenance server
////		String outputPath = IceSheetUtil.moveSimOutputDataintoProvServer(userName, 
////															subjectName, 
////															subjectVersion, 
////															simUuid,
////															jobUuid);
//		
//		// have access to Provenance DB to store these data
		
		try{
			HashMap<String, Object> simInfoMap = ProvDataCollector.getSimulationInfo(simulationUuid);
			Long userId = null;
			Long appId = null;
			String screenName = null;
			String simTitle = null;
			String simCreateDtStr = null;
			for (String key : simInfoMap.keySet())
			{
				if(key.contains(Constants.userIdFN)){
					userId = (Long)simInfoMap.get(key);
				}else if(key.contains(Constants.screenNameFN)){
					screenName = (String)simInfoMap.get(key);
				}else if(key.contains(Constants.scienceAppIdFN)){
					appId = (Long)simInfoMap.get(key);
				}else if(key.contains(Constants.simulationTitleFN)){
					simTitle = (String)simInfoMap.get(key);
				}else if(key.contains(Constants.simulationCreateDtFN)){
					simCreateDtStr = (String)simInfoMap.get(key);
				}
			}
			
			// get science app name and version
			String appName = null;
			String appVersion = null;
			HashMap<String, String> appInfoMap = ProvDataCollector.getScienceAppInfo(appId);
			for (String key : appInfoMap.keySet())
			{
				if(key.contains(Constants.scienceAppNameFN)){
					appName = appInfoMap.get(key);
				}else if(key.contains(Constants.scienceAppVersionFN)){
					appVersion = appInfoMap.get(key);
				}
			}
			
			// insert simulation subject information
			try{
				loadSimulationSubjectInfo(appId, appName,appVersion);
			}catch(Exception ex){
				String message = ex.getMessage();
				if(message.contains("Duplicate")){
					// it's definitely fine with this.
				}else{
					throw new Exception();
				}
			}
			
			// get job data 
			HashMap<String, String> jobDataMap = 
								ProvDataCollector.getJobData(appName, appVersion, simulationUuid);
			
			// insert input data hash key value
			try{
				for(String jobUuid : jobDataMap.keySet()){
					String jobData = jobDataMap.get(jobUuid);
			
					// get job detailed information
					HashMap<String, String> jobDetailedMap = 
							ProvDataCollector.getJobDetailedData(jobUuid);
					
					String inputFileDir = ProvDataCollector.getJobInputFileDir(jobUuid);
					
					String elapsedTime = jobDetailedMap.get(Constants.jobCompletionTimeFN);
					String statusCode = jobDetailedMap.get(Constants.jobStatusFN);
					
					// insert input data key into hash table
					loadInputOutputInfo(screenName, appId, appName, appVersion, simulationUuid, jobUuid, jobData);
					
					// insert simulation provenance metadata/**** Must be implemented... **/
					String provDocLoc = IceSheetUtil.getProvDocLocation(); 
					loadOverallProvenanceInfo(Constants.SERVICE_START_YEAR_MONTH, appId, simulationUuid, simTitle, simCreateDtStr, provDocLoc);
					
					// detailed provenance data
//					loadDetailedProvenanceInfo(jobUuid, jobUuid, appId, null, null, null,
//											 jobData,
//											 elapsedTime,
//											 statusCode, jobUuid, jobUuid, jobUuid, jobUuid);
				}
			}catch(Exception ex){
				String message = ex.getMessage();
				if(message.contains("exist")){
					// it's definitely fine with this.
				}else{
					throw new Exception();
				}
			}
			
			// insert simulation provenance information 
			
			// insert job parameter information 
			
			// insert job parameter detailed information
			// close IceSheet DB
			IceSheetDBConnector.close();
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
	}
	
//	/****
//	 * Load a Hash Table matching given subjectName and version
//	 * @return 
//	 */
//	public static String loadSimulationProvenanceMain(String simulationUuid) throws Exception{
//		String outputDirPath = null;
//		
//		String userName = null;
//		Long subjectId = null;
//		String subjectName = null;
//		String subjectVersion = null;
//		String simulationTitle = null;
//		String simCreateDate = null;
//		String jobUuid = null;
//		String jobInputFileDir = null;
//		String jobData = null;
//	    String statusCode = null;
//	    String jobStartDate = null;
//	    String jobEndDate = null;
//		
//	    HashMap<String, Object> simDetailedMap = ProvDataCollector.getNewSimulationInfo(simulationUuid);
//	    
//	    for(Map.Entry<String, Object> entry : simDetailedMap.entrySet()){
//	    	if(entry.getKey().contains(Constants.screenNameFN)){
//	    		userName = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.simUuidFN)){
//	    		simulationUuid = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.scienceAppIdFN)){
//	    		subjectId = (Long)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.scienceAppNameFN)){
//	    		subjectName = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.scienceAppVersionFN)){
//	    		subjectVersion = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.simulationTitleFN)){
//	    		simulationTitle = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.simulationCreateDtFN)){
//	    		simCreateDate = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.jobUuidFN)){
//	    		jobUuid = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.jobDataFN)){
//	    		jobData = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.jobStatusFN)){
//	    		statusCode = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.jobStartDtFN)){
//	    		jobStartDate = (String)entry.getValue();
//	    	}else if(entry.getKey().contains(Constants.jobEndDtFN)){
//	    		jobEndDate = (String)entry.getValue();
//	    	}
//	    }
//	    
//		outputDirPath = loadDetailedProvenanceInfo(simulationUuid,
//												   userName, 
//												   subjectId, 
//												   subjectName, 
//												   subjectVersion, 
//												   simulationTitle, 
//												   simCreateDate, 
//												   jobUuid, 
//												   jobInputFileDir, 
//												   jobData, 
//												   statusCode, 
//												   jobStartDate, 
//												   jobEndDate);
//		return outputDirPath;
//	}
	
	/****
	 * Keep input/output information into DB
	 * @param userName
	 * @param subjectId
	 * @param subjectName
	 * @param subjectVersion
	 * @param simulationuuid
	 * @param inputFileDir
	 * @param jobUuid
	 * @param jobData
	 * @throws Exception
	 */
	private static String loadInputOutputInfo(String userName, 
			   							   Long subjectId, 
										   String subjectName,
										   String subjectVersion,
										   String simulationuuid,
//										   String inputFileDir, 
										   String jobUuid,
										   String jobData) throws Exception {
		// open IceSheet DB
		if(IceSheetDBConnector.open(false)){
			if(!Constants.PAPER_EXP)
				System.out.println("IceSheet Database successfully opened!");
		}
		String inputPath = "/EDISON/LDAP/DATA/"+userName+"/repository/";
	
		Long inputDataHashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
		Long inputFileHashKey = IceSheetUtil.generateInputFileHashKey(subjectId, jobData, inputPath);
		
//		if(inputFileDir != null){
//			inputFileHashKey = IceSheetUtil.generateInputFileHashKey(subjectId, jobData, inputPath);
//		}else{
//			inputFileHashKey = Constants.DEFAULT_INPUTFILE_HASH_KEY;
//		}
		
		String outputDirPath = ProvDataMatcher.findOutputDataDirectory(subjectId, inputDataHashKey, inputFileHashKey);
		if(outputDirPath == null){
			// first move the outcome directory to our provenance server
			/*****
			 * The following method should be implemented...
			 */
			//outputDirPath = IceSheetUtil.moveSimOutputDataintoProvServer(userName, subjectName, subjectVersion, simulationuuid, jobUuid);
//			outputDirPath = "/EDISON/LDAP/DATA/"+subjectName+"/"+subjectVersion+"/"+simulationuuid+"/"+jobUuid;
			String inputDataFileHashKey = inputDataHashKey + "_" + inputFileHashKey;
			outputDirPath = "/EDISON/LDAP/DATA/"+subjectName+"/"+subjectVersion+"/"+inputDataFileHashKey+"/"+Constants.OUTPUT_DIR_NAME_SUFFIX;

			String insertStmt = 
					" INSERT INTO " + Constants.INPUTDATA_HT_TABLE + " VALUES (" + 
							subjectId + "," +
							inputDataHashKey + "," +
							inputFileHashKey + ",'" +
							outputDirPath + "')";
			
			try{
				IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
			}catch(Exception ex){
				String message = ex.getMessage();
				if(message.contains("Duplicate")){
					System.out.println();// this is fine.
				}
			}
		}
		
		// close IceSheet DB
		IceSheetDBConnector.close();
		
		return outputDirPath;
	}

	/*****
	 * Store all provenances
	 * @param subjectID science app ID
	 * @param subjectName science app name
	 * @param subjectVersion science app version
	 * @param userSimDataMap user simulation data 
	 */
	public static HashMap<String, Long> storeAllProvenances(String subjectName,
															String subjectVersion) throws Exception{
	   // # of total simulations - # of duplicate simulations 	
	   HashMap<String, Long> resMap = new HashMap<String, Long>();
	   
	   // get science app ID
	   long subjectId = -1;
	   try{
		   subjectId = ProvDataCollector.getScienceAppId(subjectName, subjectVersion);
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	
	   	// insert simulation subject info
		try{
			loadSimulationSubjectInfo(subjectId, subjectName,subjectVersion);
		}catch(Exception ex){
			String message = ex.getMessage();
			if(message.contains("Duplicate")){
				// it's definitely fine with this.
			}else{
				throw new Exception();
			}
		}
		
		// collect all simulations from the portal DB
	   HashMap<String, HashMap<String, HashMap<String, String>>> userSimDataMap = ProvDataCollector.getAllSimulations(subjectName, subjectVersion);
System.out.println("loading provenances started...");
long searchTimeSum = 0;
long startTime = System.currentTimeMillis();
		long totalCnt = 0;
		long dupCnt = 0;
	
	   if(userSimDataMap != null && userSimDataMap.size() > 0){
			// open IceSheet DB
		   if(IceSheetDBConnector.open(false)){
//				System.out.println("IceSheet Database successfully opened!");
		   }

		   // for each user, let's load his/her simulations into our provenance database
		   for (Map.Entry<String,HashMap<String,HashMap<String,String>>> userEntry : userSimDataMap.entrySet())
		   {
				String userName = userEntry.getKey();
//				System.out.print("userName:" + userName);
				HashMap<String,HashMap<String,String>> simDataMap = userEntry.getValue();
				// for each simulation, let's store job data 
				for (Map.Entry<String,HashMap<String,String>> simEntry : simDataMap.entrySet())
				{
					String simUuid = simEntry.getKey();
					
//System.out.print(", simUuid:" + simUuid);
					HashMap<String,String> jobDataMap = (HashMap<String,String>)simEntry.getValue();
					String jobElapsedTime = "";
					String jobQueuingTime = "";
					String jobStatusCode = "";
					String jobUuid = "";
					String jobData = "";
					String simCreateDt = "";
					String simTitle = "";
					for (Map.Entry<String,String> jobEntry : jobDataMap.entrySet())
					{
						String keyName = jobEntry.getKey();
						if(keyName.equalsIgnoreCase(Constants.PARAM_VAL_SET_ELAPSED_TIME_ATT_NAME)){
							jobElapsedTime = jobEntry.getValue();
						}else if(keyName.equalsIgnoreCase(Constants.PARAM_VAL_SET_STATUS_ATT_NAME)){
							jobStatusCode = jobEntry.getValue();
						}else if(keyName.equalsIgnoreCase(Constants.PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME)){
							simCreateDt = jobEntry.getValue();
						}else if(keyName.equalsIgnoreCase(Constants.simulationTitleFN)){
							simTitle = jobEntry.getValue();
						}else if(keyName.equalsIgnoreCase(Constants.PARAM_VAL_SET_QUEUING_TIME_ATT_NAME)){
							jobQueuingTime = jobEntry.getValue();
							if(Long.parseLong(jobQueuingTime) < 0) jobQueuingTime = "0";
						}else{
							jobUuid = keyName;
							jobData = jobEntry.getValue();
						}
					}
					
					// This method should be relocated under IceSheetUtil.
					String inputFileDir = ProvDataCollector.getJobInputFileDir(jobUuid);
					// First, get the output path directory after inserting input data/file hash key(s).
					
	long startTime2 = System.currentTimeMillis();
					//String outputDirPath = loadInputOutputInfo(userName, subjectId, subjectName, subjectVersion, simUuid, inputFileDir, jobUuid, jobData);
					String outputDirPath = loadInputOutputInfo(userName, subjectId, subjectName, subjectVersion, simUuid, jobUuid, jobData);
	long endTime2 = System.currentTimeMillis();
	searchTimeSum += (endTime2-startTime2);
					// Second, insert the overall provenance information into the database for browsing
					// Overall simulation provenance information
//					String provFileLoc = PROVDocManager.getProvLoc(simUuid);
					ProvDataLoader.loadOverallProvenanceInfo(Constants.SERVICE_START_YEAR_MONTH, 
											    subjectId, 
											    simUuid, 
											    simTitle, 
											    simCreateDt, 
											    outputDirPath);
					
					// Now let's keep parameter-wise information. 
//System.out.print(", jobuuid:" + jobUuid);
//System.out.println(", jobData:" + jobData);
					// insert parametric values
					boolean dup = insertIntoBatchProvenanceData(subjectId, subjectName, subjectVersion, simUuid, jobUuid, jobData, simCreateDt,jobQueuingTime,jobElapsedTime,jobStatusCode);
					if(dup)
						dupCnt++;
					totalCnt++;
				}
			}
//			// Execute a batch
//			executeBatchProvData();
			
			// close IceSheet DB
			IceSheetDBConnector.close();
		}
	   
	   // deliver how many were duplicate.
	   resMap.put(Constants.TOTAL_LOAD_CNT_STR, new Long(totalCnt));
	   resMap.put(Constants.TOTAL_DUP_CNT_STR, new Long(dupCnt));
long endTime = System.currentTimeMillis();	 
	   System.out.println("loading time: " + (endTime-startTime) + " (ms), per sim: " + ((endTime-startTime)/(long)totalCnt) + " (ms)");
	   System.out.println("search time: " + searchTimeSum + " (ms), per sim: " + (searchTimeSum/(long)totalCnt) + " (ms)");
	   System.out.println("app: "+subjectName+", total: " + totalCnt + ", duplicate: " + dupCnt);
	   return resMap;
	}

	/*****
	 * Insert parametric values into a corresponding science app
	 * @param paramMap a map of parameters
	 */
	private static boolean insertIntoBatchProvenanceData(Long subjectId, 
														 String subjectName,
														 String subjectVersion,
														 String simulationuuid,
														 String jobUuid,
														 String jobData,
														 String simCreateDt,
														 String queuingTime,
														 String elapsedTime,
														 String statusCode) throws Exception{
		// TODO Auto-generated method stub
		// get output data path from this 
//		String outputDataDirPath = IceSheetUtil.preserveOutputData(subjectName,subjectVersion,simulationuuid,jobUuid);
		
		/***
		 *  now build a hash table entry for input data and output data directory
		 */
		// get input data hash key
//		long inputDataHashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
//		
//		// extract parameter info
		LinkedHashMap<String, String> paramMap = IceSheetUtil.extractInputParamInfoFromOldPortData(subjectId, jobData);
//		if(jobData.contains("40.8")){
//			System.out.println("hy");
//		}
		boolean isDuplicate = insertParametricValues(subjectId, paramMap, simCreateDt,queuingTime,elapsedTime, statusCode);
		
		// get input file hash key
//		long inputFileHashKey = IceSheetUtil.generateInputFileHashKey(jobData);
//				
//		// examine if there's any matching item from this table on the generated keys 
//		String resDirPath = ProvDataMatcher.findOutputDataDirectory(subjectId, inputDataHashKey, inputFileHashKey);
//		if(resDirPath == null){
//			// first move the outcome directory to our provenance server
//			resDirPath = IceSheetUtil.moveSimOutputDataintoProvServer(subjectName, subjectVersion, simulationuuid, jobUuid);
//			String insertStmt = 
//					" INSERT INTO " + Constants.INPUTDATA_HT_TABLE + " VALUES (" + 
//							subjectId + "," +
//							inputDataHashKey + "," +
//							inputFileHashKey + ",'" +
//							resDirPath + "')";
//			IceSheetDBConnector.addToBatch(insertStmt);
//		}
		return isDuplicate;
	}

	/****
	 * Get the predicate string for parameter insertion
	 * @param paramMap a map of parameters
	 * @return 
	 */
	private static String getQueryStr(String paramTableName, 
									  HashMap<String, String> paramMap){
		StringBuffer queryStrBuff = new StringBuffer();
		queryStrBuff.append("SELECT " + Constants.PARAM_VAL_SET_ID_ATT_NAME  + " ");
		queryStrBuff.append(" FROM " + paramTableName + " ");
		queryStrBuff.append(" WHERE ");
		if(paramMap.size() > 0){
			int i=0;
			String predicateStr = "";
			for(Map.Entry<String, String> paramEntry : paramMap.entrySet()){
				i++;
				String paramName = paramEntry.getKey();
				String paramVal = paramEntry.getValue();
				predicateStr += " " + paramName + "=" + paramVal;
				
				if(i <= paramMap.size()-1){
					predicateStr += " AND ";	
				}
				
			}
			queryStrBuff.append(predicateStr);
		}else{
			// build a where clause having a default key-value pair 
			String queryString = "SELECT COLUMN_NAME "
					+ "FROM INFORMATION_SCHEMA.COLUMNS "
					+ "WHERE TABLE_SCHEMA = '"+Constants.PROV_DB_NAME+"' AND TABLE_NAME = '"+paramTableName+"'";
			String predicateStr = " ";
			try {
				Vector<String> columnNameVec = new Vector<String>();
				ResultSet rs = IceSheetDBConnector.executeQuery(queryString);
				while(rs.next()){
					String columnName = rs.getString(1);
					if(!columnName.equalsIgnoreCase(Constants.PARAM_VAL_SET_ID_ATT_NAME) 
						&& !columnName.equalsIgnoreCase(Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME)){ 
						columnNameVec.add(columnName);
					}
				}
				rs.close();
				
				// now build a where clause string
				for(int i=0;i<columnNameVec.size();i++){
					String columnName = columnNameVec.get(i);
					predicateStr += columnName + " = " + Constants.DEFAULT_ZERO_VALUE;
					if(i < columnNameVec.size()-1){
						predicateStr += " AND ";
					}
				}
				queryStrBuff.append(predicateStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return queryStrBuff.toString(); 
	}
	
	/****
	 * Get the string for parameter insertion
	 * @param insert insert 
	 * @param tableName table name
	 * @param paramMap a map of parameters
	 * @return 
	 */
	private static String getDMLStmtStr(boolean insert, 
										String paramTableName, 
										LinkedHashMap<String, String> paramMap){
		String res = "";
		
		StringBuffer insertStmt = new StringBuffer();
		StringBuffer attrOrderStmt = new StringBuffer();
		StringBuffer valueStmt = new StringBuffer();
		StringBuffer uniqueStmt = new StringBuffer();
		StringBuffer createStmt = new StringBuffer();
		
		int i = 0;
		if(insert){
//			attrOrderStmt.append(" (");
//			valueStmt.append(" (");
		}else{
			createStmt.append(" CREATE TABLE " + paramTableName + "(");
			createStmt.append(" " + Constants.PARAM_VAL_SET_ID_ATT_NAME + " BIGINT(20) PRIMARY KEY  AUTO_INCREMENT,\n");
			uniqueStmt.append(" CONSTRAINT "+ Constants.PARAM_CONSTRAINT_NAME +" UNIQUE (");
		}
		
		for(Map.Entry<String, String> paramEntry : paramMap.entrySet()){
			i++;
			String paramName = paramEntry.getKey();
			String paramVal = paramEntry.getValue();
			if(insert){
				if(i == 1){ // if this is the very first attribute
					attrOrderStmt.append(" (");
					valueStmt.append(" (");
				}
				
				attrOrderStmt.append(paramName);
				valueStmt.append(paramVal);
			}else{
				// special case
//				if(paramTableName.contains(Long.toString(Constants.pianostring))){
//					createStmt.append(paramName + " " + Constants.PARAM_VARCHAR_TYPE + " " + Constants.KEYWORD_DEFAULT + "  ");
//					uniqueStmt.append(paramName);
//				}else{
					createStmt.append(paramName + " " + Constants.PARAM_DOUBLE_TYPE + " " + Constants.KEYWORD_DEFAULT + " 0 ");
					uniqueStmt.append(paramName);
//				}
			}
			
			if(i <= paramMap.size()-1){
				if(insert){
					attrOrderStmt.append(",");
					valueStmt.append(",");
				}
				else{
					uniqueStmt.append(",");
					createStmt.append(",\n");
				}
			}
		}
		if(insert){
			insertStmt.append(" INSERT INTO " + paramTableName + " ");
			
			String attrOrderStr = attrOrderStmt.toString();
			String valueStmtStr = valueStmt.toString();
			
			if(attrOrderStr.length() == 0){
				attrOrderStr = "("+Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME + ")";
				valueStmtStr = "("+Constants.DEFAULT_FREQ_VALUE + ")";
			}else{
				attrOrderStr += ","+Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME + ")";
				valueStmtStr += ","+Constants.DEFAULT_FREQ_VALUE + ")";
			}
			insertStmt.append(attrOrderStr + " VALUES " + valueStmtStr);
			res = insertStmt.toString();
		}else{
			uniqueStmt.append(")");
//			String freqAttName = "frequency";
//			String statusAttName = "status";
//			String elapsedTimeAttName = "elapsedtime";
//			
			createStmt.append(",\n" + Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME +  " DECIMAL,\n");
//			createStmt += statusAttName +  "INT ,\n";
//			createStmt += elapsedTimeAttName +  "DECIMAL \n";
			createStmt.append(uniqueStmt.toString()+"\n");
			createStmt.append(")");
			res = createStmt.toString();
		}
		return res; 
	}
	
	/****
	 * Get the string for parameter insertion
	 * @param insert insert 
	 * @param tableName table name
	 * @param paramMap a map of parameters
	 * @return 
	 */
	private static String getParamDetailedCreateTableStmtStr(String paramDetailedTableName){
		StringBuffer createStmt = new StringBuffer();
//		create table EDAPP_33901_Detailed(
//				   paramValSetId BIGINT(20) NOT NULL, 
//				   snapshotTime DATETIME NOT NULL,
//				   queuingTime BIGINT(20) NOT NULL,
//				   elapsedTime BIGINT(20) NOT NULL,
//				   status TINYINT ,
//				   PRIMARY KEY (paramValSetId, capturedTime)
//				);
		createStmt.append(" CREATE TABLE " + paramDetailedTableName + "(");
		createStmt.append(" " + Constants.PARAM_VAL_SET_ID_ATT_NAME + " BIGINT(20) NOT NULL,");		
		createStmt.append(" " + Constants.PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME + " DATETIME(3) NOT NULL,");		
		createStmt.append(" " + Constants.PARAM_VAL_SET_QUEUING_TIME_ATT_NAME + " BIGINT(20),");		
		createStmt.append(" " + Constants.PARAM_VAL_SET_ELAPSED_TIME_ATT_NAME + " BIGINT(20),");		
		createStmt.append(" " + Constants.PARAM_VAL_SET_STATUS_ATT_NAME + " TINYINT,");
		createStmt.append(" PRIMARY KEY ("+ Constants.PARAM_VAL_SET_ID_ATT_NAME +", "+Constants.PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME+")");
		createStmt.append(")");
		return createStmt.toString(); 
	}
	
	/****
	 * Get the string for parameter insertion
	 * @param insert insert 
	 * @param tableName table name
	 * @param paramMap a map of parameters
	 * @return 
	 */
	private static String getParamDetailedInsertStmtStr(String paramDetailedTableName,
														Long paramSetId, 
														String simCreateDt,//simulation creation date
														Long queuingTime,
														Long elapsedTime,
														int statusCode){
		StringBuffer insertStmt = new StringBuffer();
		
		StringBuffer attrOrderStmt = new StringBuffer();
		StringBuffer valueStmt = new StringBuffer();
		
//		java.util.Date dt = new java.util.Date();
//		java.text.SimpleDateFormat sdf = 
//		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		String currentTime = sdf.format(dt);
		
		attrOrderStmt.append("(" + Constants.PARAM_VAL_SET_ID_ATT_NAME
				+","+Constants.PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME
				+","+Constants.PARAM_VAL_SET_QUEUING_TIME_ATT_NAME
				+","+Constants.PARAM_VAL_SET_ELAPSED_TIME_ATT_NAME
				+","+Constants.PARAM_VAL_SET_STATUS_ATT_NAME+")");
		valueStmt.append("(" + paramSetId+",'"+simCreateDt+"',"+queuingTime+","+elapsedTime+","+statusCode+")");
						
		// insert the data 
		insertStmt.append(" INSERT INTO " + paramDetailedTableName + " "+ attrOrderStmt.toString() + " VALUES " + valueStmt.toString());
		return insertStmt.toString();
	}
	
	
	public static boolean insertParametricValues(Long subjectId, 
											   LinkedHashMap<String, String> paramMap, 
											   String simCreateDt,
											   String jobQueuingTime,
											   String jobElapsedTime,
											   String jobStatusCode) throws Exception {
		// TODO Auto-generated method stub
		boolean isDuplicate = false;
		String paramTableName = Constants.PROV_SCIENCAPP_TABLE_NAME_PREFIX+subjectId.toString();
		String paramDetailedTableName = Constants.PROV_SCIENCAPP_TABLE_NAME_PREFIX+subjectId.toString()+Constants.PROV_SCIENCAPP_TABLE_NAME_SUFFIX;
		
		Long paramId = null;
		// first, check if a given parameter set is already in the table
		// then, we get its id and update the param detailed table.
		// otherwise, we insert that set into the param table and also insert the param detailed record into the table.
		String queryStmt = getQueryStr(paramTableName, paramMap);
		try{
			ResultSet rs = IceSheetDBConnector.executeQuery(queryStmt);
			while(rs.next()){
				paramId = rs.getLong(1);
			}
			rs.close();
//			if(paramId == null){ // if param value set does not exist...
//				IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
//				ResultSet rs2 = IceSheetDBConnector.executeQuery(queryStmt);
//				while(rs2.next()){
//					paramId = rs2.getLong(1);
//				}
//				rs2.close();
//				
//				int status = Constants.SUCCESS;
//				String paramDetailedInsertStmt = getParamDetailedInsertStmtStr(paramDetailedTableName, paramId, new Long(0), status);
//				// insert param detailed record into param detailed table
//				IceSheetDBConnector.insertRecordIntoProvDB(paramDetailedInsertStmt);
//			}
//			else{ // if param value set exists...
//				long frequency = getParamSetValFrequency(paramTableName, paramId);
//				// update frequency
//				String updateStmt = getParamValSetFreqUpdateStmt(paramTableName, paramId, ++frequency);
//				IceSheetDBConnector.executeUpdate(updateStmt);
//			}
		}catch(Exception ex){
			if(ex != null){
				String msg = ex.getMessage();
				if(msg != null && (msg.contains("doesn't exist") || ex.getMessage().contains("does not exist"))){
					// tables should be created here.
					createParamTables(paramTableName, paramDetailedTableName, paramMap);
				}
			}
		}
		
		// insert a parameter value set record into database
		String insertStmt = "";
		boolean isInsert = true;
		if(paramId == null){
			insertStmt = getDMLStmtStr(isInsert, paramTableName, paramMap);
if(!Constants.PAPER_EXP)
	System.out.println(insertStmt);
//			if(insertStmt.contains("(100.00,1)")){
//				System.out.println(insertStmt);
//			}
			IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
			
			// get paramId
			ResultSet rs = IceSheetDBConnector.executeQuery(queryStmt);
			while(rs.next()){
				paramId = rs.getLong(1);
			}
			rs.close(); 
		}else{
			isDuplicate = true;
			// if exists then, we just update its frequency
			long frequency = getParamSetValFrequency(paramTableName, paramId);
//			// update frequency
			String updateStmt = getParamValSetFreqUpdateStmt(paramTableName, paramId, ++frequency);
if(!Constants.PAPER_EXP)
	System.out.println(updateStmt);
			IceSheetDBConnector.executeUpdate(updateStmt);
		}
		
//		if(paramId == 2){
//			System.out.println("stop");
//		}
		// insert a detailed parameter value set record into database
		int status = Constants.SUCCESS;
		if(!jobStatusCode.equalsIgnoreCase(Constants.JOB_SUCCESS_STATUS_CODE)){
			status = Constants.FAILURE;
		}
		
		String paramDetailedInsertStmt = getParamDetailedInsertStmtStr(paramDetailedTableName, paramId, simCreateDt,Long.parseLong(jobQueuingTime),Long.parseLong(jobElapsedTime), status);
if(!Constants.PAPER_EXP)
	System.out.println(paramDetailedInsertStmt);
		// insert param detailed record into param detailed table
		try{
			IceSheetDBConnector.insertRecordIntoProvDB(paramDetailedInsertStmt);
		}catch(Exception ex){
			String message = ex.getMessage();
			if(message.contains("Duplicate")){
				
			}else{
				throw new Exception(ex.getMessage());
			}
		}
//		String insertStmt = "";
//		boolean isInsert = true;
//		try{
////			String dropStmt = "DROP TABLE " + paramTableName + " CASCADE CONSTRAINTS ";
//			insertStmt = getDMLStmtStr(isInsert, paramTableName, paramMap);
//System.out.println(insertStmt);
//			IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
//		}catch(Exception ex){
//			if(ex.getMessage().contains("doesn't exist") || ex.getMessage().contains("does not exist")){
//				// tables should be created here.
//				createParamTables(paramTableName, paramDetailedTableName, paramMap);
//				// retry inserting this record.
//				IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
//			}
////			else if(ex.getMessage().contains("Duplicate")){
////				long frequency = getParamSetValFrequency(paramTableName, paramId);
////				// update frequency
////				String updateStmt = getParamValSetFreqUpdateStmt(paramTableName, paramId, ++frequency);
////				IceSheetDBConnector.executeUpdate(updateStmt);
////			}
//		}
		return isDuplicate;
	}

	/*****
	 * Create parameter tables 
	 * @param paramTableName parameter main table
	 * @param paramDetailedTableName parameter detailed table
	 * @param paramMap parameter key value map
	 */
	private static void createParamTables(String paramTableName, String paramDetailedTableName,
			LinkedHashMap<String, String> paramMap) throws Exception{
		// TODO Auto-generated method stub
		// create table first
		try{
			boolean isInsert = false;
			String createStmt = getDMLStmtStr(isInsert, paramTableName, paramMap);
//			drop table EDAPP_33901;
//			create table EDAPP_33901 
//			 (paramValSetId BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
//			  a DOUBLE NOT NULL,
//			  b DOUBLE NOT NULL,
//			  c DOUBLE NOT NULL,
//			  d DOUBLE NOT NULL,
//			  e DOUBLE NOT NULL,
//			  CONSTRAINT param_val_set_cnt UNIQUE (a,b,c,d,e)
//			);
//			
if(!Constants.PAPER_EXP)
	System.out.println(createStmt);
			IceSheetDBConnector.createTable(createStmt);

			// create param detailed table as well
			createStmt = getParamDetailedCreateTableStmtStr(paramDetailedTableName);
if(!Constants.PAPER_EXP)
	System.out.println(createStmt);
			IceSheetDBConnector.createTable(createStmt);
		}catch(Exception ex2){
			throw new Exception (ex2.getMessage());
		}
	}

	/****
	 * Get frequency data from ParamTable
	 * @param paramTableName param table name
	 * @param paramValSetId parameter value set Id
	 * @return frequency
	 * @throws Exception
	 */
	private static long getParamSetValFrequency(String paramTableName, Long paramValSetId) throws Exception{
		// TODO Auto-generated method stub
		long res = -1;
		
		String freqQueryStmt = "SELECT " + Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME 
				+ " FROM " + paramTableName + " "
				+ " WHERE " + Constants.PARAM_VAL_SET_ID_ATT_NAME + " = " + paramValSetId;
if(!Constants.PAPER_EXP)
	System.out.println(freqQueryStmt);
		ResultSet rs = IceSheetDBConnector.executeQuery(freqQueryStmt);
		while(rs.next()){
			res = rs.getLong(1);
		}
		rs.close();
		return res;
	}
	
	/***
	 * Get update statement on frequency
	 * @param paramTableName parameter value set table name
	 * @param paramValSetId parameter value set Id
	 * @param frequency frequency 
	 * @return update statement
	 * @throws Exception
	 */
	private static String getParamValSetFreqUpdateStmt(String paramTableName, Long paramValSetId, long frequency) throws Exception{
		// TODO Auto-generated method stub
		String updateStmt = " UPDATE " + paramTableName + 
				" SET " + Constants.PARAM_VAL_SET_FREQUENCY_ATT_NAME + " = " + frequency + 
				" WHERE " + Constants.PARAM_VAL_SET_ID_ATT_NAME + " = " + paramValSetId;
		return updateStmt;
	}
}
