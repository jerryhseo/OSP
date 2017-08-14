<%@ include file="../init.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/html/Information/css/infomation.css">

<portlet:resourceURL var='downManualFile' id='downManualFile' escapeXml="false">
	<portlet:param name="type" value="manualfileDownload"/>
</portlet:resourceURL>

<liferay-portlet:resourceURL var="appSearchURL" id="appSearch" copyCurrentRenderParameters="false" escapeXml="false">
	<liferay-portlet:param name="type" value="searchApp"/>
</liferay-portlet:resourceURL>


<div class='row-fluid infomation-portlet'>
	<div class="span12 infomationSection" id="infomationSection">
		<div class="appIcon" id="<portlet:namespace/>appIcon"></div>
<%-- 		<img id='<portlet:namespace/>appIcon' width="123" height="111" src="<%=request.getContextPath()%>/images/apppage/img.gif" /> --%>
		<div class="appcontent">
			<ul>
				<li id="<portlet:namespace/>appName"></li>
				<li id="<portlet:namespace/>appTitle"></li>
				<li id="<portlet:namespace/>appVersion"></li>
			</ul>
		</div>
		<div class="appmanual">
			<input id='<portlet:namespace/>appManualButton' class="btn_blue1" type="button" value="Manual" />
		</div>
	</div>
</div>
<script type="text/javascript">

Liferay.on(OSP.Event.OSP_HANDSHAKE, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet == myId){
		var <portlet:namespace/>connector = e.portletId;
		
		var events = [
			OSP.Event.OSP_EVENTS_REGISTERED,
			OSP.Event.OSP_RESPONSE_APP_INFO
		];
		
		var eventData = {
				portletId: myId,
				targetPortlet: <portlet:namespace/>connector,
				data: events
			};
	}
	Liferay.fire( OSP.Event.OSP_REGISTER_EVENTS, eventData );
});

Liferay.on( 
	OSP.Event.OSP_EVENTS_REGISTERED,
	function( e ){
		var myId = '<%=portletDisplay.getId()%>';
		if(e.targetPortlet === myId){
			var eventData = {
					portletId: myId,
					targetPortlet: e.portletId
			}
			Liferay.fire( OSP.Event.OSP_REQUEST_APP_INFO, eventData );
		}
	}
);

Liferay.on(OSP.Event.OSP_RESPONSE_APP_INFO, function( e ){
	var myId = '<%=portletDisplay.getId()%>';
	if(e.targetPortlet === myId){
		console.log( 'Sear App: '+e.data);
		<portlet:namespace/>searchApp(e.data.scienceAppId);
	}
});

function <portlet:namespace/>searchApp(scienceAppId){
	var dataForm = {
		"<portlet:namespace/>scienceAppId" : scienceAppId
	};
	
	jQuery.ajax({
		type: "POST",
		url: "<%=appSearchURL%>",
		async : false,
		data : dataForm,
		dataType: 'json',
		success: function(result) {
			if(result.iconId!=0){
				var imageURL = "/documents/";
					imageURL += result.iconRepositoryId;
					imageURL += "/"+result.iconId;
					imageURL += "/"+result.iconUuid;
					imageURL += "?imageThumbnail=2";
				$('#<portlet:namespace/>appIcon').css("background-image", imageURL);
			}
			
			if(result.manualId!=0){
				$('#<portlet:namespace/>appManualButton').click(function(){
					window.location.href="<%=downManualFile.toString()%>&<portlet:namespace/>fileEntryId="+result.manualId;
				});
			}else{
				$('#<portlet:namespace/>appManualButton').css("display", "none");
			}
			$('#<portlet:namespace/>appTitle').html(result.currentTitle);
			$('#<portlet:namespace/>appName').html(result.name);
			$('#<portlet:namespace/>appVersion').html(result.version);
		},error:function(jqXHR, textStatus, errorThrown){
			if(jqXHR.responseText !== ''){
				alert(textStatus+": "+jqXHR.responseText);
			}else{
				alert(textStatus+": "+errorThrown);
			}  
		}
	});
	
}

function <portlet:namespace/>setAppInformationData(appInformation){
	$('#<portlet:namespace/>appIcon').css("background-image", appInformation.AppIcon);
	$('#<portlet:namespace/>appTitle').html(appInformation.AppTitle);
	$('#<portlet:namespace/>appName').html(appInformation.AppName);
	$('#<portlet:namespace/>appVersion').html(appInformation.AppVersion);
	if(appInformation.AppManual == 0)
		$('#<portlet:namespace/>appManualButton').attr("disabled", "disabled");
	else
		$('#<portlet:namespace/>appManualButton').click(function(){
			window.location.href='<%=downManualFile.toString()%>&<portlet:namespace/>fileEntryId='+appInformation.AppManual;
			//<portlet:namespace/>getManualResource(appInformation);
		});
	
}
</script>