package com.kisti.osp.icesheet.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;

import com.kisti.osp.icesheet.PROV.PROVDocManager;
import com.kisti.osp.icesheet.PROV.PROVDocWriter;
import com.kisti.osp.icesheet.PROV.PROVModeller;
import com.kisti.osp.icesheet.PROV.Simulation;
import com.kisti.osp.icesheet.PROV.SimulationJob;
import com.kisti.osp.icesheet.PROV.SimulationJobData;
import com.kisti.osp.icesheet.PROV.SimulationSubject;
import com.kisti.osp.icesheet.PROV.UserInfo;
import com.kisti.osp.icesheet.portlet.Constants;
import com.kisti.osp.icesheet.portlet.IBConnector;
import com.kisti.osp.icesheet.portlet.IceSheetUtil;
import com.kisti.osp.icesheet.portlet.ProvData;
import com.kisti.osp.icesheet.portlet.ProvDataCollector;
import com.kisti.osp.icesheet.portlet.ProvDataLoader;
import com.kisti.osp.icesheet.portlet.ProvDataMatcher;
import com.kisti.osp.icesheet.portlet.ProvDataRetriever;

/*****
 * Unit Testing for Young's code
 * @author yksuh
 *
 */
class ScienceAppInfo{
	public ScienceAppInfo(){}
	public String scienceAppName = "";
	public String scienceAppVersion = "";
	public String startDateTime = "";
	public String endDateTime = "";
}

public class TestSuiteYksuh {
	public static void createPROVTest(){
		// TODO Auto-generated method stub
//		try {
//			
//			
//		} catch (NoSuchDataTypeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Vector<UserInfo> userVec = new Vector<UserInfo>(); 
		Vector<Simulation> simVec = new Vector<Simulation>();
		Vector<SimulationJob> simJobVec = new Vector<SimulationJob>();
		Vector<SimulationJobData> simJobDataVec = new Vector<SimulationJobData>();
		
		// test PROV doc creation
		PROVModeller.constructPROVDoc(userVec, simVec, simJobVec, simJobDataVec);
	}
	
	public static void main(String[] args) {
  		//createPROVTest();
//		String token = "";
//		try {
//			token = IBConnector.getLoginToken(Constants.IB_LOGIN_NAME, Constants.IB_PASSWORD);
//			System.out.println("received token:"+token);
//		} catch (UnsupportedOperationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
//		try {
//			String fileId = "L0VESVNPTi8uL0xEQVAvREFUQS8xMzQwMS8yMTcvYzAyMTcxNzAzNTUvcmVwb3NpdG9yeS94eXpfMS4yLnR4dA==";
//			String fileInfo = IBConnector.getFileInfo(token,fileId);
//			System.out.println(fileInfo);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String scienceAppName = "2D_Incomp_P";
//		String scienceAppVersion = "3.0.0";
//		String startDateTime = "2016-7-1 00:00:00";
//		String endDateTime = "2017-5-10 00:00:00";
		
//		String scienceAppName = "KDFT";  // (multiple input files) only
//		String scienceAppVersion = "2.0.1";
//		String startDateTime = "2016-7-1 00:00:00";
//		String endDateTime = "2017-4-25 00:00:00";
		
//		String scienceAppName = "gamess";  // (multiple input files) only
//		String scienceAppVersion = "1.0.0";
//		String startDateTime = "2016-7-1 00:00:00";
//		String endDateTime = "2017-4-25 00:00:00";
		
//		String scienceAppName = "CSD_EPLAST"; // input file only
//		String scienceAppVersion = "1.1.0";
//		String startDateTime = "2016-7-1 00:00:00";
//		String endDateTime = "2017-4-25 00:00:00";
		String startDateTime = "2016-7-1 00:00:00";
		String endDateTime = "2017-5-31 00:00:00";
		
		Vector<ScienceAppInfo> appVec = new Vector<ScienceAppInfo>();
		ScienceAppInfo sca = null;
		sca = new ScienceAppInfo();
//		sca.scienceAppName = "WaveSimulation"; // 69601
//		sca.scienceAppVersion = "1.0.0";
//		sca.startDateTime = startDateTime;
//		sca.endDateTime = endDateTime;
//		appVec.add(sca);
//		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "rollercoaster"; // 33901
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
//		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "gravityslingshot";  // 27901
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
//		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "Bowling"; // 27701
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
////		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "roundSTMtip";  // 25701
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
////		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "acuteSTMtip"; // 25601
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
////		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "PhaseDiagramSW"; // 33501
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
////		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "pianostring";  // 28201
//		sca.scienceAppVersion = "1.0.0";
//		appVec.add(sca);
//		-----
		sca = new ScienceAppInfo();
		sca.scienceAppName = "gamess";  // 21301
		sca.scienceAppVersion = "1.0.0";
		appVec.add(sca);
//		
//		sca = new ScienceAppInfo();
//		sca.scienceAppName = "uChem";  // 41301
//		sca.scienceAppVersion = "4.0.1";
//		appVec.add(sca);

		
//		for(int i=0;i<appVec.size();i++){
//			ScienceAppInfo sai = appVec.get(i);
//			String scienceAppName = sai.scienceAppName;
//			String scienceAppVersion = sai.scienceAppVersion;
//			String orgStartDateTime = sai.startDateTime;
//			String orgEndDateTime = sai.endDateTime;
			
			// First build hash table for a given science app
//			ProvDataLoader.buildInputDataHashTable(subjectName, subjectVersion);
			// say that a new simulation comes in our IceSheet
//			String simulationUuid = "00102191-8708-44ac-af0a-ef715963db48";
//			HashMap<String, String> jobDataMap = ProvDataLoader.getJobData(subjectName, subjectVersion, simulationUuid);
			
		
//			String scienceAppName = "rollercoaster"; // 33901
//			String scienceAppVersion = "1.0.0";
//			
//			String scienceAppName = "gravityslingshot";  // 27901
//			String scienceAppVersion = "1.0.0";
			
//			String scienceAppName = "acuteSTMtip"; // 25601
//			String scienceAppVersion = "1.0.0";
			
//			String scienceAppName = "roundSTMtip";  // 25701
//			String scienceAppVersion = "1.0.0";
		
//			String scienceAppName = "Bowling";  // 27701
//			String scienceAppVersion = "1.0.0";
		
//			String scienceAppName = "WaveSimulation";  // 69601
//			String scienceAppVersion = "1.0.0";
		
//			String scienceAppName = "PhaseDiagramSW";  // 33501
//			String scienceAppVersion = "1.0.0";

//			String scienceAppName = "pianostring";  // 28201
//			String scienceAppVersion = "1.0.0";
			
//			try{
//				ProvDataLoader.storeAllProvenances(scienceAppName, 
//												   scienceAppVersion);	
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//			
////			String searchDayStart = "60";
////			String topK = "10";
////			Vector<ProvData> resVec = null;
////			try {
////				resVec = ProvDataRetriever.retrieveProvenance(null, scienceAppName, scienceAppVersion, searchDayStart, topK);
////			} catch (Exception e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			for(int j=0;j<resVec.size();j++){
////System.out.println((j+1)+"=>"+resVec.get(j).simulationTitle+"|"+resVec.get(j).simulationCreateDt+"|"+resVec.get(j).outputDirPath);
////			}
//			
//		}
			// a given simulation uuid
			String simulationUuid = "0073da90-b1d0-4849-95fb-2e05074ef837";
			try {
				String outputDirPath = "";
				
				/****** Can be given from external component ********************************************************/
				String userName = null;
				Long subjectId = null;
				String subjectName = null;
				String subjectVersion = null;
				String simulationTitle = null;
				String simCreateDate = null;
				String jobUuid = null;
				String jobInputFileDir = null;
				String jobData = null;
			    String statusCode = null;
			    String jobStartDate = null;
			    String jobEndDate = null;
				
			    HashMap<String, Object> simDetailedMap = ProvDataCollector.getNewSimulationInfo(simulationUuid);
			    
			    for(Map.Entry<String, Object> entry : simDetailedMap.entrySet()){
			    	if(entry.getKey().contains(Constants.screenNameFN)){
			    		userName = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.simUuidFN)){
			    		simulationUuid = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.scienceAppIdFN)){
			    		subjectId = (Long)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.scienceAppNameFN)){
			    		subjectName = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.scienceAppVersionFN)){
			    		subjectVersion = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.simulationTitleFN)){
			    		simulationTitle = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.simulationCreateDtFN)){
			    		simCreateDate = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.jobUuidFN)){
			    		jobUuid = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.jobDataFN)){
//			    		jobData = (String)entry.getValue();
			    		jobData = "{\"-param\":{\"port_data\":\"v0 = 14.5 ; thetaV0 = 90 ; thetaE0 = 0 ; thetaJ0 = 160 ;\",\"editor_type\":\"Inputdeck\"}}";
			    	}else if(entry.getKey().contains(Constants.jobStatusFN)){
			    		statusCode = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.jobStartDtFN)){
			    		jobStartDate = (String)entry.getValue();
			    	}else if(entry.getKey().contains(Constants.jobEndDtFN)){
			    		jobEndDate = (String)entry.getValue();
			    	}
			    }
			    /******************************************************************************************************/
				outputDirPath = ProvDataLoader.loadSimulationProvenance(simulationUuid,
														   userName, 
														   subjectId, 
														   subjectName, 
														   subjectVersion, 
														   simulationTitle, 
														   simCreateDate, 
														   jobUuid, 
														   jobInputFileDir, 
														   jobData, 
														   statusCode, 
														   jobStartDate, 
														   jobEndDate);
				System.out.println(outputDirPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		
//			try {
//				String outputProvDirPath = ProvDataLoader.loadSimulationProvenanceMain(simulationUuid);
//System.out.println(outputProvDirPath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			String outputDir = "/usr/liferay/liferay-portal-6.2/tomcat-7.0.62/";
////			Vector<String> fileNamesVec = ProvDataRetriever.getAllFileList(outputDir);	
////			for (int i=1;i<=fileNamesVec.size();i++)
////			{
////					String fileName = fileNamesVec.get(i-1);
////					System.out.println(fileName);
////			}
//			String fileName = "RUNNING.txt";
//			
//			String fullFileName = outputDir + fileName;
//			// /usr/liferay/liferay-portal-6.2/tomcat-7.0.62/RUNNING.txt
//			String contents = ProvDataRetriever.readOutputFile(fullFileName);
//			System.out.println(contents);
//			long scienceAppId = -1;
//			try{
//				scienceAppId = IceSheetUtil.getScienceAppId(scienceAppName, scienceAppVersion);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//			HashMap<String, HashMap<String, HashMap<String, String>>> userSimDataMap = null;
//			try{
//				userSimDataMap = IceSheetUtil.getAllSimulations(scienceAppName, scienceAppVersion);
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
			
////			IceSheetUtil.readAllSimulations(userSimDataMap);

			
//			long subjectId = 0;
//			String jobData = "";
			// Provide parameters for WaveSimulation
//			subjectId = Constants.WaveSimulation;
//			jobData = IceSheetUtil.readJSONFile("/Users/yksuh/workspace/Edison/liferay-plugins-sdk-6.2/portlets/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/newJobData.json");
//			// Invoke the Matcher's main method
//			try {
//				String outputPath = ProvDataMatcher.lookUpExistingProvenanceMain(subjectId, jobData);
//				if(outputPath == null){
//					System.out.println("There's no simulation yet.");
//				}else{
//					System.out.println("The output available at: " + outputPath);
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
			// Provide parameters for WaveSimulation
//			subjectId = Constants.WaveSimulation;
//			String scienceAppName = "WaveSimulation";
//			String scienceAppVersion = "1.0.0";
//			String simUuid = "1d7fb7ca-3f78-4681-85e3-b358d7a8d7ef";
//			String jobUuid = "76631bbb-9ed9-4ba0-9a6f-e00882751fe5";
//			
//			jobData = IceSheetUtil.readFile(
//					"/Users/yksuh/workspace/Edison/liferay-plugins-sdk-6.2/portlets/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
//					+ "c03401712140_1d7fb7ca-3f78-4681-85e3-b358d7a8d7ef_76631bbb-9ed9-4ba0-9a6f-e00882751fe5.json");
//			// Invoke the Matcher's main method
//			try {
//				ProvDataLoader.loadProvenanceMain(subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
//			
//			HashMap<String, Vector<Object>> ProvDC = ProvDataCollector.collectProvenanceDataByApp(scienceAppName,scienceAppVersion,orgStartDateTime,orgEndDateTime);
//			
//			SimulationSubject simSubject = ProvDataCollector.getTargetSimulationSubject();
//			
//			if(simSubject != null){
//				try{
//					ProvDataLoader.loadSimulationSubjectInfo(simSubject.subjectId, 
//															simSubject.subjectName,
//															simSubject.subjectVersion);
//				}catch(Exception ex){
//					// TODO Auto-generated catch block
//					ex.printStackTrace();
//					System.exit(-1);
//				}
//			}
//			
//			if(ProvDC != null){
//				try {
//					ProvDataLoader.loadPROVDocInBulk(ProvDC);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					System.exit(-1);
//				}
//			}
//			
//			System.out.println("Bulk loading is completed.");
//		}
		
//		System.out.println("Hash table build is completed.");
		
//		String testSimUuid = "729664e6-fa61-4eb0-9902-4ec3ecd8af3f";
//		String testSimUuid = "00026e7a-af31-4e92-a2fa-0bf2198a808c";
//		ProvDataCollector.collectProvenanceData(testSimUuid);
//		String provDoc = PROVDocTest.constructPROVDoc(
//				  ProvDataCollector.getUserInfoRecords(),
//				  ProvDataCollector.getSimulationRecords(), 
//				  ProvDataCollector.getSimulationJobRecords(), 
//				  ProvDataCollector.getSimulationJobDataRecords());
//		PROVDocWriter docWriter = new PROVDocWriter(InteropFramework.newXMLProvFactory());
//		Document provDoc = docWriter.makeProvDoc(ProvDataCollector.getUserInfoRecords(),
//							  ProvDataCollector.getSimulationRecords(), 
//							  ProvDataCollector.getSimulationJobRecords(), 
//							  ProvDataCollector.getSimulationJobDataRecords());
//		
//		ProvDataLoader.loadPROVDoc(ProvDataCollector.getSimulationUuid(), provDoc);
//		
////		String provDoc = ProvDataRetriever.retrievePROVDoc(Constants.SERVICE_START_YEAR_MONTH,
////										  					testSimUuid);
//		
//		System.out.println(provDoc);
//
//		
		
//		Set simKey = ProvDC.keySet();
//		
//		// PROVModeller.constructPROVDoc(userVec, simVec, simJobVec, simJobDataVec);
//		
//		for (Iterator iterator = simKey.iterator(); iterator.hasNext();)
//		{
//			String simuuid = (String)iterator.next();
//			Vector<Object> simData = ProvDC.get(simuuid);
//
//		//  Vector<Simulation> simVec = (Vector<Simulation>) simData.set(0, simuuid + "=" + "{" + simuuid + "=" + simuuid);
//			Vector<Simulation> simVec = (Vector<Simulation>) simData.elementAt(0);
//			Vector<SimulationJob> simJobVec = (Vector<SimulationJob>) simData.elementAt(1);
//			Vector<SimulationJobData> simJobDataVec = (Vector<SimulationJobData>) simData.elementAt(2);
//			Vector<UserInfo> userVec = (Vector<UserInfo>) simData.elementAt(3);
//
//			String fileName =  simuuid + ".json" ;
//			
//			PROVDocWriter prov = new PROVDocWriter(InteropFramework.newXMLProvFactory());
//			prov.openingBanner();
//			Document document = prov.makeProvDoc(userVec, simVec, simJobVec, simJobDataVec);
//			prov.doConversions(document, fileName);
//			prov.closingBanner(); 
//		}
		
		
//		Vector<UserInfo> userVec = ProvDataCollector.getUserInfoRecords(); 
//		Vector<Simulation> simVec = ProvDataCollector.getSimulationRecords();
//		Vector<SimulationJob> simJobVec = ProvDataCollector.getSimulationJobRecords();
//		Vector<SimulationJobData> simJobDataVec = ProvDataCollector.getSimulationJobDataRecords();
		
		// PROVModeller.constructPROVDoc(userVec, simVec, simJobVec, simJobDataVec);
		
//		PROVDocWriter prov = new PROVDocWriter(InteropFramework.newXMLProvFactory());
//		prov.openingBanner();
//		Document document = prov.makeProvDoc(userVec, simVec, simJobVec, simJobDataVec);
		 
//		System.out.println(document.toString());
		 
	}

}
