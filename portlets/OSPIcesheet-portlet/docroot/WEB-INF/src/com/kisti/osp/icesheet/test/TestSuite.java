package com.kisti.osp.icesheet.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;

import com.kisti.osp.icesheet.PROV.PROVDocWriter;
import com.kisti.osp.icesheet.PROV.PROVModeller;
import com.kisti.osp.icesheet.PROV.Simulation;
import com.kisti.osp.icesheet.PROV.SimulationJob;
import com.kisti.osp.icesheet.PROV.SimulationJobData;
import com.kisti.osp.icesheet.PROV.UserInfo;
import com.kisti.osp.icesheet.portlet.Constants;
import com.kisti.osp.icesheet.portlet.IceSheetUtil;
import com.kisti.osp.icesheet.portlet.ProvDataCollector;
import com.kisti.osp.icesheet.portlet.ProvDataLoader;

/*****
 * Provide a suite of unit testing. 
 * Create ProvDoc 
 *  * @author majin
 *
 */
public class TestSuite {

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
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		//testPROV();
		
//		testWaveSimulation();
		
		testroundSTMtip();
//		
//		testBowling();
//		
//		testGravityslingshot();
//		
//		testPhaseDiagramSW();
//		
//		testPianoString();
//		
//		testacuteSTMtip();
		
		testUChem();
		
	}

	// ProvDOC Creating test 
	public static void testPROV(){
		//createPROVTest();

//		ProvDataCollector ProvDC = new ProvDataCollector();

		
		HashMap<String, Vector<Object>>ProvDC =	ProvDataCollector.collectProvenanceDataByApp("2D_InComp_P","3.0.0","2017-03-01:00-00-00","2017-12-31:00-00-00");
		
//		String provDoc = PROVModeller.constructPROVDoc(
//				  ProvDataCollector.getUserInfoRecords(),
//				  ProvDataCollector.getSimulationRecords(), 
//				  ProvDataCollector.getSimulationJobRecords(), 
//				  ProvDataCollector.getSimulationJobDataRecords());


		Set simKey = ProvDC.keySet();
		
		// PROVModeller.constructPROVDoc(userVec, simVec, simJobVec, simJobDataVec);
		
		for (Iterator iterator = simKey.iterator(); iterator.hasNext();)
		{
			String simuuid = (String)iterator.next();
			Vector<Object> simData = ProvDC.get(simuuid);

		//  Vector<Simulation> simVec = (Vector<Simulation>) simData.set(0, simuuid + "=" + "{" + simuuid + "=" + simuuid);
			Vector<Simulation> simVec = (Vector<Simulation>) simData.elementAt(0);
			Vector<SimulationJob> simJobVec = (Vector<SimulationJob>) simData.elementAt(1);
			Vector<SimulationJobData> simJobDataVec = (Vector<SimulationJobData>) simData.elementAt(2);
			Vector<UserInfo> userVec = (Vector<UserInfo>) simData.elementAt(3);

			
			PROVDocWriter prov = new PROVDocWriter();
			prov.openingBanner();
			String document = prov.makeProvDoc(userVec, simVec, simJobVec, simJobDataVec);
			System.out.println(document);
			prov.closingBanner(); 
		}
	}
	
	
	
	// add to test function for each simulation SW
	public static void testroundSTMtip()
	{
		// Provide parameters for roundSTMtip
		long subjectId = Constants.roundSTMtip;
		String scienceAppName = "roundSTMtip";
		String scienceAppVersion = "1.0.0";
		String simUuid = "002bcb3e-c6a3-4f07-8f95-0b084acef97f";
		String jobUuid = "aa89d5b8-58dc-43ef-8707-4bdf34c25a67";
		String userName = "c00401311627";
		
		String jobData = IceSheetUtil.readFile(
				"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				//"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "roundSTMtip_c00401311627_002bcb3e-c6a3-4f07-8f95-0b084acef97f_aa89d5b8-58dc-43ef-8707-4bdf34c25a67.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testacuteSTMtip()
	{
		// Provide parameters for acuteSTMtip
		long subjectId = Constants.acuteSTMtip;
		String scienceAppName = "acuteSTMtip";
		String scienceAppVersion = "1.0.0";
		String simUuid = "012e71a1-e9eb-43f5-a3d5-29fe0da2c284";
		String jobUuid = "68073e00-d8d7-4645-b82c-b6d599b5b46a";
		String userName = "c01391311020";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "acuteSTMtip_c01391311020_012e71a1-e9eb-43f5-a3d5-29fe0da2c284_68073e00-d8d7-4645-b82c-b6d599b5b46a.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testBowling()
	{
		// Provide parameters for Bowling
		long subjectId = Constants.Bowling;
		String scienceAppName = "Bowling";
		String scienceAppVersion = "1.0.0";
		String simUuid = "fc58de8a-e38c-4424-98b6-97dabdcbb875";
		String jobUuid = "ce9eaa17-da2c-48b0-bcf1-d1579ab3fba9";
		String userName = "eunwon";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "Bowling_eunwon_fc58de8a-e38c-4424-98b6-97dabdcbb875_ce9eaa17-da2c-48b0-bcf1-d1579ab3fba9.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void testWaveSimulation()
	{
		// Provide parameters for WaveSimulation
		long subjectId = Constants.WaveSimulation;
		String scienceAppName = "WaveSimulation";
		String scienceAppVersion = "1.0.0";
		String simUuid = "9c2d29dc-17ac-4970-b003-c55cddfeb185";
		String jobUuid = "fa166193-ac9f-45b9-b428-c3070062ee44";
		String userName = "c02941717224";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "WaveSimulation_c02941717224_9c2d29dc-17ac-4970-b003-c55cddfeb185_fa166193-ac9f-45b9-b428-c3070062ee44.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
			IceSheetUtil.moveSimOutputDataintoProvServer(userName, scienceAppName, scienceAppVersion, simUuid, jobUuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testGravityslingshot()
	{
		// Provide parameters for gravityslingshot
		long subjectId = Constants.gravityslingshot;
		String scienceAppName = "gravityslingshot";
		String scienceAppVersion = "1.0.0";
		String simUuid = "002c825b-fdf1-4129-b76d-e6d5af5c6ae0";
		String jobUuid = "ee8271b6-ca10-4d64-8e11-1a8278f5afbc";
		String userName = "c02961717322";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "gravityslingshot_c02961717322_002c825b-fdf1-4129-b76d-e6d5af5c6ae0_ee8271b6-ca10-4d64-8e11-1a8278f5afbc.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testPhaseDiagramSW()
	{
		// Provide parameters for PhaseDiagramSW
		long subjectId = Constants.PhaseDiagramSW;
		String scienceAppName = "PhaseDiagramSW";
		String scienceAppVersion = "1.0.0";
		String simUuid = "08082504-00c4-4720-b60d-9edd7009e1c2";
		String jobUuid = "0ab14670-8167-49f4-afa2-ff7e76b4098d";
		String userName = "c0462201514185";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
						+ "PhaseDiagramSW_c0462201514185_08082504-00c4-4720-b60d-9edd7009e1c2_0ab14670-8167-49f4-afa2-ff7e76b4098d.json");
		// Invoke the Matcher's main method
		try {
			ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testPianoString()
	{
		// Provide parameters for pianostring
		long subjectId = Constants.pianostring;
		String scienceAppName = "pianostring";
		String scienceAppVersion = "1.0.0";
		String simUuid = "01836315-9669-49a2-9919-ca484a8ce09b";
		String jobUuid = "592d7455-2552-403e-b2a3-a930c2d849d2";
		String userName ="c03532017027601";
		
		String jobData = IceSheetUtil.readFile(
				//"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				
				"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
				+ "pianostring_c03532017027601_01836315-9669-49a2-9919-ca484a8ce09b_592d7455-2552-403e-b2a3-a930c2d849d2.json");
		// Invoke the Matcher's main method
		try {
			String outputPath = ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testUChem() //Using input file Simulation
	{
		// Provide parameters for pianostring
				long subjectId = Constants.uChem;
				String scienceAppName = "uChem";
				String scienceAppVersion = "4.0.1";
				String simUuid = "d7ec59be-cd8d-46a6-8d6c-98e173e4215f";
				String jobUuid = "05c39535-1828-43fc-b28a-b0aa2469b8d2";
				String userName ="robertyun";
				
				String jobData = IceSheetUtil.readFile(
						"D:/liferay_work/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
						
						//"/home/majin/liferay_workspace/OSPIcesheet-portlet/docroot/WEB-INF/src/com/kisti/osp/icesheet/test/SampleJobData/"
						+ "uChem_robertyun_d7ec59be-cd8d-46a6-8d6c-98e173e4215f_05c39535-1828-43fc-b28a-b0aa2469b8d2.json");
				// Invoke the Matcher's main method
				try {
					//ProvDataLoader.loadProvenanceMain(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);
					String outputPath = ProvDataLoader.inputFileloadProv(userName, subjectId, scienceAppName, scienceAppVersion, simUuid, jobUuid, jobData);  //input file load and output copy 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
	}
	

}
