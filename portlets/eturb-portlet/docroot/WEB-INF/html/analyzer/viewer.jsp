<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ include file="/common/init.jsp"%>

<%@page import="com.liferay.portal.theme.ThemeDisplay" %>

<liferay-portlet:resourceURL var="IBhandlerURL" id="handleIceBraker" copyCurrentRenderParameters="false" escapeXml="false"/>

<%
String DEV_IB_URL = "http://150.183.247.210:8080/ldap/";
String REAL_IB_URL = "http://www.edison.re.kr/ldap";
String Local_MAC_URL = "http://118.128.153.167:9090/fileDownload";

String frameURL = "/eturb-portlet/public/viewer.html";

HttpServletRequest r = PortalUtil.getHttpServletRequest(renderRequest);
String paramUrl = PortalUtil.getOriginalServletRequest(r).getParameter("url");
if (paramUrl == null || paramUrl == "") paramUrl = "_.prj";
String url = frameURL + "?serverUrl=" + IBhandlerURL + "&url=" + paramUrl;
String portletId= portletDisplay.getId();
%>
<style>
.analyzerIFrame {
    width: 100%;
}
.hideDivClass {
	display:none;
}
</style>

<input type="hidden" id="eventState" value="idle">
<div id="viewerDiv"></div>
<script>
function <portlet:namespace/>setProjectState(param) {
	EventProjectObj.data = param;
	$('#eventState').val('ready');
}

var EventProjectObj = { 'data': null };
//var event = new Event('patchData');
var vcToken = "";

$(document).on('ready',function(){
	var EventStack = [];
	
	function <portlet:namespace/>callViewerRequest(param) { 
		if (param && !param.url) {
			param.url = <portlet:namespace/>makeRequestUrl(param.fileId, param.name, param.token);
		}
		if (param && param.token) {
			console.log('[callViewerRequest] vcToken : ' + param.token);
			vcToken = param.token;
		}
		
		$('#viewerFrame')[0].contentWindow.viewerRequest(param);	
	}
	
	function <portlet:namespace/>callViewerRequestIframeLoading(param) {
		if (!param) {
			console.log('[callViewerRequest] param is empty...');
			return;
		}
		param.url = <portlet:namespace/>makeRequestUrl(param.fileId, param.name, param.token);
		
		var viewerObj = $('#viewerFrame')[0].contentWindow;
		if (!viewerObj.viewerRequest) {
			EventStack.push(param);
			document.getElementById('viewerFrame').onload = function() {
				//console.dir(param);
				var obj = null;
				if (EventStack && EventStack.length > 0) {
					for(var i = 0 ; i < EventStack.length && i < MAX_LOADING_FILE; i++) {
						try {
							obj = EventStack[i];
							console.log('call viewerRequest using EventStack ...');
							console.dir(obj);
							
							this.contentWindow.viewerRequest(obj);	
						} catch (Err) {
							console.error(Err);
						}
					}
					EventStack = [];	
				}
			};
		} else {
			$('#viewerFrame')[0].contentWindow.viewerRequest(param);
			if (EventStack && EventStack.length > 0) {
				console.err('EventStack is NOT Empty...');
				console.dir(EventStack);
				EventStack = [];
			}
		}
	}
	
	function <portlet:namespace/>makeProjectGroupData(orgNameArr, orgUrlArr, dataArr, token) {
		if (!dataArr) return;
		dataArr = JSON.parse(dataArr);
		console.log('makeProjectGroupData');
		console.dir(dataArr);
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
		console.dir(param);
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
		console.dir(result);
		
		<portlet:namespace/>setProjectState(result);
		$('<iframe id="viewerFrame" src="<%= url %>" allowfullscreen></iframe>')
	     .appendTo('#viewerDiv');
	}
	
	function <portlet:namespace/>makeRequestUrl(fileId, fileName, token) {
		var currentUrl = window.location.href, url = "";
		
		console.log('makeRequestUrl : ' + currentUrl);
		if (currentUrl.indexOf('192.168.0.4') > -1 || currentUrl.indexOf('localhost') > -1) { // local
			url = "<%= Local_MAC_URL %>" + '?id=' + fileId;
			url += '&icebreakerUrl=' + "<%= DEV_IB_URL %>";
			url += '&vcToken=' + token;
			url += '&reqType=edison';
			url += '&fileName=' + fileName;
		} else if (currentUrl.indexOf('150.183.247.221') > -1) { // dev
			url = "<%= DEV_IB_URL %>api/file/download?id=" + fileId;
			url += '&vcToken=' + token;
			url += '&reqType=edison';
			url += '&fileName=' + fileName;
		} else if (currentUrl.indexOf('www.edison.re.kr') > -1) { // real
			url = "<%= REAL_IB_URL %>api/file/download?id=" + fileId;
			url += '&vcToken=' + token;
			url += '&reqType=edison';
			url += '&fileName=' + fileName;
		}
		
		/*
		// ?�전방식(to server)
		var url = "<%= IBhandlerURL %>" + '&<portlet:namespace/>fileId=' + fileId;
		url += '&<portlet:namespace/>icebreakerUrl=' + "<%= DEV_IB_URL %>";
		url += '&<portlet:namespace/>vcToken=' + token;
		url += '&<portlet:namespace/>reqType=edison';
		url += '&fileName=' + fileName;
		*/
		
		return url;
	}
	
	Liferay.on('eTurb_Analyzer_call',function(event) {
		console.log('eTurb_Analyzer_call receive!!');
		console.dir(event);
		
		var cmd = event.cmd;
		var param = event.param;
		
		console.log('call ' + cmd);
		console.dir(param);
		
		
		if (cmd == 'showAnalyzer') {	// show graph portlet
			$('#p_p_id_' + "<%= portletId %>" + '_').show();
		} else if (cmd == 'hideAnalyzer') {
			$('#p_p_id_' + "<%= portletId %>" + '_').hide();
		} else if (cmd == 'add.geometry' || cmd == 'add.mesh') {
			//callViewerRequestIframeLoading(param);
			var dataArr = JSON.parse(param.data), data = null;
			if (typeof(dataArr) == 'string') dataArr = JSON.parse(dataArr);
			console.log('add params : ');
			console.dir(dataArr);
			for(var i = 0 ; i < dataArr.length ; i++) {
				data = dataArr[i];
				if (data) {
					data.token = param.token;
					data.command = param.command;
					data.url = <portlet:namespace/>makeRequestUrl(data.fileId, data.name, data.token);
					if (data.url) {
						console.dir(data);
						<portlet:namespace/>callViewerRequest(data);
					}
				}
			}
			//<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'current.geometry' || cmd == 'current.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'show.geometry' || cmd == 'show.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'hide.geometry' || cmd == 'hide.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'select.entity' || cmd == 'hide.entity' || cmd == 'show.entity') {
			console.log('entity event : ' + cmd);
			console.dir(param);
			if (param) {
				var entityObj = param;
				var entityParam = JSON.parse(param.data);
				if (typeof(entityParam) == 'string') entityParam = JSON.parse(entityParam);
				entityObj.entity = entityParam;
				
				<portlet:namespace/>callViewerRequest(entityObj);
			}
		} else if (cmd == 'select.geometry' || cmd == 'select.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'add.entity') {
			console.log('start add.entity!!');
			console.dir(param);
			var paramId = param.data[0];
			console.log('paramId : ' + paramId);
			$('#viewerFrame')[0].contentWindow.makeEntitySet(paramId);
		} else if (cmd == 'remove.geometry' || cmd == 'remove.mesh') {
			console.log('start remove.geometry...');
			var paramDataArr = JSON.parse(param.data), removeObj = null;
			if (typeof(paramDataArr) == 'string') paramDataArr = JSON.parse(paramDataArr);
			for(var i = 0 ; i < paramDataArr.length ; i++) {
				removeObj = paramDataArr[i];
				if (removeObj && param) {
					var data = {};
					data.token = param.token;
					data.command = param.command;
					data.name = removeObj.name;
					data.fileId = removeObj.fileId;
					
					<portlet:namespace/>callViewerRequest(data);
				}
			}
		} else if (cmd == 'loadProject') {
			<portlet:namespace/>loadProject(param);	
		} else if (cmd == 'responseSceneInfo') {
			var result = $('#viewerFrame')[0].contentWindow.getSceneInfo();
			//alert(result);
			Liferay.fire('eTurb_Dashboard_call', {
				cmd: 'get.display',
				param: result,
			});
		} else {
			alert('정의되지 않은 명령입니다.');
		}
		
		return false;
	});

	/*
	Liferay.on( 'portletReady', function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if( myId === e.portletId ){
			
		}
	});
	*/
});

function uploadBlob(paramObj) {
	var currentUrl = window.location.href, ibUrl = "";
	
	if (currentUrl.indexOf('192.168.0.4') > -1 || currentUrl.indexOf('localhost') > -1) { // local
		ibUrl = "<%= DEV_IB_URL %>api/file/upload?cluster=EDISON-CFD&name=";
	} else if (currentUrl.indexOf('150.183.247.221') > -1) { // dev
		ibUrl = "<%= DEV_IB_URL %>api/file/upload?cluster=EDISON-CFD&name=";
	} else if (currentUrl.indexOf('www.edison.re.kr') > -1) { // real
		ibUrl = "<%= REAL_IB_URL %>ldap/api/file/upload?cluster=EDISON-CFD&name=";
	}
	
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
        console.log(result);
    });
	return false;
}

function sendEditorPortlet(cmd, json) {
	console.log('--- sendEditorPortlet');
	console.dir(json);
	console.log(cmd);
	console.log(JSON.stringify(json));
	
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
		console.log('>>>> loadProject : eventState <<<<<< status : ' + $('#eventState').val());
		return EventProjectObj.data;
	} else if (cmd == 'project-loaded') {
		console.log('>>>> project-loaded');
		Liferay.fire('eTurb_Dashboard_call', {
			cmd: 'block.end',
			param: '',
		});
	}
}
</script>