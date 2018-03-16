package com.kisti.osp.icesheet.PROV;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.interop.InteropFramework.ProvFormat;
import org.openprovenance.prov.model.ActedOnBehalfOf;
import org.openprovenance.prov.model.Activity;
import org.openprovenance.prov.model.Agent;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasAttributedTo;
import org.openprovenance.prov.model.WasGeneratedBy;

import com.kisti.osp.icesheet.portlet.Constants;


public class PROVDocWriter {	
	
	Simulation simInfo = new com.kisti.osp.icesheet.PROV.Simulation();
	String scienceAppNameVal = simInfo.scienceAppNameVal;
	
	//public static final String PROVSIM_NS = Constants.EDISON_PREFIX_NS; 	
	public static final String PROVSIM_NS = "http://www.edison.re.kr/";
	public static final String PROVSIM_PREFIX = "provSim";
	
	public static final String Solver_PREFIX = Constants.solverNameVal;
	public final String Solver_NS = PROVSIM_NS + Solver_PREFIX;
	
	private static ProvFactory pFactory;
	private static Namespace ns;
	
	public PROVDocWriter()
	{
		PROVDocWriter.pFactory = InteropFramework.newXMLProvFactory();
		ns = new Namespace();
		ns.addKnownNamespaces();
		ns.register(PROVSIM_PREFIX, PROVSIM_NS);
		ns.register(Solver_PREFIX, Solver_NS);		
	}
	
	
	public PROVDocWriter(ProvFactory pFactory)
	{
		PROVDocWriter.pFactory = pFactory;
		ns = new Namespace();
		ns.addKnownNamespaces();
		ns.register(PROVSIM_PREFIX, PROVSIM_NS);
		ns.register(Solver_PREFIX, Solver_NS);		
	}
	
	public static QualifiedName qn(String n) {
		return ns.qualifiedName(PROVSIM_PREFIX, n, pFactory);
		// return ns.qualifiedName(Solver_PREFIX, n, pFactory);
	}
	
	//Qualified Name Method 
	private QualifiedName QualifiedName(String input) {
		// TODO Auto-generated method stub
		return ns.qualifiedName(PROVSIM_PREFIX, input, pFactory);
	}
	
	
	public static void  writetoFile(String jsonStr){
	
//		Vector<Simulation>Simulation = ProvDataCollector.getSimulationRecords();
//		Vector<SimulationJob>SimulationJob = ProvDataCollector.getSimulationJobRecords();		
//		Vector<SimulationJobData>SimulationJobData = ProvDataCollector.getSimulationJobDataRecords();
//		Vector<UserInfo>UserInfo = ProvDataCollector.getUserInfoRecords();
//		
//		for(int i = 0; i < Simulation.size(); i++)
//		{
//			String Simuuid = Simulation.get(i).simUuidVal;
//			long userid = Simulation.get(i).userIdVal;
//			
//
//		}		
	}
	
	/***
	 * Make a PROV document with given parameters
	 * 
	 * @param Simuuid
	 *            simulation uuid
	 * @param inputpath
	 *            input path
	 * @param outputpath
	 *            output path
	 * @param JobSeqval
	 *            job sequence counts
	 * @param userid
	 *            user id
	 * @param jobuuid
	 *            job uuid
	 * @return Document
	 */
	
	public String makeProvDoc(Vector<UserInfo> userVec, Vector<Simulation> simVec, 
			Vector<SimulationJob> simJobVec, Vector<SimulationJobData> simJobDataVec)
	{
		HashMap<Long, String> userInfoMap = buildUserInfoMap(userVec);
		if(userInfoMap == null){
			System.out.println("There is no user.");
			return null;
		}
		Document document = pFactory.newDocument();		
		Entity input = pFactory.newEntity(qn("Input"));
		Entity output = pFactory.newEntity(qn("OutputPath"));
		

		String res = "";
		// if a simulation record exists, 
		// then create a PROV doc
		int numSims = simVec.size();
		if(numSims > 0){
			// for each simulation record

				// it gets iterated to fetch necessary information
				Simulation simRec = simVec.elementAt(0);
				UserInfo userRec = userVec.elementAt(0);
				SimulationJob simJobRec = simJobVec.elementAt(0);
				//for JobData..  
				SimulationJobData simJobDataRec = simJobDataVec.elementAt(0);
				
				// get a specific area
				long groupId = simRec.groupIdVal;
								
				// get simulation uuid 
				String simUuid = simRec.simUuidVal;
				
				// get user id
				long userId = simRec.userIdVal;
				// get screen name
				String screenName = userInfoMap.get(new Long(userId));
				if(screenName == null)
					//Main._logger.reportError("No user screen name.");
					System.out.println("No user screen name.");
				
				// create Agent (User)
				Agent user = pFactory.newAgent(qn(Constants.USERNAME), userRec.firstNameVal);

				// get science app id
				long scienceAppId = simRec.scienceAppIdVal;
				// get science app name
				String scienceAppName = simRec.scienceAppNameVal;
				
				// create Agent (Solver = ScienceApp)
				Agent solver = pFactory.newAgent(qn(Constants.simScienceAppNameFN), scienceAppName);
				
				WasAttributedTo wat = pFactory.newWasAttributedTo(qn(String.valueOf(userId)),
						input.getId(), qn(String.valueOf(scienceAppId)));
				
				// ActedOnBehalfOf
				ActedOnBehalfOf aob = pFactory.newActedOnBehalfOf(qn(String.valueOf(screenName)),
						qn(scienceAppName), qn(screenName));
				
				
				//WasAssociatedWith WAW
				WasAssociatedWith waw1 = pFactory
						.newWasAssociatedWith(qn(Solver_PREFIX),
								qn(scienceAppName), qn(screenName));
				
				Activity simulation = pFactory.newActivity(qn(simUuid), simUuid);

				// get number of jobs
				int numJobs = simJobVec.size();
				// get number of job data
				int numJobData = simJobVec.size();
				
				long prevNum = 0;
				String prevUuid = "";
				// for each job 
				for(int j=1; j<=numJobs; j++){
					
					// job uuid 
					String jobUuidVal = simJobRec.jobUuidVal;
					// job sequence number
					long jobSeqNum = simJobRec.jobSeqNoVal;		
					// job outputPath 
//					String jobExecPathVal = simJobRec.jobExecPathVal;
//					output.setValue(pFactory.newValue(jobExecPathVal));
					
					
					Entity jobs = pFactory.newEntity(qn("jobuuid" + jobSeqNum));
					jobs.setValue(pFactory.newValue(jobUuidVal));
					
					
					//jobData Entity Create and Set Value  
					String jobDataVal = simJobDataRec.jobDataVal;
					
					Entity jobData = pFactory.newEntity(qn("jobData"+ jobSeqNum));
//					String fileContents = FilecontentsParse(jobDataVal);
			
//					jobData.setValue(pFactory.newValue(fileContents));
					jobData.setValue(pFactory.newValue(jobDataVal));

//					Entity JobData = pFactory.newEntity(qn("jobData" + jobData));
//					JobData.setValue(pFactory.newValue(jobData));
					
					WasAttributedTo WATJob = pFactory.newWasAttributedTo(qn(jobUuidVal), qn("Job" + String.valueOf(jobSeqNum)), qn(scienceAppName));
					WasGeneratedBy wgbjob = pFactory.newWasGeneratedBy(jobs,
							"WasGeneratedBy", simulation);	
					
					document.getStatementOrBundle().addAll(
							Arrays.asList(new StatementOrBundle[] { jobs, jobData, WATJob, wgbjob })); //job erase ?
				}
				
				// Set FileName for Input Entity 
				String jobDataStr =  simJobDataRec.jobDataVal;
				if(jobDataStr.contains("fileName"))
				{
					int i = jobDataStr.indexOf("fileName");
					int j = jobDataStr.indexOf("}");
//					String index = jobDataStr.substring(i, 12);
//					int k = index.indexOf(0);
//					
					String fileName = jobDataStr.substring(i, j);
//					String element = fileName.replaceAll("\\", "_");
					
					input.setValue(pFactory.newValue(fileName));
				}else
					input.setValue(pFactory.newValue("Input File None"));

				
//				//parsing Filecontents
//				FilecontentsParse(jobDataStr);
				
				
//				int s = jobDataStr.indexOf("size:");
//				int z = jobDataStr.indexOf(",");
//				
//				String fileSize = jobDataStr.substring(s, z);
//				
//				input.setValue(pFactory.newValue(fileSize));
				
				
				Used used = pFactory.newUsed(QualifiedName(simUuid), QualifiedName("inputFile"));
				
				// job outputPath 
				output.setValue(pFactory.newValue("/EDISON/LDAP/" + userRec.screenNameVal +"/" + simUuid + "/result"));
				
				//WasGeneratedBy output 
				WasGeneratedBy wgbOutput = pFactory.newWasGeneratedBy(output,
						"WasGeneratedBy", simulation);
				
				document.getStatementOrBundle().addAll(
						Arrays.asList(new StatementOrBundle[] { input, output, solver, used,
								wat, simulation, wgbOutput, aob, waw1 }));
				
				String fileName = simUuid  + ".json" ;
				String pathName = "/EDISON/" + simRec.scienceAppNameVal + "/" + simRec.scienceAppVersionVal + "/" + fileName; 
				//return res;
				document.setNamespace(ns);
				doConversions(document, fileName);
				return pathName;
			}
		return res;	
	}


	/*****
	 * Return a map of user's screen name from user id
	 * @param userVec
	 * @return user map 
	 */
	private static HashMap<Long, String> buildUserInfoMap(Vector<UserInfo> userVec) {
		// TODO Auto-generated method stub
		if(userVec == null | userVec.capacity()== 0) return null;
		else{ 
			HashMap<Long, String> userMap = new HashMap<Long, String>();
			for(int i=0;i<userVec.size();i++){
				UserInfo ui = userVec.get(i);
				userMap.put(new Long(ui.userIdVal), new String(ui.screenNameVal));
			}
			return userMap;
		}
	}
	
	private String SimDataParse()
	{
		String parseData = "";
		
		return parseData;
	}
		

	public void openingBanner() {
		// TODO Auto-generated method stub
		System.out.println("*************************");
		System.out.println("* Converting document  ");
		System.out.println("*************************");
	}
	
	public void doConversions(Document document, String file) {
		// TODO Auto-generated method stub
		InteropFramework intF = new InteropFramework();
			
	    OutputStream out = new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};

	    //intF.writeDocument(out, ProvFormat.JSON, document);
	    intF.writeDocument(file, ProvFormat.JSON, document);
	    
	    //intF.writeDocument(file, document);
	   
	    //intF.writeDocument(out, ProvFormat.SVG, document);
	}

/*	private String FilecontentsParse(String jobDataStr)
	{
		
		int i = jobDataStr.indexOf("file-content");
		int j = jobDataStr.indexOf("}");
		
		String filecontents = jobDataStr.substring(i, j);
		
		return filecontents;
	}*/
	
	public void closingBanner() {
		System.out.println("");
		System.out.println("*************************");
	}

}
