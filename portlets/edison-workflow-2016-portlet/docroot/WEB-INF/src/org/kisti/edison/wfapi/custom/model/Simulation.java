package org.kisti.edison.wfapi.custom.model;

import java.util.List;
import java.util.Map;

public class Simulation{
    private String title;
    private String description;
    private Long solverId;
    private String clientId;
    private String backend;
    private String pause;
    private List<String> parentNodes;
    private List<String> childNodes;
    private Map<String, List<String>>outPort;
    private Map<String, String> outPortFile;
    private List<Job> jobs;
    public String getPause(){
        return pause;
    }
    public void setPause(String pause){
        this.pause = pause;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Long getSolverId(){
        return solverId;
    }
    public void setSolverId(Long solverId){
        this.solverId = solverId;
    }
    public String getClientId(){
        return clientId;
    }
    public void setClientId(String clientId){
        this.clientId = clientId;
    }
    public String getBackend(){
        return backend;
    }
    public void setBackend(String backend){
        this.backend = backend;
    }
    public List<String> getParentNodes(){
        return parentNodes;
    }
    public void setParentNodes(List<String> parentNodes){
        this.parentNodes = parentNodes;
    }
    public List<String> getChildNodes(){
        return childNodes;
    }
    public void setChildNodes(List<String> childNodes){
        this.childNodes = childNodes;
    }
    public Map<String, List<String>> getOutPort(){
        return outPort;
    }
    public void setOutPort(Map<String, List<String>> outPort){
        this.outPort = outPort;
    }
    public Map<String, String> getOutPortFile(){
        return outPortFile;
    }
    public void setOutPortFile(Map<String, String> outPortFile){
        this.outPortFile = outPortFile;
    }
    public List<Job> getJobs(){
        return jobs;
    }
    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
    }
    
}
