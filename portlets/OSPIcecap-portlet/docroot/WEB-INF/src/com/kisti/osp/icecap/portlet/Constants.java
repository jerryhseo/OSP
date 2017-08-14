package com.kisti.osp.icecap.portlet;

public class Constants {
	public final static String ICECAP_MAIN_AXIS = "/html/icecapmanager/";
	public final static String JSP_FILE_EXTENSION = ".jsp";
	public final static String TYPE_CREATION_RESULT_JSP = "create_type_res" + JSP_FILE_EXTENSION;
	public final static String COLLECTION_CREATION_RESULT_JSP = "create_collection_res" + JSP_FILE_EXTENSION;
	public final static String ENTRY_CREATION_RESULT_JSP = "create_entry_res" + JSP_FILE_EXTENSION;
	public static final String COLLECTION_MAIN_JSP = "create_collection_main" + JSP_FILE_EXTENSION;
	public static final String ENTRY_MAIN_JSP = "create_entry_main" + JSP_FILE_EXTENSION;
	public static final String ANALYZER_MAIN_JSP = "create_analyzer_main" + JSP_FILE_EXTENSION;
	public static final String EDITOR_MAIN_JSP = "create_editor_main" + JSP_FILE_EXTENSION;
	public static final String SEARCH_SIM_DATA_MAIN_JSP = "search_sim_data_main" + JSP_FILE_EXTENSION;
	public static final String ENTRY_JSP = "create_entry" + JSP_FILE_EXTENSION;
	
	public final static String ANALYZER_CREATION_RESULT_JSP = "create_analyzer_res" + JSP_FILE_EXTENSION;
	public final static String EDITOR_CREATION_RESULT_JSP = "create_editor_res" + JSP_FILE_EXTENSION;
	
	public final static String EDISON_ROOT_PATH = "/EDISON/";
	public final static String EDISON_DATA_TYPE_PATH = "DATA_TYPE/";
	public final static String SAMPLE_DATA_TYPE_FILE_NAME = "sample_data_type";
	
	
//	public final static String PORTAL_DB_IP = "150.183.247.20";
	public final static String PROV_SERVER_DB_IP = "150.183.247.84";
	public final static String USERNAME = "root";
//	public final static String DB_NAME = "edison2_release";
	public final static String PASSWORD = "superman2016!!";
	public static final long COLLECTION_CREATION = 0;
	public static final long TYPE_ANAYLYZER_ADDITION = 1;
	public static final long TYPE_EDITOR_ADDITION = 2;
	public static final long ENTRY_CREATION = 3;
	public static final String SEARCH_SIM_DATA_RES_JSP = "search_sim_data_res" + JSP_FILE_EXTENSION;
	
	
//	public final static String DB_NAME = "edison2_release";
//	public final static String DB_NAME = "edison2_release";
	public static String SIMULATION_TABLE_NAME = "EDSIM_Simulation";
	
	public static String SIMULATION_JOB_TABLE_NAME = "EDSIM_SimulationJob";
	
	public static String SIMULATION_JOB_DATA_TABLE_NAME = "EDSIM_SimulationJobData";
	
	public static String USER_TABLE_NAME = "User_";
	
	public static String screenNameFN = "screenName";
	
	public static String firstNameFN = "firstName";
	
	public static String groupIdFN = "groupId";
	
	public static String userIdFN = "userId";
	
	public static String scienceAppIdFN = "scienceAppId";
	
	public static String scienceAppNameFN = "scienceAppName";
	
	public static String clusterNameFN = "cluster";
	
	public static String simUuidFN = "simulationUuid";
	
	public static String jobUuidFN = "jobUuid";
	
	public static String jobSeqNoFN = "jobSeqNo";
	
	public static String jobStartDtFN = "jobStartDt";
	
	public static String jobEndDtFN = "jobEndDt";
	
	public static String jobStatusFN = "jobStatus";
	
	public static String jobUniversityFieldFN = "jobUniversityField";
	
	public static String jobInputDeckNameFN = "jobInputDeckName";
	
	public static String jobInputDeckYnFN = "jobInputDeckYn";
	
	public static String jobDataFN = "jobData";
	
	public static int jobCompletionTimeThreshold = 60;
	
	public static String jobExecPathFN = "jobExecPath";
	
	public static String solverNameVal = "2D_Comp";
	
	public static String jobCompletionTimeFN = "jobCompletionTime";

//	public static String SUPERMAN_LOG_DIR_NAME = "logs/";
//	public static String SUPERMAN_JSON_DIR_NAME = "jsons/";
//
//	public static String SUPERMAN_NAME = "Superman";
//	
	public static String ACTED_ON_BEHALF_OF = " ActedOnBehalfOf ";

	public static String WAS_DERIVED_FROM = " WasDerivedFrom ";

	public static String WAS_USED_BY = " WasUsedBy ";

	public static String WAS_ATTRIBUTED_TO = " WasAttributedTo ";
}
