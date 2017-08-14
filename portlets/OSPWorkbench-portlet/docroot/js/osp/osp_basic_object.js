(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP.Constants )	return;
	}
	else
		window.OSP = {};
	
	OSP.Constants = {
			MAP: 'map_',
			ID:'id_',
			PREFERENCES:'preferences_',
			NAMESPACE:'namespace_',
			PARENT:'parent_',
			NAME:'name_',
			RELATIVE:'relative_',
			SAMPLE_UUID:'sampleUuid_',
			TARGET_LANGUAGE:'targetLanguage_',
			LOCALIZED_TEXT:'localizedText_',
			STATUS:'status_',
			STRUCTURE:'structure_',
			MANDATORY:'mandatory_',
			DATA_TYPE:'dataType_',
			DATA_COLLECTION:'dataCollection_',
			DATA_TYPE_UUID:'dataTypeUuid_',
			DATA_UUID:'dataUuid_',
			PATH:'path_',
			PATH_TYPE:'pathType_',
			CONTEXT:'context_',
			DESCRIPTION:'description_',
			DEFAULT_EDITOR:'defaultEditor_',
			AVAILABLE_EDITORS:'availableEditors_',
			DEFAULT_ANALYZER:'defaultAnalyzer_',
			AVAILABLE_ANALYZERS:'availableAnalyzers_',
			ORDER:'order_',
			INPUT_PORTS:'inputPorts_',
			PORT_NAMES:'portNames_',
			PORT_NAME:'portName_',
			INPUT_DATA:'inputData_',
			OUTPUT_PORTS:'outputPorts_',
			OUTPUT_DATA:'outputData_',
			TYPE:'type_',
			SCIENCE_APP:'scienceApp_',
			VERSION:'version_',
			TITLE:'title_',
			SRC_PORT_NAME:'sourcePortName_',
			PROCEED_CONDITIONS:'proceedConditions_',
			COMPARATIVE:'comparative_',
			COMPARATIVE_PARAM:'comparativeParam_',
			COMPARATIVE_VALUE:'comparativeValue_',
			UPDATES:'updates_',
			ACTION:'action_',
			TASKS:'tasks_',
			TASK_UUID:'taskUuid_',
			DESTINATION_PORT:'destinationPort_',
			STYLE:'style_',
			CONNECTIONS:'connections_',
			INPUTS:'inputs_',
			URI:'uri_',
			ICON:'icon_',
			IMAGE:'image_',
			LAYOUT:'layout_',
			TEMPLATE_ID:'templateId_',
			PORTLETS:'portlets_',
			PORTLET:'portlet_',
			PORTLET_ID:'portletId_',
			CURRENT_PORTLET:'currentPortlet_',
			ASSIGNED_PORTS:'assignedPorts_',
			INSTANCE_ID:'instanceId_',
			COLUMNS:'columns_',
			LOCATION:'location_',
			HANDSHAKE:'handshake_',
			HEIGHT:'height_',
			DATA:'data_',
			DISPLAY:'display_',
			ONE:'one_',
			TWO:'two_',
			PENDDING_EVENT:'penddingEvent',
			PAIRS:'pairs_',

			//DataStructure
			//Parameter types
			STRING : 'string',
			NUMERIC : 'numeric',
			GROUP : 'group',
			COMMENT : 'comment',
			LIST : 'list',
			VECTOR: 'vector',
			//brace and space types
			SQUARE:"SQUARE",
			SQUARE_SPACE:"SQUARE_SPACE",
			ROUND:"ROUND",
			ROUND_SPACE:"ROUND_SPACE",

			//property names
			LOWER_BOUNDARY : 'lowerBoundary_',
			UPPER_BOUNDARY : 'upperBoundary_',
			OPERAND : 'operand_',
			LOWER_OPERAND : 'lowerOperand_',
			UPPER_OPERAND : 'upperOperand_',
			PARAMETER_NAME: 'parameterName_',
			LIST_ITEM:'listItem_',
			LIST_ITEMS:'listItems_',
			LIST_ITEM_VALUE : 'listItemValue_',
			RANGE : 'range_',
			VALUE : 'value_',
			ASSIGN_VALUE:'assignValue_',
			ACTIVATE_CONDITIONS : 'activateConditions_',
			TEXT:'text_',
			NAME_TEXT:'nameText_',
			ACTIVE:'active_',
			DISABLED:'disabled_',
			UNIT:'unit_',
			SWEEP:'sweep_',
			SLICE_COUNT:'sliceCount_',
			SLICE_VALUE:'sliceValue_',
			SLICE_MAX:'sliceMax_',
			SWEEP_METHOD:'sliceMethod_',
			SWEEPABLE:'sweepable_',
			SWEEPED:'sweeped_',
			SWEEP_MAX:'sweepMax_',
			SWEEP_COUNT:'sweepCount_',
			DIMENSION:'dimension_',
			BRACE_CHAR:'braceChar_',
			DELIMITER:'delimiter_',
			SAMPLE:'sample_',
			SPACE:'space_',
			VALUE_DELIMITER:'valueDelimiter_',
			PARAMETER_DELIMITER:'parameterDelimiter_',
			COMMENT_CHAR:'commentChar_',
			CONTROL_CHAR:'controlChar_',
			VECTOR_FORM:'vectorForm_',
			PARAMETER_FORM:'parameterForm_',
			PARAMETERS:'parameters_',
			DEFAULT_LANGUAGE_ID:'defaultLanguageId_',
			AVAILABLE_LANGUAGE_IDS:'availableLanguageIds_',
			LOG_PORTS: 'logPorts_',

			//Constants
			MAX_DIGIT: 1e15,

			//Workflow proceed actions
			PROCEED:'proceed',
			UPDATE_PROCEED:'updateProceed',
			REPEAT:'repeat',
			UPDATE_REPEAT:'updateRepeat',
			SUSPEND:'suspend',
			STOP:'stop',

			// Port type constants
			FILE:'file',
			EXT:'ext',
			FOLDER:'folder',
			LOG:'log',

			UUID:'uuid_',
			JOB_NAME : 'jobName_',
			JOB_UUID:'jobUuid_',
			APP_UUID:'appUuid_',
			SCHEDULER_UUID:'schedulerUuid_',
			SIMULATION_UUID:'simaulationUuid_',
			AFTER_ANY:'afterAny_',
			AFTER_OK:'afterOK_',
			APP_BIN_PATH:'appBinPath_',
			APP_EXE_FILE_NAME:'appExeFileName_',
			JOB_TITLE:'jobTitle_',
			NODES:'nodes_',
			PROCESSORS_PER_NODE:'processorsPerNode_',
			SUBMIT_ARGS:'submitArgs_',
			CREATE_TIME:'createTime_',
			QUEUE_NAME:'queueName_',
			QUEUED_TIME:'queuedTime_',
			MODIFIED_TIME:'modifiedTime_',
			START_TIME:'startTime_',

			verifyPathType: function( type ){
				if( !( type === 'file' || type==='ext' || type ==='folder' ) )
					return false;
				else
					return true;
			},
			getBraceTypes : function (){
				var types = [];
				types.push(this.SQUARE);
				types.push(this.SQUARE_SPACE);
				types.push(this.ROUND);
				types.push(this.ROUND_SPACE);
				return types;
			},
			getParameterTypes : function (){
				var types = [];
				types.push(this.NUMERIC);
				types.push(this.VECTOR);
				types.push(this.STRING);
				types.push(this.LIST);
				types.push(this.GROUP);
				types.push(this.COMMENT);
				return types;
			},
			getDefinedPathTypes : function(){
				var types = [];
				types.push(this.FILE);
				types.push(this.EXT);
				types.push(this.FOLDER);
				return types;
			}
	}; // End of Constants

	OSP.Event = {
			OSP_RESIZE: 'OSP_REPORT',
			OSP_FILE_SELECTED: 'OSP_FILE_SELECTED',
			OSP_FILE_SAVED_AS: 'OSP_FILE_SAVED_AS',
			OSP_FILE_DESELECTED: 'OSP_FILE_DESELECTED',
			OSP_LOAD_FILE: 'OSP_LOAD_FILE',
			OSP_LOAD_DATA:'OSP_LOAD_DATA',
			OSP_REQUEST_DATA:'OSP_REQUEST_DATA',
			OSP_RESPONSE_DATA:'OSP_RESPONSE_DATA',
			OSP_DATA_CHANGED:'OSP_DATA_CHANGED',
			OSP_UPLOAD_FILE:'OSP_UPLOAD_FILE',
			OSP_DOWNLOAD_FILE:'OSP_DOWNLOAD_FILE',
			OSP_REPORT_NAMESPACE:'OSP_REPORT_NAMESPACE',
			OSP_DATA_LOADED:'OSP_DATA_LOADED',
			OSP_SAMPLE_SELECTED:'OSP_SAMPLE_SELECTED',
			OSP_UPLOAD_SELECTED:'OSP_UPLOAD_SELECTED',
			OSP_OK_CLICKED:'OSP_OK_CLICKED',
			OSP_CANCEL_CLICKED:'OSP_CANCEL_CLICKED',
			OSP_REQUEST_PORT_INFO:'OSP_REQUEST_PORT_INFO',
			OSP_RESPONSE_PORT_INFO:'OSP_RESPONSE_PORT_INFO',
			OSP_PORT_SELECTED:'OSP_PORT_SELECTED',
			OSP_NEW_SIMULATION:'OSP_NEW_SIMULATION',
			OSP_ADD_JOB:'OSP_ADD_JOB',
			OSP_SUBMIT_SIMULATION:'OSP_SUBMIT_SIMULATION',
			OSP_REQUEST_SAMPLE_CONTENT:'OSP_REQUEST_SAMPLE_CONTENT',
			OSP_ERROR:'OSP_ERROR',
			Constants: {
				BROADCAST: 'BROADCAST',
				CONTENT:'CONTENT',
				FILE_NAME:'FILE_NAME',
				URL:'URL'
			},
			command :{

			},
			//Yejin Kwon - workbench processing
			OSP_HANDSHAKE: 'OSP_HANDSHAKE',
			OSP_INITIALIZATION_RESPONSE: 'OSP_INITIALIZATION_RESPONSE',
			OSP_SIMULATION_ADD: 'OSP_SIMULATION_ADD',
			OSP_JOB_ADD: 'OSP_JOB_ADD',
			OSP_JOB_SELECT: 'OSP_JOB_SELECT',
			OSP_EDITOR_SELECT: 'OSP_EDITOR_SELECT',
			OSP_LOG_SELECT: 'OSP_LOG_SELECT',
			OSP_PORT_CHANGED:'OSP_PORT_CHANGED',
			OSP_ANALYZER_SELECT: 'OSP_ANALYZER_SELECT',


			reportProcessStatus: function( portletId, event, srcEvent, srcEventData, status ){
				var eventData = {
						portletId: portletId,
						targetPortlet: srcEventData.portletId,
						sourceEvent: srcEvent,
						sourceData: srcEventData,
						processStatus: status,
					};

					Liferay.fire( event, eventData );
			},
			reportDataChanged: function( portletId, targetId, data ){
				var eventData = {
						portletId: portletId,
						targetPortlet: targetId,
						data: data
					};

				Liferay.fire( OSP.Event.OSP_DATA_CHANGED, eventData );
			},

			reportFileSelected : function( portletId, targetId, data ){
				var eventData = {
						portletId: portletId,
						targetPortlet: targetId,
						data: data
					};

				console.log( 'FileSelected++++++++++++====================');
				console.log(eventData);
				Liferay.fire( OSP.Event.OSP_FILE_SELECTED, eventData );
			},

			reportFileDeselected : function( portletId, targetId, data ){
				var eventData = {
						portletId: portletId,
						targetPortlet: targetId,
						data: data
					};

				Liferay.fire( OSP.Event.OSP_FILE_DESELECTED, eventData );
			},

			responseDataToRequest: function( portletId, data, srcEventData ){
				var eventData = {
						portletId: portletId,
						targetPortlet: srcEventData.portletId,
						sourceEvent: OSP.Event.OSP_REQUEST_DATA,
						sourceData: srcEventData,
						data: data
					};

				Liferay.fire( OSP.Event.OSP_RESPONSE_DATA, eventData );
			},
			reportError: function( portletId, targetPortlet, message ){
				var eventData = {
						portletId: portletId,
						targetPortlet: targetPortlet,
						message: message
				};
				
				Liferay.fire( OSP.Event.OSP_ERROR, eventData );
			},
			stripNamespace: function( namespace ){
				var id = namespace.slice(namespace.indexOf('_'));
				return id.slice(0, id.lastIndexOf('_'));
			},
			getNamespace: function( instanceId ){
				return '_'+instanceId+'_';
			}

	}; // End of Event

	OSP.Enumeration = {
			WorkbenchType: {
				SIMULATION_APP:'SIMULATION_APP',
				SIMULATION_WORKFLOW: 'SIMULATION_WORKFLOW',
				SIMULATION_APP_TEST:'SIMULATION_APP_TEST',
				SIMULATION_WORKFLOW_TEST: 'SIMULATION_WORKFLOW_TEST',
				ANALYSIS_RERUN_APP:'SIMULATION_APP',
				ANALYSIS_RERUN_WORKFLOW:'SIMULATION_WORKFLOW',
				MONITORING_ANALYSIS:'MONITORING_ANALYSIS',
				MONITORING_RERUN_APP:'MONITORING_RERUN_APP',
				MONITORING_RERUN_WORKFLOW:'MONITORING_RERUN_WORKFLOW',
				CONTENT:'CONTENT',
				ANALYSYS:'ANALYSYS',
				CURRICULUM:'CURRICULUM',
				VIRTUAL_LAB:'VIRTUAL_LAB'
			},
			PathType :{
				NONE:'none',
				URL: 'url',
				FILE: 'file',
				FOLDER:'folder',
				EXT: 'ext',
				CONTEXT:'context'
			},
			SweepMethod : {
				BY_SLICE: 'slice',
				BY_VALUE: 'value'
			},
			DivSection : {
				SWEEP_SLICE_VALUE: 'sweepSliceValue'
			},
			OpenStatus : {
				PUBLIC: 0,
				RESTRICT: 1,
				PRIVATE: 2
			},

			ProcessStatus: {
				SUCCESS: 0,
				FAIL:-1
			},

			SimulationStatus : {
				QUEUE: 10,
				RUNNING: 11,
				SUSPEND: 12,
				SUCCESS: 13,
				FAIL: 14,
				CANCEL: 15
			},
			Location : {
				AT_LOCAL: 20,
				AT_SERVER: 21,
				AT_REMOTE: 22
			},
			DataStatus : {
				EMPTY: 30,
				DIRTY: 31
			},
			AppType : {
				STATIC_SOLVER: 40,
				JAVASCRIPT_SOLVER: 41,
				DYNAMIC_SOLVER: 42,
				STATIC_CONVERTER: 43,
				DYNAMIC_CONVERTER: 44,
				EDITOR: 45,
				ANALYZER: 46,
				EDITABLE_ANALYZER: 47
			}
	}; // End of Enumeration

	OSP.Util = {
			guid : function(){
				 return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(char) {
					 var random = Math.random()*16|0, value = char === 'x' ? random : (random&0x3|0x8);
					 return value.toString(16);
				 })
			},
			safeFloatSum : function ( x, y ){
				return ( parseFloat(x)*OSP.Constants.MAX_DIGIT +
							  parseFloat(y)*OSP.Constants.MAX_DIGIT) /
							  OSP.Constants.MAX_DIGIT;
			},
			safeFloatSubtract : function ( x, y ){
				return (	parseFloat(x)*OSP.Constants.MAX_DIGIT -
								parseFloat(y)*OSP.Constants.MAX_DIGIT) /
								OSP.Constants.MAX_DIGIT;
			},
			isInteger : function ( num ){
				return num%1 == 0;
			},
			isExponetioal : function( numStr ){
				if( numStr.search(/[eEdD]/i) == -1 )
					return false;
				else
					return true;
			},
			toFloatString : function( num, exponential ){
				if( exponential )
					return num.toExponential();
				else
					return num.toString();
			},
			toLocalizedXml : function( jsonObject, availableLanguageIds, defaultLanguageId ) {
				if( !availableLanguageIds )	availableLanguageIds = '';
				if( !defaultLanguageId )	defaultLanguageId = '';

				var xml = '<?xml version=\'1.0\' encoding=\'UTF-8\'?>';
				xml += '<root available-locales=\'';
				xml += availableLanguageIds+ '\' ';
				xml += 'default-locale=\'' + defaultLanguageId + '\'>';

				for( var languageId in jsonObject ){
					var value = jsonObject[languageId];
					xml += '<display language-id=\'' + languageId + '\'>' + value
								+ '</display>';
				}
				xml += '</root>';

				return xml;
			},
			toJSON : function( obj ){
				return JSON.parse( JSON.stringify(obj) );
			},
			isEmpty : function( obj ){
				if( obj == null )	return true;
				if( obj.length == 0 )
					return true;

				if( typeof obj !== 'object' )	return false;

				for (var key in obj){
					if( OSP.Util.isEmpty(obj[key]) == false )	return false;
				}

				return true;
			},
			convertToPath: function( filePath ){
				var path = new OSP.Path();
				
				filePath = this.removeEndSlashes( filePath );
				
				var lastIndexOfSlash = filePath.lastIndexOf( '/');
				if( lastIndexOfSlash < 0){
					path.parent('');
					path.name(filePath);
				}
				else{
					path.parent( filePath.slice( 0, lastIndexOfSlash ) );
					path.name( filePath.slice( lastIndexOfSlash+1 ) );
				}

				return path;
			},
			removeEndSlashes : function( strPath){
				var index = strPath.indexOf( '/' );
				if( index == 0)
					strPath = strPath.slice(1);
				
				index = strPath.lastIndexOf( '/' );
				if( index === strPath.length-1 )
					strPath = strPath.slice( 0, index);
				
				return strPath;
			},
			removeArrayElement: function( array, index ){
				array.splice( index, 1 );
				return array;
			},
			isBrowserEdge: function(){
				var ua=navigator.userAgent,tem,M=ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
				if(/trident/i.test(M[1])){
					tem=/\brv[ :]+(\d+)/g.exec(ua) || [];
					//return {name:'IE',version:(tem[1]||'')};
					return false;
				}

				return true;
			},
			addFirstArgument: function( argument, args ){
				var newArgs = [];
				for(var i=0; i<args.length; i++){
				  newArgs.push(args[i]);
				}
				newArgs.unshift(argument);
				return newArgs;
			}
	}; // End of OSP.Util

	OSP.Debug = {
			eventTrace : function( message, event, eventData ){
				console.log( '/+++++++++'+message+'++++++++/');
				console.log( event );
				console.log( eventData );
				console.log( '/++++++++++++++++++++++++++/');
			}
	}; // End of OSP.Debug

})(window);