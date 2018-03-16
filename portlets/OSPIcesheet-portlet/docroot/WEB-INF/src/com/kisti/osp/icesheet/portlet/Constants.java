package com.kisti.osp.icesheet.portlet;

public class Constants {
	public static final String ICESHEET_MAIN_AXIS = "/html/icesheetmanager/";
	public final static String JSP_FILE_EXTENSION = ".jsp";
	public final static String ENTITIY_CREATION_RESULT_JSP = "create_entitiy_res" + JSP_FILE_EXTENSION;

	public final static String PROV_PREFIX_NS = "http://www.w3.org/ns/prov#";
	public final static String XSD_PREFIX_NS = "http://www.w3.org/2000/10/XMLSchema#";
	public final static String RDF_PREFIX_NS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	public final static String EDISON_PREFIX_NS = "http://www.edison.re.kr/provenance/icesheet/";
	
	public final static String SUPERMAN_AXIS = "/html/main/";
	public final static String PORTAL_DB_IP = "150.183.247.20";
	public final static String USERNAME = "root";
	public final static String PASSWORD = "dpel+*db";
	public final static String PORTAL_DB_NAME = "edison2_release";
	
	/****
	 * yksuh added the following two statements.
	 */
	/***
	 * PROV database name
	 */
	public final static String PROV_DB_NAME = "edison2_prov";
	/****
	 * Simulation provenance table name
	 */
	public final static String SIMULATION_PROV_TABLE_NAME = "EDSIM_SimulationProv";
	/*****
	 * InputData HashTable table name
	 */
	public final static String SIMULATION_INPUTDATA_HASHTABLE_TABLE_NAME = "EDSIM_InputDataHT";
	/***
	 * InputDataHashKey name
	 */
	public final static String inputDataHashKeyFN = "inputDataHashKey";
	/***
	 * InputDataHashTable version
	 */
	public final static String inputFileHashKeyFN = "inputFileHashKey";
	/***
	 * Subject (Science App or Workflow) ID
	 */
	public final static String subjectIdFN = "subjectId";
	/***
	 * Subject name
	 */
	public final static String subjectNameFN = "subjectName";
	/***
	 * Subject version
	 */
	public final static String subjectVersionFN = "subjectVersion";
	
	/****
	 * Science app table name
	 */
	public final static String SIMULATION_SUBJECT_TABLE_NAME = "EDAPP_SimulationSubject";
	/****
	 * Service Start Year and Month Field Name
	 */
	public final static String serviceStartYrMnFN = "serviceYearMon";
	/****
	 * Simulation create Date Field Name
	 */
	public final static String simulationCreateDtFN = "simulationCreateDt";
	
	/*****
	 * Year/Month to start EDISON service (format: "YYYY-MM")
	 */
	public final static String SERVICE_START_YEAR_MONTH = "201608";
	
	/*****
	 * PortalDB-relevant variables...
	 */
	public static String SIMULATION_TABLE_NAME = "EDSIM_Simulation";
	
	public static String SIMULATION_JOB_TABLE_NAME = "EDSIM_SimulationJob";
	
	public static String SIMULATION_JOB_DATA_TABLE_NAME = "EDSIM_SimulationJobData";
	
	public static String USER_TABLE_NAME = "User_";
	
	public static String screenNameFN = "screenName";
	
	public static String firstNameFN = "firstName";
	
	public static String groupIdFN = "groupId";
	
	public static String userIdFN = "userId";
	
	public static String scienceAppIdFN = "scienceAppId";
	
	public static String simScienceAppNameFN = "scienceAppName";
	
	public static final String scienceAppNameFN = "name";
	
	public static final String scienceAppVersionFN = "version";
	
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

	public static String SUPERMAN_LOG_DIR_NAME = "logs/";
	public static String SUPERMAN_JSON_DIR_NAME = "jsons/";

	public static String SUPERMAN_NAME = "Superman";
	
	public static String ACTED_ON_BEHALF_OF = " ActedOnBehalfOf ";

	public static String WAS_DERIVED_FROM = " WasDerivedFrom ";

	public static String WAS_USED_BY = " WasUsedBy ";

	public static String WAS_ATTRIBUTED_TO = " WasAttributedTo ";
	
	public static String serviceStartYearMonthFN = "serviceYearMon";
	
	public static String provLocFN = "provLoc";
	
	public static String JSON_FILE_EXTENSION = ".json";
	
	public static String INPUTDATA_HT_TABLE = "EDSIM_InputDataHT";
	public static long DEFAULT_INPUTFILE_HASH_KEY  = 0;
	public static long DEFAULT_INPUTDATA_HASH_KEY  = 0;
	public static String DEFAULT_NULL_VALUE = "null";
	public static String PORT_DATA_ATT_NAME = "port_data";
	public static String HASH_CODE_STR = "hashCode";
	public static String OLD_PORT_DATA_ATT_NAME = "-i";
	public static String OLD_PORT_DATA_ATT_NAME2 = "-inp";   // for -inp case 
	public static String OLD_PORT_DATA_ATT_NAME3_f = "-f";   // for -f case 
	
	public static String FILE_CONTENT_ATT_NAME = "file-content";
	public static String JOB_SUCCESS_STATUS_CODE = "1701011";
	
	public static String JOB_SUCCESS = "0";
	public static String JOB_FAILURE = "1";
	
	public static String PROV_SCIENCAPP_TABLE_NAME_PREFIX = "EDAPP_";
	public static String PARAM_CONSTRAINT_NAME = "param_val_set_cnt";
	
	public static String PROV_SCIENCAPP_TABLE_NAME_SUFFIX = "_Detailed";
	public final static String OUTPUT_DIR_NAME_SUFFIX = "result";
	
	public static String PARAM_DOUBLE_TYPE = "DOUBLE";
	public static String DEFAULT_FREQ_VALUE = "1";

	public static String DEFAULT_ZERO_VALUE = "0";
	
	/****
	 * Param attribute name
	 */
	public static String PARAM_VAL_SET_ID_ATT_NAME = "paramValSetId";
	public static String PARAM_VAL_SET_FREQUENCY_ATT_NAME = "frequency";
	
	/****
	 * Param Detailed attribute names
	 */
	public static String PARAM_VAL_SET_SNAPSHOT_TIME_ATT_NAME = "snapshotTime";
	public static String PARAM_VAL_SET_ELAPSED_TIME_ATT_NAME = "elapsedTime";
	public static String PARAM_VAL_SET_QUEUING_TIME_ATT_NAME = "queuingTime";
	public static String PARAM_VAL_SET_STATUS_ATT_NAME = "status";
	public static int SUCCESS = 0;
	public static int FAILURE = 1;
	public static String KEYWORD_DEFAULT = "Default";
	private static String RESPONSE_EXTENSION = "_res";
	public static String TOTAL_LOAD_CNT_STR = "# of stored jobs:";
	public static String TOTAL_DUP_CNT_STR = "# of duplicate jobs:";
	public static String INPUTDATA_HASH_KEY = "inputDataHashKey";
	public static String HASH_KEY_SEP = "_";
	public static String PROV_SERVER_IP = "150.183.247.84";
	public static String PROV_SERVER_USER_ACCNT = "yksuh";
	public static String DEFAULT_TOP_K = "5";
	public static boolean PAPER_EXP = true;
	
	/***
	 * Science App table name
	 */
	public final static String SCIENCAPP_TABLE_NAME = "EDAPP_ScienceApp";
	public final static String PROV_CREATE_APP_RES = "create_PROV_app" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String PROV_CREATE_RES = "create_PROV" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String PROV_SEARCH_RES = "search_PROV" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String STORE_ALL_PROV_REC_RES = "storeAllProvpRec" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String STORE_SINGLE_PROV_REC_RES = "storeSingleProvRec" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String RETRIEVE_OUTPUT_RES = "retrieveOutput" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String BROWSE_FILES_RES = "browseFiles" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String READ_OUTPUT_FILE_RES = "readFile" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String BROWSE_SIMS_RES = "browseSimulations" + RESPONSE_EXTENSION + JSP_FILE_EXTENSION;
	
	public final static String jobSubmitDtFN	="jobSubmitDt";
	public final static String IB_LOGIN_NAME = "edisonadm";
	public final static String IB_PASSWORD = "edison2013!!";
	public final static String DATE_STR_CONNECTOR = "-";
	public final static String DATE_DEFAULT_TIMESTAMP = "00:00:00";
	public static final String appVersionFN = "version";
	public static final String appNameFN = "name";
	public static final String simulationTitleFN = "simulationTitle";
	
	/****
	 * Simulation subject ID list
	 */
	public static final long WaveSimulation = 69601;
	public static final long rollerCoaster = 33901;
	public static final long gravityslingshot = 27901;
	public static final long Bowling = 27701;
	public static final long roundSTMtip = 25701;
	public static final long acuteSTMtip = 25601;
	public static final long PhaseDiagramSW = 33501;
	public static final long pianostring = 28201;
	public static final long gamess = 21301;
	public static final long uChem = 41301;
}
