package com.kisti.osp.icesheet.portlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class IceSheetUtil {
	/****
	 * Execute a given command
	 * @param command a given command
	 * @return install message
	 */
	public static String executeCommand(String command) {
		String res = "";
		//String command = zipCommand + " " + givenFile + targetDir;
		String[] commandAndArgs = new String[]{"usr/bin/sh", "-c", command};

		//System.out.println(commandAndArgs);
		// Get Runtime instance.
		Runtime rt = Runtime.getRuntime();
		// Declare a process.
		Process p0 = null;
		try {
			// Let's execute the command.
			p0 = rt.exec(commandAndArgs);
			// Get any input stream.
			InputStream instd = p0.getInputStream();
			// Let's get it through buffered reader.
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			String temp = "";
			// System.out.println("new line executed command: " + command);
			while ((temp = buf_reader.readLine()) != null) {
				// System.out.println("temp: " + temp);
				//res += temp + "\n";
			}
			// Let's close buffered reader
			buf_reader.close();

			// Get any error stream.
			InputStream errstd = p0.getErrorStream();
			// Let's get it through buffered reader.
			BufferedReader buf_err_reader = new BufferedReader(
					new InputStreamReader(errstd));
			// Initialize a temporary variable.
			temp = "";
			// Until there's no more error message,
			while ((temp = buf_err_reader.readLine()) != null) {
				// Append a current error message to the error message
				// container.
				res += temp + "\n";
			}
			// Report an error.
			System.err.println(res);
			// Close buffered error reader.
			buf_err_reader.close();
			// Let's wait p0 for completion.
			p0.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/*****
	 * Obtain hash key based on the new port data format
	 * @param jobData
	 * @return hash key 
	 */
	private static long getHashKeyFromNewPortData(String jobData){
		long hashkey = Constants.DEFAULT_INPUTDATA_HASH_KEY;
		String extractedPortData = extractNewPortData(jobData);
		hashkey = extractedPortData.hashCode();
//		System.out.println("HASH KEY : " + hashkey);
		return hashkey;
	}
	/****
	 * Extract only new port data from jobData
	 * @param jobData
	 * @return
	 */
	private static String extractNewPortData(String jobData) {
		// TODO Auto-generated method stub
		String res ="";
		try{
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( jobData );
			HashMap<String, Object> myMap = (HashMap<String, Object>) JSONObject.toBean(jsonObject, Map.class);
			if(myMap != null && myMap.size() > 0){
				for (Map.Entry<String, Object> entry : myMap.entrySet())
				{
//System.out.println(entry.getKey() + "/" + entry.getValue());
//System.out.println("key:" + entry.getKey());
					Object obj = entry.getValue();
					JSONObject portDataObj = JSONObject.fromObject(obj);
					HashMap<String, Object> portDataMap = (HashMap<String, Object>) JSONObject.toBean(portDataObj, Map.class);
					for (Map.Entry<String, Object> entry2 : portDataMap.entrySet())
					{
						String key = entry2.getKey();//port_data
						if(key.equalsIgnoreCase(Constants.PORT_DATA_ATT_NAME)){
							String portDataVal = (String)entry2.getValue();
							if(!portDataVal.equalsIgnoreCase("")){
								res = portDataVal;
								break;
							}else{
//								throw new Exception("no port data!");
							}
						}
					}
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
	
	/****
	 * Take a given jobData and return only parameter parts
	 * @param jobData
	 * @return parameter information
	 */
	public static LinkedHashMap<String, String> extractInputParamInfoFromNewPortData(long subjectId, String jobData) throws Exception{
		// TODO: extract parameter set string
		LinkedHashMap<String, String> resMap = new LinkedHashMap<String, String>();
		String portDataVal = extractNewPortData(jobData);
		resMap = parseParametersFromNewPortData(subjectId, portDataVal);
		return resMap;
	}
	/*****
	 * Parse input data parameters from a given port_data string
	 * @param portDataVal port data string
	 * @return a list of key value pairs
	 */
	private static LinkedHashMap<String, String> parseParametersFromNewPortData(Long subjectId, String portDataVal){
		LinkedHashMap<String, String> resMap = new LinkedHashMap<String, String>();
		/****
		 * If a given subjectId equals to WaveSimulation, then do the following tokenizing.
		 */
		if(subjectId == Constants.WaveSimulation){
			StringTokenizer st = new StringTokenizer(portDataVal, "\n");
			while(st.hasMoreTokens()){
				String keyValPairStr = st.nextToken();
				StringTokenizer st2 = new StringTokenizer(keyValPairStr, " |,");
				while(st2.hasMoreTokens()){
					String key = (st2.nextToken()).trim();
					String value = (st2.nextToken()).trim();
//System.out.println(key + "=>" + value);
					resMap.put(key, value);
				}
			}
		}
		else if(subjectId == Constants.rollerCoaster){
			// fill how to tokenize inputData for rollerCoaster
		}else if(subjectId == Constants.gravityslingshot){
			// fill how to tokenize inputData for gravityslingshot
			StringTokenizer st = new StringTokenizer(portDataVal, ";\n");
			while(st.hasMoreTokens()){
				String keyValPairStr = st.nextToken();
				StringTokenizer st2 = new StringTokenizer(keyValPairStr, " |=");
				while(st2.hasMoreTokens()){
					String key = (st2.nextToken()).trim();
					String value = (st2.nextToken()).trim();
//System.out.println(key + "=>" + value);
					resMap.put(key, value);
				}
			}
		}else if(subjectId == Constants.Bowling){
			// fill how to tokenize inputData for Bowling
		}else if(subjectId == Constants.roundSTMtip){
			
			// fill how to tokenize inputData for roundSTMtip
		}else if(subjectId == Constants.acuteSTMtip){
			// fill how to tokenize inputData for acuteSTMtip
		}else if(subjectId == Constants.PhaseDiagramSW){
			// fill how to tokenize inputData for PhaseDiagramSW
		}
		else if(subjectId == Constants.gamess){
			// fill how to tokenize inputData for gamess
			
		}else if(subjectId == Constants.uChem){
			// fill how to tokenize inputData for uChem
		}
		return resMap;
	}

	/*****
	 * Generate hash key based on the old port data format
	 * @param jobData
	 * @return hash key
	 */
	private static long getHashKeyFromOldPortData(long subjectId, String jobData){
		long hashkey = Constants.DEFAULT_INPUTDATA_HASH_KEY;
		if(subjectId == Constants.WaveSimulation || subjectId == Constants.rollerCoaster)
		{
			String extractedPortData = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME,jobData);
			hashkey = extractedPortData.hashCode();
		}
		else if(subjectId == Constants.roundSTMtip 
				  || subjectId == Constants.gravityslingshot
				  || subjectId == Constants.Bowling 
				  || subjectId == Constants.PhaseDiagramSW
				  || subjectId == Constants.pianostring
				  || subjectId == Constants.acuteSTMtip) {
		String extractedPortData = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME2,jobData);
		hashkey = extractedPortData.hashCode();
		}
		else if (subjectId == Constants.uChem){
			String extractedPortData = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME2,jobData);
		}
		
		return hashkey;
	}
	
	/*****
	 * Generate hash key based on the old port data format
	 * @param jobData
	 * @return hash key
	 */
	private static long getHashKeyFromNewPortData(long subjectId, String jobData){
		long hashkey = Constants.DEFAULT_INPUTDATA_HASH_KEY;
		if(subjectId == Constants.WaveSimulation 
		|| subjectId == Constants.rollerCoaster 
		|| subjectId == Constants.roundSTMtip 
		  || subjectId == Constants.gravityslingshot
		  || subjectId == Constants.Bowling 
		  || subjectId == Constants.PhaseDiagramSW
		  || subjectId == Constants.pianostring
		  || subjectId == Constants.acuteSTMtip) {
		String extractedPortData = extractNewPortData(jobData);
		hashkey = extractedPortData.hashCode();
		}
		
		return hashkey;
	}
	
	/****
	 * Take a given jobData and return only parameter parts
	 * @param jobData
	 * @return parameter information
	 */
	public static LinkedHashMap<String, String> extractInputParamInfoFromOldPortData(long subjectId, String jobData) throws Exception{
//		// TODO: extract parameter set string
		// TODO: extract parameter set string
		LinkedHashMap<String, String> resMap = new LinkedHashMap<String, String>();
		if(subjectId == Constants.WaveSimulation || subjectId == Constants.rollerCoaster){
			String portDataVal = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME,jobData);
			resMap = parseParametersFromOldPortData(subjectId, portDataVal);
		}
		else if (subjectId == Constants.roundSTMtip 
			  || subjectId == Constants.gravityslingshot
			  || subjectId == Constants.Bowling 
			  || subjectId == Constants.PhaseDiagramSW
			  || subjectId == Constants.pianostring
			  || subjectId == Constants.acuteSTMtip
			  || subjectId == Constants.uChem){
			String portDataVal = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME2,jobData);
			resMap = parseParametersFromOldPortData(subjectId, portDataVal);
		}
		else if(subjectId == Constants.gamess)
		{
			String portDataVal = extractOldPortData(Constants.OLD_PORT_DATA_ATT_NAME3_f,jobData);
			resMap = parseParametersFromOldPortData(subjectId, portDataVal);
		}
		
		//resMap = parseParametersFromOldPortData(subjectId, portDataVal);
		return resMap;
	}

	private static LinkedHashMap<String, String> parseParametersFromOldPortData(long subjectId, String portDataVal) {
//		HashMap<String, String> resMap = new HashMap<String, String>();
		LinkedHashMap<String, String> resMap = new LinkedHashMap<String, String>();
		
		/****
		 * If a given subjectId equals to WaveSimulation, then do the following tokenizing.
		 */
		if(subjectId == Constants.WaveSimulation
		|| subjectId == Constants.rollerCoaster
		|| subjectId == Constants.gravityslingshot
		|| subjectId == Constants.pianostring
		|| subjectId == Constants.PhaseDiagramSW
		|| subjectId == Constants.roundSTMtip
		|| subjectId == Constants.Bowling
		|| subjectId == Constants.acuteSTMtip){
			StringTokenizer st = new StringTokenizer(portDataVal, "\n");
			while(st.hasMoreTokens()){
				String keyValPairStr = st.nextToken();
				StringTokenizer st2 = new StringTokenizer(keyValPairStr, "=|;");
				while(st2.hasMoreTokens()){
					String key = (st2.nextToken()).trim();
					String value = (st2.nextToken()).trim();
//System.out.println(key + "=>" + value);
					if(value == null 
					|| value.equalsIgnoreCase(""))
						value = "0";
					else{
//						if(subjectId == Constants.pianostring && key.){
//							if(value.contains("Indometh")){
//								value = "1";
//							}else{
//								value = "0";
//							}
//						}
						try{
							double d = Double.parseDouble(value);
						}catch(Exception ex){
							if(subjectId == Constants.pianostring){
								if(value.contains("Indometh")){
									value = "1";
								}else{
									value = "0";
								}
							}
						}
					}
					resMap.put(key, value);
				}
			}
		}
//		else if(subjectId == Constants.rollerCoaster){
//			// fill how to tokenize inputData for rollerCoaster
//			StringTokenizer st = new StringTokenizer(portDataVal, "\n");
//			
//		}else if(subjectId == Constants.gravityslingshot){
//			// fill how to tokenize inputData for gravityslingshot
//		}else if(subjectId == Constants.Bowling){
//			// fill how to tokenize inputData for Bowling
//			StringTokenizer st = new StringTokenizer(portDataVal, "\n");
//			while(st.hasMoreTokens()){
//				String keyValPairStr = st.nextToken();
//				StringTokenizer st2 = new StringTokenizer(keyValPairStr, "=|;");
//				while(st2.hasMoreTokens()){
//					String key = (st2.nextToken()).trim();
//					String value = (st2.nextToken()).trim();
//					System.out.println(key + "=>" + value);
//					resMap.put(key, value);
//				}
//			}
//		}else if(subjectId == Constants.roundSTMtip){
//			// fill how to tokenize inputData for roundSTMtip
//			StringTokenizer st = new StringTokenizer(portDataVal, "\n");
//			while(st.hasMoreTokens()){
//				String keyValPairStr = st.nextToken();
//				StringTokenizer st2 = new StringTokenizer(keyValPairStr, "=|;");
//				while(st2.hasMoreTokens()){
//					String key = (st2.nextToken()).trim();
//					String value = (st2.nextToken()).trim();
//					System.out.println(key + "=>" + value);
//					resMap.put(key, value);
//				}
//			}
//		}else if(subjectId == Constants.acuteSTMtip){
//			// fill how to tokenize inputData for acuteSTMtip
//		}else if(subjectId == Constants.PhaseDiagramSW){
//			// fill how to tokenize inputData for PhaseDiagramSW
//		}
//		else if(subjectId == Constants.gamess){
//			// fill how to tokenize inputData for gamess
//		}else if(subjectId == Constants.uChem){
//			// fill how to tokenize inputData for uChem
//		}
		return resMap;
	}
//	Constants.OLD_PORT_DATA_ATT_NAME
	/****
	 * Extract old port data (file-content) from jobData
	 * @param jobData
	 * @return file content 
	 */
	private static String extractOldPortData(String portDataKey, String jobData) {
		String res ="";
		boolean found = false;
		try{
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( jobData );
			HashMap<String, Object> myMap = (HashMap<String, Object>) JSONObject.toBean(jsonObject, Map.class);
			if(myMap != null && myMap.size() > 0){
				for (Map.Entry<String, Object> entry : myMap.entrySet())
				{
//				    System.out.println(entry.getKey() + "/" + entry.getValue());
					if(!found){
						String key = entry.getKey();
//System.out.println("key:" + key);
						if(key.equalsIgnoreCase(portDataKey)){
							Object obj = entry.getValue();
							JSONArray inputDataArray = JSONArray.fromObject(obj);
							for(int i=0;i<inputDataArray.size();i++){
								if(!found){
									JSONObject portDataObj = inputDataArray.getJSONObject(i);
									HashMap<String, Object> portDataMap = (HashMap<String, Object>) JSONObject.toBean(portDataObj, Map.class);
									for (Map.Entry<String, Object> entry2 : portDataMap.entrySet())
									{
										String secondKey = entry2.getKey();//port_data
										if(secondKey.equalsIgnoreCase(Constants.FILE_CONTENT_ATT_NAME)){
											String portDataVal = (String)entry2.getValue();
											if(!portDataVal.equalsIgnoreCase("")){
												res = portDataVal;
												found = true;
												break;
											}else{
//													throw new Exception("no port data!");
											}
										}
									}
								}else{
									break;
								}
							}
						}	
					}else{
						break;
					}
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}
	
	/****
	 * Extract input file data (file-content) from jobData  
	 * @param jobData
	 * @return file content 
	 */
	private static String extractInputFileData(String portDataKey, String jobData) {
		String res ="";
		boolean found = false;
		try{
			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( jobData );
			HashMap<String, Object> myMap = (HashMap<String, Object>) JSONObject.toBean(jsonObject, Map.class);
			if(myMap != null && myMap.size() > 0){
				for (Map.Entry<String, Object> entry : myMap.entrySet())
				{
//				    System.out.println(entry.getKey() + "/" + entry.getValue());
					if(!found){
						String key = entry.getKey();
//System.out.println("key:" + key);
						if(key.equalsIgnoreCase(portDataKey)){
							Object obj = entry.getValue();
							JSONArray inputDataArray = JSONArray.fromObject(obj);
							for(int i=0;i<inputDataArray.size();i++){
								if(!found){
									JSONObject portDataObj = inputDataArray.getJSONObject(i);
									HashMap<String, Object> portDataMap = (HashMap<String, Object>) JSONObject.toBean(portDataObj, Map.class);
									for (Map.Entry<String, Object> entry2 : portDataMap.entrySet())
									{
										String secondKey = entry2.getKey();//port_data
										if(secondKey.equalsIgnoreCase(Constants.FILE_CONTENT_ATT_NAME)){
											String portDataVal = (String)entry2.getValue();
											if(!portDataVal.equalsIgnoreCase("")){
												res = portDataVal;
												found = true;
												break;
											}else{
//													throw new Exception("no port data!");
											}
										}
									}
								}else{
									break;
								}
							}
						}	
					}else{
						break;
					}
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return res;
	}

//	private static String InpextractOldPortData(String jobData) {
//		String res ="";
//		boolean found = false;
//		try{
//			JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( jobData );
//			HashMap<String, Object> myMap = (HashMap<String, Object>) JSONObject.toBean(jsonObject, Map.class);
//			if(myMap != null && myMap.size() > 0){
//				for (Map.Entry<String, Object> entry : myMap.entrySet())
//				{
////				    System.out.println(entry.getKey() + "/" + entry.getValue());
//					if(!found){
//						String key = entry.getKey();
//						System.out.println("key:" + key);
//						if(key.equalsIgnoreCase(Constants.OLD_PORT_DATA_ATT_NAME2)){
//							Object obj = entry.getValue();
//							JSONArray inputDataArray = JSONArray.fromObject(obj);
//							for(int i=0;i<inputDataArray.size();i++){
//								if(!found){
//									JSONObject portDataObj = inputDataArray.getJSONObject(i);
//									HashMap<String, Object> portDataMap = (HashMap<String, Object>) JSONObject.toBean(portDataObj, Map.class);
//									for (Map.Entry<String, Object> entry2 : portDataMap.entrySet())
//									{
//										String secondKey = entry2.getKey();//port_data
//										if(secondKey.equalsIgnoreCase(Constants.FILE_CONTENT_ATT_NAME)){
//											String portDataVal = (String)entry2.getValue();
//											if(!portDataVal.equalsIgnoreCase("")){
//												res = portDataVal;
//												found = true;
//												break;
//											}else{
////													throw new Exception("no port data!");
//											}
//										}
//									}
//								}else{
//									break;
//								}
//							}
//						}	
//					}else{
//						break;
//					}
//				}
//			}
//
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return res;
//	}
	
	public static Long generateInputDataHashKey(Long subjectId, String jobData) throws Exception{
		long hashKey = Constants.DEFAULT_INPUTDATA_HASH_KEY;
				
		// we need to extract 'file-content' value from jobData
		// and then should generate a hash key
		
		// Step 1. Extract the file-content string from jobData.
		// Step 2. Generate hash code for that file-content string
		// Step 3. Return the hash code.
		// TODO: extract parameter set string
		
		if(subjectId == Constants.WaveSimulation){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
//			hashKey = getHashKeyFromNewPortData(jobData);
		}
		else if (subjectId == Constants.rollerCoaster){
			// TODO: add a routine for rollerCoaster
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.gravityslingshot){
			// TODO: add a routine for gravityslingshot
//			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
			hashKey = getHashKeyFromNewPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.roundSTMtip){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.Bowling){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.acuteSTMtip){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.PhaseDiagramSW){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}
		else if (subjectId == Constants.pianostring){
			hashKey = getHashKeyFromOldPortData(subjectId, jobData);
		}

		
		// ....
if(!Constants.PAPER_EXP)
	System.out.println("HASH VALUE: " + hashKey);
		return hashKey;
	}
	
	/****
	 * Get a key of a input file(s)
	 * @param object
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NumberFormatException 
	 * @throws SftpException 
	 */
	public static Long generateInputFileHashKey(Long subjectId, String jobData, String inputPath) throws NumberFormatException, NoSuchAlgorithmException, SftpException{
		long hashKey = Constants.DEFAULT_INPUTFILE_HASH_KEY;
		
		// Step 1. Read an input file (inputFile)
		if(subjectId == Constants.uChem){
			try {
				hashKey = getHashKeyFromInputFileData(subjectId, jobData, inputPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Step 2. Generate a input file key on the read contents from an input file
		
		// Step 3. input file copy 
		
		
		return hashKey;
	}
	
	
	/***
	 * Generate Hashkey Using InputFile 
	 * @param subjectId
	 * @param jobData 
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws NumberFormatException 
	 * @throws SftpException 
	 */
	private static long getHashKeyFromInputFileData(Long subjectId,
			String jobData, String inputPath) throws NumberFormatException,
			NoSuchAlgorithmException, IOException, SftpException {
		long hashkey = Constants.DEFAULT_INPUTFILE_HASH_KEY;
		String inputFileName = "";
		String destPath = "/EDISON/LDAP/DATA/PROVENANCE/input";

		if (jobData.contains("fileName")) {
			int i = jobData.indexOf("fileName");
			int last = jobData.indexOf("}");
			inputFileName = jobData.substring(i + 12, last - 3);
		}

		Session session = null;
		Channel channel = null;

		StringBuilder obj_StringBuilder = new StringBuilder();

		try {
			// create a object
			JSch jsch = new JSch();
			// creating a session object
			session = jsch.getSession("prov", "150.183.247.103", 22002);
			// setting a password
			session.setPassword("prov2017!!");
			// Create a Properties object of Java Util
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// connecting to SFTP server
			session.connect();
			System.out.println("Host connected");
			// creating a channel object for SFTP
			channel = session.openChannel("sftp");
			// connecting channel to SFTP Server
			channel.connect();
			System.out.println("sftp channel opened and connected.");
			ChannelSftp ChannelSftp = (ChannelSftp) channel;
			// Go to particular directory using SftpChannel
			ChannelSftp.cd(inputPath);
			// Print the current path on console
			System.out.println("Path = " + ChannelSftp.pwd());
			InputStream inputStream = ChannelSftp
					.get(inputPath + inputFileName);
			System.out.println("inputstream path :" + inputStream);
			char[] buffer = new char[0x10000];
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			int int_Line = 0;
			do {
				int_Line = reader.read(buffer, 0, buffer.length);
				if (int_Line > 0) {
					System.out.println("BUFFER :" + buffer);

					obj_StringBuilder.append(buffer, 0, int_Line);
				}
			} while (int_Line >= 0);
			reader.close();
			inputStream.close();
			ChannelSftp.exit();
			channel.disconnect();
			session.disconnect();
		}
		catch (JSchException exc) {
			exc.printStackTrace();
		}
//		byte[] content = getFileContents(obj_StringBuilder.toString());
//		hashkey = Long.valueOf(getHashcode(content));
		 hashkey = (obj_StringBuilder.toString()).hashCode();

		return hashkey;
	}
	
	
    /**
     * read file and return byte array
     * 
     * @param filepath
     * @return byte 
     * @throws IOException
     */
    public static byte[] getFileContents(String filepath) throws IOException {
        BufferedInputStream bistream = new BufferedInputStream(
                new FileInputStream(filepath));
 
        byte[] contents = new byte[bistream.available()];
 
        bistream.close();
 
        return contents;
    }
    
    /***
     * make SHA-256 hashvalue
     * 
     */
    public static String getHashcode(byte[] message) throws NoSuchAlgorithmException {
     
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(message);

        byte[] hashbytes = md.digest();

        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < hashbytes.length; i++) {
            sbuilder.append(String.format("%02x", hashbytes[i] & 0xff));
        } 
        return sbuilder.toString();
    }

	/***
	 * copy input file from user's storage into our provenance server 
	 * @param userName
	 * @param inputFileName
	 * 
	 * 1.ssh inoino@edison "cat /filepath/filename" | diff - /filepath/filename (x)
	 * 2.vimdiff scp://inoino@edison//remote_path/remote_filename /local_path/local_filename (x)
	 * 
	 */
	public static String moveInputFileintoProvServer(String userName, String inputFileName){
		
		String userPath = "/EDISON/LDAP/DATA/" + userName + "/repository/" ;
		String filePath = userPath + inputFileName;
		String destPath = "/EDISON/IceSheet/"+"input/";
		
		List<String> cmd = new ArrayList<String>();
		cmd.clear();
		cmd.add("/usr/bin/scp");
	    cmd.add("-r");
	    cmd.add("-P");
	    cmd.add("22002");
	    cmd.add("inoino@edison:~"+filePath);

	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		CommandLine cmdLine = CommandLine.parse(cmd.get(0));
		
		for (int i=1, n=cmd.size() ; i<n ; i++ ) {
	        cmdLine.addArgument(cmd.get(i), false);
	    }
		int exitValue = -1;
		try {
			exitValue = executor.execute(cmdLine);
		} catch (ExecuteException e) {
			System.out.println("chown cmd fail: " + cmd.toString());
		} catch (IOException e) {
			System.out.println("chown command fail, IOException" + cmd.toString());
		}
		cmd.clear();
		
		return userPath;
		
	}
	
	
	
	/***
	 * Copy data from a simulation output on storage into our provenance server
	 * @param userName
	 * @param subjectName
	 * @param subjectVersion
	 * @param simulationuuid
	 * @param jobUuid
	 * @return
	 */
	public static String moveSimOutputDataintoProvServer(String userName, 
											String subjectName, 
											String subjectVersion, 
											String simulationuuid, 
											String jobUuid) throws Exception{
		// TODO Auto-generated method stub
		String outputPath = Constants.DEFAULT_NULL_VALUE;
		String orgOutputPath = "/EDISON/LDAP/DATA/"+subjectName+"/"+subjectVersion+"/"+simulationuuid+"/"+jobUuid;
		String destPath = "/EDISON/LDAP/DATA/PROVENANCE/"+subjectName+"/"+subjectVersion+"/"+simulationuuid+"/"+jobUuid+"/"+ "output"; 
		
		
		String SimDir = "cd ~" + userName + "/" +  simulationuuid + "/"+ jobUuid + "/result";
		
		
		String userDir = "/EDISON/LDAP/DATA/" + userName + "/" +  simulationuuid + "/"+ jobUuid + "/result";
		
		//for job-Result folder
		String jobpath = "jcd "+ jobUuid;
		
		new File(destPath).mkdir();
		
		List<String> cmd = new ArrayList<String>();
		cmd.clear();
	    cmd.add("/usr/bin/scp");
	    cmd.add("-r");
	    cmd.add("-P");
	    cmd.add("22002");
	    cmd.add("inoino@edison:~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result");
	    cmd.add(destPath);	  
		
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		CommandLine cmdLine = CommandLine.parse(cmd.get(0));
		
		for (int i=1, n=cmd.size() ; i<n ; i++ ) {
	        cmdLine.addArgument(cmd.get(i), false);
	    }
		int exitValue = -1;
		try {
			exitValue = executor.execute(cmdLine);
		} catch (ExecuteException e) {
			System.out.println("chown cmd fail: " + cmd.toString());
		} catch (IOException e) {
			System.out.println("chown command fail, IOException" + cmd.toString());
		}
		cmd.clear();
		
		
//		String cp = "scp -c -r -P 22002 inoino@edison:~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result " + destPath;
//		
//		Process proc = Runtime.getRuntime().exec(cp);
//
//        // Read the output
//
//        BufferedReader reader =  
//              new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//        String line = "";
//        while((line = reader.readLine()) != null) {
//            System.out.print(line + "\n");
//        }
//
//        proc.waitFor();   
//		
//		executeCommand(ToString.toStringOf(cp));

		
		
//	    Runtime.getRuntime().exec("cmd /c" + cp);
		
//		CommandLine commandLine = new CommandLine(cp);
//		new DefaultExecutor().execute(CommandLine.parse(cmd));
		
		
//		JSch jsch = new JSch();
//	    Session session = jsch.getSession("majin", "inoino@edison:~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result", 22002);
//	    session.setPassword("majin2016!!");
//	    session.setConfig("StrictHostKeyChecking", "yes");
//	    session.connect();
//	    ChannelSftp channel = null;
//	    channel = (ChannelSftp)session.openChannel("sftp");
//	    channel.connect();
//	        File localFile = new File(destPath);
//	        //If you want you can change the directory using the following line.
//	        channel.cd("inoino@edison:~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result");
//	    channel.put(new FileInputStream(localFile),localFile.getName());
//	        channel.disconnect();
//	    session.disconnect();

//	    cmd.add("/usr/bin/scp");
//	    cmd.add("-r");
//	    cmd.add("-P");
//	    cmd.add("22002");
//	    cmd.add("inoino@edison:~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result");
//	    cmd.add(destPath);	   

		/*		
		String a = new String("/EDISON/IceSheet/WaveSimulation/1.0.0/");
		String b = new String("/EDISON/IceSheet/WaveSimulation/1.0.0/");
		
		
		String trans = new String( File.separator + "EDISON" +  File.separator + "IceSheet" +  File.separator + "WaveSimulation" +
				 File.separator + "1.0.0");
		
		List<String> cmd = new ArrayList<String>();
		cmd.clear();
		cmd.add("/usr/bin/bash");
		cmd.add("-c");
	    cmd.add("ls");
//	    cmd.add("-c");
//	    cmd.add(a);
//	    cmd.add(b);
	    boolean isWindows = System.getProperty("os.name")
	    		  .toLowerCase().startsWith("windows");
	    
	    ProcessBuilder builder = new ProcessBuilder();
	    if (isWindows) {
	        builder.command("cmd.exe", "/c", "dir");
	    } else {
	        builder.command("sh", "-c", "ls");
	    }
	    builder.directory(new File(System.getProperty(trans)));
	    Process process = builder.start();
	    StreamPumper streamGobbler = 
	      new StreamPumper(process.getInputStream(), null);
	    Executors.newSingleThreadExecutor().submit(streamGobbler);
	    int exitCode = process.waitFor();
	    assert exitCode == 0;*/
	    
/*	    
	    
	    String[] cmd = {"/usr/bin/bash", "-c", "-r", a, b};
	    
	  //Build command 
        List<String> commands = new ArrayList<String>();
        commands.add("/usr/bin/bash");
        commands.add("-c");
        commands.add("ls");
        //Add arguments
        commands.add(a);
        System.out.println(commands);

        //Run macro on target
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File(trans));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        //Read output
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;
        while ((line = br.readLine()) != null)
            if (!line.equals(previous)) {
                previous = line;
                out.append(line).append('\n');
                System.out.println(line);
            }

        //Check result
        if (process.waitFor() == 0) {
            System.out.println("Success!");
            System.exit(0);
        }

        //Abnormal termination: Log command parameters and output and throw ExecutionException
        System.err.println(commands);
        System.err.println(out.toString());
        System.exit(1);*/
   
	    
	    
//	    String[] args = new String[]{"/usr/bin/cp", "-c", "-r", "/EDISON/IceSheet/WaveSimulation/1.0.0/1.t", "/EDISON/IceSheet/WaveSimulation/1.0.0/1.0.0/1.t"}; 
//		executeCommand(ToString.toStringOf(cmd));

//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
//		InputStream inputStream = null;
//		DefaultExecutor executor = new DefaultExecutor();
//		executor.setStreamHandler(streamHandler);
//		
//		ProcessBuilder pb = new ProcessBuilder("/usr/bin/bash", "-c", "echo HELLO > myfile.txt");
//		pb.directory();
//		pb.redirectErrorStream(true);
//		Process p = pb.start();
//	
//		p.waitFor();
		
/*		
		//
		ArrayList<Object> list = new ArrayList<>();
		Runtime r = Runtime.getRuntime();
		ProcessBuilder builder = new ProcessBuilder();
		
		try{
			Runtime.getRuntime();
//			builder.command(cmd);
			Process p = r.exec(cmd);
			inputStream = p.getInputStream();
			InputStreamReader inputstreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inputstreamReader);
			String str;
			while((str = br.readLine()) != null)
				list.add(str);
			try{
				p.waitFor();
			}catch(InterruptedException e){
				System.out.println("Process wa interrupted" + e.toString());
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}*/
		
		
		
		
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
//		InputStream inputStream = null;
//		DefaultExecutor executor = new DefaultExecutor();
//		executor.setStreamHandler(streamHandler);
//		Runtime r = Runtime.getRuntime();
//		ProcessBuilder builder = new ProcessBuilder();
//		
//		try{
//			r.getRuntime();
//			builder.command(cmd);
//			Process p = r.exec(cmd.toString());
//			InputStream inputStrem = p.getInputStream();
//			InputStreamReader inputstreamReader = new InputStreamReader(inputStrem);
//			BufferedReader br = new BufferedReader(inputstreamReader);
//			String str;
//			while((str = br.readLine()) != null)
//				list.add(str);
//			try{
//				p.waitFor();
//			}catch(InterruptedException e){
//				System.out.println("Process wa interrupted" + e.toString());
//			}
//		}catch(Exception e){
//			System.out.println(e.toString());
//		}
		
		
		
		
		// Step 1. Figure out what directory contains the results of a given simulation.
		// Step 2. Copy that output directory over to our server.
		// Step 3. Return the new output directory path on our server.
		
		/****
		 *  File location convention
		 */
		// input file       => /EDISON/IceSheet/Subject_Name/Subject_Version/InputFile/ : InputFile => hashkey on the input file contents
		// output directory => /EDISON/IceSheet/Subject_Name/Subject_Version/InputFile/result
		
		return destPath;
	}
	
	
	/***
	 * Copy data from a simulation output on storage into EDISON server
	 * @param userName
	 * @param subjectName
	 * @param subjectVersion
	 * @param simulationuuid
	 * @param jobUuid
	 * @return
	 */
	@SuppressWarnings("null")
	public static HashMap<String, String> copySimOutputDataintoEDISON(String userName, 
											String subjectName, 
											String subjectVersion, 
											String simulationuuid, 
											String jobUuid) throws Exception{
		// TODO Auto-generated method stub
		String outputPath = Constants.DEFAULT_NULL_VALUE;
		String orgOutputPath = "/EDISON/LDAP/DATA/"+subjectName+"/"+subjectVersion+"/"+simulationuuid+"/"+jobUuid;
		String destPath = "/EDISON/LDAP/PROVENANCE/"+subjectName+"/"+subjectVersion+"/"+simulationuuid+"/"+jobUuid+"/"+ "output"; 
		 
		
		String SimDir = "cd ~" + userName + "/" +  simulationuuid + "/"+ jobUuid + "/result";
		
		
		String userDir = "/EDISON/LDAP/DATA/" + userName + "/" +  simulationuuid + "/"+ jobUuid + "/result";
		
		//for job-Result folder
		String jobpath = "jcd "+ jobUuid;
		
		
		mkdirCommand(destPath);
		
		copyCommand("~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result", destPath);

/*		
		List<String> cmd = new ArrayList<String>();
		cmd.clear();
	    cmd.add("/usr/bin/cp");
	    cmd.add("-a");
//using scp option	    
	    //cmd.add("-P");
//scp port	    
	    //cmd.add("22002");
	    cmd.add("~"+userName+"/jobs/"+simulationuuid+"/"+jobUuid+".job/result");
	    cmd.add(destPath);	  
		
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		CommandLine cmdLine = CommandLine.parse(cmd.get(0));
		
		for (int i=1, n=cmd.size() ; i<n ; i++ ) {
	        cmdLine.addArgument(cmd.get(i), false);
	    }
		int exitValue = -1;
		try {
			exitValue = executor.execute(cmdLine);
		} catch (ExecuteException e) {
			System.out.println("chown cmd fail: " + cmd.toString());
		} catch (IOException e) {
			System.out.println("chown command fail, IOException" + cmd.toString());
		}
		cmd.clear();
		
*/
		HashMap<String, String> pathMap = null;
		pathMap.put("simulationuuid", simulationuuid);
		pathMap.put("jobuuid", jobUuid);
		pathMap.put("isExist", "true");
		return pathMap;
	}
	
	
	public static String mkdirCommand(String dirPath) throws IOException
	{
		Session session = null;
		Channel channel = null;

		StringBuilder obj_StringBuilder = new StringBuilder();

		try {
			// create a object
			JSch jsch = new JSch();
			// creating a session object
			session = jsch.getSession("prov", "150.183.247.103", 22002);
			// setting a password
			session.setPassword("prov2017!!");
			// Create a Properties object of Java Util
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// connecting to SFTP server
			session.connect();
			System.out.println("Host connected");
			
		
			// mkdir command  
			String command="mkdir -p ";
			String sudo_pass=null;
			
			
			command += dirPath;
			channel=session.openChannel("exec");
			
	
			//((ChannelExec) channel).setPty(true);
		      // man sudo
		      // -S The -S (stdin) option causes sudo to read the password from the
		      // standard input instead of the terminal device.
		      // -p The -p (prompt) option allows you to override the default
		      // password prompt and use a custom one.
		      ((ChannelExec)channel).setCommand(command);
		      
		      
			// Print the path on console
			
			InputStream inputStream = null;
			try {
				inputStream = channel.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("inputstream path :" + inputStream);
			
			OutputStream out = channel.getOutputStream();
		    ((ChannelExec)channel).setErrStream(System.err);
		    channel.connect();
		    
		    out.write((sudo_pass+"\n").getBytes());
		    out.flush();

		    byte[] tmp=new byte[1024];
		      while(true){
		        while(inputStream.available()>0){
		          int i=inputStream.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		      channel.disconnect();
		      session.disconnect();
		      }
			catch(Exception e){
		      System.out.println(e);
		     } 
		return dirPath;
	}
	
	
	public static String copyCommand(String simjobPath, String destPath) throws IOException
	{
		Session session = null;
		Channel channel = null;

		StringBuilder obj_StringBuilder = new StringBuilder();

		try {
			// create a object
			JSch jsch = new JSch();
			// creating a session object
			session = jsch.getSession("prov", "150.183.247.103", 22002);
			// setting a password
			session.setPassword("prov2017!!");
			// Create a Properties object of Java Util
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// connecting to SFTP server
			session.connect();
			System.out.println("Host connected");
			
		
			// mkdir command  
			String command="cp -a " + simjobPath + " "+ destPath;
			String sudo_pass=null;
			
			channel=session.openChannel("exec");
			
	
			//((ChannelExec) channel).setPty(true);
		      // man sudo
		      // -S The -S (stdin) option causes sudo to read the password from the
		      // standard input instead of the terminal device.
		      // -p The -p (prompt) option allows you to override the default
		      // password prompt and use a custom one.
		      ((ChannelExec)channel).setCommand(command);
		      
		      
			// Print the path on console
			
			InputStream inputStream = null;
			try {
				inputStream = channel.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			System.out.println("inputstream path :" + inputStream);
			
			OutputStream out = channel.getOutputStream();
		    ((ChannelExec)channel).setErrStream(System.err);
		    channel.connect();
		    
		    out.write((sudo_pass+"\n").getBytes());
		    out.flush();

		    byte[] tmp=new byte[1024];
		      while(true){
		        while(inputStream.available()>0){
		          int i=inputStream.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
		      channel.disconnect();
		      session.disconnect();
		      }
			catch(Exception e){
		      System.out.println(e);
		     } 
		return destPath;
	}
	
	
	
	public static String cdCommand(String inputPath, String inputFileName )
	{
		List<String> cmd = new ArrayList<String>();
		cmd.clear();
	    cmd.add("/usr/bin/cd");
	    cmd.add("-r");
	    //cmd.add("-P");
	    cmd.add("22002");
	    cmd.add("inoino@edison:~"+inputPath);
	    cmd.add(inputFileName);	  
		
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);
		CommandLine cmdLine = CommandLine.parse(cmd.get(0));
		
		for (int i=1, n=cmd.size() ; i<n ; i++ ) {
	        cmdLine.addArgument(cmd.get(i), false);
	    }
		int exitValue = -1;
		try {
			exitValue = executor.execute(cmdLine);
		} catch (ExecuteException e) {
			System.out.println("chown cmd fail: " + cmd.toString());
		} catch (IOException e) {
			System.out.println("chown command fail, IOException" + cmd.toString());
		}
		cmd.clear();
		
		return inputPath;
	}
	
	public static String getJobData(String fileName){
		String jobData = "";
		return jobData;
	}

	/*****
	 * Read the entire file content from a given filename
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return sb.toString();
	}

	
	/*****
	 * Print out all the data from user simulation data
	 * @param userSimDataMap
	 */
	public static void readAllSimulations(HashMap<String, HashMap<String, HashMap<String, String>>> userSimDataMap){
		if(userSimDataMap != null && userSimDataMap.size() > 0){
			for (Map.Entry<String,HashMap<String,HashMap<String,String>>> userEntry : userSimDataMap.entrySet())
			{
//			    System.out.println(entry.getKey() + "/" + entry.getValue());
				String userName = userEntry.getKey();
				System.out.print("userName:" + userName);
				HashMap<String,HashMap<String,String>> simDataMap = userEntry.getValue();
				for (Map.Entry<String,HashMap<String,String>> simEntry : simDataMap.entrySet())
				{
					String simUuid = simEntry.getKey();
					System.out.print(", simUuid:" + simUuid);
					HashMap<String,String> jobDataMap = (HashMap<String,String>)simEntry.getValue();
					for (Map.Entry<String,String> jobEntry : jobDataMap.entrySet())
					{
						String jobUuid = jobEntry.getKey();
						String jobData = jobEntry.getValue();
						System.out.print(", jobuuid:" + jobUuid);
						System.out.println(", jobData:" + jobData);
					}
				}
			}
		}
	}

	public static String getProvDocLocation() {
		// TODO Auto-generated method stub
		return null;
	}
}
