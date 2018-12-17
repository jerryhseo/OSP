<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="./viewer.jsp" %>

<script type="text/javascript">
Liferay.on('OSP_FROM_EDITOR_EVENT',function(event) {
	var myId = '<%=portletDisplay.getId()%>';
	if(event.targetPortlet === myId){
		
		var cmd = event.command;
		var param = event.data;
		
		console.log('OSP_FROM_EDITOR_EVENT: ['+event.portletId+', '+myId+', '+cmd+', '+new Date()+']', param );
		if (cmd == 'add.geometry' || cmd == 'add.mesh') {
			var dataArr = JSON.parse(param.data), data = null;
			if (typeof(dataArr) == 'string') dataArr = JSON.parse(dataArr);
			for(var i = 0 ; i < dataArr.length ; i++) {
				data = dataArr[i];
				if (data) {
					data.token = param.token;
					data.command = param.command;
					data.url = <portlet:namespace/>makeRequestUrl(data.fileId, data.name, data.token);
					if (data.url) {
						<portlet:namespace/>callViewerRequest(data);
					}
				}
			}
		} else if (cmd == 'current.geometry' || cmd == 'current.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'show.geometry' || cmd == 'show.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'hide.geometry' || cmd == 'hide.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'select.geometry' || cmd == 'select.mesh') {
			<portlet:namespace/>callViewerRequest(param);
		} else if (cmd == 'remove.geometry' || cmd == 'remove.mesh') {
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
					
					console.log(data);
					<portlet:namespace/>callViewerRequest(data);
				}
			}
		} else if (cmd == 'loadProject') {
			<portlet:namespace/>loadProject(param);  
		} else if (cmd == 'get.display') {
			var myId = '<%=portletDisplay.getId()%>';
			var cmd = "set.display";
			var result = $('#<portlet:namespace/>viewerFrame')[0].contentWindow.getSceneInfo();
			var eventData = {
					portletId : myId,
					targetPortlet : event.portletId,
					command : cmd,
					data : result
				};
			Liferay.fire(OSP.Event.OSP_FROM_ANALYZER_EVENT, eventData);
		}
	}
});
</script>
