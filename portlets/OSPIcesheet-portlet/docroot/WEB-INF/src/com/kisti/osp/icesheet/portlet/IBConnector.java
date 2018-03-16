package com.kisti.osp.icesheet.portlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class IBConnector {
	
	/*****
	 * Get login token
	 * @param loginName login name
	 * @param password password
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws IOException
	 */
	public static String getLoginToken(String loginName, String password) throws UnsupportedOperationException, IOException{
		String token = "";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://www.edison.re.kr/ldap/api/user/login");
//		List nameValuePairs = new ArrayList(2);
//		nameValuePairs.add(new BasicNameValuePair("login", loginName)); //you can as many name value pair as you want in the list.
//		nameValuePairs.add(new BasicNameValuePair("password", password)); //you can as many name value pair as you want in the list.
//		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>"); 
		sb.append("<login><userId>"+loginName+"</userId>");
		sb.append("<password>"+password+"</password></login>");
		String loginString = sb.toString();
//System.out.println(loginString);
		StringEntity input = new StringEntity(loginString);
		input.setContentType("application/xml");
		post.setEntity(input);
		post.setHeader("Accept", "application/xml");
		post.setHeader("Content-Type", "application/xml");
		HttpResponse response = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
//			System.out.println(line);
			if(line.startsWith("Token:")){
				int index = 0;
				StringTokenizer st = new StringTokenizer(line,":|\n|");
				while(st.hasMoreTokens()){
					String temp = st.nextToken();
					temp = temp.trim();
					index++;
					if(index == 2){
						token = temp;
						break;
					}
				}
			}
			if(token.length() > 0) break;
		}
		return token;
	}
	
	/****
	 * Get file information associated with a given input file id
	 * @param token authentication token
	 * @param fileId a given file id
	 * @return file contents
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getFileInfo(String token, String fileId) throws ClientProtocolException, IOException {
		String line = "";
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("https://www.edison.re.kr/ldap/api/file/" + fileId);
//		List nameValuePairs = new ArrayList(2);
//		nameValuePairs.add(new BasicNameValuePair("login", loginName)); //you can as many name value pair as you want in the list.
//		nameValuePairs.add(new BasicNameValuePair("password", password)); //you can as many name value pair as you want in the list.
//		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>"); 
//		sb.append("<login><userId>"+loginName+"</userId>");
//		sb.append("<password>"+password+"</password></login>");
//		String loginString = sb.toString();
//System.out.println(loginString);
//		StringEntity input = new StringEntity(loginString);
//		input.setContentType("application/xml");
//		post.setEntity(input);
//		post.setHeader("Accept", "application/xml");
//		post.setHeader("Content-Type", "application/xml");
		
		get.setHeader("Accept", "application/json");
		get.setHeader("Content-Type", "application/json");
		get.setHeader("Authorization","Basic "+token);
		
		HttpResponse response = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((line += rd.readLine()) != null) {
//			System.out.println(line);
//			if(line.startsWith("Token:")){
//				int index = 0;
//				StringTokenizer st = new StringTokenizer(line,":|\n|");
//				while(st.hasMoreTokens()){
//					String temp = st.nextToken();
//					temp = temp.trim();
//					index++;
//					if(index == 2){
//						token = temp;
//						break;
//					}
//				}
//			}
//			if(token.length() > 0) break;
		}
		
//		HttpGet get = new HttpGet("http://www.edison.re.kr/ldap/api/file/" + fileId);
//		// StringEntity input = new StringEntity('product');
//		// post.setEntity(input);
//		HttpResponse response = client.execute(get);
//		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//		String line = "";
//		while ((line = rd.readLine()) != null) {
//			System.out.println(line);
//		}
		return line;
	}
}
