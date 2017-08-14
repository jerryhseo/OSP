<%@page import="com.kisti.osp.analyzer.portlet.paraview.ProcessReadyCallback"%>
<%@page import="com.kisti.osp.analyzer.portlet.paraview.LauncherHelper"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>

<html>
<body>Starting ParaView server...
</body>
</html>

<%
	String dataDirToUse = request.getSession().getAttribute("dataDirectory").toString();
	String targetURL = request.getSession().getAttribute("launcherURL").toString();
	String fileToLoad = (String)request.getSession().getAttribute("fileToLoad");
	String bodyJSON = "";
	if( fileToLoad == null || fileToLoad.isEmpty()){
		bodyJSON = "{\"application\":\"visualizer-dir\", \"dataDir\":\"" + dataDirToUse + "\"}"; // need to add attribute: "secret": "xxxxx"
		System.out.println("Initial file to load is not provided..."+bodyJSON);
	}
	else{
		System.out.println("Initial file to load: " + fileToLoad); 
		bodyJSON = "{\"application\":\"visualizer\", \"dataDir\":\"" + dataDirToUse + "\",\"fileToLoad\":\"" +fileToLoad  +"\"}"; // need to add attribute: "secret": "xxxxx"
	}
	// For secret on server add in laucnher config
	// "--authKey", "${secret}"
	
	// To preload a file
	// 1) add key with path relative to dataDir (i.e. "fileToLoad": "vtk/myData.vtk" => if dataDir = /Edison/124213 ==> filePath = /Edison/124213/vtk/myData.vtk)
	// 2) Update launcher config with additional argument "--file-to-load", "${fileToLoad}"

	final HttpServletRequest httpRequest = request;
	final JspWriter htmlOutput = out;

	// Make POST request (endpoint, content)
	// => 503: no more resources
	// => 200: Ok go to visualizer
	
	LauncherHelper.startNewProcess(targetURL, bodyJSON,
			new ProcessReadyCallback() {
				public void onResponse(String jsonString) throws IOException {
					// If invalid you get
					// code: 503 { error: { message: "There is no room for you!!!"}}
					// ===> Need to redirect on error page

					// If success then redirect to visualizer
					httpRequest.getSession().setAttribute("config", jsonString);
					htmlOutput.print("<script>window.location.href = window.location.href.replace('start.jsp', 'visualizer.jsp')</script>");
				};
			});
%>

