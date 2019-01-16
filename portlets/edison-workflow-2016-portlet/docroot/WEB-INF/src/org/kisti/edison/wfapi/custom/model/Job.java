package org.kisti.edison.wfapi.custom.model;

import java.util.List;

public class Job{
    private String title;
    private String description;
    private Long solverId;
    private String solverName;
    private String executable;
    private String type;
    private String execution;
    private String category;
    private String cluster;
    private List<Item> files;
    private List<Item> attributes;
    private List<Item> dependencies;
    public String getCluster(){
        return cluster;
    }
    public void setCluster(String cluster){
        this.cluster = cluster;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
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
    public String getSolverName(){
        return solverName;
    }
    public void setSolverName(String solverName){
        this.solverName = solverName;
    }
    public String getExecutable(){
        return executable;
    }
    public void setExecutable(String executable){
        this.executable = executable;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getExecution(){
        return execution;
    }
    public void setExecution(String execution){
        this.execution = execution;
    }
    public List<Item> getFiles(){
        return files;
    }
    public void setFiles(List<Item> files){
        this.files = files;
    }
    public List<Item> getAttributes(){
        return attributes;
    }
    public void setAttributes(List<Item> attributes){
        this.attributes = attributes;
    }
    public List<Item> getDependencies(){
        return dependencies;
    }
    public void setDependencies(List<Item> dependencies){
        this.dependencies = dependencies;
    }
}
