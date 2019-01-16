package org.kisti.edison.wfapi.custom.model;

import java.util.List;

public class Workflow{
    private String title;
    private String userId;
    private String accessToken;
    private String refUuid = "0";
    private String reUseWorkflowUuid;
    private List<Simulation> simulations;
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getAccessToken(){
        return accessToken;
    }
    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getRefUuid(){
        return refUuid;
    }
    public void setRefUuid(String refUuid){
        this.refUuid = refUuid;
    }
    public String getReUseWorkflowUuid(){
        return reUseWorkflowUuid;
    }
    public void setReUseWorkflowUuid(String reUseWorkflowUuid){
        this.reUseWorkflowUuid = reUseWorkflowUuid;
    }
    public List<Simulation> getSimulations(){
        return simulations;
    }
    public void setSimulations(List<Simulation> simulations){
        this.simulations = simulations;
    }
}
