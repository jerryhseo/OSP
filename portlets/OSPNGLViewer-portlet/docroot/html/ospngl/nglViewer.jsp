<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>NGL - webapp</title>



<!-- NGL -->
<script src="<%=request.getContextPath()%>/build/js/ngl.dev.js"></script>

<!-- UI -->
<script src="<%=request.getContextPath()%>/js/lib/signals.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lib/tether.min.js"></script>
<script src="<%=request.getContextPath()%>/js/lib/colorpicker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.extra.js"></script>
<script src="<%=request.getContextPath()%>/js/ui/ui.ngl.js"></script>
<script src="<%=request.getContextPath()%>/js/gui.js"></script>
<!-- NGL Style -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" />
<link rel="subresource" href="<%=request.getContextPath()%>/css/light.css" />
<link rel="subresource" href="<%=request.getContextPath()%>/css/dark.css" />
</head>

<body>
<%@include file="/html/init.jsp"%>

<!-- NGL style -->
<script src="<%=request.getContextPath()%>/js/plugins.js"></script>


<div id="viewArea">
	<div id="nglViewer" style="width:100%; height:100%;">
	</div>
</div>


<%
	//System.out.println("\n\n\n\n\n\nTest\n");
	//System.out.println(request.getContextPath());
%>
<script>

NGL.cssDirectory="<%= request.getContextPath()%>/css/";
NGL.documentationUrl = "<%=request.getContextPath()%>/build/docs/index.html";
NGL.examplesListUrl = "<%=request.getContextPath()%>/build/scriptsList.json";
NGL.examplesScriptUrl = "<%=request.getContextPath()%>/scripts/";
NGL.exampleDataDirectoryURL = "<%=request.getContextPath()%>/data/";
//Datasources

NGL.DatasourceRegistry.add("data", new NGL.StaticDatasource("<%=request.getContextPath()%>/data/"));

var mdsrv = NGL.getQuery("mdsrv")
if (mdsrv) {
	NGL.DatasourceRegistry.add("file", new NGL.MdsrvDatasource(mdsrv))
	NGL.DatasourceRegistry.listing = NGL.DatasourceRegistry.get("file")
	NGL.DatasourceRegistry.trajectory = NGL.DatasourceRegistry.get("file")
}


//Plugins
NGL.PluginRegistry.add("apbs", "<%=request.getContextPath()%>/plugins/apbs.plugin");
	
var stage;
document.addEventListener("DOMContentLoaded", function(){
	stage = new NGL.Stage();
	var viewElement = document.getElementById('nglViewer');
	NGL.StageWidget(stage, 'nglViewer');


	
	var load = NGL.getQuery("load");
	if(load)
		stage.loadFile(load, {defaultRepresentation: true});
		
	
	var script = NGL.getQuery("script");
	if (script) stage.loadFile("<%=request.getContextPath()%>/scripts/" + script + ".js");
	
	
	var plugin = NGL.getQuery("plugin");
	console.log("test plugin in view");
	console.log(plugin);
	if (plugin) NGL.PluginRegistry.load(plugin, stage);

	
	var struc = NGL.getQuery("struc");
	var traj = NGL.getQuery("traj")
		
	if(struc) {
		stage.loadFile(struc, {defaultRepresentation: true}).then(function(o) {
       		if(traj) o.addTrajectory(traj)});
	}
	
		
	//stage.loadFile("<%= request.getContextPath()%>/data/4opj.pdb", {defaultRepresentation: true});
	
});
</script>
</body>
</html>