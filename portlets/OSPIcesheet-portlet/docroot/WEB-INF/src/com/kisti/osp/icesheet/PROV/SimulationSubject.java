package com.kisti.osp.icesheet.PROV;

/****
 * class for representing an arbitrary simulation subject
 * @author yksuh
 *
 */
public class SimulationSubject {
	public Long subjectId; 
	public String subjectName;
	public String subjectVersion;
	
	public SimulationSubject(){}
	
	public SimulationSubject(
			Long subjId,
			String subjName,
			String subjVersion
			){
		this.subjectId = subjId;
		this.subjectName = subjName;
		this.subjectVersion = subjVersion;
	}
}
