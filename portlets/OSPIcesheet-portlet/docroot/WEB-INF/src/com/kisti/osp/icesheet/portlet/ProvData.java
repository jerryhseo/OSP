package com.kisti.osp.icesheet.portlet;

public class ProvData {
//	public String 
	public String simulationUuid;
	public String simulationTitle;
	public String simulationCreateDt;
	public String outputDirPath;
	
	public ProvData(){}
	
	public ProvData(
			String simUuid,
			String simTitle,
			String simCreateDt,
			String outputDirPath
			){
		this.simulationUuid = simUuid;
		this.simulationTitle = simTitle;
		this.simulationCreateDt = simCreateDt;
		this.outputDirPath = outputDirPath;
	}
}
