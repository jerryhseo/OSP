package org.kisti.eturb.editor.portlet.dashboard.helper;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kisti.edison.science.NoSuchScienceAppException;
import org.kisti.edison.science.model.ScienceApp;
import org.kisti.edison.science.service.ScienceAppLocalServiceUtil;
import org.kisti.edison.util.EdisonPropsUtil;
import org.kisti.eturb.dbservice.model.AnalyzerJob;
import org.kisti.eturb.dbservice.model.OutputData;
import org.kisti.eturb.dbservice.service.SimulationLocalServiceUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.kisti.osp.util.OSPFileUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Component
public class EturbAppHelper{
    
    private static Log log = LogFactory.getLog(EturbAppHelper.class);
    
    private static final String FILESYSTEM_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String EXECUTE_BASE_PATH = "/EDISON/eTURB";
    private static final String BIN_PATH = "bin";
    private static final String ANALYZER_DEFAULT_INPUT_NAME = "-inp";
    private static final String ANALYZER_DEFAULT_OUTPUT_NAME = "-out";
    private static final String ANALYZER_DEFAULT_VIEW_NAME = "-view";
    private static final String ANALYZER_DEFAULT_LOG_NAME = "-log";
    
    private static final String SYMBOLIC_APP_BASE_PATH = "/EDISON/eTURB";
    private static final String SYMBOLIC_BIN_PATH = "edison";
    private static final String SYMBOLIC_APP_EXECUTE_SCRIPT="direct-script";
    
    
    public boolean exeAnalyzer(long projectId, String inputFileName, String fileId, String fileContent,
        ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user) throws Exception{
        return exeAnalyzer(projectId, inputFileName, fileId, fileContent, false, themeDisplay, analyzerJob, user);
    }
    
    public boolean exeSymbolicAnalyzer(long projectId, String inputFileName, String fileId, String fileContent,
        ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user) throws Exception{
        return exeAnalyzer(projectId, inputFileName, fileId, fileContent, true, themeDisplay, analyzerJob, user);
    }
    /**
     * APP Direct 실행
     * @param projectId
     * @param inputFileName
     * @param fileId      - IB File ID
     * @param fileContent - File 내용 (File 내용의 값이 존재 할 경우 파일 생성 후 실행)
     * @param isSymbolic  - APP 실행 시 symbolic link에 의한 실행 여부
     * @param themeDisplay
     * @param analyzerJob
     * @param user
     * @return
     * @throws Exception
     */
    private boolean exeAnalyzer(long projectId, String inputFileName, String fileId, String fileContent,
        boolean isSymbolic, ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user) throws Exception{
    	Path appPath = null;
    	Path exeFile = null;
    	
    	if(isSymbolic){
    		appPath = getSymbolicAppPath(themeDisplay, analyzerJob);
            exeFile = appPath.resolve(Paths.get(SYMBOLIC_BIN_PATH, SYMBOLIC_APP_EXECUTE_SCRIPT));
    	}else{
    		appPath = getAppPath(themeDisplay, analyzerJob);
            exeFile = appPath.resolve(Paths.get(BIN_PATH, analyzerJob.getExeFileName()));
    	}
    	
        Path executePath = getAppExecutePath(themeDisplay, analyzerJob);
        Path workingPath = executePath.resolve(Paths.get(user.getScreenName(), analyzerJob.getAnalyzerUuid()));
        
        Path inputFilePath = workingPath.resolve(inputFileName);
        Path resultPath = workingPath.resolve(analyzerJob.getOutputData().getParent_());
        
        if(!StringUtils.hasText(fileContent)){
        	SimulationLocalServiceUtil.simulationWithIBInputFile(themeDisplay, projectId, analyzerJob, inputFilePath, inputFileName, fileId);
        }else{
            log.info("create-mesh-data content");
            log.info(fileContent);
        	SimulationLocalServiceUtil.simulationWithInputFile(projectId, analyzerJob, fileContent, inputFilePath);
        }
        
        doAnalyzer(exeFile, inputFilePath, resultPath);
        
        return true;
    }
    
    public boolean exeKflowMeshAnalyzer(long projectId,String datFilePath, String sdeFilePath, ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user) throws Exception{
        	Path appPath = null;
        	Path exeFile = null;
        	
        	appPath = getAppPath(themeDisplay, analyzerJob);
            exeFile = appPath.resolve(Paths.get(BIN_PATH, analyzerJob.getExeFileName()));
        	
            Path executePath = getAppExecutePath(themeDisplay, analyzerJob);
            Path workingPath = executePath.resolve(Paths.get(user.getScreenName(), analyzerJob.getAnalyzerUuid()));
            Path resultPath = workingPath.resolve(analyzerJob.getOutputData().getParent_());
            
            String resultPathString = resultPath.toString();
            resultPathString = resultPathString.endsWith(FILESYSTEM_SEPARATOR) ? resultPathString
                    : resultPathString + FILESYSTEM_SEPARATOR;
                String[] command = new String[]{
                    exeFile.toString(), "-inp", sdeFilePath, 
                    "-grd", datFilePath,
                    ANALYZER_DEFAULT_OUTPUT_NAME, resultPathString};
                
                for(String cmd : command){
                    log.info(cmd);
                }
                ProcessBuilder builder = new ProcessBuilder(command);
                builder.redirectOutput(Redirect.INHERIT);
                builder.redirectError(Redirect.INHERIT);
                builder.start();
                
            return true;
        }
    
    public String createInputFiles(long projectId,List<HashMap<String, String>> fileList,ThemeDisplay themeDisplay,AnalyzerJob analyzerJob, User user) throws SystemException{
    	
    	Path executePath = getAppExecutePath(themeDisplay, analyzerJob);
        Path workingPath = executePath.resolve(Paths.get(user.getScreenName(), analyzerJob.getAnalyzerUuid()));
        
        SimulationLocalServiceUtil.simulationWithInputFiles(projectId, analyzerJob, fileList, workingPath);
        
        return workingPath.toString();
    }
    
    private void doAnalyzer(Path exeFile, Path inputFile, Path resultPath) throws IOException{
        String resultPathString = resultPath.toString();
        resultPathString = resultPathString.endsWith(FILESYSTEM_SEPARATOR) ? resultPathString
            : resultPathString + FILESYSTEM_SEPARATOR;
        String[] command = new String[]{
            exeFile.toString(), ANALYZER_DEFAULT_INPUT_NAME, inputFile.toString(), 
            ANALYZER_DEFAULT_OUTPUT_NAME, resultPathString};
        for(String cmd : command){
            log.info(cmd);
        }
        ProcessBuilder builder = new ProcessBuilder(command);
        builder.redirectOutput(Redirect.INHERIT);
        builder.redirectError(Redirect.INHERIT);
        builder.start();
    }
    
    public Path getAppPath(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob) throws SystemException{
        return Paths.get(
            PrefsPropsUtil.getString(themeDisplay.getCompanyId(), EdisonPropsUtil.SCIENCEAPP_BASE_PATH),
            analyzerJob.getAppName(), analyzerJob.getAppVersion());
    }
    
    public Path getSymbolicAppPath(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob) throws SystemException{
        return Paths.get(SYMBOLIC_APP_BASE_PATH,analyzerJob.getAppName(), analyzerJob.getAppVersion());
    }
    
    public Path getAppExecutePath(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob) throws SystemException{
        return Paths.get(
        	EXECUTE_BASE_PATH,
            analyzerJob.getAppName(), analyzerJob.getAppVersion());
    }

    public String getTimeLog(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user)
        throws SystemException, IOException{
        Path path = Paths.get(
        	EXECUTE_BASE_PATH,
            analyzerJob.getAppName(), analyzerJob.getAppVersion(), user.getScreenName(),
            analyzerJob.getAnalyzerUuid(), analyzerJob.getLogFile().getParent_(), analyzerJob.getLogFile().getName_());
        
        return readTextFile(path);
    }
    
    public String getOutText(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user)
        throws SystemException, IOException{
        Path path = Paths.get(
        	EXECUTE_BASE_PATH,
            analyzerJob.getAppName(), analyzerJob.getAppVersion(),user.getScreenName(),
            analyzerJob.getAnalyzerUuid(), analyzerJob.getOutputData().getParent_(), analyzerJob.getOutputData().getName_());
        
        return readTextFile(path);
    }
    
    public String getViewText(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob, User user)
            throws SystemException, IOException{
            Path path = Paths.get(
            	EXECUTE_BASE_PATH,
                analyzerJob.getAppName(), analyzerJob.getAppVersion(),user.getScreenName(),
                analyzerJob.getAnalyzerUuid(), analyzerJob.getOutputViewData().getParent_(), analyzerJob.getOutputViewData().getName_());
            
            return readTextFile(path);
        }
    
    protected String readTextFile(Path path) throws IOException {
		return OSPFileUtil.readTextFile(path);
	}
    
    public AnalyzerJob prepareAnalyzer(String appName, String appVersion, String userScreenName)
        throws SystemException, NoSuchScienceAppException{
        UUID analyzerUuid = UUID.randomUUID();
        // TODO: request to FILE API with dataFileId, analyzerUuid.toString()
        return prepareAnalyzer(appName, appVersion, analyzerUuid.toString(), userScreenName);
    }
    
    public AnalyzerJob prepareAnalyzer(String appName, String appVersion, String analyzerUuid, String userScreenName)
        throws SystemException, NoSuchScienceAppException{
        if(!StringUtils.hasText(analyzerUuid)){
            analyzerUuid = UUID.randomUUID().toString();
        }
        ScienceApp analyzer = ScienceAppLocalServiceUtil.getScienceApp(
            appName, appVersion);
        String exeFileName = analyzer.getExeFileName();
        String analyzerOutput = ScienceAppLocalServiceUtil.getScienceAppOutputPorts(analyzer.getScienceAppId());
        OutputData outputDataJson = new Gson().fromJson(
            getOutputData(ANALYZER_DEFAULT_OUTPUT_NAME, analyzerOutput), OutputData.class);
        
        OutputData outputViewDataJson = new Gson().fromJson(
                getOutputData(ANALYZER_DEFAULT_VIEW_NAME, analyzerOutput), OutputData.class);
        
        
        Path executePath =  Paths.get(EXECUTE_BASE_PATH, appName, appVersion, userScreenName, analyzerUuid,outputDataJson.getParent_());
        		
        AnalyzerJob analyzerJob = new AnalyzerJob(
            analyzerUuid.toString(), appName,
            appVersion, exeFileName, outputDataJson, outputViewDataJson, executePath.toAbsolutePath().toString()+File.separator);
        String analyzerLog = ScienceAppLocalServiceUtil.getScienceAppLogPorts(analyzer.getScienceAppId());
        
        if(StringUtils.hasText(analyzerLog)){
        	if(!analyzerLog.equals("false")){
        		OutputData logFileJson = new Gson().fromJson(
        				getOutputData(ANALYZER_DEFAULT_LOG_NAME, analyzerLog), OutputData.class);
        		analyzerJob.setLogFile(logFileJson);
        	}
        }
        
        
        return analyzerJob;
    }
    
    public String getOutputData(String portName, String analyzerOutput){
        JSONObject outputPorts = (JSONObject) JSONSerializer.toJSON(analyzerOutput);
        try{
        	return outputPorts.getJSONObject(portName).getString("outputData_");
        }catch (JSONException e) {
        	return "";
		}
    }
    
    public void removeRemoteFilePath(ThemeDisplay themeDisplay, AnalyzerJob analyzerJob,User user) throws SystemException, IOException{
    	Path executePath = getAppExecutePath(themeDisplay, analyzerJob);
    	Path workingPath = executePath.resolve(Paths.get(user.getScreenName(), analyzerJob.getAnalyzerUuid()));
    	
    	FileUtils.cleanDirectory(workingPath.toAbsolutePath().toFile());
    	workingPath.toAbsolutePath().toFile().delete();
    }
    
}
