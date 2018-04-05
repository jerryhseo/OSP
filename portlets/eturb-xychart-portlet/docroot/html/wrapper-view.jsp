<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="loadFileUrl" id="loadFile" copyCurrentRenderParameters="false"/>
<style>
#p_p_id<portlet:namespace/> {display:none;}
#<portlet:namespace/>canvas {height: 670px; width: 98%; margin-top: 13.2px; border: 1px solid #d3d3d3; overflow: hidden;}
</style>
<div style="min-height: 670px; display: none;" id="<portlet:namespace/>xyPlotter">
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
          $('#p_p_id_' + "<%=portletDisplay.getId()%>" + '_').hide();
          $("#<portlet:namespace/>xyPlotter").hide();
          Liferay.fire("eTurb_Analyzer_call", {cmd: "showAnalyzer"});
      },
      setResultPath: function(resultPath){
          camberFile = {
              <portlet:namespace/>filePath: resultPath + "Camber.dat"
          };
          surProFile = {
              <portlet:namespace/>filePath: resultPath + "SurPro.dat"
          };
      }
    };
})();

function <portlet:namespace/>setResultPath(){
    <portlet:namespace/>xyChart.setResultPath(<portlet:namespace/>resultPath);
}

function <portlet:namespace/>reloadIframe(){
    document.getElementById("<portlet:namespace/>canvas").contentDocument.location.reload(true);
}

<%-- Liferay.on('controlXYPlotter',function(event) {
    var cmd = event.cmd;
    var param = event.param;
    if (cmd == 'showXYPloter') {
        console.log('controlXYPlotter called \n', event);
        <portlet:namespace/>setResultPath();
        <portlet:namespace/>reloadIframe();
        $('#p_p_id_' + "<%=portletDisplay.getId()%>" + '_').show();
        $('#<portlet:namespace/>xyPlotter').show();
        Liferay.fire("eTurb_Analyzer_call", {cmd: "hideAnalyzer"});
    }
});
 --%>
 
Liferay.on('setXYPlotterResultPath', function(event) {
    var cmd = event.cmd;
    var param = event.param;
    if (cmd == 'setResultPath') {
        console.log('setXYPlotterResultPath called \n', event);
        <portlet:namespace/>resultPath = param["resultPath"];
        <portlet:namespace/>setResultPath();
        $('#p_p_id_' + "<%=portletDisplay.getId()%>" + '_').show();
        $('#<portlet:namespace/>xyPlotter').show();
        Liferay.fire("eTurb_Analyzer_call", {cmd: "hideAnalyzer"});
        if(<portlet:namespace/>xyChart.isReadyToDisplay()){
            <portlet:namespace/>reloadIframe();
        }
    }
});
</script>