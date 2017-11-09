package org.kisti.eturb.dbservice.model;

import java.util.Map;

public class AnalyzerJob{
    private String analyzerUuid;
    private String appName;
    private String appVersion;
    private String exeFileName;
    private OutputData logFile;
    private OutputData outputData;
    private OutputData outputViewData;
    private Map<String, Object> inputFileContent;
    private MeshData meshFileContent;
    private String resultPath;
    
    public AnalyzerJob(
        String analyzerUuid, String appName, String appVersion, 
        String exeFileName, OutputData outputData, OutputData outputViewData, String resultPath){
        this.analyzerUuid = analyzerUuid;
        this.appName = appName;
        this.appVersion = appVersion;
        this.exeFileName = exeFileName;
        this.outputData = outputData;
        this.outputViewData = outputViewData;
        this.resultPath = resultPath;
    }
    public void setLogFile(OutputData logFile){
        this.logFile = logFile;
    }
    public Map<String, Object> getInputFileContent(){
        return inputFileContent;
    }
    public void setInputFileContent(Map<String, Object> inputFileContent){
        this.inputFileContent = inputFileContent;
    }
    public void setOutputViewData(OutputData outputViewData){
        this.outputViewData = outputViewData;
    }
    public MeshData getMeshFileContent(){
        return meshFileContent;
    }
    public void setMeshFileContent(MeshData meshFileContent){
        this.meshFileContent = meshFileContent;
    }
    public void setResultPath(String resultPath){
        this.resultPath = resultPath;
    }
    
    public String getAnalyzerUuid(){
        return analyzerUuid;
    }
    public String getAppName(){
        return appName;
    }
    public String getAppVersion(){
        return appVersion;
    }
    public String getExeFileName(){
        return exeFileName;
    }
    public OutputData getOutputData(){
        return outputData;
    }
    public OutputData getLogFile(){
        return logFile;
    }
    
    public OutputData getOutputViewData(){
        return outputViewData;
    }
    public String getResultPath(){
        return resultPath;
    }
    
}
