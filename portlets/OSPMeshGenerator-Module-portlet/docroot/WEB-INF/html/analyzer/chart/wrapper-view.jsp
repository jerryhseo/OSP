<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="loadFileUrl" id="loadFile" copyCurrentRenderParameters="false"/>

<link type="text/css" rel="stylesheet" href="${contextPath}/css/analyzer/chart/main.css" media="screen"/>

<style type="text/css">
#<portlet:namespace/>canvas {overflow: hidden;}

/* .eturb-xy-chart-portlet{width:inherit; border:none; height: 100%;} */
/* .eturb-xy-chart-portlet .menu-section{height: 7%;} */
/* .eturb-xy-chart-portlet .canvas{ vertical-align:middle; width:inherit; border:none; height: 92%;} */
</style>


<div style="height: inherit; display: none;" id="<portlet:namespace/>xyPlotter">
  <div class="eturb-xy-chart-portlet common-analyzer-portlet" id="<portlet:namespace/>ground">
    <div class="span12" style="height: inherit;">
      <div class="row-fluid canvas-wrapper" id="<portlet:namespace/>canvasPanel">
        <iframe class="span12 canvas" id="<portlet:namespace/>canvas"
          src="<%=request.getContextPath()%>/html/xy-chart/xy-chart.jsp?type=chart1"></iframe>
      </div>
    </div>
  </div>
</div>
<script>
var xyChartNamespace = "<portlet:namespace/>";
var <portlet:namespace/>resultPath = "";
var <portlet:namespace/>xyChart = (function(){
    var loadFileUrl = "${loadFileUrl}";
    var camberFile = "";
    var surProFile = "";
    var connector = "";
    return {
      getLoadFileUrl: function(){
          return loadFileUrl; 
      },
      getCamberFile: function(){
          return camberFile;
      },
      getSurProFile: function(){
          return surProFile;
      },
      isReadyToLoad: function(){
          return (<portlet:namespace/>resultPath && !/^\s*$/.test(<portlet:namespace/>resultPath)); 
      },
      isReadyToDisplay: function(){
          return $("#<portlet:namespace/>xyPlotter").is(":visible");
      },
      closeXYPloter: function(){
    	<portlet:namespace/>fireClose();
      },
      setResultPath: function(resultPath){
          camberFile = {
              <portlet:namespace/>filePath: resultPath + "Camber.dat"
          };
          surProFile = {
              <portlet:namespace/>filePath: resultPath + "SurPro.dat"
          };
      },
      getConnector:function(){
    	  return connector;
      },
      setConnector:function(portletId){
    	  connector = portletId;
      }
    };
})();

function <portlet:namespace/>setResultPath(){
    <portlet:namespace/>xyChart.setResultPath(<portlet:namespace/>resultPath);
}

function <portlet:namespace/>setConnector(portletId){
    <portlet:namespace/>xyChart.setConnector(portletId);
}

function <portlet:namespace/>reloadIframe(){
    document.getElementById("<portlet:namespace/>canvas").contentDocument.location.reload(true);
}

function <portlet:namespace/>fireClose(){
	var myId = '<%=portletDisplay.getId()%>';
	var cmd = "close.chart"
	var eventData = {
			portletId : myId,
			targetPortlet : <portlet:namespace/>xyChart.getConnector(),
			command : cmd
		};
	Liferay.fire(OSP.Event.OSP_FROM_ANALYZER_EVENT, eventData);
}

 
Liferay.on(OSP.Event.OSP_FROM_EDITOR_EVENT, function(e) {
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		var command = e.command;
		console.log('OSP_FROM_EDITOR_EVENT: ['+e.portletId+', '+myId+', '+command+', '+new Date()+']', e.data );
		if (command == 'set.path') {
			<portlet:namespace/>resultPath = e.data.resultPath;
			<portlet:namespace/>setResultPath();
			<portlet:namespace/>setConnector(e.portletId);
			$('#<portlet:namespace/>xyPlotter').show();
			if(<portlet:namespace/>xyChart.isReadyToDisplay()){
				<portlet:namespace/>reloadIframe();
			}
		}
	}
});
</script>