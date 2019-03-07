<%@ include file="../init.jsp" %>

<style>
</style>

<div class="container-fluid" style="padding: 0px 5px 0px 5px;">
	<div class = "row-fluid" style="margin-bottom:10px;">
		<div class="col-sm-12" >
			<h4><span style="display:inline-block;">Data: </span></h4><input type="file" id="<portlet:namespace/>dataFile" style="display:inline-block;">
		</div>
	</div>
	<div class = "row-fluid" style="border: solid grey 1px;min-height:100px;margin:0px;">
		<div  class="col-sm-5"  style="max-height:400px;overflow:auto;" >
			<div><h5>Events</h5></div>
			<fieldset id="<portlet:namespace/>events">
			</fieldset>
		</div>
		<div  class="col-sm-7" style="max-height:400px;overflow:auto;">
			<div><h5>Data</h5></div>
			<fieldset id="<portlet:namespace/>data" >
			</fieldset>
		</div>
	</div>
	<br>
	<div class = "row-fluid" style="margin:0px;">
		<input type="button" class="btn btn-primary col-sm-6" value="Clear processed events" id="<portlet:namespace/>clear">
	</div>
	<div class = "row-fluid" style="margin:0px;">
		<div class="col-sm-6" style="padding:0px 2px 0px 0px;">
			<h5>Sent</h5><br>
			<div id="<portlet:namespace/>sent"  style="border:solid grey 1px;min-height:100px;margin:0;padding:0;max-height:400px;overflow:auto;"></div>
		</div>
		<div class="col-sm-6" style="padding: 0px 0px 0px 2px;">
			<h5>Received</h5><br>
			<div id="<portlet:namespace/>received"  style="border:solid grey 1px;min-height:100px;margin:0;padding:0;max-height:400px;overflow:auto;"></div>
		</div>
	</div>
</div>

<script>
var <portlet:namespace/>simualtorData;
//console.log( <portlet:namespace/>simulatorData );
var <portlet:namespace/>processFuncs = {
		readServerFile: function(data){
			console.log( 'readServerFile: ', data );
		}
};

function <portlet:namespace/>resolveSimulatorData(){
	$('#<portlet:namespace/>events').empty();
	$('#<portlet:namespace/>data').empty();
	
	for( var event in <portlet:namespace/>simulatorData.events ){
		var radio = $('<input type="radio" name="<portlet:namespace/>event" value="'+event+'">'+' '+event+'<br>');
		radio.on('click', <portlet:namespace/>eventClick);
		
		$('#<portlet:namespace/>events').append(radio);
		
	}
}

$('#<portlet:namespace/>dataFile').on('change', function(){
	var reader = new FileReader();
    var fileName = '';
    
    fileName = OSP.Util.getLocalFileName(this);
    var file = OSP.Util.getLocalFile( this );
    reader.readAsText(file);

    reader.onload = function (evt) {
        <portlet:namespace/>simulatorData = JSON.parse(evt.target.result);
        console.log( 'simulatorData: ', <portlet:namespace/>simulatorData );
        <portlet:namespace/>resolveSimulatorData();
    };
});

Liferay.on(OSP.Event.OSP_REGISTER_EVENTS, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( myId !== e.targetPortlet )	return;
	
	console.log('OSP_REGISTER_EVENTS: ', e);
	<portlet:namespace/>receivedEvent( OSP.Event.OSP_REGISTER_EVENTS, e);
	
	var eventData = {
			portletId: myId,
			targetPortlet: e.portletId
	}
	Liferay.fire( OSP.Event.OSP_EVENTS_REGISTERED, eventData );
	<portlet:namespace/>sentEvent(OSP.Event.OSP_EVENTS_REGISTERED, eventData);
});

Liferay.on(OSP.Event.OSP_RESPONSE_DATA, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( myId !== e.targetPortlet )	return;
	
	<portlet:namespace/>processFuncs[e.params.procFunc](e.data);
	<portlet:namespace/>receivedEvent( OSP.Event.OSP_RESPONSE_DATA, e);
});

Liferay.on(OSP.Event.OSP_DATA_CHANGED, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( myId !== e.targetPortlet )	return;
	
	<portlet:namespace/>receivedEvent( OSP.Event.OSP_DATA_CHANGED, e);
	
	let portlets = <portlet:namespace/>simulatorData.portlets;
	let params = {};
	params.changeAlert = false;
	for( var i in portlets ){
		let portlet = portlets[i];
		let event = 'OSP_LOAD_DATA';
		if( portlet !== e.portletId ){
			let eventData = {
					portletId: myId,
					targetPortlet: portlet,
					data: e.data,
					params: params
			};
			<portlet:namespace/>sentEvent(event, eventData);
			Liferay.fire( event, eventData );
		}
	}
});

Liferay.on(OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, function(e){
	var myId = '<%=portletDisplay.getId()%>';
	if( myId !== e.targetPortlet )	return;
	
	<portlet:namespace/>receivedEvent( OSP.Event.OSP_REQUEST_SAMPLE_CONTENT, e);
});

function <portlet:namespace/>eventClick(){
	$('#<portlet:namespace/>data').empty();
	var event = this.value;
	var data = <portlet:namespace/>simulatorData.events[event];
	console.log('event data: ', data);

	var fireEvents = function( datum, params){
		console.log( 'Event fired: '+event);
		if( event === OSP.Event.OSP_REQUEST_DATA )
			params.procFunc = 'readServerFile';
		
		for( var index in <portlet:namespace/>simulatorData.portlets ){
			var portlet = <portlet:namespace/>simulatorData.portlets[index];
			var eventData = {
					portletId: '<%=portletDisplay.getId()%>',
					targetPortlet: portlet,
					data: datum,
					params: params
			}
			<portlet:namespace/>sentEvent(event, eventData);
			Liferay.fire( event, eventData );
		}
	};
	
	if( data.length === 0){
		fireEvents({}, {});
		return;
	}
	
	var dataItemClick = function(){
		console.log('data Item clicked: '+this.value);
		var datum = data[this.value].data;
		console.log( 'datum ', datum);
		var params = data[this.value].params;
		
		fireEvents( datum, params );
	};
	
	for( var index in data ){
		var item = $('<input type="radio" name="<portlet:namespace/>dataItem" value="'+index+'"><pre>'+JSON.stringify(data[index],null, 4)+'</pre><br>');
		item.on('click', dataItemClick);
		$('#<portlet:namespace/>data').append( item );
	};
}

function <portlet:namespace/>sentEvent(event, eventData){
	console.log('sentEvent: ', event, eventData );
	var sentContent = $('<pre></pre>');
	sentContent.text(event+'\n'+JSON.stringify(eventData, null, 4));
	$('#<portlet:namespace/>sent').prepend(sentContent);
}

function <portlet:namespace/>receivedEvent( event, e ){
	var eventData = {
			porltetId: e.portletId,
			targetPortlet: e.targetPortlet,
			data: e.data,
			params: e.params
	};
	
	var content = $('<pre></pre>');
	content.text(event+'\n'+JSON.stringify(eventData, null, 4));
	$('#<portlet:namespace/>received').prepend(content);
}

$('#<portlet:namespace/>clear').click(function(){
	$('#<portlet:namespace/>sent').empty();
	$('#<portlet:namespace/>received').empty();
});

</script>