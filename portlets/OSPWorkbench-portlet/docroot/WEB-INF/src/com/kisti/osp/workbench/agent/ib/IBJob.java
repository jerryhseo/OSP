package com.kisti.osp.workbench.agent.ib;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.kisti.edison.util.CustomUtil;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;

public class IBJob {
	protected String type = "SEQUENTIAL";
	protected String category = "";
	protected String title = "";
	protected String description = "";
	protected String solverId = "";
	protected String solverName = "";
	protected String cyberLabId = "";
	protected String classId = "";
	protected String executable = "";
	protected Map<String, String> attributes = null;
	protected String[] dependencies = null;
	protected Map<String, String> commandOptions = null;
	protected String cluster = "";
	protected String simulationUuid = "";
	
	public IBJob(
			String type, 
			String title, 
			String executable, 
			String[] dependencies,
			String cluster, 
			Map<String, String> commandOptions, 
			String category, 
			Map<String, String> attributes, 
			String simulationUuid,
			String description, 
			String solverId, 
			String solverName,
			String cyberLabId, 
			String classId ) {
		super();
		this.type = type;
		this.category = category;
		this.title = title;
		this.description = description;
		this.solverId = solverId;
		this.solverName = solverName;
		this.cyberLabId = cyberLabId;
		this.classId = classId;
		this.executable = executable;
		this.attributes = attributes;
		this.dependencies =dependencies;
		this.cluster = cluster;
		this.commandOptions = commandOptions;
		this.simulationUuid = simulationUuid;
	}
	
	
	public String getSimulationUuid() {
		return simulationUuid;
	}
	public void setSimulationUuid(String simulationUuid) {
		this.simulationUuid = simulationUuid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSolverId() {
		return solverId;
	}
	public void setSolverId(String solverId) {
		this.solverId = solverId;
	}
	public String getSolverName() {
		return solverName;
	}
	public void setSolverName(String solverName) {
		this.solverName = solverName;
	}
	public String getCyberLabId() {
		return cyberLabId;
	}
	public void setCyberLabId(String cyberLabId) {
		this.cyberLabId = cyberLabId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getExecutable() {
		return executable;
	}
	public void setExecutable(String executable) {
		this.executable = executable;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String[] getDependencies() {
		return dependencies;
	}
	public void setDependencies(String[] dependencies) {
		this.dependencies = dependencies; 
	}
	public Map<String, String> getCommandOptions() {
		return this.commandOptions;
	}
	public void setCommandOptions(Map<String, String> commandOptions) {
		this.commandOptions = commandOptions;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	
	public String toXML(){
		StringBuffer xml = new StringBuffer();
		
		xml.append("<job>");
		xml.append("<type>"+this.getType().toUpperCase()+"</type>");
		xml.append("<category>"+this.getCategory().toUpperCase()+"</category>");			
		xml.append("<title>"+this.getTitle()+"</title>");
		xml.append("<description>"+this.getDescription()+"</description>");
		xml.append("<solverId>"+this.getSolverId()+"</solverId>");
		xml.append("<solverName>"+this.getSolverName()+"</solverName>");
		xml.append("<cyberLabId>"+this.getCyberLabId()+"</cyberLabId>");
		xml.append("<classId>"+this.getClassId()+"</classId>");										
		xml.append("<executable>"+this.getExecutable()+"</executable>");
		
		Set<String> keys = null;
		if( !this.getType().equalsIgnoreCase("Sequential") ){
			xml.append("<attributes>");
			keys = this.attributes.keySet();
			for( String key : keys ){
				xml.append("<item key=\""+key+"\" value=\""+this.attributes.get(key)+"\"/>");
			}
			xml.append("</attributes>");
		}
		
		xml.append("<dependencies>");
		xml.append("</dependencies>");
		
		xml.append("<files>");
		
		keys = this.commandOptions.keySet();
		String execution = "<execution>";
		for( String key : keys ){
			System.out.println("File Key: "+key);
			xml.append("<item key=\""+key.replace("-", "")+"\" value=\""+this.commandOptions.get(key)+"\"/>");
			System.out.println("File Item: "+xml.toString());
			execution += " "+key+ " $"+key.replace("-", "");
		}
		execution += "</execution>";
		xml.append("</files>");
		xml.append(execution);
		
		xml.append("	<cluster>"+this.getCluster()+"</cluster>");
		xml.append("</job>");
		
		return xml.toString();
	}
	
	public String toJSON(){
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		json.put("title", this.getTitle());
		json.put("type", this.getType());
		json.put("category", this.getCategory());
		json.put("cluster", this.getCluster());
		json.put("description", this.getDescription());
		json.put("solverId", this.getSolverId());
		json.put("solverName", this.getSolverName());
		json.put("cyberLabId", this.getCyberLabId());
		json.put("classId", this.getClassId());
		json.put("executable", this.getExecutable());
		
		return json.toString();
	}
}
