package com.kisti.osp.icesheet.portlet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProvDataMatcher {
	
//	- retrieve(scienceAppId, jobData): result directory on our provenance server if true: ProvDataMatcher.lookUpExistingProvenanceMain(subjectId, jobData)
//	- fileList(path): Vector<String> => file names: ProvDataRetriever.getAllFiles(outputPath);
//	- fileReader(path/filename): String => file contents 
//	- load(appId, appName, appVersion, jobTitle:String, 
//			simCreateDt:String, simId:string, jobUuid:string,jobData:string,jobStatus:String,jobStart,jobEnd)
	/****
	 * Load a Hash Table from hash table file
	 */
	public static void loadProvenanceHashTable() throws Exception{
		// use provenanceHashTables
	}
	
	/****
	 * Load a simulation subject into database
	 * @param subjectId subject id
	 * @param subjectName subject name
	 * @param subjectVersion subject version
	 */
	public static boolean compareProvenances(String subjectName,
											 String subjectVersion,
											 String simulationUuid,
											 Map<String, String> jobDataMap) throws Exception{	
		return false;
	}
	
	/****
	 * Load a simulation subject into database
	 * @param subjectId subject id
	 * @param subjectName subject name
	 * @param subjectVersion subject version
	 */
	public static boolean compareNewProveanceWithExistingProvenances(
											  String subjectName,
											  String subjectVersion,
											  String simulationUuid,
											  HashMap<String, String> jobDataMap) throws Exception{	
//		// now construct input data hash table for a given science app
//		if(IceSheetDBConnector.open(false)){
//			System.out.println("Database successfully opened!");
//		}
//		if(EdisonDBConnector.open(false)){
//			System.out.println("Database successfully opened!");
//		}
//		String getJobInfoQuery = "SELECT t0.scienceAppId, "
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
//				  + "AND t1.simulationUuid = '"+simulationUuid+"' "
//		  		  + "AND t2.jobUuid = t3.jobUuid";
//		
//System.out.println(getJobInfoQuery);		
//		
//		String outputDirPath = getOutputDirPath();
//		
//		ResultSet rs = EdisonDBConnector.executeQuery(getJobInfoQuery);		
//		try {
//			while(rs.next()){
//				Long subjectId = rs.getLong(1);
////								Long jobUuid = rs.getLong(2);
//				String jobData = rs.getString(3);
//				long inputDataHashKey = jobData.hashCode();
//				long inputFileHashKey = generateInputFileHashKey(null);
//				String outputDataDirectory = findOutputDataDirectory(subjectId, inputDataHashKey, inputFileHashKey);
//				if(outputDataDirectory == null){
//					String insertStmt = 
//							" INSERT INTO " + Constants.INPUTDATA_HT_TABLE + " VALUES IN (" + 
//									subjectId + "," +
//									inputDataHashKey + "," +
//									inputFileHashKey + "," +
//									outputDirPath + ")";
//System.out.println(insertStmt);
//					IceSheetDBConnector.insertRecordIntoProvDB(insertStmt);
//					IceSheetDBConnector.commit();
//				}else{
//System.out.println("output path: " +  outputDataDirectory);			
//					continue;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		EdisonDBConnector.close();
		return false;
	}
	
	/****
	 * Look up a specific hash table with a given jobdata record
	 */
	public static String lookUpExistingProvenanceMain(Long subjectId, String jobData) throws Exception{
		String outputPath = null;
		
//		 get input parameters of this subject
		Long inputDataHashKey = null;
		
		// get input file hash key
//		if(jobInputFileDir != null){
//			inputFileHashKey = IceSheetUtil.generateInputFileHashKey(jobInputFileDir);
//		}else{
//			inputFileHashKey = Constants.DEFAULT_INPUTFILE_HASH_KEY;
//		}
		
		// get input data hash key
		if(jobData != null){
			inputDataHashKey = IceSheetUtil.generateInputDataHashKey(subjectId, jobData);
			System.out.println("input data hash key:" + inputDataHashKey);
		}

		// examine if there's any matching item from this table on the generated keys 
		outputPath = ProvDataMatcher.findOutputDataDirectory(subjectId, inputDataHashKey, Constants.DEFAULT_INPUTFILE_HASH_KEY);
		return outputPath;
	}
	
	/****
	 * Load a Hash Table from hash table file
	 */
	public static void writeOutProvenanceHashTableToFile() throws Exception{
		// use provenanceHashTables
	}

	
	/****
	 * Check if there's any item already existing in the Hash Table (DB)
	 * @param subjectId
	 * @param inputDataHashKey
	 * @param inputFileHashKey
	 * @return the output directory of existing results
	 */
	public static String findOutputDataDirectory(Long subjectId, Long inputDataHashKey, Long inputFileHashKey) {
//		// TODO Auto-generated method stub
//		if(IceSheetDBConnector.open(false)){
//			System.out.println("IceSheet Database successfully opened!");
//		}
	 	String strOutputDirPath = null;
		
		if(subjectId != null){
			String query = " SELECT outputDirPath "
					+ "FROM " + Constants.INPUTDATA_HT_TABLE + 
					" WHERE subjectId = " + subjectId;
			if(inputDataHashKey != null){
				query += " and inputDataHashKey = " + inputDataHashKey;
			}
			if(inputFileHashKey != null){
				query += " and inputFileHashKey = " + inputFileHashKey;
			}
if(!Constants.PAPER_EXP)			
	System.out.println(query);
			ResultSet rs = null;
			try {
				rs = IceSheetDBConnector.executeQuery(query);
				while(rs.next()){
					strOutputDirPath = rs.getString(1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return strOutputDirPath;
	}
}
