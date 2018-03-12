(function(window){
	'use strict';
	
	if(typeof(OSPEvent) === 'undefined'){
        window.OSPEvent = {};

		window.OSPEvent.Constants = {
			OSP_APP_WORKBENCH_EVENT: 'appWorkbenchEvent',
			OSP_WF_WORKBENCH_EVENT: 'wfWorkbenchEvent',
			OSP_EDU_WORKBENCH_EVENT: 'eduWorkbenchEvent',
			
			EVENT_NAME: 'eventName',
			EVENT_SOURCE: 'eventSource',
			EVENT_TARGET: 'eventTarget',
			EVENT_DATA: 'eventData',
			
			BROADCAST: 'broadcast',
			
			SCOPE: 'scope',
			SCOPE_SESSION: 'portletSession',
			SCOPE_REQUEST: 'request',
			
			//commands
			COMMAND: 'command',
			REFRESH: 'refresh',
			CHANGE_ANALYZER: 'changeAnalyzer',
			CHANGE_EDITOR: 'changeEditor',
			INITIALIZE:'initialize',
			REQ_DATA:'requestData',
			REP_DATA:'responseData',
			REQ_FUNC:'requestFunction',
			REP_FUNC:'responseFunction',
			RESIZE:'resize',
			DISPLAY:'display',
			LOAD_FILE: 'loadFile',
			
			PORT_NAME: 'portName',
			URL: 'url',
			FILENAME: 'fileName',
			DIR_PATH: 'dirPath',
			FILE_PATH: 'filePath',
			DIR_NAME:'dirName',
			FILE_CONTENT:'fileContent',
			PORTLET_NAME:'portletName',
			UUID:'uuid',
			RAW_DATA:'rawData',
			RESERVED:'reserved',
			SIZE: 'size',
			WIDTH:'width',
			HEIGHT:'height'
		};

		/*
		 OSPEvent JSON Object Expression
		 {
		 	'eventName': // one of OSP_APP_WORKBENCH_EVENT, OSP_WF_WORKBENCH_EVENT, OSP_EDU_WORKBENCH_EVENT
		 	'eventSource': //event source portlet id
		 	'eventTarget': //event destination portlet id
		 	'dataLength': //EventData count
		 	'eventData': //OSPEventData object
		 		{
		 			'command': //one of the follows
		 			 	// 'refresh': refresh portlet,
						// 'changeAnalyzer': change analyzer portlet
						// 'changeEditor': change editor portlet
						// 'initialize': initialize portlet
						// 'requestData': please send your data
						// 'responseData': this is the response for your request
						// 'requestFunction': what is your functions?
						// 'responseFunction': these are my functions
						// 'resize' : resize portlet

		 			'portName': // portName of the ScieceApp
		 			'url': // URL of the data
		 			'fileName': 
		 			'dirPath':
		 			'filePath': 
		 			'dirName': 
		 			'fileContent': // file content 
		 			'portletName': 
		 			'uuid': 
		 			'rawData': 
		 			'reserved': 
		 			'size': // array [x, y] 
		 			'scope': // data scope
		 		}
		 }
		 */
		window.OSPEvent.EventData = function(){
			this.getCommand = function(){
				return this[OSPEvent.Constants.COMMAND];
			};
			this.setCommand = function( command ){
				this[OSPEvent.Constants.COMMAND] = command;
			};
			this.get = function( attr, url ){
				var paramValue;
				if( url ){
				    $.ajax({	
			    		type:'post',
			    		async: false,
			    		url: url, 
			    		data: {
			    			'<portlet:namespace/>command' : 'getParam',
			    			'<portlet:namespace/>paramName' : attr
			    		},
			    		dataType: 'json',
			    		beforeSend: function(){
			    			console.log('AJAX executed soon.');
			    		},
				   		success: function(data){
				   			paramValue = data;
						},
			    		complete: function(){
			    			console.log('AJAX completed.');
			    		}
					});
				}
				else
					paramValue = this[attr];
				
				return paramValue;
			};
			this.set = function( attr, val, url ){
				var result = false;
				if( url ){
				    $.ajax({	
			    		type:'post',
			    		async: false,
			    		url: url, 
			    		data: {
			    			'<portlet:namespace/>command' : 'setParam',
			    			'<portlet:namespace/>paramName' : attr,
			    			'<portlet:namespace/>value': val
			    		},
			    		dataType: 'json',
			    		beforeSend: function(){
			    			console.log('AJAX executed soon.');
			    		},
				   		success: function(data){
				   			result = data;
						},
			    		complete: function(){
			    			console.log('AJAX completed.');
			    		}
					});
				}
				else{
					this[attr] = val;
				}
				
				return result;
			};
			this.getPortName = function(){
				return this.get(OSPEvent.Constants.PORT_NAME);
			};
			this.setPortName = function( portName ){
				return this.set(OSPEvent.Constants.PORT_NAME, portName);
			};
			this.getUrl = function(){
				return this.get(OSPEvent.Constants.URL);
			};
			this.setUrl = function( url ){
				return this.set(OSPEvent.Constants.URL, url);
			};
			this.getRawData = function( url ){
				return this.get( OSPEvent.Constants.RAW_DATA, url );
			};
			this.setRawData = function( data, url ){
				return this.set( OSPEvent.Constants.RAW_DATA, data, url );
			};
			this.getScope = function(){
				return this.get( OSPEvent.Constants.SCOPE );
			};
			this.setScope = function( scope ){
				return this.set( OSPEvent.Constants.SCOPE, scope );
			};
			this.getSize = function(){
				var size = this.get( OSPEvent.Constants.SIZE );
				if( size ){
					return size;
				}
				else{
					this.set(OSPEvent.Constants.SIZE, []);
					return this.get( OSPEvent.Constants.SIZE );
				}
			};
			this.setSize = function( width, height ){
				var size = [];
				size.push( width );
				size.push( height );
				return this.set( OSPEvent.Constants.SIZE, size );
			};
			this.getWidth = function(){
				var size = this.get( OSPEvent.Constants.SIZE );
				return size[0];
			};
			this.setWidth = function( width ){
				var size = this.get( OSPEvent.Constants.SIZE );
				size[0] = width;
				return true;
			};
			this.getHeight = function(){
				var size = this.get( OSPEvent.Constants.SIZE );
				return size[1];
			};
			this.setHeight = function( height ){
				var size = this.get( OSPEvent.Constants.SIZE );
				size[1] = height;
				return true;
			};
			this.getFileName = function(){
				return this.get( OSPEvent.Constants.FILENAME );
			};
			this.setFileName = function( fileName ){
				return this.set( OSPEvent.Constants.FILENAME, fileName );
			};
			this.getFilePath = function(){
				return this.get( OSPEvent.Constants.FILE_PATH );
			};
			this.setFilePath = function( filePath ){
				return this.set( OSPEvent.Constants.FILE_PATH, filePath );
			};
			this.getDirPath = function(){
				return this.get( OSPEvent.Constants.DIR_PATH );
			};
			this.setDirPath = function( dirPath ){
				return this.set( OSPEvent.Constants.DIR_PATH, dirPath );
			};
			this.getDirName = function(){
				return this.get( OSPEvent.Constants.DIR_NAME );
			};
			this.setDirName = function( dirName ){
				return this.set( OSPEvent.Constants.DIR_NAME, dirName );
			};
			this.getFileContent = function( url ){
				return this.get( OSPEvent.Constants.FILE_CONTENT, url );
			};
			this.setFileContent = function( fileContent ){
				return this.set( OSPEvent.Constants.FILE_CONTENT, fileContent, url );
			};
			this.getPortletName = function(){
				return this.get( OSPEvent.Constants.PORT_NAME );
			};
			this.setPortletName = function( portletName ){
				return this.set( OSPEvent.Constants.PORT_NAME, portletName );
			};
			this.getUuid = function(){
				return this.get( OSPEvent.Constants.UUID );
			};
			this.setUuid = function( uuid ){
				return this.set( OSPEvent.Constants.UUID, uuid );
			};
			this.getReserved = function( url ){
				return this.get( OSPEvent.Constants.RESERVED, url );
			};
			this.setReserved = function( reserved, url ){
				return this.set( OSPEvent.Constants.RESERVED, reserved, url );
			};
		};

		window.OSPEvent.Event = function( event, emitter, target ){
			this[OSPEvent.Constants.EVENT_NAME] = event;
			this[OSPEvent.Constants.EVENT_SOURCE] = emitter;
			this[OSPEvent.Constants.EVENT_TARGET] = target;
			
			this.getEventName = function(){
				return this[OSPEvent.Constants.EVENT_NAME];
			};
			this.setEventName = function( eventName ){
				this[OSPEvent.Constants.EVENT_NAME] = eventName;
			};
			this.getEventSource = function(){
				return this[OSPEvent.Constants.EVENT_SOURCE];
			};
			this.setEventSource = function(source){
				this[OSPEvent.Constants.EVENT_SOURCE] = source;
			};
			this.getEventTarget = function (){
				return this[OSPEvent.Constants.EVENT_TARGET];
			};
			this.setEventTarget = function(target){
				this[OSPEvent.Constants.EVENT_TARGET] = target;
			};
			this.getEventData = function(){
				var data = this[OSPEvent.Constants.EVENT_DATA];
				if( data )	return data;
				else{
					this[OSPEvent.Constants.EVENT_DATA] = new OSPEvent.EventData(); 
					return this[OSPEvent.Constants.EVENT_DATA];
				}
			};
			this.setEventData = function(data){
				this[OSPEvent.Constants.EVENT_DATA] = data;
			};
			this.getPortName = function(){
				return this.getEventData().getPortName();
			};
			this.setPortName = function( portName ){
				return this.getEventData().setPortName(portName);
			};
			this.getUrl = function(){
				return this.getEventData().getUrl();
			};
			this.setUrl = function( url ){
				return this.getEventData().setUrl( url );
			};
			this.getRawData = function( url ){
				return this.getEventData().getRawData( url );
			};
			this.setRawData = function( data, url ){
				return this.getEventData().setRawData( data, url );
			};
			this.getScope = function(){
				return this.getEventData().getScope();
			};
			this.setScope = function( scope ){
				return this.getEventData().setScope( scope );
			};
			this.getSize = function(){
				return this.getEventData().getSize();
			};
			this.setSize = function( width, height ){
				return this.getEventData().setSize( width, height );
			};
			this.getWidth = function(){
				return this.getEventData().getWidth();
			};
			this.setWidth = function( width ){
				return this.getEventData().setWidth( width );
			};
			this.getHeight = function(){
				return this.getEventData().getHeight();
			};
			this.setHeight = function( height ){
				return this.getEventData().setHeight( height );
			};
			this.getFileName = function(){
				return this.getEventData().getFileName();
			};
			this.setFileName = function( fileName ){
				return this.getEventData().setFileName( fileName );
			};
			this.getFilePath = function(){
				return this.getEventData().getFilePath();
			};
			this.setFilePath = function( filePath ){
				return this.getEventData().setFilePath( filePath );
			};
			this.getDirPath = function(){
				return this.getEventData().getDirPath();
			};
			this.setDirPath = function( dirPath ){
				return this.getEventData().setDirPath( dirPath );
			};
			this.getDirName = function(){
				return this.getEventData().getDirName();
			};
			this.setDirName = function( dirName ){
				return this.getEventData().setDirName( dirName );
			};
			this.getFileContent = function( url ){
				return this.getEventData().getFileContent( url );
			};
			this.setFileContent = function( fileContent ){
				return this.getEventData().setFileContent( fileContent, url );
			};
			this.getPortletName = function(){
				return this.getEventData().getPortletName();
			};
			this.setPortletName = function( portletName ){
				return this.getEventData().setPortletName( portletName );
			};
			this.getUuid = function(){
				return this.getEventData().getUuid();
			};
			this.setUuid = function( uuid ){
				return this.getEventData().setUuid( uuid );
			};
			this.getReserved = function( url ){
				return this.getEventData().getReserved( url );
			};
			this.setReserved = function( reserved, url ){
				return this.getEventData().setReserved( reserved, url );
			};
		};
		window.OSPEvent.createEvent = function(event, source, target){
			return new OSPEvent.Event(event, source, target);
		};
	}
    else{
        console.log("OSPEvent already defined.");
    }
		
})(window);
