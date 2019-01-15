<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@ page import="com.kisti.osp.visualizer.portlet.paraview.LauncherHelper"%>
<%@ page
	import="com.kisti.osp.visualizer.portlet.paraview.ProcessReadyCallback"%>

<html style="height:100%">
<body style="margin:0px; height:100%">Starting ParaView server...
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

	final HttpServletRequest httpRequest = request;
	final JspWriter htmlOutput = out;

	LauncherHelper.startNewProcess(targetURL, bodyJSON,
			new ProcessReadyCallback() {
				public void onResponse(String jsonString) throws IOException {
					// Check if response is a valid config
					// TODO

					// If success redirect to visualizer
					httpRequest.getSession().setAttribute("config",	jsonString);
					htmlOutput.print("<script>window.location.href = window.location.href.replace('start.jsp', 'visualizer.jsp')</script>");

					// Else redirect to error page leting the user know that all the resources are taken
				};
			});
%>