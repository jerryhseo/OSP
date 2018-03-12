(function(window){
	if( typeof(window.OSPEvent) === 'undefined' ){
		'use strict';

		window.OSPEvent = {};
		
		window.OSPEvent.Constants = {
			OSP_WORKBENCH_EVENT_INIT:'OSPEventInit',
			OSP_WORKBENCH_EVENT_REQ: 'OSPEventRequest',
			OSP_WORKBENCH_EVENT_REP: 'OSPEventResponse',
			OSP_WORKBENCH_EVENT_BROADCAST: 'OSPEventBroadcast',
			
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
			INITIAL_SIZE:'initialSize',
			
			PORT_NAME: 'portName',
			URL: 'url',
			FILE_NAME: 'fileName',
			FOLDER_PATH: 'dirPath',
			FILE_PATH: 'filePath',
			FOLDER_NAME:'dirName',
			FILE_CONTENT:'fileContent',
			PORTLET_NAME:'portletName',
			UUID:'uuid',
			RAW_DATA:'rawData',
			RESERVED:'reserved',
			SIZE: 'size',
			WIDTH:'width',
			HEIGHT:'height',
			SAMPLE_FILE_PATH:'sampleFilePath',
			DATA_FORM:'dataForm',
			EXTENSION:'extension',
			FOLDER:'folder',
			FILE:'file',
			SCIENCE_DATA_TYPE:'scienceDataType',
			INPUT_DATA:'inputData',
			INPUT_PORTS:'inputPorts',
			OUTPUT_PORTS:'outputPorts',
			DIRTY:'dirty',
			FILE_TO_LOAD:'fileToLoad'
		};
		
		var defaultCss = {
				
		};
		
		var _PortBase = function(){
			this[OSPEvent.Constants.DIRTY] = false;
			
			this.getPortName = function(){
				return this[OSPEvent.Constants.PORT_NAME];
			};
			this.setPortName = function( portName ){
				this[OSPEvent.Constants.PORT_NAME] = portName;
				this.setDirty(true);
			};
			this.getSampleFilePath = function(){
				return this[OSPEvent.Constants.SAMPLE_FILE_PATH];
			};
			this.setSampleFilePath = function( sampleFilePath ){
				this[OSPEvent.Constants.SAMPLE_FILE_PATH] = sampleFilePath;
				this.setDirty(true);
			};
			this.getScienceDataType = function(){
				return this[OSPEvent.Constants.SCIENCE_DATA_TYPE];
			};
			this.setScienceDataType = function( dataType ){
				this[OSPEvent.Constants.SCIENCE_DATA_TYPE] = dataType;
				this.setDirty(true);
			};
			this.isDirty = function(){
				if( OSPEvent.Constants.DIRTY )
					return this[OSPEvent.Constants.DIRTY];
				else
					return false;
			};
			this.setDirty = function( dirty ){
				this[OSPEvent.Constants.DIRTY] = dirty;
			};
		};

		var InputPort = function(){
			_PortBase.apply(this);
			
			this.getInputData = function(){
				return this[OSPEvent.Constants.INPUT_DATA];
			};
			this.setInputData = function( inputData ){
				this[OSPEvent.Constants.INPUT_DATA] = inputData;
				this.setDirty(true);
			};
			this.isInputDataDefined = function(){
				if( this[OSPEvent.Constants.INPUT_DATA] &&
					this[OSPEvent.Constants.INPUT_DATA] !== '{}' )	return true;
				else	return false;
			}
		};
		
		var OutputPort = function(){
			_PortBase.apply(this);
			
			this.getFolderPath = function(){
				return this[OSPEvent.Constants.FOLDER_PATH];
			};
			this.setFolderPath = function( folderPath ){
				this[OSPEvent.Constants.FOLDER_PATH] = folderPath;
				this.setDirty(true);
			};
			this.getFolderName = function(){
				return this[OSPEvent.Constants.FOLDER_NAME];
			};
			this.setFolderName = function( folderName ){
				this[OSPEvent.Constants.FOLDER_NAME] = folderName;
				this.setDirty(true);
			};
			this.getFileName = function(){
				return this[OSPEvent.Constants.FILE_NAME];
			};
			this.setFileName = function( fileName ){
				 this[OSPEvent.Constants.FILE_NAME] = fileName;
				 this.setDirty(true);
			};
			this.getFullPath = function(){
				return this.getFolderName() + '/' + this.getFileName();
			};
			this.getDataForm = function(){
				return this[OSPEvent.Constants.DATA_FORM];
			};
			this.setDataForm = function( dataForm ){
				this[OSPEvent.Constants.DATA_FORM] = dataForm;
				this.setDirty(true);
			};
			this.validateOutput = function( url ){
				var folderPath = this.getFolderPath();
				var fileName = this.getFileName();
				var dataForm = this.getDataForm();
				
				var validateData = {
					'<portlet:namespace/>command'	 : 'validateOutput',
					'<portlet:namespace/>folderPath' : folderPat,
					'<portlet:namespace/>fileName': fileName,
					'<portlet:namespace/>dataForm':dataForm
				};
				var validateResult = false;
				
				$.ajax({	
					type:'post',
					async: false,
					url: url, 
					data: validateData,
					dataType: 'json',
					beforeSend: function(){
						console.log('AJAX executed soon.');
					},
						success: function(data){
							validateResult = data.validateResult;
					},
					complete: function(){
						console.log('AJAX completed.');
					}
				});
			
				return validateResult;
			};
		};
		
		var PortMap = function(){
			var map = {};
			this.addPort = function( port ){
				map[port.getPortName()] = port;
				return Object.keys(map).length;
			};
			this.removePort = function( portName ){
				delete map[portName];
				return Object.keys(map).length;
			};
			this.getPort = function( portName ){
				return map[portName];
			};
			this.count = function(){
				return Object.keys(map).length;
			};
		};
		
		var OSPEventData = function( command, source, target ){
			this[OSPEvent.Constants.COMMAND] = command;
			this[OSPEvent.Constants.EVENT_SOURCE] = source;
			this[OSPEvent.Constants.EVENT_TARGET] = target;

			this.getCommand = function(){
				return this[OSPEvent.Constants.COMMAND];
			};
			this.setCommand = function( command ){
				this[OSPEvent.Constants.COMMAND] = command;
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

			this.getCommand = function(){
				return this[OSPEvent.Constants.COMMAND];
			};
			this.setCommand = function( command ){
				this[OSPEvent.Constants.COMMAND] = command;
			};
			this.get = function( param ){
				return this[param];
			};
			this.set = function( param, value ){
				this[param] = value;
			};
			this.getInputPorts = function(){
				if( this[OSPEvent.Constants.INPUT_PORTS] )
					return this.get(OSPEvent.Constants.INPUT_PORTS);
				else{
					this.set(OSPEvent.Constants.INPUT_PORTS, new PortMap() );
					return this.get(OSPEvent.Constants.INPUT_PORTS);
				}
			};
			this.setInputPorts = function( inputPorts ){
				return this.set(OSPEvent.Constants.INPUT_PORTS, inputPorts);
			};
			this.addInputPort = function( inputPort ){
				return this.getInputPorts().addPort( inputPort );
			};
			this.removeInputPort = function( portName ){
				return this.getInputPorts().removePort( portName );
			};
			this.getInputPort = function( portName ){
				return this.getInputPorts().getPort( portName );
			};
			this.getOutputPorts = function(){
				if( this[OSPEvent.Constants.OUTPUT_PORTS] )
					return this.get(OSPEvent.Constants.OUTPUT_PORTS);
				else{
					this.set(OSPEvent.Constants.OUTPUT_PORTS, new PortMap());
					return this.get(OSPEvent.Constants.OUTPUT_PORTS);
				}
			};
			this.setOutputPorts = function( outputPorts ){
				this.set( OSPEvent.Constants.OUTPUT_PORTS, outputPorts );
			};
			this.addOutputPort = function( outputPort ){
				return this.getOutputPorts(),addPort( outputPort );
			};
			this.removeOutputPort = function( portName ){
				return this.getOutputPorts().removePort( portName );
			};
			this.getOutputPort = function( portName ){
				return this.getOutputPorts().getPort( portName );
			};
			this.getUrl = function(){
				return this.get(OSPEvent.Constants.URL);
			};
			this.setUrl = function( url ){
				return this.set(OSPEvent.Constants.URL, url);
			};
			this.getFileToLoad = function(){
				return this.get(OSPEvent.Constants.FILE_TO_LOAD);
			};
			this.setFileToLoad = function( file ){
				this.set(OSPEvent.Constants.FILE_TO_LOAD, file);
			};
			this.getRawData = function( url ){
				return this.get( OSPEventConstants.RAW_DATA, url );
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
					return [];
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
				return this.get( OSPEvent.Constants.FILE_NAME );
			};
			this.setFileName = function( fileName ){
				return this.set( OSPEvent.Constants.FILE_NAME, fileName );
			};
			this.getFilePath = function(){
				return this.get( OSPEvent.Constants.FILE_PATH );
			};
			this.setFilePath = function( filePath ){
				return this.set( OSPEvent.Constants.FILE_PATH, filePath );
			};
			this.getFolderPath = function(){
				return this.get( OSPEvent.Constants.FOLDER_PATH );
			};
			this.setFolderPath = function( folderPath ){
				return this.set( OSPEvent.Constants.FOLDER_PATH, folderPath );
			};
			this.getFolderName = function(){
				return this.get( OSPEvent.Constants.FOLDER_NAME );
			};
			this.setFolderName = function( folderName ){
				return this.set( OSPEvent.Constants.FOLDER_NAME, folderName );
			};
			this.getFileContent = function( url ){
				return this.get( OSPEvent.Constants.FILE_CONTENT, url );
			};
			this.setFileContent = function( fileContent ){
				return this.set( OSPEvent.Constants.FILE_CONTENT, fileContent, url );
			};
			this.getPortletName = function(){
				return this.get( OSPEvent.Constants.PORTLET_NAME );
			};
			this.setPortletName = function( portletName ){
				return this.set( OSPEvent.Constants.PORTLET_NAME, portletName );
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

		window.OSPEvent.getEventData = function(event, source, target){
			return new OSPEventData(event, source, target);
		};
	}
	else{
		console.log("OSPEvent already defined.");
	}
})(window);
