<%@ include file="../init.jsp" %>
<portlet:defineObjects />

This is the <b>Fake Workbench</b> portlet in View mode.
<input type="button" value="aaa">

<script>
Liferay.on( 'portletReady', function(eventData){
	
	//alert(eventData.portletId+" "+eventData.targetPortlet);
	
	var myId = '<%=portletDisplay.getId()%>';
	if( myId !== eventData.portletId )
	{
		var sendData = 
		{
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:eventData.portletId,
			data:
			{
				
			}
		};	
		
				
		Liferay.fire('OSP_HANDSHAKE', sendData);
	}
});

Liferay.on( 'OSP_REGISTER_EVENTS', function(eventData){
	
	var myId = '<%=portletDisplay.getId()%>';
	if( myId === eventData.targetPortlet )
	{		
		var sendData = 
		{
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:eventData.portletId,			
			Registered : 'Yes'			
		};
		
		//alert(eventData.portletId+"==== "+sendData.Registered);
				
		Liferay.fire('OSP_EVENTS_REGISTERED', sendData);
	}
});

Liferay.on( 'OSP_REQUEST_PATH', function(eventData){
	
	var myId = '<%=portletDisplay.getId()%>';
	
	if( myId === eventData.targetPortlet )
	{		
		var inputData = {
				type_  : 'file',
				parent_: '',
				name_  : 'energy.csv'
		};
		
		var sendData = 
		{
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:eventData.portletId,			
			data : inputData							
			
		};		
				
		Liferay.fire('OSP_LOAD_DATA', sendData);
	}
});

Liferay.on( 'OSP_REQUEST_OUTPUT_PATH', function(e){
	
	var myId = '<%=portletDisplay.getId()%>';
	
	if( myId === e.targetPortlet )
	{
		console.log('[workbench] OSP_REQUEST_OUTPUT_PATH: ['+e.portletId+', '+new Date()+']', e.data);
		var inputData = {
				type_  : 'file',
				parent_: '',
				name_  : 'energy.csv'
		};
		
		var sendData = 
		{
			portletId: '<%=portletDisplay.getId()%>',
			targetPortlet:e.portletId,			
			data : inputData							
			
		};		
				
		Liferay.fire('OSP_LOAD_DATA', sendData);
	}
});

</script>