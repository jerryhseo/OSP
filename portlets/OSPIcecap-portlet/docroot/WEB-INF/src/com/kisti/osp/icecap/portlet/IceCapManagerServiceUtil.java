package com.kisti.osp.icecap.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IceCapManagerServiceUtil {
	/****
	 * Create a directory
	 * @param givenPath a given path
	 * @return install message
	 */
	public static String makeDir(String givenPath) {
		String res = "";
		String command = "sudo mkdir -p " + givenPath;
System.out.println(givenPath);
		String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
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
		
		// allow for write
		executeCommand("sudo chmod 777 " + givenPath);
		return res;
	}
	
	/****
	 * Execute a given command
	 * @param command a given command
	 * @return install message
	 */
	public static String executeCommand(String command) {
		String res = "";
		//String command = zipCommand + " " + givenFile + targetDir;
		String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
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

	/****
	 * Save a data type sample file to a designated path
	 * @param reqDataTypeName requested data type name
	 * @param reqDataVersion requested data version
	 * @param uploadedSampleFileName uploaded sample file name
	 * @param uploadedSampleFilePath uploaded sample file path 
	 * @return sample file path 
	 */
	public static String saveDataTypeSampleFile(String reqDataTypeName, String reqDataVersion, String uploadedSampleFileName, String uploadedSampleFilePath) {
		// TODO Auto-generated method stub
		String dataTypeSamplePath = "";
		
		String dataTypeSampleFileName = "";
System.out.println("file name: " + uploadedSampleFileName);
		StringTokenizer st = new StringTokenizer(uploadedSampleFileName, ".");
		int tokenCnt = 0;
		String extension = "";
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			tokenCnt++;
			if(tokenCnt == 2){
				extension = token;
			}
		}
		
		if(extension == null) extension = "";
		System.out.println("file path: " + dataTypeSampleFileName);
		
		// set sample data type file name
		dataTypeSampleFileName = Constants.SAMPLE_DATA_TYPE_FILE_NAME + "." + extension;
		// set sample data path 
		/***
		 * Destination path = "/EDISON/DATA_TYPE/type_name/version/XXX.yyy"
		 */
		String dataTypeSampleFileDir = Constants.EDISON_ROOT_PATH+Constants.EDISON_DATA_TYPE_PATH + reqDataTypeName + "/" + reqDataVersion + "/";
		// make a directory unless created
		IceCapManagerServiceUtil.makeDir(dataTypeSampleFileDir);
		// set sample file path 
		dataTypeSamplePath = dataTypeSampleFileDir + dataTypeSampleFileName;
		// make a mv command
		String mvCommand = "mv " + uploadedSampleFilePath + " " + dataTypeSamplePath;
System.out.println(mvCommand);
		IceCapManagerServiceUtil.executeCommand(mvCommand);
		
		return dataTypeSamplePath;
	}
}
