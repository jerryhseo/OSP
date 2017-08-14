<%@ include file="/common/init.jsp" %>
<aui:form>
	<aui:input name="stringValue" id="stringValue" label="String Value" value=""  style="margin-bottom:0px;"></aui:input>
	<aui:button id="confirmBtn" value="confirm" style="display:none;float:left;width:150px;height:30px;font-size: 15px;padding:0px;margin-top:0px;"/>
	<aui:button id="cancelBtn" value="cancel" style="display:none;float:right;width:150px;height:30px;font-size: 15px;padding:0px;margin-top:0px;"/>
</aui:form>
<script src="${contextPath}/js/science_platform_events.js"></script>
<aui:script>

var A = AUI();
var receivedPayload = null;
A.use('aui-base','aui-io-request','liferay-util-window','liferay-portlet-url','aui-node',function(A) {
	var portName = '<%= request.getParameter("portName")%>';
	var workflowType = '<%= request.getParameter("workflowType")%>';
	var dialogId = '';
	var taskId = '<%= request.getParameter("taskId")%>';
	var portData = '<%= request.getParameter("portData")%>';
	
	A.one("#<portlet:namespace/>stringValue").val(portData);
	
	// workflowType value : workflow or single
	//workflowìì ì¬ì©í  ê²½ì° ì°½ì¼ë¡, ScienceAppìì ë¨ì¼ë¡ ì¤íí  ê²½ì° single
	if( workflowType == "workflow")
	{
		dialogId = '<%= request.getParameter("dialogId")%>';
		taskId = '<%= request.getParameter("taskId")%>';
		A.one("#confirmBtn").show();
		A.one("#cancelBtn").show();
	}
	else
	{
		Liferay.on(OSPEvent.Constants.IPC_EVENT_REQUEST_CONTENT, function(event){
			 var content = {
					taskId : taskId=='null'?"":taskId,
					portName : portName=='null'?"":portName,
					value: A.one('#<portlet:namespace/>stringValue').val()
			};
		 
			 var payload = OSPEvent.createEvent(
					event.getPortName(),
					OSPEvent.Constants.TYPE_TARGET,
					'<portlet:namespace/>', 
					event.getEventEmitter(),
					content);
			
			Liferay.fire(OSPEvent.Constants.IPC_EVENT_CONTENT_BROADCAST, payload);
		});
	}
	
	A.one('#confirmBtn').on('click', function(event){
		var stringValue = A.one('#<portlet:namespace/>stringValue').val();
		
		if( stringValue == '')
			alert("no empty value");
		else
		{
			Liferay.Util.getOpener().fetchResult( taskId,portName,stringValue );
			Liferay.Util.getOpener().closePopup( dialogId );
		}
	});

	A.one('#cancelBtn').on('click', function(event){
		Liferay.Util.getOpener().closePopup( dialogId );
	});
});
</aui:script>