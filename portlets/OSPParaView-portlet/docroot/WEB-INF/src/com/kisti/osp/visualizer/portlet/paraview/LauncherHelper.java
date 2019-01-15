package com.kisti.osp.visualizer.portlet.paraview;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LauncherHelper {
	public static void startNewProcess(String targetURL, String bodyJSON, ProcessReadyCallback callback) throws IOException {
		HttpURLConnection connection = null;
		try {
			// Create connection
			URL url = new URL(targetURL);
			//System.out.println("Connection URL: "+url.toString());
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length",	Integer.toString(bodyJSON.getBytes().length));
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(bodyJSON);
			wr.close();

			// Get Response
			StringBuilder jsonContent = new StringBuilder();
			int BUFFER_SIZE = 1024 * 1024;
			char[] buffer = new char[BUFFER_SIZE];
			int size = 0;

			InputStreamReader reader = new InputStreamReader(
					connection.getInputStream());
			while ((size = reader.read(buffer, 0, BUFFER_SIZE)) >= 0) {
				jsonContent.append(buffer, 0, size);
			}
			reader.close();
			//System.out.println("Reading finished: ");
			//System.out.println(jsonContent.toString());
			callback.onResponse(jsonContent.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
