<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ include file="/common/init.jsp"%>

<%@page import="com.liferay.portal.theme.ThemeDisplay" %>

<liferay-portlet:resourceURL var="IBhandlerURL" id="handleIceBraker" copyCurrentRenderParameters="false" escapeXml="false"/>

<liferay-portlet:renderURL var="eturbanalyzerURL" copyCurrentRenderParameters="false" plid="${eturbanalyzerPlid}" portletName="eturbanalyzer_WAR_eturbportlet">
    <liferay-portlet:param name="icebreakerUrl" value="${icebreakerUrl}"/>
</liferay-portlet:renderURL>

<%
String Local_MAC_URL = "http://118.128.153.167:9090/fileDownload";
String frameURL = "/OSPMeshGenerator-Module-portlet/public/viewer.html";

HttpServletRequest r = PortalUtil.getHttpServletRequest(renderRequest);
String paramUrl = PortalUtil.getOriginalServletRequest(r).getParameter("url");
if (paramUrl == null || paramUrl == "") paramUrl = "_.prj";
String url = frameURL + "?serverUrl=" + IBhandlerURL + "&url=" + paramUrl;
String portletId= portletDisplay.getId(); 
%>

<style type="text/css">
div#<portlet:namespace/>viewerDiv{
	height: inherit;
	border: 1px solid #d3d3d3;
}
iframe#<portlet:namespace/>viewerFrame{
	width: 100%;
	height: inherit;
	border: 0;
}
</style>

<input type="hidden" id="eventState" value="idle">
<div id="<portlet:namespace/>viewerDiv"></div>
<script type="text/javascript">
function <portlet:namespace/>setProjectState(param) {
	EventProjectObj.data = param;
	$('#eventState').val('ready');
}

var EventProjectObj = { 'data': null };
//var event = new Event('patchData');
var vcToken = "";
var IceBreakerUrl = "${icebreakerUrl}";
//console.log("IceBreakerUrl : " + IceBreakerUrl);
var EventStack = [];
function <portlet:namespace/>callViewerRequest(param) { 
	if (param && !param.url) {
		param.url = <portlet:namespace/>makeRequestUrl(param.fileId, param.name, param.token);
	}
	if (param && param.token) {
		vcToken = param.token;
	}
	
	$('#<portlet:namespace/>viewerFrame')[0].contentWindow.viewerRequest(param);	
}

function <portlet:namespace/>callViewerRequestIframeLoading(param) {
	if (!param) {
		console.log('[callViewerRequest] param is empty...');
		return;
	}
	param.url = <portlet:namespace/>makeRequestUrl(param.fileId, param.name, param.token);
	
	var viewerObj = $('#<portlet:namespace/>viewerFrame')[0].contentWindow;
	if (!viewerObj.viewerRequest) {
		EventStack.push(param);
		document.getElementById('<portlet:namespace/>viewerFrame').onload = function() {
			//console.dir(param);
			var obj = null;
			if (EventStack && EventStack.length > 0) {
				for(var i = 0 ; i < EventStack.length && i < MAX_LOADING_FILE; i++) {
					try {
						obj = EventStack[i];
						//console.log('call viewerRequest using EventStack ...');
						//console.dir(obj);
						
						this.contentWindow.viewerRequest(obj);	
					} catch (Err) {
						console.error(Err);
					}
				}
				EventStack = [];	
			}
		};
	} else {
		$('#<portlet:namespace/>viewerFrame')[0].contentWindow.viewerRequest(param);
		if (EventStack && EventStack.length > 0) {
			console.err('EventStack is NOT Empty...');
			//console.dir(EventStack);
			EventStack = [];
		}
	}
}

function <portlet:namespace/>makeProjectGroupData(orgNameArr, orgUrlArr, dataArr, token) {
	if (!dataArr) return;
	dataArr = JSON.parse(dataArr);
	console.log('makeProjectGroupData');
	//console.dir(dataArr);
	var url = null, obj = null, fileId = null;
	for(var i = 0 ; i < dataArr.length ; i++) {
		obj = dataArr[i];
		if (obj && obj.name && obj.name.length >= 1) {
			orgNameArr.push(obj.name);
			fileId = obj.fileId;
			if (fileId) {
				url = <portlet:namespace/>makeRequestUrl(fileId, obj.name, token);
				orgUrlArr.push(url);
			}	
		}
	}
}

function <portlet:namespace/>loadProject(param) {
	var result = null;
	if (!param) return result;
	
	var geoUrlArr = [], geoNameArr = [];
	var meshUrlArr = [], meshNameArr = [];
	var token = param.token;
	
	if (token) vcToken = token;
	
	console.log('-------------- start makeProjectJson ------------');
	//console.dir(param);
	<portlet:namespace/>makeProjectGroupData(geoNameArr, geoUrlArr, param.geometryGroup, token);
	<portlet:namespace/>makeProjectGroupData(meshNameArr, meshUrlArr, param.meshGroup, token);
	
	result = {
		"geometryGroup": {
			"entity": [],
			"name": geoNameArr,
			"url": geoUrlArr
		},
		"camera": {
			"position": {
				"z": 0,
				"y": 0,
				"x": 0,
			}
		},
		"meshGroup": {
			"name": meshNameArr,
			"url": meshUrlArr,
		},
	};
	
	console.log('----- result makeProjectJson');
	result = JSON.stringify(result);
	//console.dir(result);
	
	<portlet:namespace/>setProjectState(result);
	$("#<portlet:namespace/>viewerDiv").empty();
	$('<iframe id="<portlet:namespace/>viewerFrame" src="<%= url %>" class="viewerStyle" allowfullscreen></iframe>')
     .appendTo('#<portlet:namespace/>viewerDiv');
}

function <portlet:namespace/>makeRequestUrl(fileId, fileName, token) {
	var currentUrl = window.location.href, url = "";
	
	if (currentUrl.indexOf('192.168.0.4') > -1 || currentUrl.indexOf('localhost') > -1) { // local
		url = "<%= Local_MAC_URL %>" + '?id=' + fileId;
		url += '&icebreakerUrl=' + IceBreakerUrl;
		url += '&vcToken=' + token;
		url += '&reqType=edison';
		url += '&fileName=' + fileName;
	} else { 
		url = IceBreakerUrl + "/api/file/download?id=" + fileId;
		url += '&vcToken=' + token;
		url += '&reqType=edison';
		url += '&fileName=' + fileName;
	}
	
	return url;
}

function uploadBlob(paramObj) {
	var currentUrl = window.location.href;
	var ibUrl = IceBreakerUrl + "/api/file/upload?cluster=EDISON-CFD&name=";
	
	var fd = new FormData();
    fd.append('<portlet:namespace/>reqType', 'UPLOAD_IB');
    fd.append('<portlet:namespace/>fname', paramObj.fname);
    fd.append('<portlet:namespace/>data', paramObj.data);
    fd.append('<portlet:namespace/>vcToken', vcToken);
    fd.append('<portlet:namespace/>ibUrl', ibUrl);
    
	$.ajax({
        type: 'POST',
        url: "<%= IBhandlerURL %>",
        data: fd,
        processData: false,
        contentType: false
    }).done(function(result) {
        //console.log(result);
    });
	return false;
}

function sendEditorPortlet(cmd, json) {
	console.log('--- sendEditorPortlet');
	//console.dir(json);
	//console.log(cmd);
	//console.log(JSON.stringify(json));
	
	if (cmd == 'makeEntitySet') {
		if (json) {
			var errMsg = "";
			if (json.error) {
				errMsg = json.error;
				if (errMsg.indexOf('Unsupported type') > -1) {
					//alert('지원하지 않는 Type입니다.');
					console.log('지원하지 않는 Type입니다.');
				} else if (errMsg.indexOf('Unsupported format') > -1) {
					//alert('지원하지 않는 Format입니다.');
					console.log('지원하지 않는 Format입니다.');
				} else if (errMsg.indexOf('select first') > -1) {
					alert('선택한 Boundary가 없습니다.');
				}
				return false;
			}
			
			var data = {
				"id": json.id,
				"data": json			};
			
			Liferay.fire('eTurb_Dashboard_call', {
				'cmd': "get.bcdata",				// makeEntitySet : add select faces group Info
				'param': data,
			});	
		}
	} else if (cmd == 'loadProject') {
		//console.log('>>>> loadProject : eventState <<<<<< status : ' + $('#eventState').val());
		return EventProjectObj.data;
	} else if (cmd == 'project-loaded') {
		//console.log('>>>> project-loaded');
		Liferay.fire('eTurb_Dashboard_call', {
			cmd: 'block.end',
			param: '',
		});
	}
}
</script>