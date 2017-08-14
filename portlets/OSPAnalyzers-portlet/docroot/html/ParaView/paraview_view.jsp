<%@include file="../init.jsp"%>

<iframe id="<portlet:namespace/>canvas" ></iframe>

<script>
Liferay.on(OSP.Event.OSP_RESIZE, function(eventData){
	if( eventData.targetPortlet === OSP.Event.Constants.BROADCAST ||
			eventData.targetPortlet === '<portlet:namespace/>' ){
		$('#<portlet:namespace/>canvas').prop( 'width', eventData.width);
		$('#<portlet:namespace/>canvas').prop('height', eventData.height);
		$('#<portlet:namespace/>canvas').prop('src', '<%=request.getContextPath()%>/html/paraview/pv5/start.jsp');
	}
});

$('#<portlet:namespace/>canvas').prop( 'width', '100%');
$('#<portlet:namespace/>canvas').prop('height', '740px');
$('#<portlet:namespace/>canvas').prop('src', '<%=request.getContextPath()%>/html/paraview/pv5/start.jsp');
</script>
