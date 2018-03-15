package com.kisti.osp.icesheet.PROV;

import com.kisti.osp.icesheet.portlet.Constants;

public class PROVDocManager {
	/****
	 * Get a specific json location of this simulation provenance
	 * @param simulationUuid
	 * @return
	 */
	public static String getProvLoc(String simulationUuid) throws Exception {
		// TODO Auto-generated method stub
		String jsonFileName = locateProvLoc(simulationUuid);
		return jsonFileName;
	}

	/****
	 * TODO: fill to function
	 */
	/*****
	 * Locate a specific json file location associated with simulationuuid
	 * @param simulationUuid
	 * @return
	 */
	private static String locateProvLoc(String simulationUuid) throws Exception {
		// Jin: please invoke your method to return the location a json file matching simulationUuid
		
		String fileName = simulationUuid + Constants.JSON_FILE_EXTENSION;
		//		String pathName = "/EDISON/" + simRec.scienceAppNameVal + "/" + simRec.scienceAppVersionVal + "/" + fileName;
		
		return fileName; 
	}

}
