(function(window){
	'use strict';
	   
	if( window.OSP )	return;
	
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
			CONTEXT:'context_',
			DESCRIPTION:'description_',
			DEFAULT_EDITOR:'defaultEditor_',
			AVAILABLE_EDITORS:'availableEditors_',
			DEFAULT_ANALYZER:'defaultAnalyzer_',
			AVAILABLE_ANALYZERS:'availableAnalyzers_',
			ORDER:'order_',
			INPUT_PORTS:'inputPorts_',
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
			PORTLET_ID:'portletId_',
			INSTANCE_ID:'instanceId_',
			COLUMNS:'columns_',
			LOCATION:'location_',
			HANDSHAKE:'handshake_',
			HEIGHT:'height_',
			DATA:'data_',
			DISPLAY:'display_',
			
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
			stripNamespace: function( namespace ){
				var id = namespace.slice(namespace.indexOf('_'));
				return id.slice(0, id.lastIndexOf('_')); 
			}

	}; // End of Event
		
	OSP.Enumeration = {
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
				var lastIndexOfSlash = filePath.lastIndexOf( '/');
				var parent;
				var fileName;
				if( lastIndexOfSlash == -1 || lastIndexOfSlash == filePath.length -1 ){
					parent = '';
					fileName = filePath;
				}
				else{
					parent = filePath.slice( 0, lastIndexOfSlash );	
					fileName = filePath.slice( filePath.lastIndexOf( '/')+1, filePath.length );
				}
			
				return {
					parent: parent,
					fileName: fileName
				}
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
					console.log( 'IE Version: '+tem[1] );
					return false;
				}
				
				return true;
			},
			addFirstArgument: function( argument, args ){
				var newArgs;
				if( OSP.Util.isBrowserEdge() ){
					newArgs = Array.from( args );
				}
				else{
					newArgs = [];
					for(var i=0; i<args.length; i++){
					  newArgs.push(args[i]);
					}
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
		
	OSP._MapObject = function(){
		this.property = function( key, value){
			switch( arguments.length ){
				case 1:
					if( this.hasOwnProperty(key) )
						return this[key];
					else
						return false;
				case 2:
					this[key] = value;
					return true;
				default:
					return false;
			}
		};
			
		this.removeProperty = function( firstKey, secondKey ){
			switch( arguments.length ){
			case 1:
				delete this[firstKey];
				return true;
			case 2:
				var firstObj = this.property(firstKey);
				if( typeof firstObj === 'object' )
					return delete firstObj[secondKey];

				return false;
			default:
				return false;
			}
		};
		
		this.toJsonObject = function(){
			return JSON.parse( JSON.stringify(this) );
		};

		this._deserialize = function( key, value ){
			if( typeof value === 'function')	return false;
			else	this.property(key, value);
			
			return true;
		};
	}; // End of _MapObject 
		
	OSP._OpenObject = function( jsonObject ){
		OSP._MapObject.apply(this);

		this.uuid = function( uuid ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.UUID, arguments) );
		};

		this.name = function( name ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		this.version = function( version ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.VERSION, arguments) );
		};
		
		this.status = function( status ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.STATUS, arguments) );
		};

		this.sample = function( samplePath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments) );
		};
		
		this.samplePath = function( path ){
			var samplePath = this.sample(); 
			if( !samplePath )	return false;
			return sample.fullPath.apply( samplePath, arguments );
		};
		
		this.safeSample = function (){
			var sample = this.sample();
			if( !sample ){
				sample = new OSP.Path();
				this.sample( sample );
			}
			return sample;
		}
		
		this.setSample = function( parent, name, pathType, relative ){
			var sample = this.safeSample();
			return sample.setPath.apply( sample, arguments );
		};
		
		this.sampleUri = function( uri ){
			var sample = this.safeSample();
			return sample.uri.apply( sample, arguments );
		};
		
		this.samplePathType = function( pathType ){
			var sample = this.safeSample();
			return sample.type.apply( sample, arguments );
		};
		
		this.sampleParentFolderPath = function( parentPath ){
			var sample = this.safeSample();
			return sample.parent.apply( sample, arguments );
		};
		
		this.sampleName = function( name ){
			var sample = this.safeSample();
			return sample.name.apply( sample, arguments );
		};
		
		this.sampleRelative = function( relative ){
			var sample = this.safeSample();
			return sample.relative.apply( sample, arguments );
		};
		
		this.getSampleData = function( url, params ){
			
		};
		
		this.title = function( title ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TITLE, arguments) );
		};
		
		this.localizedTitle = function( languageId, text ){
			var title = this.title();
			
			switch( arguemnts.length ){
				case 1:
					if( !title )	return '';
					return title[languageId];
				case 2:
					if( !title ){
						title = {};
						this.title( title );
					}
					return title[languageId] = text;
				default:
					console.log( 'localizedTitle(): argument count mismatch('+arguments.length+')');
					return false;
			}
		};
		
		this.removeLocalizedTitle = function( languageId ){
			var title = this.title();
			if( !title )	return true;
			
			return delete title[languageId];
		};
		
		this.toTitleXml = function( availableLanguageIds, defaultLanguageId ){
			var title = this.title();
			if( !title )	title = {};
			return OSP.Util.toLocalizedXml( title, availableLanguageIds, defaultLanguageId );
		};

		this.targetLanguage = function ( languageId ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TARGET_LANGUAGE, arguments) );
		};
		
		this.description = function( description ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.DESCRIPTION, arguments) );
		};
		
		this.localizedDescription = function( languageId, text ){
			var description = this.description();
			
			switch( arguemnts.length ){
				case 1:
					if( !description )	return '';
					return description[languageId];
				case 2:
					if( !description ){
						description = {};
						this.description( description );
					}
					return description[languageId] = text;
				default:
					return false;
			}
		};

		this.removeLocalizedDescription = function( languageId ){
			var description = this.description();
			if( !description )	return true;
			
			return delete description[languageId];
		};
		
		this.toDescriptionXml = function( availableLanguageIds, defaultLanguageId ){
			var description = this.description();
			if( !description )	description = {};
			return OSP.Util.toLocalizedXml( description, availableLanguageIds, defaultLanguageId );
		};
		
		this.icon = function( iconPath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.ICON, arguments) );
		};
		
		this.image = function( imagePath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.IMAGE, arguments) );
		};
		
		this.layout = function( layout ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LAYOUT, arguments) );
		};
		
		this.layoutTemplateId = function( templateId ){
			var layout = this.layout();
			if( !layout ){
				layout = new OSP.Layout();
				this.layout( layout );
			}
			return layout.templateId.apply ( layout, arguments );
		};
		
		this.layoutColumn = function( columnId, portlets ){
			var layout = this.layout();
			if( !layout )	return false;
			
			return layout.column.apply ( layout, arguments );
		};
		
		this.layoutAddPortlet = function (columnId, portlet){
			var layout = this.layout();
			if( !layout ){
				layout = new OSP.Layout();
				this.layout( layout );
			}
			return layout.addPortlet.apply ( layout, arguments );
		};
		
		this.layoutRemovePortlet = function( columnId, portletId ){
			var layout = this.layout();
			if( !layout )	return true;
			
			return layout.removePortlet.apply ( layout, arguments );
		};
		
		this.layoutRemoveColumn = function( columnId ){
			var layout = this.layout();
			if( !layout )	return true;
			
			return layout.removeColumn.apply ( layout, arguments );
		};
		
		this.getLayoutColumnNames = function(){
			var layout = this.layout();
			if( !layout )	return false;
			
			return layout.getColumnNames.apply ( layout, arguments );
		};
		
		this.hasLayoutPortlet = function( portletId, columnId ){
			var layout = this.layout();
			if( !layout )	return false;
			
			return layout.hasPortlet.apply ( layout, arguments );
		};
		
		this.getLayoutPortlet = function( portletId, columnId ){
			var layout = this.layout();
			if( !layout )	return false;
			
			return layout.getPortlet.apply ( layout, arguments );
		};
		
		this.deserializeLayout = function( jsonLayout ){
			this.layout( new OSP.Layout( jsonLayout ) );
		};
	}; // End of _OpenObject 

	OSP._StyleObject = function(){
		this.style = function( style ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.STYLE, arguments) );
		};
		
		this.css = function( key, value ){
			switch( arguments.length ){
			case 1:
				var style = this.style();
				if( !style )	return	false;
				return style[key];
			case 2:
				var style = this.style();
				if( !style ){
					style = {};
					this.style(style);
				}
				return style[key] = value;
			default:
				return false;
			}
		};
		
		this.removeCss = function( key ){
			var style = this.style();
			if( !style )	return true;
			
			delete style[key];
		};
	}; // End of _StyleObject 

	OSP.Path = function( jsonObject ){
		OSP._MapObject.apply(this);
		
		this.uri = function( uri ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.URI, arguments) );
		};
		
		this.setPath = function( id, parent, name, type, relative ){
			if( arguments.length < 3 )
				return false;
			this.id( id );
			this.parent(parent);
			this.type(type);
			if( type === OSP.Constants.FOLDER )
				this.folderName(name);
			else if( type === OSP.Constants.EXT )
				this.extension(name);
			else
				this.fileName(name);
			
			this.relative(relative);
			return true;
		};
		
		this.id = function( id ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.ID, arguments) );
		};
		
		this.type = function( type ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
		};

		this.parent = function( parent ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.PARENT, arguments) );
		};
		
		this.name = function( name ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		this.fileName = function( fileName ){
			if( this.type() !== OSP.Constants.FILE )
				return false;
			
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};
		
		this.relative = function( relative ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.RELATIVE, arguments) );
		};
		
		this.extension = function( ext ){
			if( this.type() !== OSP.Constants.EXT )
				return false;

			switch( arguments.length ){
			case 0:
				return this.property(OSP.Constants.NAME).replace('*', '').replace('.', '').replace('/','');
			case 1:
				return this.property(OSP.Constants.NAME, ext.replace('*', '').replace('.', '').replace('/',''));
			default:
				return false;
			}
		};

		this.folderName = function( folderName ){
			if( this.type() !== OSP.Constants.FOLDER )
				return false;

			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};
		
		this.fullPath = function( path ){
			switch( arguments.length ){
			case 0:
				if( this.type() === OSP.Constants.URI )
					return this.uri();
				if( !this.parent() )
					return this.fileName();
				return this.parentPath()+'/'+ this.fileName();
			case 1:
				var pathObj = OSP.Util.convertToPath( path );
				this.parent(pathObj.parent);
				return this.name(pathObj.fileName);
			default:
				console.log( 'Arcument count mismatch: fullPath' );
				return false;
			}
		};
		
		this.clone = function(){
			return new OSP.Path( OSP.Util.toJSON( this ) );
		};
		
		this.deserialize = function( jsonObject ){
			for( var key in jsonObject )
				this._deserialize( key, jsonObject[key] );
		};
		
		if( arguments.length === 1 ){
			this.deserialize(jsonObject);
		}
	}; // End of Path 

	OSP.InputData = function( jsonObject ){
		OSP.Path.apply(this);
		
		this.context = function( context ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.CONTEXT, arguments) );
		};
		
		this.clone = function(){
			return new OSP.InputData( OSP.toJSON(this) );
		};

		if( arguments.length === 1 )
			this.deserialize(jsonObject);
	}; // End of InputData
	
	
	OSP.Layout = function( jsonLayout ){
		OSP._MapObject.apply( this );
		
		var Portlet = function( jsonObject ){
			OSP._MapObject.apply(this);
			
			this.id = function( portletId ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ID, arguments));
			};
			
			this.instanceId = function( instanceId ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.INSTANCE_ID, arguments));
			};
			
			this.getInstanceId = function(){
				var instanceString;
				var idStr = "" + this.InstanceId();
				var pad = "0000";
				var instanceString = pad.substring(0, pad.length - idStr.length) + idStr;
				return this.id()+'_INSTANCE_'+instanceString;
			};
			
			this.portName = function( portName ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PORT_NAME, arguments));
			};
			
			this.namespace = function(){
				return '_'+this.id()+'_';
			};
			
			this.data = function( data ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DATA, arguments));
			};
			
			this.location = function( location ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.LOCATION, arguments));
			};
			
			this.height = function( height ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.HEIGHT, arguments));
			};
			
			this.type = function( type ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments));
			};
			
			this.handshake = function( handshake ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.HANDSHAKE, arguments));
			};
			
			this.display = function( display ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DISPLAY, arguments));
			};
			
			this.preferences = function( preferences ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PREFERENCES, arguments));
			};
			
			this.addPreference = function( key, value ){
				var preferences = this.preferences();
				switch( arguments.length){
				case 1:
					if( !preferences )	return '';
					return preferences[key];
				case 2:
					if( !preferences ){
						preferences = [];
						this.preferences( preferences );
					}
					return preferences[key] = value;
				default:
					console.log( 'Arguments mismatch: Portlet.preference.');
					return false;
				}
			};
			
			this.removePreference = function( preference ){
				var preferences = this.preferences();
				if( !preferences )	return true;
				return delete preferences[preference];
			};
			
			this.clone = function(){
				return new this.Portlet( OSP.Util.toJSON(this) );
			};
			
			this.deserialize = function( jsonPortlet ){
				
				for( var key in jsonPortlet ){
					switch( key ){
					case OSP.Constants.ID:
					case OSP.Constants.TYPE:
					case OSP.Constants.HEIGHT:
					case OSP.Constants.PORT_NAME:
					case OSP.Constants.PREFERENCES:
					case OSP.Constants.LOCATION:
					case OSP.Constants.DATA:
					case OSP.Constants.HANDSHAKE:
					case OSP.Constants.DISPLAY:
					case OSP.Constants.INSTANCE_ID:
						this.property( key, jsonPortlet[key] );
						break;
					default:
						console.log( 'Un-recognizable Portlet property: '+key);
					}
				}
			};
			
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
		}; // End of Portlet 
		this.newPortlet = function(jsonPortlet ){
			return new Portlet(jsonPortlet);
		};
		
		this.templateId = function( templateId ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TEMPLATE_ID, arguments));
		};
		
		this.columns = function( columns ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COLUMNS, arguments));
		};
		
		/**
		 * PUBLIC: Get all column IDs
		 * ARGUMENTS: none
		 *  
		 *  RETURN: String array
		 */
		this.getColumnIds = function(){
			var columns = this.columns();
			if( !columns )	return '';
			return Object.keys(columns);
		};
		
		/**
		 * PUBLIC: Determine to has a specific portlet
		 * ARGUMENTS:
		 * 	String portletId: portlet ID
		 * 	String columnId: column ID. option.
		 *  
		 *  RETURN: 
		 *  	true: if the portlet exist
		 *  	false:otherwise
		 */
		this.getPortlets = function( portletId ){
			var columns = this.columns();
			if( !columns )	return false;

			var retrievedPortlets = [];
			for( var key in columns ){
				var portlets  = columns[key];
				for( var index in portlets ){
					var portlet = portlets[index];
					console.log('portletId: '+portlets[index].id());
					if( portletId === portlet.id() ){
						retrievedPortlets.push(portlet);
						console.log( portletId );
					}
				}
			}
			return retrievedPortlets;
		};
		
		this.getPortlet = function( portletId ){
			var columns = this.columns();
			if( !columns )	return false;

			for( var key in columns ){
				var portlets  = columns[key];
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portletId === portlet.id() )
						return portlet;
				}
			}
			return false;
		};
		
		this.getColumnPortlets = function( columnId, portletId ){
			var portlets = this.column(columnId);
			if( !portlets )	return false;

			var retrievedPortlets = [];
			for( var index in portlets ){
				var portlet = portlets[index];
				if( portletId === portlet.id() )
					retrievedPortlets.push(portlet);
			}
			return retirevedPortlet;
		};
		
		this.getColumnPortlet = function( columnId, portletId ){
			var portlets = this.column(columnId);
			if( !portlets )	return false;

			var retrievedPortlets = [];
			for( var index in portlets ){
				var portlet = portlets[index];
				if( portletId === portlet.id() )
					return portlet;
			}
			return false;
		};
		
		this.getPortPortlets = function( portName, portletId ){
			var columns = this.columns();
			var retrievedPortlets = [];
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portlet.id() === portletId && portlet.portName() === portName )
						retrievedPortlets.push(portlet);
				}
			}
			
			return retrievedPortlets;
		};
		
		this.getPortPortlet = function( portName, portType, portletId ){
			var columns = this.columns();
			var retrievedPortlets = [];
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portlet.id() === portletId && portlet.portName() === portName && portlet.type() === portType)
						return portlet;
				}
			}
			
			return false;
		};

		/**
		 * PUBLIC: get or set portlets to a column. 
		 * 			  Overrides portlets if the column already exists.  
		 * ARGUMENTS:
		 * 	String columnId: column ID
		 * 	[] portlets: array of OSP Porltet objects to be set
		 *  
		 *  RETURN: 
		 *  	[] : OSP Portlet object array 
		 *  	false: if column doesn't exist or setting failed. 
		 */
		this.column = function( columnId, portlets ){
			var columns = this.columns();
			
			switch( arguments.length ){
				case 1:
					if( !columns )	return false;
					return !columns[columnId] ? false : columns[columnId];
				case 2:
					if( !columns ){
						columns = {};
						this.columns(columns);
					}
					return columns[columnId] = portlets;
				default:
					console.log('Arguments mismatch: Layout.column()');
					return false;
			}
		};
		
		/**
		 * PUBLIC: add a portlet to a column. 
		 * 			  Replace portlet if the portlet already exists in the column.
		 * 			  If the column doesn't exist, create new column.  
		 * ARGUMENTS:
		 * 	columnId: String column ID
		 * 	portlet: OSP Porltet object to be added
		 *  
		 *  RETURN: 
		 *  	[] : OSP Portlet object array of a column 
		 */
		this.addPortlet = function( columnId, portlet ){
			var portlets = this.column(columnId);
			if( !portlets ){
				portlets = [];
				this.column(columnId, portlets);
			}
			console.log( portlets );
			var retrievedPortlets = this.getPortlets(portlet.id());
			console.log('retrieved length: '+retrievedPortlets.length );
			console.log(retrievedPortlets );
			switch( retrievedPortlets.length ){
				case 0:
					portlets.push( portlet );
					return portlets;
				case 1:
					retrievedPortlets[0].instanceId( 1 );
					portlet.instanceId( 2 );
					portlets.push( portlet );
					return portlets;
				default:
					var instanceId = 1;
					var duplicated = false;
					do{
						for( var index in retrievedPortlets ){
							if( retrievedPortlets[index].instanceId() === instanceId ){
								duplicated = true;
								instanceId++;
							}
							else
								duplicated = false;
						}
					}while( duplicated );
					
					portlet.instanceId( instanceId );
					portlets.push( portlet );
					return portlets;
			}
		};
		
		/**
		 * PUBLIC: Remove a portlet from a column. 
		 * ARGUMENTS:
		 * 	String columnId: column ID
		 * 	String portletId: portlet id
		 *  
		 *  RETURN: 
		 *  	[] : OSP Portlet object array of a column
		 *  false: if failed or the specified portlet doesn't exist. 
		 */
		this.removeColumnPortlets = function( columnId, portletId ){
			var portlets = this.column(columnId);
			if( !portlets )	return false;
			
			for( var index in portlets ){
				if( portlets[index].id() === portletId ){
					OSP.Util.removeArrayElement(portlets, index);
				}
			}
			
			return false;
		};
		
		this.removePortPortlets = function( portName, portletId ){
			var columns = this.columns();
			if( !columns )	return false;
			
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portlet.id() === portletId && portlet.portName() === portName)
						OSP.Util.removeArrayElement(portlets, index);
				}
			}
			
			return true;
		};
		
		this.removePortPortlet = function( portName, portType, portletId ){
			var columns = this.columns();
			if( !columns )	return false;
			
			var retrievedPortlets = [];
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( 	portlet.id() === portletId && 
							portlet.portName() === portName && 
							portlet.type() === portType )
						OSP.Util.removeArrayElement(portlets, index);
				}
			}
			
			return false;
		};
		
		this.removeColumn = function( columnId ){
			var columns = this.columns();
			if( !columns )	return true;
			
			return delete columns[columnId] ;
		};
		
		this.getDisplayPortPortlet = function( portName, portType ){
			var columns = this.columns();
			if( !columns )
				return false;
			
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portlet.portName() === portName && portlet.type() === portType && portlet.display() )
						return portlet;
				}
			}
			
			return false;
		};
		
		this.portPortletDisplay = function( portName, portType, portletId, display ){
			var portlet = this.getPortPortlet(portName, portType, portletId );
			if( !portlet )	return false;
			
			switch( arguments.length ){
				case 3:
					portlet.display();
					break;
				case 4:
					portlet.display( display );
					break;
				default:
					return false;
			}
			
			return portlet;
		};
		
		this.switchDisplayPortPortlet = function( portName, portType, toPortletId ){
			var displayPortlet = this.getDisplayPortPortlet( portName, portType );
			if( !displayPortlet )	return false;
			displayPortlet.display( false );
			
			var toPortlet = this.getPortPortlet(portName, portType, toPortletId);
			if( !toPortlet )	return false;
			toPortlet.display( true );
			
			return toPortlet;
		};
		
		
		this.evaluatePortletLocations = function( namespace ){
			var columns = this.columns();
			if( !columns )return false;
			
			for( var columnId in columns ){
				var column = columns[columnId];
				var i = 1;
				for( var index in column ){
					var portlet = column[index];
					portlet.location( namespace+columnId+'_'+ i );
					i++;
				}
			}
			
			return true;
		};
		
		this.getColumnPortletLocation = function( columnId, portletId ){
			return this.getPortlet(portletId, columnId).location();
		};
		
		this.handshake = function( portletId, handshake ){
			var portlet = this.getPortlet(portletId);
			if( !portlet ) return false;
			switch( arguments.length ){
			case 1:
				return portlet.handshake();
			case 2:
				return portlet.handshake( handshake );
			default:
				console.log('Arguments mismatch: OSP.Layout.handshake()');
				return false;
			}
		};
		
		this.handshakeCompleted = function(){
			var columns = this.columns();
			for( var columnId in columns ){
				var portlets = this.column(columnId);
				for( var index in portlets ){
					var portlet = portlets[index];
					if( !portlet.handshake() )
						return false;
				}
			}
			
			return true;
		};
		
		this.clone = function(){
			return new OSP.Layout( OSP.Util.toJSON(this) );
		};
		
		this.deserialize = function( jsonLayout ){
			for( var key in jsonLayout ){
				switch( key ){
					case OSP.Constants.TEMPLATE_ID:
						this.property( key, jsonLayout[key] );
						break;
					case OSP.Constants.COLUMNS:
						var columnsJson = jsonLayout[key];
						for( var columnId in columnsJson ){
							var jsonPortlets = columnsJson[columnId];
							for( var index in jsonPortlets ){
								this.addPortlet( columnId, new Portlet(jsonPortlets[index]) );
							}
						}
						break;
					default:
						console.log("Un-recognizable key: "+ key);
				}
			}
		};
		
		if( arguments.length === 1 )
			this.deserialize( jsonLayout );
	}; // End of Layout 
	OSP.newLayout = function( jsonLayout ){
		return new OSP.Layout( jsonLayout );
	};
	
	OSP.DataCollection = function(){
		OSP._OpenObject.apply(this);
		
		this.dataType = function( dataTypeName, dataTypeVersion ){
			switch( arguments.length ){
			case 0:
				return this.property(OSP.Constants.DATA_TYPE);
			default:
				var dataType = {
						name: dataTypeName,
						version: dataTypeVersion
				};
				return this.property( OSP.Constants.DATA_TYPE, dataType );
			}
		};
		
		this.layout = function( layout ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LAYOUT, arguments) );
		};
		
		this.layoutColumnPortlet = function( column, portletId ){
			var layout = this.layout();
			switch( arguments.length ){
			case 1:
				if( !layout )	return '';
				return layout[column];
			case 2:
				if( !layout ){
					layout = {};
					this.layout( layout );
				}
				
				layout[column] = portletId;
				return layout;
			default:
				console.log( 'Arguments count mismatch: layoutColumnPortlet('+arguments.length+')');
				return false;
			}
		};
		
		this.removeLayoutColumnPortlet = function( column ){
			var layout = this.layout();
			if( !layout )	return true;
			
			return delete layout[column];
		};
		
		this.code = function( code ){
			
		};
	}; // End of DataCollection
	
	OSP.DataEntry = function(){
		OSP._MapObject.apply(this);
		
		this.dataCollection = function( collectionName, collectionVersion ){
			switch( arguments.length ){
			case 0:
				return this.property(OSP.Constants.DATA_COLLECTION);
			default:
				var dataCollection = {
						name: collectionName,
						version: collectionVersion
				};
				return this.property( OSP.Constants.DATA_COLLECTION, dataCollection );
			}
		};
		
		this.scienceApp = function( scienceApp ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SCIENCE_APP, arguments) );
		};
		
		this.inputs = function( inputs ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.INPUTS, arguments) );
		};
		
		this.addInput = function( portName, inputData ){
			var inputs = this.inputs();
			if( ! inputs ){
				inputs = {};
				this.inputs( inputs );
			}
			
			inputs[portname] = inputData;
			return inputs;
		};
		
		this.inputData = function( portName ){
			var inputs = this.inputs();
			if( ! inputs )	return false;
			
			return inputs[portName];
		};
		
		this.removeInput = function( portName ){
			var inputs = this.inputs();
			if( ! inputs )	return true;
			
			delete inputs[portName];
			return inputs;
		};
		
		this.path = function( path ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.PATH, arguments) );
		};
		
		this.safePath = function(){
			var path = this.path();
			if( !path ){
				path = new OSP.Path();
				this.path( path );
			}
			return path;
		};

		this.setPath = function( parent, name, type, relative ){
			var path = this.safePath();
			return path.setPath.apply( path, parent, name, type, relative );
		};
		
		this.pathParent = function( parent ){
			var path = this.safePath();
			return path.parent.apply( path, parent );
		};
		
		this.pathType = function( type ){
			var path = this.safePath();
			return path.type.apply(path, type);
		};
		
		this.pathName = function( name ){
			var path = this.safePath();
			return path.name.apply(path, name);
		};
		
		this.pathRelative = function( relative ){
			var path = this.safePath();
			return path.relative.apply(path, relative);
		};
		
		this.clone = function(){
			return new OSP.Data( OSP.Util.toJSON(this) );
		};

		this.deserializeInputs = function( jsonObject ){
			var inputs = {};
			for( var portName in jsonObject ){
				var path = new OSP.Path( jsonObject[portName] );
				inputs[portName] = path;
			}
			
			this.inputs( inputs );
			return inputs;
		};
		
		this.deserializePath = function( jsonObject ){
			var path = new OSP.Path( jsonObject );
			this.path(path);
			return path;
		};
	}; // End of DataEntry 

	OSP.DataType = function(){
		OSP._OpenObject.apply(this);
		
		var DataStructure = function( jsonObject ){
			OSP._MapObject.apply(this);
			
			var Range = function( jsonObject ) {
				OSP._MapObject.apply( this );
				
				this.lowerBoundary = function( boundary ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LOWER_BOUNDARY, arguments) );
				};
				
				this.upperBoundary = function( boundary ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.UPPER_BOUNDARY, arguments) );
				};
				
				this.operand = function( operand ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.OPERAND, arguments) );
				};
				
				this.includeBoundary = function( whichEnd, flag ){
					var operand = this.operand();
					if( !operand )	return false;
					
					if( whichEnd === OSP.Constants.LOWER_BOUNDARY && flag == true )
						operand = '=' + operand.substring(1);
					else if( whichEnd === OSP.Constants.LOWER_BOUNDARY && flag == false )
						operand = '<' + operand.substring(1);
					else if( whichEnd === OSP.Constants.UPPER_BOUNDARY && flag == true )
						operand = operand.substring(0, 1) + '=';
					else if( whichEnd === OSP.Constants.UPPER_BOUNDARY && flag == false )
						operand = operand.substring(0, 1) + '>';
					else
						return false;
					
					this.operand( operand );
					return true;
				};
				
				this.checkLowerBoundary = function(){
					var operand = this.operand();
					if( !operand )	return false;
					else{
						if( operand[0] === '=' ) return true;
						else	return false;
					}
				};
				
				this.checkUpperBoundary = function(){
					var operand = this.operand();
					if( !operand )	return false;
					else{
						if( operand[1] === '=' ) return true;
						else	return false;
					}
				};
				
				this.setRange = function( lowerBoundary, upperBoundary, operand ) {
					var result = this.property( OSP.Constants.LOWER_BOUNDARY, lowerBoundary );
					if( result === false )	return result;
					result = this.property( OSP.Constants.UPPER_BOUNDARY, upperBoundary );
					if( result === false )	return result;
					result = this.property( OSP.Constants.OPERAND, operand);
					
					return result;
				};
				
				this.verify = function( value ){
					var lowerBoundaryContained = this.checkLowerBoundary();
					var upperBoundaryContained = this.checkUpperBoundary();
					var lowerBoundary = this.lowerBoundary();
					var upperBoundary = this.upperBoundary();
					
					if( !lowerBoundary && !upperBoundary )	return true;
					
					if( lowerBoundaryContained && !upperBoundary){
						if( Number(value) >= Number(lowerBoundary) )	return true;
					}
					else if ( !lowerBoundaryContained && !upperBoundary ){
						if( Number(value) > Number(lowerBoundary) )	return true;
					}
					else if ( !lowerBoundary && upperBoundaryContained ){
						if( Number(value) <= Number(upperBoundary) )	return true;
					}
					else if( !lowerBoundary && !upperBoundaryContained){
						if( Number(value) < Number(upperBoundary) )	return true;
					}
					else if(  lowerBoundaryContained && upperBoundaryContained ){
						if( Number(value) >= Number(lowerBoundary) && Number(value) <= Number(upperBoundary) )	
							return true;
					}
					else if( !lowerBoundaryContained && upperBoundaryContained ){
						if( Number(value) > Number(lowerBoundary) && Number(value) <= Number(upperBoundary) )	
							return true;
					}
					else if( lowerBoundaryContained && !upperBoundaryContained ){
						if( Number(value) >= Number(lowerBoundary) && Number(value) < Number(upperBoundary) )	
							return true;
					}
					else if( !lowerBoundaryContained && !upperBoundaryContained ){
						if( Number(value) > Number(lowerBoundary) && Number(value) < Number(upperBoundary) )	
							return true;
					}
					
					return false;
				};
				
				this.clone = function(){
					return new Range( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonRange ){
					for( var key in jsonRange ){
						this.property( key, jsonRange[key] );
					}
				};
				
				this.upgrade = function( oldRange ){
					for( var key in oldRange ){
						if( OSP.Util.isEmpty(oldRange[key]) )	continue;
						switch( key ){
						case 'lower-limit':
							this.lowerBoundary(oldRange[key]);
							break;
						case 'upper-limit':
							this.upperBoundary(oldRange[key]);
							break;
						case 'operand':
							this.operand(oldRange[key]);
							break;
						default:
							alert( 'Undefined Range property: '+key);	
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of Range 
			this.newRange = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new Range();
					case 1:
						return new Range(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newRange');
						return null;
				}
			};
			
			var _ActivateCondition = function( jsonObject ){
				OSP._MapObject.apply(this);
				
				this.parameterName = function( name ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_NAME, arguments) );
				};

				this.type = function( type ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
				};
				
				this._upgrade = function( key, value, newCondition ){
					if( OSP.Util.isEmpty(value) ) return;

					switch( key ){
					case 'variable-name':
						newCondition.parameterName( value );
						break;
					case 'type':
						newCondition.type( value );
						break;
					}
				};
			}; // End of _ActivateCondition 
			
			var NumericActivateCondition = function( jsonObject ){
				_ActivateCondition.apply(this);
				this.type(OSP.Constants.NUMERIC);
				
				this.range = function( range ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments) );
				};

				this.rangeLowerBoundary = function( boundary ){
					var range = this.range();
					if( !range )	return false;
					
					return range.lowerBoundary.apply( range, arguments );
				};
				
				this.rangeUpperBoundary = function( boundary ){
					var range = this.range();
					if( !range )	return false;
					
					return range.upperBoundary.apply( range, boundary );
				};
				
				this.rangeOperand = function( operand ){
					var range = this.range();
					if( !range )	return false;
					
					return range.operand.apply( range, operand );
				};
				
				this.rangeCheckLowerBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					return range.checkLowerBoundary();
				};
				
				this.rangeCheckUpperBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					return range.checkUpperBoundary();
				};
				
				this.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = this.range();
					if( !range )	return false;
					return range.includeBoundary( whichEnd, flag );
				};
				
				this.verifyRange = function( value ){
					var range = this.range();
					if( !range )	return true;
					return range.verify( value );
				};
				
				this.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = this.range();
					if( !range ){
						range = new Range();
						this.range( range );
					}
					
					return range.setRange(lowerBoundary, upperBoundary, operand);
				};

				this.setCondition = function(parameterName, lower, upper, operand, assignValue) {
					var range = this.range();
					if( !range ){
						range = new Range();
						this.range( range );
					}
					
					this.parameterName(parameterName);
					switch( arguments.length ){
					case 5:
						this.property(OSP.Constants.ASSIGN_VALUE, assignValue);
					case 4:
						return range.setRange(lower, upper, operand);
					default:
						return false;
					}
				};
				
				this.checkActivate = function( value ){
					if( this.verifyRange(value) == true ){
						if( this.assignValue() == false )
							return true;
						else
							return this.assignValue();
					}
					else
						return false;
				};
				
				this.assignValue = function( assignValue ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.ASSIGN_VALUE, arguments) );
				};
				
				this.clone = function(){
					return new NumericActivateCondition( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function(jsonObject){
					for( var key in jsonObject){
						switch( key ){
						case OSP.Constants.RANGE:
							this.range( new Range( jsonObject[key] ) );
							break;
						default:
							this.property(key, jsonObject[key]);
						}
					}
				};
				
				this.upgrade = function( oldCondition ){
					for( var key in oldCondition ){
						if( OSP.Util.isEmpty(oldCondition[key]) )	continue;
						
						switch( key ){
						case 'domain':
							var range = new Range();
							range.upgrade( oldCondition[key] );
							this.range( range );
							break;
						default:
							this._upgrade( key, oldCondition[key], this );
							break;
						}
					}
				}
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of NumericActivateCondition 
			this.newNumericActivateCondition = function( jsonObject ){
				switch( arguments.length ){
				case 0:
					return new NumericActivateCondition();
				case 1:
					return new NumericActivateCondition(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newNumericActivateCondition');
					return null;
				}
			};
			
			var ListItemActivateCondition = function( jsonObject ){
				_ActivateCondition.apply(this);
				this.type(OSP.Constants.LIST);
				
				this.value = function( value ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LIST_ITEM_VALUE, arguments) );
				};
				
				this.setCondition = function(parameterName, listItemValue, assignValue) {
					switch( arguments.length ){
					case 3:
						this.assignValue(assignValue);
					case 2:
						this.parameterName(parameterName);
						this.value(listItemValue);
						return this;
					}
					
					return false;
				};
				
				this.assignValue = function( value ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.ASSIGN_VALUE, arguments) );
				};
				
				this.checkActivate = function(value){
					if(this.value() === value){
						if( !this.assignValue() || this.assignValue() === '')
							return true;
						else
							return this.assignValue();
					}
					else	return false;
				};
				
				this.clone = function(){
					return new ListItemActivateCondition( OSP.Util.toJSON(this) );
				};
				
				this.upgrade = function( oldCondition ){
					for( var key in oldCondition ){
						if( OSP.Util.isEmpty(oldCondition[key]) )	continue;
						
						switch( key ){
						case 'list-item-value':
							this.value(oldCondition[key]);
							break;
						case 'assign-value':
							this.assignValue(oldCondition[key]);
							break;
						default:
							this._upgrade( key, oldCondition[key], this );
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of ListItemActivateCondition 
			this.newListItemActivateCondition = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListItemActivateCondition();
					case 1:
						return new ListItemActivateCondition(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newListItemActivateCondition');
						return null;
				}
			};
			
			var ListItem = function( jsonObject ) {
				OSP._MapObject.apply(this);
				
				this.text = function( textObject ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TEXT, arguments) );
				};
				
				this.value = function( value ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments) );
				};
				
				this.localizedText = function(languageId, text) {
					var itemText = this.text();
					
					switch( arguments.length ){
					case 1:
						if( !itemText )	return '';
						else	return itemText[languageId];
					case 2:
						if( !itemText ){
							itemText = {};
							this.text( itemText );
						}
						return itemText[languageId] = text;
					default:
						return false;
					}
				};
				
				this.removeItemText = function(languageId) {
					var itemText = this.text();
					if( !itemText )	return true;
					
					return delete itemText[languageId];
				};
				
				this.textXml = function(availableLanguageIds, defalutLanguageId) {
					var itemText = this.text();
					if( !itemText )	itemText = {};
					return OSP.Util.toLocalizedXml( itemText, availableLanguageIds, defalutLanguageId );
				};
				
				this.activateConditions = function( conditions ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ACTIVATE_CONDITIONS, arguments));
				};
				
				this.addActivateCondition = function( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue ) {
					var conditions = this.activateConditions();
					if( !conditions ){
						conditions = [];
						this.activateConditions(conditions);
					}

					var condition;
					switch( arguments.length ){
					case 1:
						condition = conditionOrPrameterName;
						break;
					case 2:
						condition = new ListItemActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue );
						break;
					case 3:
						condition = new ListItemActivateCondition();
						condition.setCondition(conditionOrPrameterName, minOrListItemValue, maxOrAssignValue );
						break;
					case 4:
						condition = new NumericActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand );
						break;
					case 5:
						condition = new NumericActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue );
						break;
					default:
						return conditions;
					}
					
					conditions.push( condition );
					return conditions;
				};
				
				this.removeActivateCondition = function( parameterName, minOrListItemValue, max ) {
					var conditions = this.activateConditions();
					if( !conditions )	return true;
					
					switch( arguments.length ){
					case 1:
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								this.removeActivateCondition(parameterName);
								break;
							}
						}
						return conditions;
					case 2:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					case 3:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.rangeLowerBoundary() &&
								max === condition.rangeUpperBoundary() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					default:
						return conditions;
					}
				};
				
				this.checkActivate = function(parameterName, value){
					var conditions = this.activateConditions();
					if( !conditions )	return true;
					
					var activate = false;
					for( var index in conditions ){
						var condition = conditions[index];
						if( condition.parameterName() === parameterName ){
							activate = condition.checkActivate( value );
							if( activate )	return activate;
						}
					}
					
					return activate;
				};
				
				this.clone = function(){
					return new ListItem( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject ){
						if( key === OSP.Constants.ACTIVATE_CONDITIONS ){
							var jsonConditions = jsonObject[key];
							for( var index in jsonConditions ){
								var jsonCondition = jsonConditions[index];
								var condition;
								if( jsonCondition[OSP.Constants.TYPE] === OSP.Constants.LIST)
									condition = new ListItemActivateCondition( jsonCondition );
								else 
									condition = new NumericActivateCondition( jsonCondition );
								
								this.addActivateCondition(condition);
							}
						}
						else
							this.property( key, jsonObject[key] );
					}
				};
				
				this.upgrade = function( oldListItem ){
					for( var key in oldListItem ){
						if( OSP.Util.isEmpty(oldListItem[key]) )	continue;

						switch( key ){
						case 'value':
							this.value(oldListItem[key]);
							break;
						case 'localized-text-map':
							this.text(oldListItem[key]['map']);
							break;
						case 'activate-condition-container':
							var oldConditions = oldListItem[key]['container'];
							var newConditions = [];
							for( var index in oldConditions ){
								var oldCondition = oldConditions[index];
								if( OSP.Util.isEmpty(oldCondition) )	continue;
								var newCondition;
								switch( oldCondition['type'] ){
								case 'numeric':
									newCondition = new NumericActivateCondition();
									break;
								case 'list':
									newCondition = new ListItemActivateCondition();
									break;
								default:
									alert( 'Unknown condition: '+oldCondition['type']);
									return;
								}
								newCondition.upgrade( oldCondition['type'] );
								newConditions.push(newCondition);
							}
							
							this.activateConditions(newConditions);
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of ListItem 
			this.newListItem = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListItem();
					case 1:
						return new ListItem(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newListItem');
						return null;
				}
			};
			
			var Sweep = function( jsonObject ){
				OSP._MapObject.apply(this);
				
				this.sliceCount = function( count ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SLICE_COUNT, arguments) );
				};
				
				this.sliceValue = function( value ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SLICE_VALUE, arguments) );
				};
				
				this.maxSlice = function( maxSlice ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SLICE_MAX, arguments) );
				};
				
				this.range = function( range ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments) );
				};

				this.rangeLowerBoundary = function( boundary){
					var range = this.range();
					if( !range )	return false;
					
					return range.lowerBoundary.apply( range, arguments );
				};

				this.rangeUpperBoundary = function( boundary ){
					var range = this.range();
					if( !range )	return false;
					else	return range.upperBoundary.apply(range, arguments);
				};
				
				this.rangeOperand = function( operand ){
					var range = this.range();
					if( !range )	return false;
					
					return range.operand.apply( range, operand );
				};
				
				this.rangeCheckLowerBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					
					return range.checkLowerBoundary();
				};
				
				this.rangeCheckUpperBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					
					return range.checkUpperBoundary();
				};
				
				this.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = this.range();
					if( !range )	return false;
					
					return range.includeBoundary( whichEnd, flag );
				};
				
				this.verifyRange = function( value ){
					var range = this.range();
					if( !range )	return true;
					return range.verify( value );
				};
				
				this.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = this.range();
					if( !range ){
						range = new Range();
						this.range( range );
					}
					
					return range.setRange.apply(range, arguments);
				};

				this.getSlicedValues = function(){
					var strLower = this.rangeLowerBoundary();
					var strUpper = this.rangeUpperBoundary();
					if( !strLower || !strUpper ) return false;
					
					var lower = Number( strLower );
					var upper = Number( strUpper );
					
					var values = [];
					var isExponential = OSP.Util.isExponetioal(strLower) || OSP.Util.isExponetioal(strUpper);
					var includeLower = this.rangeCheckLowerBoundary();
					var includeUpper = this.rangeCheckUpperBoundary();
					var sliceValue;
					var method = this.sweepMethod(); 
					
					var index =0;
					var sliceCount;
					
					if( method == OSP.Enumeration.SweepMethod.BY_SLICE ){
							sliceCount = Number(this.sliceCount());
							//console.log('sliceCount: '+sliceCount);
					}
					else {
							sliceValue = Number(this.sliceValue());
							sliceCount =  Math.floor(OSP.Util.safeFloatSubtract(upper, lower)/sliceValue);
							//console.log('sliceCount: '+sliceCount);
					}

					//console.log("included: "+includeLower+','+includeUpper);
					if(includeLower == true  && includeUpper == true){
						values[index] = OSP.Util.toFloatString(lower, isExponential);
						//console.log('sliced value['+index+']:'+lower);
						++index;
						--sliceCount;
						//console.log('sliced value['+sliceCount+']:'+upper);
					}
					else if(includeLower == true){
						values[index] = OSP.Util.toFloatString(lower, isExponential);
						++index;
					}
					else{
						++sliceCount;	
					}
					
					if( method == OSP.Enumeration.SweepMethod.BY_SLICE )
						sliceValue = OSP.Util.safeFloatSubtract(upper, lower) / sliceCount;
					
					var value;
					(index == 0) ? ( value = OSP.Util.safeFloatSum(lower, sliceValue)) : 
											  (value = OSP.Util.safeFloatSum(values[index-1], sliceValue)); 
					while(value < upper){
						values[index] = OSP.Util.toFloatString(value, isExponential);
//							alert('sliced value['+index+']:'+values[index]);
						//console.log('sliced value['+index+']:'+values[index]);
						++index;
						value = OSP.Util.safeFloatSum(value, sliceValue);
					}
					if(includeUpper == true){
						values[index] = OSP.Util.toFloatString(upper, isExponential);
						//console.log('sliced value['+(sliceCount-1)+']:'+upper);
					}

					return values;
				};
				
				this.sweepMethod = function( method ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_METHOD, arguments) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject){
						switch(key){
						case OSP.Constants.RANGE:
							this.range( new Range( jsonObject[key] ) );
							break;
						default:
							this.property( key, jsonObject[key] );
						}
					}
				};
				
				this.upgrade = function( oldSweep ){
					for( var key in oldSweep ){
						if( OSP.Util.isEmpty(oldSweep[key]) )	continue;

						switch( key ){
						case 'slice':
							this.sliceCount( oldSweep[key] );
							break;
						case 'slice-value':
							this.sliceValue( oldSweep[key] );
							break;
						case 'slice-max':
							this.maxSlice( oldSweep[key] );
							break;
						case 'sweep-by-slice':
							if( oldSweep[key] == true )
								this.sweepMethod( OSP.Enumeration.SweepMethod.BY_SLICE );
							else
								this.sweepMethod( OSP.Enumeration.SweepMethod.BY_VALUE );
							break;
						case 'domain':
							var range = new Range();
							range.upgrade( oldSweep[key] );
							this.range( range );
							break;
						default:
							alert( 'Unknown sweep attribute: '+ key);
							return;
						}
					}
				};

				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of Sweep 
			this.newSweep = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new Sweep();
					case 1:
						return new Sweep(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newSweep');
						return null;
				}
			};

			var _Parameter = function( jsonObject ) {
				OSP._MapObject.apply(this);
				
				this.name = function( name ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
				};
				
				this.type = function( type ){
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
				};
				
				this.nameText = function( nameText ) {
					return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME_TEXT, arguments) );
				};
				
				this.localizedNameText = function( languageId, text ) {
					var nameText = this.nameText();
					
					switch( arguments.length ){
					case 1:
						if( !nameText )	return '';
						return nameText[languageId];
					case 2:
						if( !nameText ){
							nameText = {};
							this.nameText( nameText );
						}
						return nameText[languageId] = text;
					default:
						return '';
					}
				};
				
				this.removeNameText = function( languageId ) {
					return this.removeProperty( OSP.Constants.NAME_TEXT, languageId );
				};
				
				this.localizedNameTextXML = function(availableLanguageIds, defaultLanguageId) {
					var nameText = this.nameText();
					if( !nameText ){
						nameText = {};
					}
					
					return OSP.Util.toLocalizedXml(nameText, availableLanguageIds, defaultLanguageId);
				};
				
				this.description = function( description ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DESCRIPTION, arguments));
				};
				
				this.localizedDescription = function( languageId, text ) {
					var description = this.description();
					
					switch( arguments.length ){
					case 1:
						if( !description )	return '';
						else	return description[languageId];
					case 2:
						if( !description ){
							description = {};
							this.description( description );
						}
						return description[languageId] = text;
					default:
						return '';
					}
				};
				
				this.removeLocalizedDescription = function( languageId ) {
					return this.removeProperty(OSP.Constants.NAME_TEXT, languageId);
				};
				
				this.localizedDescriptionXML = function(availableLanguageIds, defaultLanguageId) {
					var description = this.description();
					if( !description )	description = {};
					
					return OSP.Util.toLocalizedXml(description, availableLanguageIds, defaultLanguageId);
				};
				
				this.activateConditions = function( conditions ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ACTIVATE_CONDITIONS, arguments));
				};
				
				this.addActivateCondition = function( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue ) {
					var conditions = this.activateConditions();
					if( !conditions ){
						conditions = [];
						this.activateConditions(conditions);
					}

					var condition;
					switch( arguments.length ){
						case 1:
							condition = conditionOrPrameterName;
							break;
						case 2:
							condition = new ListItemActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue );
							break;
						case 3:
							condition = new ListItemActivateCondition();
							condition.setCondition(conditionOrPrameterName, minOrListItemValue, maxOrAssignValue );
							break;
						case 4:
							condition = new NumericActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand );
							break;
						case 5:
							condition = new NumericActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue );
							break;
						default:
							return conditions;
					}
					
					conditions.push( condition );
					return conditions;
				};
				
				this.cleanActivateConditions = function(){
					return this.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);
				};
				
				this.removeActivateCondition = function( parameterName, minOrListItemValue, max ) {
					var conditions = this.activateConditions();
					if( !conditions )	return true;
					
					switch( arguments.length ){
					case 1:
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								this.removeActivateCondition(parameterName);
								break;
							}
						}
						return conditions;
					case 2:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					case 3:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.rangeLowerBoundary() &&
								max === condition.rangeUpperBoundary() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					default:
						return conditions;
					}
				};
				
				this.checkActivate = function(parameterName, value){
					var conditions = this.activateConditions();
					if( !conditions )	return true;
					
					var activate = false;
					for( var index in conditions ){
						var condition = conditions[index];
						if( condition.parameterName() === parameterName ){
							activate = condition.checkActivate( value );
							if( activate )	return activate;
						}
					}
					
					return activate;
				};
				
				this.order = function( order ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ORDER, arguments));
				};

				this.active = function( active ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ACTIVE, arguments));
				};

				this.disabled = function( disabled ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DISABLED, arguments));
				};

				this.verifyName = function(name){
					if(/[a-zA-Z\\_]/.test(name.charAt(0)) == false) return false;
					if(/[^a-zA-Z0-9\\_]/.test(name))	return false;
					return true;
				};

				this._deserialize = function( key, jsonObject ){
					switch(key){
					case OSP.Constants.ACTIVATE_CONDITIONS:
						var jsonConditions = jsonObject;
						
						for( var index in jsonConditions ){
							var jsonCondition = jsonConditions[index];
							var condition;
							if( jsonCondition[type] === OSP.Constants.VARIABLE_TYPE_LIST )
								condition = this.newListItemActivateCondition( jsonCondition );
							else
								condition = this.newNumericActivateCondition( jsonCondition );
							
							this.addActivateCondition(condition);
						}
						break;
					default:
						this.property( key, jsonObject );	
					}
				};
				
				this._upgrade = function( key, value, newParameter ){
					if( OSP.Util.isEmpty(value) )	return;

					switch( key ){
					case 'name':
						newParameter.name( value );
						break;
					case 'type':
						if( value === 'control_active' || value === 'control_associate')
							value = 'list';
						newParameter.type( value );
						break;
					case 'name-text-map':
						newParameter.nameText( value['map'] );
						break;
					case 'description-map':
						newParameter.description( value['map'] );
						break;
					case 'order':
						newParameter.order( value );
						break;
					case 'active':
						newParameter.active( value );
						break;
					case 'disable':
						newParameter.disabled( value );
						break;
					case 'activate-condition-container':
						if( OSP.Util.isEmpty(value) )	break;
						
						var oldConditions = value['container'];
						if( oldConditions.length === 0 )	break;
						var newConditions = [];
						
						for( var index in oldConditions ){
							var oldCondition = oldConditions[index];
							if( OSP.Util.isEmpty(oldCondition) )	continue;

							var newCondition;
							switch( oldCondition['type'] ){
							case 'numeric':
								newCondition = new NumericActivateCondition();
								break;
							case 'list':
								newCondition = new ListItemActivateCondition();
								break;
							default:
								alert( 'Unknown condition type: '+oldCondition['type'] );
								return;
							}
							newCondition.upgrade( oldCondition );
							newConditions.push( newCondition );
						}
						
						newParameter.activateConditions(newConditions);
						break;
					}
				}
			}; // End of _Parameter 
			
			var NumericParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.NUMERIC);
				this.active(true);
				
				this.unit = function( unit ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.UNIT, arguments));
				};
				
				this.value = function( value ) {
					if( arguments.length === 1 ){
						if( this.verifyRange( value ) === false )
							return false;
					}
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};
				
				this.range = function( range ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments));
				};
				
				this.rangeLowerBoundary = function( boundary ){
					var range = this.range();
					if( !range )	return false;
					
					return range.lowerBoundary.apply( range, arguments );
				};
				
				this.rangeUpperBoundary = function( boundary ){
					var range = this.range();
					if( !range )	return false;
					
					return range.upperBoundary.apply( range, arguments );
				};
				
				this.rangeOperand = function( operand ){
					var range = this.range();
					if( !range )	return false;
					
					return range.operand.apply( range, operand );
				};
				
				this.rangeCheckLowerBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					
					return range.checkLowerBoundary();
				};
				
				this.rangeCheckUpperBoundary = function(){
					var range = this.range();
					if( !range )	return false;
					
					return range.checkUpperBoundary();
				};
				
				this.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = this.range();
					if( !range )	return false;
					
					return range.includeBoundary( whichEnd, flag );
				}
				
				this.verifyRange = function( value ){
					var range = this.range();
					if( !range )	return true;
					return range.verify( value );
				};
				
				this.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = this.range();
					if( !range ){
						range = new Range();
						this.range( range );
					}
					
					return range.setRange.apply( range, arguments );
				};
				
				this.sweep = function( sweep ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEP, arguments));
				};
				
				this.sweepable = function( flag ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEPABLE, arguments));
				};
				
				this.maxSweepSlice = function( max ){
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}

					return sweep.maxSlice.apply( sweep, arguments );
				};
				
				this.sweepMethod = function( method ){
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}

					return sweep.sweepMethod.apply( sweep, arguments );
				};
				
				this.sweepCount = function( count ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_COUNT, arguments));
				};
				
				this.sweepSliceCount = function( count ){
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}

					return sweep.sliceCount.apply( sweep, arguments );
				};
				
				this.sweepSliceValue = function( value ){
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}

					return sweep.sliceValue.apply( sweep, arguments );
				};
				
				this.getSweepSlicedValues = function(){
					var sweep = this.sweep();
					if( !sweep )	return false;
					
					return sweep.getSlicedValues();
				};
				
				this.sweepRange = function( range ){
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}
					
					return sweep.range.apply( sweep, arguments );
				};
				
				this.sweepRangeLowerBoundary = function( boundary ) {
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}
					
					return sweep.rangeLowerBoundary.apply(sweep, arguments);
				};
				
				this.sweepRangeUpperBoundary = function( boundary ) {
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}
					
					return sweep.rangeUpperBoundary.apply(sweep, arguments);
				};
				
				this.sweepRangeOperand = function( operand ) {
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep(sweep);
					}
					
					return sweep.rangeOperand.apply(sweep, arguments);
				};
				
				this.sweepRangeCheckLowerBoundary = function(){
					var sweep = this.sweep();
					if( !sweep )	return false;
					
					return sweep.rangeCheckLowerBoundary();
				};
				
				this.sweepRangeCheckUpperBoundary = function(){
					var sweep = this.sweep();
					if( !sweep )	return false;
					
					return sweep.rangeCheckUpperBoundary();
				};
				
				this.sweepRangeIncludeBoundary = function( whichEnd, flag ){
					var sweep = this.sweep();
					if( !sweep )	return false;
					
					return sweep.rangeIncludeBoundary( whichEnd, flag );
				};

				this.setSweepRange = function(lowerBoundary, upperBoundary, operand) {
					if( Number(lowerBoundary) > Number(upperBoundary) )
						return false;
					if( !this.verifyRange(lowerBoundary) ||
						 !this.verifyRange(upperBoundary))		return false;
					
					
					var sweep = this.sweep();
					if( !sweep ){
						sweep = new Sweep();
						this.sweep( sweep );
					}
					
					return sweep.setRange.apply( sweep, arguments );
				};
				
				this.verifySweepRange = function( value ){
					var sweep = this.getSweep();
					if( !sweep )	return false;
					
					return sweep.verifyRange(value);
				};
				
				this.removeSweep = function(){
					this.removeProperty(OSP.Constants.SWEEPED);
					this.removeProperty(OSP.Constants.SWEEP);
				};
				
				this.sweeped = function( flag ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEPED, arguments));
				};
				
				this.deserialize = function (jsonObject){
					for( var key in jsonObject){
						switch(key){
						case OSP.Constants.RANGE:
							this.range(new Range(jsonObject[key]) );
							break;
						case OSP.Constants.SWEEP:
							this.sweep( new Sweep( jsonObject[key] ) );
							break;
						default:
							this._deserialize(key, jsonObject[key]);
						}
					}
				};
				
				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )continue;

						switch( key ){
							case 'unit':
								this.unit(variable[key]);
								break;
							case 'value':
								this.value(variable[key]);
								break;
							case 'sweeped':
								this.sweeped(variable[key]);
								break;
							case 'sweepable':
								this.sweepable(variable[key]);
								break;
							case 'value-domain':
								var range = new Range();
								range.upgrade( variable[key] );
								this.range( range );
								break;
							case 'sweep':
								var sweep = new Sweep();
								sweep.upgrade( variable[key] );
								this.sweep(sweep);
								break;
							default:
								this._upgrade( key, variable[key], this );
								break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of NumericParameter 
			this.newNumericParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new NumericParameter();
					case 1:
						return new NumericParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newNumericParameter');
						return null;
				}
			};
			
			var ListParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.LIST);
				this.active(true);

				this.value = function( value ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};
				
				this.listItems = function( list ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.LIST_ITEMS, arguments));
				};
				
				this.listItem = function(itemValue, listItem) {
					var listItems = this.listItems();
					
					switch( arguments.length ){
					case 1:
						if( !listItems )	return false;
						return listItems[itemValue];
					case 2:
						if( !listItems ){
							listItems = {};
							this.listItems( listItems );
						}
						return listItems[itemValue] = listItem;
					default:
						return false;
					}
				};
				
				this.addListItem = function( itemValue ){
					var listItems = this.listItems();
					if( !listItems ){
						listItems = {};
						this.listItems( listItems );
					}
					
					var listItem = new ListItem();
					listItem.value( itemValue );
					//console.log( 'list: '+JSON.stringify(listItems,null,4));
					//console.log( 'itemValue: '+itemValue);
					listItems[itemValue] = listItem;
					return listItem;
				};
				
				this.localizedListItemText = function(itemValue, languageId, text){
					var list = this.listItems();
					if( !list )	return false;
					
					var listItem = list[itemValue];
					switch( arguments.length ){
					case 1:
						return listItem;
					case 2:
						return listItem.localizedText( languageId );
					case 3:
						return listItem.localizedText( languageId, text );
					default:
						return false;
					}
				};
				
				this.localizedListItems = function( languageId ){
					var list = this.listItems();
					if( !list )	return false;
					//console.log('List: '+ JSON.stringify(list,null,4));
					var localizedListItems = {};
					for( var key in list ){
						var listItem = list[key];
						localizedListItems[key] = listItem.localizedText(languageId);
					}
					
					return localizedListItems;
				};
				
				this.listXml = function( availablLanguageIds, defaultLanguageId ){
					var list = this.listItems();
					if( !list )	return false;
					
					var listXml = [];
					for( var key in list ){
						var listItem = list[key];
						listXml.push( listItem.textXml(availablLanguageIds, defaultLanguageId) );
					}
					
					return listXml;
				};
				
				this.removeListItem = function(itemValue) {
					var list = this.list();
					if( !list )	return true;
					
					return delete list[itemValue];
				};
			
				this.clone = function(){
					return new ListParameter( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function ( jsonObject ){
					for( var key in jsonObject ){
						switch( key ){
						case OSP.Constants.LIST_ITEMS:
							var jsonList = jsonObject[key];
							for( var index in jsonList ){
								var listItem = new ListItem( jsonList[index] );
								this.listItem( listItem.value(), listItem );
							}
							break;
						default:
							this._deserialize( key, jsonObject[key] );	
						}
					}
				};
				
				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'value':
							this.value(variable[key]);
							break;
						case 'list-map':
							var oldListItems = variable[key]['map'];
							if( oldListItems.length === 0 )	break;
							
							for( var listItemKey in oldListItems ){
								var oldListItem = oldListItems[listItemKey];
								var newListItem = this.addListItem(listItemKey);
								newListItem.upgrade( oldListItem );
							}
							break;
						default:
							this._upgrade( key, variable[key], this );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of ListParameter 
			this.newListParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListParameter();
					case 1:
						return new ListParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newListParameter');
						return null;
				}
			};

			var StringParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.STRING);
				this.active(true);

				this.value = function( value ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				this.clone = function(){
					return new StringParameter( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						this._deserialize( key, jsonObject[key] );
				};

				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;
						
						switch( key ){
						case 'value':
							this.value(variable[key]);
							break;
						default:
							this._upgrade( key, variable[key], this );
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of StringParameter 
			this.newStringParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new StringParameter();
					case 1:
						return new StringParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newStringParameter');
						return null;
				}
			};

			var VectorParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.VECTOR);
				this.active(true);

				this.value = function( value ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};
				
				this.getValueString = function(leftBrace, rightBrace, delimiter, space){
					if(delimiter !== ' ')	delimiter += space;
					var string = JSON.stringify(this.value());
					string = string.replace('[', leftBrace).replace(']', rightBrace).replace(/,/g, delimiter).replace(/\"/gi,"");
					return string;
				};
				
				this.dimension = function( dimension ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DIMENSION, arguments));
				};

				this.clone = function(){
					return new VectorParameter( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						this._deserialize( key, jsonObject[key] );
				};

				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;
						
						switch( key ){
						case 'value':
							this.value(variable[key]);
							break;
						case 'dimension':
							this.dimension(variable[key]);
							break;
						default:
							this._upgrade( key, variable[key], this );
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of VectorParameter 
			this.newVectorParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new VectorParameter();
					case 1:
						return new VectorParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newVectorParameter');
						return null;
				}
			};
			
			var CommentParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.COMMENT);
				this.active(true);

				this.value = function( value ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				this.clone = function(){
					return new CommentParameter( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						this._deserialize( key, jsonObject[key] );
				};

				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;
						
						switch( key ){
						case 'value':
							this.value(variable[key]);
							break;
						default:
							this._upgrade( key, variable[key], this );
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of CommentParameter 
			this.newCommentParameter = function(){
				switch( arguments.length ){
					case 0:
						return new CommentParameter();
					case 1:
						return new CommentParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newCommentParameter');
						return null;
				}
			};

			var GroupParameter = function( jsonObject ){
				_Parameter.apply(this);
				this.type( OSP.Constants.GROUP);
				this.active(true);
				
				this.parameters = function( parameters ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PARAMETERS, arguments));
				};

				this.attachParameter = function( parameterName ){
					var parameters = this.parameters();
					if( !parameters ){
						parameters = [];
						this.parameters( parameters );
					}
					
					parameters.push( parameterName );
					return parameters;
				};
				
				this.detachParameter = function( parameterName ){
					var parameters = this.parameters();
					if( !parameters )	return true;
					
					for( var index in parameters ){
						var parameter = conditions[index];
						if( parameter === parameterName ){
							parameters.splice( index, 1 );
							return parameters;
						}
					}
					
					return parameters;
				};
				
				this.hasParameter = function( parameterName ){
					var parameters = this.parameters();
					for(var index in parameters ){
						if(parameters[index] === parameterName )	return true;
					}
					return false;
				};
				
				this.clone = function(){
					return new CommentParameter( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						this._deserialize( key, jsonObject[key] );
				};

				this.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;
						
						switch( key ){
						case 'activate-condition-container':
							var parameters = [];
							var oldConditions = variable[key]['container'];
							for( var index in oldConditions ){
								parameters.push( oldConditions[index]['variable-name'] );
							}
							this.parameters( parameters );
							break;
						default:
							this._upgrade( key, variable[key], this );
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of GroupParameter 
			this.newGroupParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new GroupParameter();
					case 1:
						return new GroupParameter(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newGroupParameter');
						return null;
				}
			};

			var VectorForm = function( jsonObject ) {
				OSP._MapObject.apply( this );
				
				this.setForm = function(braceChar, delimiter, sample) {
					this.braceChar( braceChar );
					this.delimiter( delimiter );
					this.sample( sample );
				};
				this.braceChar = function( char ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.BRACE_CHAR, arguments));
				};

				this.delimiter = function( delimiter ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DELIMITER, arguments));
				};

				this.space = function( space ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SPACE, arguments));
				};
				
				this.sample = function( sample ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments));
				};

				this.clone = function(){
					return new VectorForm( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonForm ){
					for( var key in jsonForm ){
						this._deserialize( key, jsonForm[key] );
					}
				};
				
				this.upgrade = function( oldForm ){
					for( var key in oldForm ){
						if( OSP.Util.isEmpty(oldForm[key]) )	continue;
						//console.log( key + ": " + oldForm[key]);
						switch( key ){
						case 'brace-char':
							this.braceChar(oldForm[key]);
							break;
						case 'delimiter':
							this.delimiter(oldForm[key]);
							break;
						case 'space':
							this.space( oldForm[key] );
							break;
						case 'sample':
							this.sample( oldForm[key] );
							break;
						default:
							alert( 'Unknown vector form key: '+key);
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			}; // End of VectorForm 
			this.newVectorForm = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new VectorForm();
					case 1:
						return new VectorForm(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newVectorForm');
						return null;
				}
			};

			var ParameterForm = function( jsonObject ) {
				OSP._MapObject.apply( this );
				
				this.setForm = function(valueDelimiter, parameterDelimiter, commentChar, controlChar) {
					this.valueDelimiter( valueDelimiter );
					this.parameterDelimiter( parameterDelimiter );
					this.commentChar( commentChar );
					this.controlChar( controlChar );
				};
				
				this.valueDelimiter = function( delimiter ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VALUE_DELIMITER, arguments));
				};

				this.parameterDelimiter = function( delimiter ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_DELIMITER, arguments));
				};
				
				this.commentChar = function( char ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMMENT_CHAR, arguments));
				};
				
				this.space = function( space ) {
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SPACE, arguments));
				};
				
				this.controlChar = function( char ){
					return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.CONTROL_CHAR, arguments));
				};
				
				this.clone = function(){
					return new ParameterForm( OSP.Util.toJSON(this) );
				};
				
				this.deserialize = function( jsonForm ){
					for( var key in jsonForm ){
						this._deserialize( key, jsonForm[key] );
					}
				};
				
				this.upgrade = function( oldForm ){
					for( var key in oldForm ){
						if( OSP.Util.isEmpty(oldForm[key]) )	continue;
						
						switch( key ){
						case 'value-delimiter':
							this.valueDelimiter(oldForm[key]);
							break;
						case 'variable-delimiter':
							this.parameterDelimiter(oldForm[key]);
							break;
						case 'space':
							this.space( oldForm[key] );
							break;
						case 'control-char':
							this.controlChar( oldForm[key] );
							break;
						case 'comment-char':
							this.commentChar( oldForm[key] );
							break;
						default:
							alert( 'Unknown parameter form key: '+key);
							break;
						}
					}
				};
				
				if( arguments.length === 1 )
					this.deserialize( jsonObject );
			}; // End of ParameterForm 
			this.newParameterForm = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ParameterForm();
					case 1:
						return new ParameterForm(jsonObject);
					default: 
						colsole.log( 'Arguments mismatch: newParameterForm');
						return null;
				}
			};

			this.vectorForm = function( form ) {
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.VECTOR_FORM, arguments));
			};
			
			this.setVectorForm = function(braceChar, delimiter, sample) {
				var vectorForm = this.vectorForm();
				if( !vectorForm ){
					vectorForm = this.newVectorForm();
					this.vectorForm( vectorForm );
				}
				
				return vectorForm.setForm(braceChar, delimiter, sample);
			};
			
			this.vectorFormBraceChar = function( char ) {
				var vectorForm = this.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.braceChar.apply(vectorForm, arguments);
			};

			this.vectorFormDelimiter = function( delimiter ) {
				var vectorForm = this.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.delimiter.apply(vectorForm, arguments);
			};
				
			this.vectorFormSpace = function( space ) {
				var vectorForm = this.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.space.apply(vectorForm, arguments);
			};
				
			this.vectorFormSample = function( sample ) {
				var vectorForm = this.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.sample.apply(vectorForm, arguments);
			};
				
			this.parameterForm = function( form ) {
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_FORM, arguments));
			};
				
			this.setParameterForm = function(valueDelimiter, parameterDelimiter, commentChar, controlChar) {
				var parameterForm = this.parameterForm();
				if( !parameterForm ){
					parameterForm = new ParameterForm();
					this.parameterForm( parameterForm );
				}
				
				return parameterForm.setForm(valueDelimiter, parameterDelimiter, commentChar, controlChar);
			};
			
			this.parameterFormValueDelimiter = function( delimiter ) {
				var form = this.parameterForm();
				if( !form )	return false;
				return form.valueDelimiter.apply(form, arguments);
			};

			this.parameterFormParameterDelimiter = function( delimiter ) {
				var form = this.parameterForm();
				if( !form )	return false;
				return form.parameterDelimiter.apply(form, arguments);
			};
				
			this.parameterFormCommentChar = function( char ) {
				var form = this.parameterForm();
				if( !form )	return false;
				return form.commentChar.apply(form, arguments);
			};
			
			this.parameterFormControlChar = function( char ) {
				var form = this.parameterForm();
				if( !form )	return false;
				return form.controlChar.apply(form, arguments);
			};

			this.parameterFormSpace = function( space ) {
				var form = this.parameterForm();
				if( !form )	return false;
				return form.space.apply(form, arguments);
			};

			this.parameters = function( parameters ) {
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PARAMETERS, arguments));
			};
			
			this.parameter = function( parameterName ){
				var parameters = this.parameters();
				if( !parameters )	return false;
				
				if( parameters.hasOwnProperty(parameterName) )
					return parameters[parameterName];
				else
					return false;
			};
			
			this.parameterByOrder= function( order, groupOrder ){
				var parameters = this.parameters();
				if( !parameters )	return false;
				
				for( var key in parameters ){
					var parameter = parameters[key];
					
					if( !groupOrder ){
						if( !this.isInGroup( parameter.name() ) ){
							if( parameter.order() == order)	return parameter;
						}
					}
					else{
						var group = this.parameterByOrder(groupOrder);
						var names = group.getAttachedParameterNames();
						for(var i in names ){
							parameter = this.parameter(names[i]);
							if(parameter.order() == order)	return parameter;
						}
					}
				}
			};
			
			this.topLevelParameters = function(){
				var parameters = this.parameters();
				if( !parameters )	return false;
				
				var topLevels = [];
				for( var key in parameters ){
					var parameter = parameters[key];
					if( !this.isInGroup(parameter.name()) )
						topLevels.push( parameter );
				}
				return topLevels;
			};
			
			this.activeParameters = function(){
				var parameters = this.parameters();
				if( !parameters )	return false;
				
				var actives = [];
				for( var key in parameters ){
					var parameter = parameters[key];
					if( parameter.active() )
						actives.push( parameter );
				}
				return actives;
			};
			
			this.addParameter = function( parameter ) {
				var parameters = this.parameters();
				if( !parameters ){
					parameters = {};
					this.parameters( parameters );
				};
				
				parameters[parameter.name()] = parameter;
				return parameters;
			};
			
			this.removeParameter = function( parameterName ) {
				var parameters = this.parameters();
				if( !parameters )	return true;
				
				delete parameters[parameterName];
				
				return parameters;
			};
			
			this.newParameter = function( parameterName, type ){
				if( this.verifyParameterName(parameterName) == false )
					return false;
					
				var parameters = this.parameters();

				var parameter;
				switch( type ){
					case OSP.Constants.NUMERIC:
						parameter = this.newNumericParameter();
						break;
					case OSP.Constants.LIST:
						parameter = this.newListParameter();
						break;
					case OSP.Constants.STRING:
						parameter = this.newStringParameter();
						break;
					case OSP.Constants.VECTOR:
						parameter = this.newVectorParameter();
						break;
					case OSP.Constants.COMMENT:
						parameter = this.newCommentParameter();
						break;
					case OSP.Constants.GROUP:
						parameter = this.newGroupParameter();
						break;
					case OSP.Constants.FILE:
					default:
						parameter = false;
				}

				parameter.name( parameterName );
				this.addParameter(parameter);
				return parameter;
			};
			
			this.sweepMax = function( max ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_MAX, arguments));
			};
			
			this.sweepCount = function( count ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_COUNT, arguments));
			};
			
			this.increaseSweepCount = function(){
				var count = this.sweepCount();
				this.sweepCount(++count);
				return this.sweepCount();
			};
			
			this.decreaseSweepCount = function(){
				var count = this.sweepCount();
				this.sweepCount(count>0 ? --count : 0);
				return this.sweepCount();
			};
			
			this.sweepedParameters = function(){
				var parameters = this.parameters();
				
				var sweepedParameters = [];
				for(var i in parameters ){
					var parameter = parameters[i];
					if( parameter.sweeped() )
						sweepedParameters.push( parameter );
				}

				return sweepedParameters;
			};
			
			this.cleanSweepedParameters = function(){
				var parameters = this.sweepedParameters();
				if( OSP.Util.isEmpty(parameters) )	return false;
				
				for( var index in parameters ){
					var parameter = parameters[index];
					parameter.removeSweep();
				}
				
				return true;
			};
			
			this.sweeped = function(){
				if( this.sweepCount() === 0)	return false;
				else		return true;
			};
			
			this.defaultLanguageId = function( languageId ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_LANGUAGE_ID, arguments));
			};
			
			this.availableLanguageIds = function( languageIds ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_LANGUAGE_IDS, arguments));
			};
			
			this.addLanuageId = function( languageId ){
				var languageIds = this.availableLanguageIds();
				if( !languageIds ){
					languageIds = [];
					this.availableLanguageIds(languageIds);
				}
				
				for( var index in languageIds){
					if( languageIds[index] === languageId )	return false;
				}
				
				languageIds.push(languageId);
				return true;
			};
			
			this.removeLanguageId = function( languageId ){
				var languageIds = this.availableLanuageIds();
				if( !languageIds )	return true;
				
				for( var index in languageIds){
					if( languageIds[index] === languageId ){
						languageIds.splice( index, 1);
						return true;
					}
				}
				
				return false;
			};
			
			this.verifyParameterName = function( parameterName ){
				if(/[a-zA-Z\\_]/.test(parameterName.charAt(0)) === false) return false;
				if(/[^a-zA-Z0-9\\_]/.test(parameterName))		return false;
				
				var parameters = this.parameters();
				if( !parameters )	return true;
				
				for( var i in parameters ){
					var parameter = parameters[i];
					if( parameter.name() === parameterName )
						return false;
				}
				
				return true;
			};
			
			this.cloneParameter = function( parameter, count ){
				var jsonData = JSON.stringify( parameter );
				//console.log(JSON.stringify(parameter));
				var prefix = parameter.name();
				var index;
				var clones = [];
				for(index=0; index<count; index++){
					var name = prefix + "_" + (index+1);
					var emptyIndex = index+1;
					
					while(!this.verifyParameterName(name)){
						name = prefix + "_" + emptyIndex;
						emptyIndex++;
					}
					
					var clone = this.newParameter( name, parameter.type());
					var data = JSON.parse(jsonData);
					clone.deserialize(data);
					if( clone.verifyParameterName(name) ){
						clone.name(name);
						clone.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);
						clones.push(clone);
						//console.log(JSON.stringify(clone));
					}
				}
				
				//console.log("index: "+index);
				if( index == count ){
					for( index = 0; index < count; index++ ){
						this.addParameter(clones[index]);
					}
				}
				else
					return null;
				
				return clones;
			};
			
			this.cloneParameterGroup = function( groupParameter, count ){
				if( groupParameter.type() !== OSP.Constants.GROUP )
					return [];
				
				var newGroups = this.cloneParameter( groupParameter, count );
				if( newGroups == null )	return null;
				
				for( var i in newGroups ){
					var group = newGroups[i];
					group.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);
					
					var orgSet = groupParameter.activateConditions();
					for( var j in orgSet ){
						var orgParamName = orgSet[j].parameterName();
						var orgParameter = this.getParameter( orgParameterName );
						var paramClones = this.cloneParameter( orgParameter, 1 );
						
						var clone = paramClones[0];
						this.addParameter(clone);
						group.attachParameter( clone.name() );
					}
				}
				
				return newGroups;
			};
			
			this.parameterNames = function(){
				var parameters = this.parameters();
				if( !parameters )	return false;
				
				var names = [];
				switch( arguments.length ){
				case 0:
					for( var index in parameters ){
						names.push( parameters[index].name() );
					}
					break;
				default:
					for(var index = 0; index < arguments.length; index++) {
						for( var param in parameters ){
							if( parameters[param].type() === arguments[index] ){
								names.push( parameters[param].name() );
							}
						}
					}
				}
				return names;
			};
			
			this.isInGroup = function(parameterName){
				var names = this.parameterNames(OSP.Constants.GROUP);
				//console.log(JSON.stringify(names));
				for(var index in names ){
					var group = this.parameter(names[index]);
					if( group.hasParameter(parameterName))	return true;
				}
				return false;
			};
			
			this.deserialize = function( jsonObject ){
				//console.log( JSON.stringify(jsonObject,null,4));
				for( var key in jsonObject ){
					switch ( key ){
					case OSP.Constants.VECTOR_FORM:
						this.vectorForm( this.newVectorForm( jsonObject[key]) );
						break;
					case OSP.Constants.PARAMETER_FORM:
						this.parameterForm( this.newParameterForm( jsonObject[key]) );
						break;
					case OSP.Constants.PARAMETERS:
						var jsonParameters = jsonObject[key];
						for( var i in jsonParameters ){
							var jsonParameter = jsonParameters[i];
							var parameter;
							switch( jsonParameter[OSP.Constants.TYPE] ){
							case OSP.Constants.COMMENT:
								parameter = this.newCommentParameter( jsonParameter );
								break;
							case OSP.Constants.GROUP:
								parameter = this.newGroupParameter( jsonParameter );
								break;
							case OSP.Constants.LIST:
								parameter = this.newListParameter( jsonParameter );
								break;
							case OSP.Constants.NUMERIC:
								parameter = this.newNumericParameter( jsonParameter );
								break;
							case OSP.Constants.STRING:
								parameter = this.newStringParameter( jsonParameter );
								break;
							case OSP.Constants.VECTOR:
								parameter = this.newVectorParameter( jsonParameter );
								break;
							default:
								console.log('Un-recognizable parameter type: '+jsonParameter[OSP.Constants.TYPE]);
								return false;
							}
							
							this.addParameter(parameter);
						}
						break;
					default:
						//console.log( key+':'+jsonObject[key]);
						this._deserialize( key, jsonObject[key] );
					}
				}
			};
			
			this.clone = function(){
				return new DataStructure( OSP.Util.toJSON(this) );
			};
			
			this.activeParameterFormattedInputs = function( page ){
				//console.log( this);
				var parameterDelimiter = this.parameterFormParameterDelimiter();
				if( !parameterDelimiter ){parameterDelimiter = '\n';}else{ parameterDelimiter+='\n'; }
				var valueDelimiter = this.parameterFormValueDelimiter();
				var parameterSpaceChar = this.parameterFormSpace() ? parameterSpaceChar = ' ': parameterSpaceChar = '';
				var parameterCommentChar = this.parameterFormCommentChar();
				var vectorBraceChar = this.vectorFormBraceChar();
				var vectorDelimiter = this.vectorFormDelimiter();
				
				function getFormattedValue(parameter){
					var formattedString = [];
					switch(parameter.type()){
					case OSP.Constants.NUMERIC:
						if( parameter.sweeped() ){
							var sweepValues = parameter.getSweepSlicedValues();
							
							for(var i=0; i<sweepValues.length; i++){
								formattedString[i] =  
									parameter.name() + parameterSpaceChar + valueDelimiter + 
									parameterSpaceChar + sweepValues[i] + parameterSpaceChar + parameterDelimiter;
							}
						}
						else
							formattedString[0] = 
								parameter.name() + parameterSpaceChar + valueDelimiter + 
								parameterSpaceChar + parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.LIST:
					case OSP.Constants.STRING:
						formattedString[0] = 
							parameter.name() + parameterSpaceChar + valueDelimiter + 
							parameterSpaceChar + parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.COMMENT:
						formattedString[0] = parameterCommentChar +
							parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.VECTOR:
						var startBraceChar, endBraceChar; 
						var vectorSpace;
						switch( vectorBraceChar ){
						case OSP.Constants.ROUND:
							startBraceChar = '(';
							endBraceChar = ')';
							vectorSpace = '';
							break;
						case OSP.Constants.ROUND_SPACE:
							startBraceChar = '( ';
							endBraceChar = ' )';
							vectorSpace = ' ';
							break;
						case OSP.Constants.SQUARE:
							startBraceChar = '[';
							endBraceChar = ']';
							vectorSpace = '';
							break;
						case OSP.Constants.SQUARE_SPACE:
							startBraceChar = '[ ';
							endBraceChar = ' ]';
							vectorSpace = ' ';
							break;
						}
						
						formattedString[0] = 
							parameter.name() + parameterSpaceChar + valueDelimiter + parameterSpaceChar +
							parameter.getValueString(
									startBraceChar,
									endBraceChar,
									vectorDelimiter,
									vectorSpace) + parameterSpaceChar + parameterDelimiter; 
						break;
					default:
						break;
					}
					//console.log(formattedString);
					return formattedString;
				};

				function getFormattedInputs(input, parameters){
					var inputs = [];
					for(var index in parameters){
						if( typeof parameters[index] === 'function') continue;
						
						var parameter = parameters[index];
						var values = getFormattedValue(parameter);
						switch(values.length){
						case 0:
							break;
						case 1:
							input.push(values[0]);
							break;
						default:
							var sweepInputs = [];
							for(var j=0; j<values.length; j++){
								var cloneInput = input.slice(0);
								cloneInput.push(values[j]);
								var newInputs = getFormattedInputs(cloneInput, parameters.slice(index+1));
								
								sweepInputs = sweepInputs.concat(newInputs);
							}
							return sweepInputs;
						}
					}
					//this.setFileContent(input.join("\n")+"\n");
					//inputs.push(this.clone());
					inputs.push(input);
					return inputs;
				};

				var activeParameters = this.activeParameters();
				
				var formattedInputs = getFormattedInputs([], activeParameters);
				//console.log(JSON.stringify(formattedInputs, null, 4));
				if( arguments.length === 1)
					return formattedInputs[page];
				else
					return formattedInputs;
			};
			
			this.setAllParametersInactive = function(){
				var parameters = this.getActiveParameters();
				for(var i=0; i<parameters.length; i++){
					var parameter = parameters[i];
					switch(parameter.type()){
					case OSP.Constants.COMMENT:
					case OSP.Constants.GROUP:
						break;
					default:
						parameter.active(false);
					}
				}
			};
			
			this.loadInput = function(input){
				var valueDelimiter = this.parameterFormValueDelimiter();
				var parameterDelimiter = this.parameterFormParameterDelimiter();
				if( !parameterDelimiter )	parameterDelimiter = '\n';
				var commentChar = this.parameterFormCommentChar();
				var lines =input.split(parameterDelimiter);
				this.setAllParametersInactive();
				
				for(var i in lines){
					var line = lines[i].trim().split(valueDelimiter);
					//console.log(line);
					var parameterName = line[0].trim();
					if( parameterName.length === 0) continue;
					var parameterValue;
					if ( parameterName.charAt(0) == commentChar  )
						parameterValue = parameterName.slice(1);
					else {
						parameterValue = line[1].trim();
						var parameter = this.parameter(parameterName);
						//console.log('parameter value: '+parameter.type());
						switch(parameter.type()){
						case OSP.Constants.VECTOR:
							parameter.value(JSON.parse(parameterValue));
							break;
						case OSP.Constants.NUMERIC:
							if(parameter.sweeped() === true){
								parameter.sweeped(false);
								this.decreaseSweepCount();
							}
							//alert(parameterName + ': '+parameter.sweeped());
						default:
							parameter.value(parameterValue);
							//console.log('value: '+parameterValue);
						}
							
						parameter.active(true);
					}
				}
			};
			
			this.upgrade = function ( oldStructure, newCanvas ){
				for( var key in oldStructure ){
					//console.log( 'key: '+key);
					if( OSP.Util.isEmpty( oldStructure[key] ) )
						continue;
					
					switch( key ){
					case 'sweep-max':
						this.sweepMax(oldStructure[key]);
						break;
					case 'variable-map':
						var variables = oldStructure[key]['map'];
						for( var variableName in variables ){
							var variable = variables[variableName];
							if( OSP.Util.isEmpty(variable) )	continue;
							
							var parameter;
							switch( variable['type']){
							case 'numeric':
								parameter = this.newNumericParameter();
								break;
							case 'control_active':
							case 'control_associate':
							case 'list':
								parameter = this.newListParameter();
								break;
							case 'string':
								parameter = this.newStringParameter();
								break;
							case 'vector':
								parameter = this.newVectorParameter();
								break;
							case 'group':
								parameter = this.newGroupParameter();
								break;
							case 'comment':
								parameter = this.newCommentParameter();
								break;
							default:
								alert('Unknown variable type: '+variable['type']);
								return;
							}
							
							parameter.upgrade(variable);
							this.addParameter( parameter );
						}
						break;
					case 'vector-form':
						var vectorForm = new VectorForm();
						vectorForm.upgrade( oldStructure[key] );
						this.vectorForm(vectorForm);
						break;
					case 'variable-form':
						var parameterForm = new ParameterForm();
						parameterForm.upgrade( oldStructure[key] );
						this.parameterForm(parameterForm);
						break;
					case 'default-language-id':
						this.defaultLanguageId(oldStructure[key]);
						break;
					case 'available-language-ids':
						var value = oldStructure[key].replace('{', '').replace('}', '');
						var idStrs =value.split(','); 
						var languageIds = [];
						for( var index in idStrs ){
							var id = idStrs[index].trim();
							if( id.length > 0 )
								languageIds.push( id );
						}
						this.availableLanguageIds(languageIds);
						break;
					default:
						alert( 'Unknown data structure key: '+key);
						break;
					}
				}
				
				newCanvas.append( JSON.stringify(this,null,4));
			};
			
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of DataStructure 
		this.newDataStructure = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new DataStructure();
				case 1:
					return new DataStructure(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newDataStructure');
					return null;
			}
		};

		this.structure = function( structure ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.STRUCTURE, arguments));
		};
		
		this.defaultEditor = function( uuid ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_EDITOR, arguments));
		};
		
		this.defaultAnalyzer = function( uuid ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_ANALYZER, arguments));
		};
		
		this.availableEditors = function( editors ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_EDITORS, arguments));
		};
		
		this.availableAnalyzers = function( analyzers ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_ANALYZERS, arguments));
		};
		
		this.addEditor = function( editor ){
			var editors = this.availableEditors();
			if( !editors ){
				editors = [];
				this.availableEditors(editors);
			}
			editors.push(editor);
			
			return editors;
		};
		
		this.removeEditor = function( appId ){
			var editors = this.availableEditors();
			if( !editors )	return false;
			
			for( var key in editors ){
				if( editors[key] === appId ){
					editors.splice( key, 1);
					if( appId === this.defaultEditor() )
						this.removeProperty(OSP.Constants.DEFAULT_EDITOR);
					return true;
				}
			}
			
			return false;
		};
		
		this.addAnalyzer = function( editor ){
			var analyzers = this.availableAnalyzers();
			if( !analyzers ){
				analyzers = [];
				this.availableAnalyzers(analyzers);
			}
			analyzers.push(editor);
			
			return analyzers;
		};
		
		this.removeAnalyzer = function( uuid ){
			var analyzers = this.availableAnalyzers();
			
			if( !analyzers )	return false;
			
			for( var key in analyzers ){
				if( analyzers[key] === uuid ){
					analyzers.splice( key, 1);
					if( uuid === this.defaultAnalyzer() )
						this.removeProperty(OSP.Constants.DEFAULT_ANALYZER);
					return true;
				}
			}
			
			return false;
		};
		
		this.clone = function(){
			return new OSP.DataType( OSP.Util.toJSON(this) );
		};
		
		var displayUI =  function( dataStructure, namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			canvas.empty();
			var Layout;
			var Style;
			
			var Util = {
				refreshJSON : function(){
					if( !jsonDisplay )	return;
					jsonDisplay.empty();
					jsonDisplay.html(JSON.stringify(dataStructure, null, 4));
					if( !jsonInputDisplay )	return;
					jsonInputDisplay.empty();
					jsonInputDisplay.html(JSON.stringify(dataStructure.getActiveParameterFormattedInputs(), null, 4));
				},
				convertUndefinedToEmptyString : function(value){
					if( !value )
						return '';
					else
						return value;
				},
				isFormView: function(flag){
					if( !flag || flag == false )
						return false;
					else return true;
				}
			}; // End of Util 

			var TagAttr = {
				valueRangeInputId : function(parameter){
					return namespace+'_'+parameter.name()+'_range';
				},
				valueRangeInputName : function(parameter){
					return namespace+'_'+parameter.name()+'_range';
				},
				sweepCheckBoxId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweepCheckBox';
				},
				sweepMethodRadioBySliceId: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethodBySlice';
				},
				sweepMethodRadioName: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethod';
				},
				sweepMethodRadioByValueId: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethodByValue';
				},
				sweepRangeLowerBoundaryId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_lowerBoundary';
				},
				sweepRangeUpperBoundaryId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_upperBoundary';
				},
				sweepRangeLowerOperandId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_lowerOperand';
				},
				sweepRangeUpperOperandId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_upperOperand';
				},
				sweepSliceValueId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValue';
				},
				sweepSliceValueName : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValue';
				},
				sweepSliceValueUnitId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValueUnit';
				},
				vectorInputDivId : function(parameter){
					return namespace+'_'+parameter.name()+'_vectorInputDiv';
				},
				parameterRowId : function(parameter){
					return namespace+'_'+parameter.name();
				},
				inputPageId: function(page){
					return namespace+'_page_'+page;
				}
			}; // End of TagAttr 

			var valueRangeBoundaryDiv = function(parameter, place){
				var boundary;
				var div = $('<div/>');
				if( place === OSP.Constants.LOWER_BOUNDARY){
					boundary = parameter.rangeLowerBoundary();
					div.prop('style', Style.ValueRangeLowerBoundaryDiv);
				}
				else{
					boundary = parameter.rangeUpperBoundary();
					div.prop('style', Style.ValueRangeUpperBoundaryDiv);
				}
				div.append( boundary );
				
				return div;
			};
			var rangeUnitDiv = function(parameter){
				var unit = parameter.unit();
				if( !unit )
					unit = '';
				
				var div = $('<div/>');
				div.prop('style', Style.RangeUnitDiv);
				div.append(unit);
				
				return div;
			};
			var valueRangeOperandDiv = function(parameter, place){
				var div = $('<div/>');
				div.prop('style', Style.ValueRangeOperandDiv);
				var operand;
				if( place === OSP.Constants.LOWER_BOUNDARY ){
					if( parameter.rangeCheckLowerBoundary() )
						operand = '&le;';
					else
						operand = '<';
				}
				else {
					if( parameter.rangeCheckUpperBoundary())
						operand = '&le;';
					else
						operand = '<';
				}
				div.append(operand);
				
				return div;
			};
			var valueRangeInputDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop('style', Style.ValueRangeInputDiv);
				
				var input = $('<input>');
				input.prop({
					'type':'text',
					'id' : TagAttr.valueRangeInputId(parameter),
					'name': TagAttr.valueRangeInputName(parameter),
					'style': Style.ValueRangeInput,
					'value': parameter.value()
				});
				
				if( eventFlag === true ){
					//console.log('onChange Handler binded...');
					var onInputChange = function (event){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						if( parameter.value(newValue) == false ){
							alert(newValue+' is out of the range.');
							event.data.sourceTag.val(parameter.value());
						}
						checkActivateParameters(parameter);
						Util.refreshJSON();
					}
					
					input.bind('change', 
							{
								'sourceTag': input,
								'sourceParameter':parameter
							},
							onInputChange
					);

					div.setParameterValue = function(value){
						input.prop('value', value);
						input.trigger( "change" );
					};
				}
				
				div.append(input);
				
				return div;
			};
			var sweepCheckBoxDiv = function(parameter){
				var div = $('<div/>');
				div.prop('style', Style.SweepCheckBoxDiv);
				//div.addClass(Layout.SweepCheckBoxDiv);
				var label = $('<label/>');
				label.prop('style', Style.SweepRadio);
				var checkBox = $('<input type=\"checkbox\" >Sweep</input>');
				if( parameter.sweeped() )	checkBox.prop('checked', 'checked');
				var id = TagAttr.sweepCheckBoxId(parameter);
				checkBox.prop('id', id);
				
				label.append(checkBox);
				div.append(label);
				
				return div;
			};
			var sweepMethodDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValue);
				div.addClass(Layout.SweepMethodDiv);
				div.prop('style', Style.SweepMethodDiv);
				
				var divRadio = $('<div/>');
				divRadio.addClass(Layout.SweepRadioBySlice);
				
				var label = $("<label/>");
				var radioBySlice = $('<input type=\"radio\" value=\"slice\">By Slice</input>');
				//radioBySlice.addClass(Layout.SweepRadioBySlice);
				radioBySlice.prop({
					'id': TagAttr.sweepMethodRadioBySliceId(parameter),
					'name': TagAttr.sweepMethodRadioName(parameter),
					'style': Style.SweepRadioBySlice
				});
				label.append(radioBySlice)
				divRadio.append(label);
				div.append(divRadio);
				
				divRadio = $('<div/>');
				divRadio.addClass(Layout.SweepRadioByValue);
				label = $("<label/>");
				
				var radioByValue = $('<input type=\"radio\" value=\"value\">By Value</input>');
				//radioByValue.addClass(Layout.SweepRadioByValue);
				radioByValue.prop({
					'id': TagAttr.sweepMethodRadioByValueId(parameter),
					'name': TagAttr.sweepMethodRadioName(parameter),
					'style': Style.SweepRadioByValue
				});
				
				if( parameter.sweepMethod() == OSP.Enumeration.SweepMethod.BY_SLICE )
					radioBySlice.prop('checked', 'checked');
				else
					radioByValue.prop('checked', 'checked');
				
				label.append(radioByValue)
				divRadio.append(label);
				div.append(divRadio);
				
				return div;
			};
			
			function onSweepChange(event){
				var newValue = event.data.sourceTag.val();
				var section = event.data.section;
				var parameter = 	event.data.sourceParameter;
				switch(section){
				case OSP.Constants.LOWER_BOUNDARY:
					console.log('sweepRangeLowerBoundary: '+newValue);
					if(parameter.sweepRangeLowerBoundary(newValue) == false){
						alert(newValue+' is out of the range.');
						event.data.sourceTag.val(parameter.sweepRangeLowerBoundary());
					}
					//console.log(JSON.stringify(parameter.getSweep(), null, 4));
					break;
				case OSP.Constants.UPPER_BOUNDARY:
					//console.log('sweepRangeUpperBoundary: '+parameter.sweepRangeUpperBoundary());
					if(parameter.sweepRangeUpperBoundary(newValue) == false){
						alert(newValue+' is out of the range.');
						event.data.sourceTag.val(parameter.sweepRangeUpperBoundary());
					}
					//console.log(JSON.stringify(parameter.sweep(), null, 4));
					break;
				case OSP.Constants.LOWER_OPERAND:
				//console.log('lower operand changed: '+newValue);
				if( newValue === '=')
						parameter.sweepRangeIncludeBoundary(OSP.Constants.LOWER_BOUNDARY, true);
					else
						parameter.sweepRangeIncludeBoundary(OSP.Constants.LOWER_BOUNDARY, false);
					break;
				case OSP.Constants.UPPER_OPERAND:
					//console.log('upper operand changed: '+newValue);
					if( newValue === '=')
						parameter.sweepRangeIncludeBoundary(OSP.Constants.UPPER_BOUNDARY, true);
					else
						parameter.sweepRangeIncludeBoundary(OSP.Constants.UPPER_BOUNDARY, false);
					break;
				case OSP.Enumeration.DivSection.SWEEP_SLICE_VALUE:
					var maxSlice = Number(parameter.maxSweepSlice());
					var sweepMethod = parameter.sweepMethod();
					console.log('Sweep Method: '+ sweepMethod);
					if( sweepMethod === OSP.Enumeration.SweepMethod.BY_SLICE ){
						console.log('new Slice Count: '+newValue);
						if(newValue > maxSlice || newValue < 2){
							alert('Slice should be between 2 and ' + maxSlice + '.');
							event.data.sourceTag.val( newValue < 2 ? 2 : parameter.sweepSliceCount() );
						}
						else
							parameter.sweepSliceCount( newValue );
					}
					else{
						var slices = OSP.Util.safeFloatSubtract(parameter.sweepRangeUpperBoundary(), 
																									parameter.sweepRangeLowerBoundary()) / newValue;
						if( slices > maxSlice || slices < 2){
							alert('Slice should be between 2 and ' + maxSlice + ' by slice value.');
							event.data.sourceTag.val(parameter.sweepSliceValue());
						}
						else
							parameter.sweepSliceValue(newValue);
						console.log(JSON.stringify(parameter.getSweep(), null, 4));
					}
					break;
				default:
				}
				Util.refreshJSON();
			}
			
			var sweepRangeBoundaryDiv = function(parameter, place){
				var limit;
				var div = $('<div/>');
				div.prop('style', Style.SweepRangeLowerBoundaryDiv);
				var input = $('<input/>');
				var inputId;
				if( place === OSP.Constants.LOWER_BOUNDARY){
					limit = parameter.sweepRangeLowerBoundary();
					inputId = TagAttr.sweepRangeLowerBoundaryId(parameter);
				}
				else{
					limit = parameter.sweepRangeUpperBoundary();
					inputId = TagAttr.sweepRangeUpperBoundaryId(parameter);
				}
				var props = {
					'type' : 'text',
					'id' : inputId,
					'value' : limit,
					'style' : Style.SweepRangeLowerBoundary
				};
				input.prop(props);
				input.bind('change', 
						{
							'sourceTag': input,
							'sourceParameter':parameter,
							'section' : place
						},
						onSweepChange
				);
				div.append(input);
				
				return div;
			};
			var sweepRangeOperandDiv = function(parameter, place){
				var div = $('<div/>'); 
				div.prop('style', Style.SweepRangeOperandDiv);
				var select = $('<select/>');
				select.prop('style', Style.SweepRangeOperandSelect);
				var selectId;
				var optionE, optionT;
				
				if( place === OSP.Constants.LOWER_OPERAND ){
					selectId = TagAttr.sweepRangeLowerOperandId(parameter);
					optionE = $('<option value=\"=\">&le;</option>');
					optionT = $('<option value=\"&lt;\">&lt;</option>');
					//console.log('isSweepRangeLowerBoundaryContained :' + parameter.isSweepRangeLowerBoundaryContained());
					if(parameter.sweepRangeCheckLowerBoundary() == true)
						optionE.prop('selected', 'selected');
					else
						optionT.prop('selected', 'selected');
				}
				else{
					//console.log('isSweepRangeUpperBoundaryContained :' + parameter.isSweepRangeUpperBoundaryContained());
					selectId = TagAttr.sweepRangeUpperOperandId(parameter);
					optionE = $('<option value=\"=\">&le;</option>');
					optionT = $('<option value=\"&gt;\">&lt;</option>');
					if(parameter.sweepRangeCheckUpperBoundary() == true)
						optionE.prop('selected', 'selected');
					else
						optionT.prop('selected', 'selected');
				}

				select.append(optionE);
				select.append(optionT);
				select.bind('change', 
						{
							'sourceTag': select,
							'sourceParameter':parameter,
							'section' : place
						},
						onSweepChange
				);

				div.append(select);
				return div;
			};
			var sweepSliceValueDiv = function(parameter){
				var div = $('<div/>');
				div.prop('style', Style.SweepSliceValueDiv);
				var input = $('<input>');
				input.prop({
					'type':'text',
					'id' : TagAttr.sweepSliceValueId(parameter),
					'name': TagAttr.sweepSliceValueName(parameter),
					'style': Style.SweepSliceValue
				});
				var value;
				if(parameter.sweepMethod() == OSP.Enumeration.SweepMethod.BY_SLICE  ){
					value = Util.convertUndefinedToEmptyString(parameter.sweepSliceCount());
				}else{
					value = Util.convertUndefinedToEmptyString(parameter.sweepSliceValue());
				}
				
				if( value )
					input.val(value);
				
				input.bind('change', 
						{
							'sourceTag': input,
							'sourceParameter':parameter,
							'section': OSP.Enumeration.DivSection.SWEEP_SLICE_VALUE
						},
						onSweepChange
				);
				
				div.append(input);
				div.append( sweepSliceValueUnitDiv(parameter) );
				
				return div;
			};
			var sweepSliceValueUnitDiv = function (parameter){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.sweepSliceValueUnitId(parameter),
					'style': Style.SweepSliceValueUnitDiv
				});
				if( parameter.sweepMethod() == true)
					div.append('Slices');
				else{
					if ( parameter.unit() )
						div.append(parameter.unit());
				}
				return div;
			};
			var sweepRangeDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValue);
				div.addClass(Layout.SweepMethodDiv);
				div.prop('style', Style.SweepRangeDiv);
				
				div.append(sweepRangeBoundaryDiv(parameter, OSP.Constants.LOWER_BOUNDARY));
				div.append(rangeUnitDiv(parameter));
				div.append(sweepRangeOperandDiv(parameter, OSP.Constants.LOWER_OPERAND));
				div.append($('<div style=\"display:inline;\">x</div>'));
				div.append(sweepRangeOperandDiv(parameter, OSP.Constants.UPPER_OPERAND));
				div.append(sweepRangeBoundaryDiv(parameter, OSP.Constants.UPPER_BOUNDARY));
				div.append(rangeUnitDiv(parameter));
				
				var divSweepSliceValue = sweepSliceValueDiv(parameter);
				div.append(divSweepSliceValue);
				div.setSweepSliceValue = function(value){
					var input = divSweepSliceValue.find('input');
					input.val(value);
				};
				div.setSweepSliceValueUnit = function(unit){
					var unitDiv = $('#'+TagAttr.sweepSliceValueUnitId(parameter));
					unitDiv.html(unit);
				};
				
				return div;
			};

			var sweepDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValueDiv);
				
				if(parameter.sweeped()){
					div.prop('style', Style.SweepDivVisible);
				}
				else{
					if( eventFlag === true ){
						div.prop('style', Style.SweepDivInvisible);
					}else{
					    div.prop('style', Style.SweepDivVisible);
					}
				} 
					
				
				var divSweepRange = sweepRangeDiv(parameter);
				var divSweepMethod = sweepMethodDiv(parameter);				
				
				function onSweepMethodChange(event){
					var sweepMethodDiv = event.data.sweepMethodDiv;
					var sweepRangeDiv = event.data.sweepRangeDiv;
					var parameter = event.data.parameter;
					var method = sweepMethodDiv.find('input:checked').val();
					console.log('Method: '+method);
					if( method === OSP.Enumeration.SweepMethod.BY_SLICE ){
						parameter.sweepMethod( OSP.Enumeration.SweepMethod.BY_SLICE );
						sweepRangeDiv.setSweepSliceValue(
								Util.convertUndefinedToEmptyString(parameter.sweepSliceCount()));
						sweepRangeDiv.setSweepSliceValueUnit('Slices');
					}
					else{
						parameter.sweepMethod( OSP.Enumeration.SweepMethod.BY_VALUE);
						sweepRangeDiv.setSweepSliceValue(
								Util.convertUndefinedToEmptyString(parameter.sweepSliceValue()));
						sweepRangeDiv.setSweepSliceValueUnit(
								Util.convertUndefinedToEmptyString(parameter.unit()));
					}
					Util.refreshJSON();
				}
				
				var data = {
						'sweepMethodDiv': divSweepMethod,
						'sweepRangeDiv': divSweepRange,
						'parameter': parameter
				};
				divSweepMethod.bind('change', 
						data, 
						onSweepMethodChange);
				
				div.append(divSweepMethod);
				div.append(divSweepRange);
				
				return div;
			};
			var parameterNameDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.ParameterNameDiv);
				div.prop('style', Style.ParameterNameDiv);
				//console.log('languageId: '+languageId);
				var name = parameter.name();
				div.append(name+' : ');
				return div;
			};
			var numericParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.NumericParameterValueDiv);
				var range = parameter.range();
				var divInput = valueRangeInputDiv(parameter, eventFlag);
				if( !range || JSON.stringify(range) === '{}'){
					div.append(divInput);
					div.append(rangeUnitDiv(parameter));
				}
				else {
					var lowerBoundary = parameter.rangeLowerBoundary();
					if( lowerBoundary ){
						div.append(valueRangeBoundaryDiv(parameter, OSP.Constants.LOWER_BOUNDARY));
						div.append(rangeUnitDiv(parameter));
						div.append(valueRangeOperandDiv(parameter, OSP.Constants.LOWER_OPERAND));
					}
					divInput = valueRangeInputDiv(parameter, eventFlag);
					div.append(divInput);
					div.append(rangeUnitDiv(parameter));
					
					var upperBoundary = parameter.rangeUpperBoundary();
					if( upperBoundary ){
						div.append(valueRangeOperandDiv(parameter, OSP.Constants.UPPER_OPERAND));
						div.append(valueRangeBoundaryDiv(parameter, OSP.Constants.UPPER_BOUNDARY));
						div.append(rangeUnitDiv(parameter));
					}
				}
				
				if( parameter.sweepable() ){
					var divSweep = sweepDiv(parameter, eventFlag);
					var divCheckBox = sweepCheckBoxDiv(parameter);
					var checkBox = divCheckBox.find('input');

					var onSweepDivToggle = function (event){
						var origin = event.data.origin;
						var target = event.data.target;
						var parameter = event.data.sourceParameter;
						if( parameter.sweeped() ){
							parameter.sweeped(false);
							dataStructure.sweepCount() > 0 ? dataStructure.decreaseSweepCount() : 0;
							target.toggle();
						}
						else{
							if( dataStructure.sweepMax() == dataStructure.sweepCount() ){
								origin.prop('checked', false);
							}
							else{
								dataStructure.increaseSweepCount();
								parameter.sweeped(true);
								target.toggle();
							}
						} 
						Util.refreshJSON();
					}
				
					var data = {
							'origin': checkBox,
							'target': divSweep,
							'sourceParameter': parameter
					};
					checkBox.bind('change', data, onSweepDivToggle);

					div.append(divCheckBox);
					div.append(divSweep);
				}
				

				if( eventFlag === true){
					div.setParameterValue = function(value){
						divInput.setParameterValue(value);
					};
				}

				return div;
			};
			var listParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.ListParameterValueDiv);
				var select = $('<select/>');
				var id = TagAttr.valueRangeInputId(parameter);
				var name = id;
				select.prop({
					'id' : id,
					'name' : name
				});
				var listItems = parameter.localizedListItems(languageId);
				if( OSP.Util.isEmpty(listItems) ){
					listItems = parameter.localizedListItems(dataStructure.defaultLanguageId());
				}
				//console.log(JSON.stringify(listItems,null,4));
					
				var savedValue = parameter.value();
				for(var key in listItems){
					var listItem = listItems[key];
					var option = $('<option/>');
					option.val(key);
					if( savedValue == key )
						option.prop('selected', 'selected');
					option.text(listItem);
					select.append(option);
				}
				
				if( eventFlag === true ){
					var onSelectChange = function (event){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						if( parameter.value(newValue) == false ){
							alert(newValue+' is out of the range.');
							event.data.sourceTag.val(parameter.value());
						}
						checkActivateParameters(parameter);
						Util.refreshJSON();
					}
					
					var data = {
							'sourceTag': select,
							'sourceParameter': parameter
					};
					select.bind('change', data, onSelectChange);
					div.setParameterValue = function(value){
						select.val(value);
						select.trigger('change');
					};
					div.setDisplayNone = function(itemValue){
						//console.log('Display None: ' + 'listParameterValueDiv');
						var option = select.find('option[value=\"'+itemValue+'\"]');
						option.prop('style', 'display:none');
					};
					div.listItemToggle = function(itemValue, flag){
						//console.log('ListItem Toggle: ' + 'listParameterValueDiv');
						var option = select.find('option[value=\"'+itemValue+'\"]');
						option.toggle(flag);
					}
				}

				div.append(select);
				return div;
			};
			var vectorParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.VectorParameterValueDiv);
				var leftBrace, rightBrace;
				switch(dataStructure.vectorFormBraceChar()){
				case OSP.Constants.SQUARE_SPACE:
				case OSP.Constants.SQUARE:
					leftBrace = '[ ';
					rightBrace = ' ]';
					break;
				case OSP.Constants.ROUND_SPACE:
				case OSP.Constants.ROUND:
					leftBrace = '( ';
					rightBrace = ' )';
					break;
				}
				var vector = parameter.value();
				var leftBraceDiv = $('<div style=\"display:inline;\"/>');
				leftBraceDiv.append(leftBrace);
				div.append(leftBraceDiv);
				var inputDiv = $('<div style=\"display:inline;\"/>');
				inputDiv.prop('id', TagAttr.vectorInputDivId(parameter));
				var dimension = Number( parameter.dimension() );
				for(var i=0; i<dimension; i++){
					if( i !== 0){
						inputDiv.append(dataStructure.vectorFormDelimiter());
					}
					var input = $('<input/>');
					input.prop({
						'type' : 'text',
						'style': Style.VectorInput,
						'value': vector[i]
					});
					if( eventFlag === true ){
						var onElementChange = function (event){
							var newValue = event.data.sourceTag.val();
							var parameter = 	event.data.sourceParameter;
							var vector = parameter.value();
							var index = event.data.index;
							vector[index]=newValue;
							parameter.value(vector);
						}
						
						var data = {
								'sourceTag': input,
								'sourceParameter': parameter,
								'index': i
						};
						input.bind('change', data, onElementChange);
					}
					
					inputDiv.append(input);
				}
				div.append(inputDiv);
				var rightBraceDiv = 	$('<div style=\"display:inline;\"/>');
				rightBraceDiv.append(rightBrace);
				div.append(rightBraceDiv);
				
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						var inputs = inputDiv.find('input');
						for(var i=0; i<inputs.length; i++){
							inputs[i].val(value[i]);
							inputs[i].trigger('change');
						}
					};
				}

				return div;
			};
			var stringParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.StringParameterValueDiv);
				
				var input = $('<input/>');
				input.prop({
					'type' : 'text',
					'style': Style.StringInput,
					'value': parameter.value()
				});
				if( eventFlag === true ){
					var onInputChange = function ( event ){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						parameter.value(newValue);
						Util.refreshJSON();
					}
					
					var data = {
							'sourceTag': input,
							'sourceParameter': parameter
					};
					input.bind('change', data, onInputChange);
					div.setParameterValue = function(value){
						input.val(value);
						input.trigger('change');
					};					
				}
				
				div.append(input);
				
				return div;
			};
			var parameterDescriptionDiv = function(parameter){
				var div = $('<div/>');
				//description 
				//console.log(Layout.ParameterDescriptionDiv);
				div.addClass(Layout.ParameterDescriptionDiv);
				div.prop('style', Style.ParameterDescriptionDiv);
				
				//description image add
				var descriptionImg = $('<img/>').prop("src",contextPath +"/images/question.jpg")
												.prop ("width", 20).prop("height", 20).css("cursor","pointer")
												.prop("title",parameter.localizedDescription(languageId));
				
				div.append(descriptionImg);
				//div.append(parameter.getLocalizedDescription(languageId));
				//div.append(parameter.getNameLocalizedText(languageId));
				return div;
			};
			
			//This checks if a parameter is active or not from its activation conditions. 
			var checkInitialActivate = function(parameter, tag){
				var checkListItem = function(){
					if(parameter.type() === OSP.Constants.LIST){
						//console.log('Check List Item Activate.... ');	
						var listItems = parameter.listItems();
						//console.log(JSON.stringify(listItems));
						for( var key in listItems ){
							//console.log('Check List Item Activate: '+listItems[j].getItemValue());
							var listItem = listItems[key];
							var itemConditions = listItem.activateConditions();
							for(var k=0; k<itemConditions.length; k++){
								var itemConditionParameter = dataStructure.parameter(itemConditions[k].parameterName());
								if(itemConditions[k].checkActivate(itemConditionParameter.value()) == false){
									//console.log('ListItem Toggle: ' + 'checkInitialActivate');
									tag.setDisplayNone(listItem.value());
								}
							}
						}
					}
				};
				
				var conditions = parameter.activateConditions();
				if(conditions == false ){
					checkListItem();
					parameter.active(true);
					return;
				}
				//console.log(JSON.stringify(conditions));
				for(var i=0; i<conditions.length; i++){
					var conditionParameter = dataStructure.parameter(conditions[i].parameterName());
					var assignValue = conditions[i].checkActivate(conditionParameter.value()); 
					switch( assignValue ){
					case true:
						//console.log(parameter.getName()+': '+true);
						break;
					case false:
						//console.log(parameter.getName()+': '+false);
						break;
					default:
						//console.log(parameter.getName()+': '+assignValue);
						tag.setParameterValue(assignValue);
						tag.find('input').prop('disabled', 'disabled');
						tag.find('select').prop('disabled', 'disabled');
						break;
					}
					if( assignValue !== false ){
						//console.log('Activate: '+parameter.getName());
						//checkListItem();
						parameter.active(true);
						return;
					}
				}
				//console.log('Inactivate: '+parameter.name());
				tag.prop('style', 'display:none;');
				parameter.active(false);
			};
			var checkActivateParameters = function(parameter){
				//console.log('========== checkActivateParameters ==================');
				//console.log('Source Parameter: '+parameter.getName());
				//console.log('Source Value: '+parameter.getValue());
				var parameters = dataStructure.parameters();
				
				for(var paramName in parameters){
					//console.log('-------------');
					var targetParameter = parameters[paramName];
					//console.log('Target Parameter: '+targetParameter.getName());
					//console.log('Target Type: '+targetParameter.getType());
					//Check ItemLists for LIST type parameters
					if(targetParameter.type() === OSP.Constants.LIST){
						//console.log('Processing ListParameter...........');
						var listItems = targetParameter.listItems();
						//console.log(JSON.stringify(listItems));
						//console.log('List Item Count: '+listItems.length);
						for( var itemValue in listItems ){
							var targetItem = listItems[itemValue];
							//console.log('++Target Item: '+targetItem.getItemValue());
							var activate = targetItem.checkActivate(parameter.name(), parameter.value());
							//console.log('++Activate: '+activate);
							var select = $('#'+TagAttr.valueRangeInputId(targetParameter));
							//console.log('++SelectId: '+select.prop('id'));
							var option = select.find('option[value=\"'+targetItem.value()+'\"]');
							//console.log('++Option Value: ' + option.val());
							option.toggle(activate);
						}					
					}

					if( targetParameter === parameter){
						//console.log('Source and Target are same: omitted');
						continue;
					}
					if(targetParameter.type() === OSP.Constants.GROUP){
						//console.log('Target parameter type is a GROUP: omitted');
						targetParameter.active(true);
						continue;
					}
					var activate = false;
					var disable = false;
					var conditions = targetParameter.activateConditions();
					if( conditions == false )	activate = true;
					else{
						for(var j=0; j<conditions.length; j++){
							var condition = conditions[j];
							var conditionParameter = dataStructure.parameter(condition.parameterName());
							var assignValue = condition.checkActivate(conditionParameter.value()); 
							switch( assignValue ){
							case true:
								activate= true;
								disable = false;
								break;
							case false:
								activate = false;
								break;
							default:
								activate= true;
								disable = true;
								break;
							}
							if( activate == true ){
								parameter.active(true);
								break;
							}
						}
					}
					//console.log('Activate: '+activate);
					//console.log('Disabled: '+disable);

					targetParameter.active(activate);
					if( activate == true ){
						targetParameter.disabled(disable);
						$('#'+TagAttr.parameterRowId(targetParameter)).toggle(true);
						if( disable == true ){
							$('#'+TagAttr.parameterRowId(targetParameter)).find('input').prop('disabled', 'disabled');
							$('#'+TagAttr.parameterRowId(targetParameter)).find('select').prop('disabled', 'disabled');
						}
						else{
							$('#'+TagAttr.parameterRowId(targetParameter)).find('input').removeAttr('disabled');
							$('#'+TagAttr.parameterRowId(targetParameter)).find('select').removeAttr('disabled');
						}
					}
					else{
						$('#'+TagAttr.parameterRowId(targetParameter)).toggle(false);
					}
					
					//console.log('-------------');
				}
			};
			
			var numericParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = numericParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var groupParameterRow = function( parameter, eventFlag ){
				var outerDiv = $('<div  style="text-align:right;"/>');/*class=\"span12\" 삭제*/
				outerDiv.prop('id', TagAttr.parameterRowId(parameter));
				var div = $('<div/>');
				div.prop('style', Style.ParameterRow);
				div.addClass(Layout.ParameterRow);
				//div.prop('style', Style.GroupParameterRow);
				var groupNameDiv = $('<div class=\"span6\" style=\"font-weight:bold; text-align:left; display:inline;\"/>');
				var groupName = parameter.localizedNameText(languageId);
				if( !groupName || groupName == false || groupName == '' )
					groupName = parameter.localizedNameText(dataStructure.defaultLanguageId());
				
				if( OSP.Util.isEmpty(groupName) )
					groupName = parameter.name();
				
				groupNameDiv.append("&nbsp;").append(groupName);
				div.append(groupNameDiv);
				var groupDescriptionDiv = $('<div class=\"span6\" style=\"display:inline; text-align:left; \"/>');
				groupDescriptionDiv.append(parameter.localizedDescription(languageId));
				div.append(groupDescriptionDiv);

				var attachedNames = parameter.parameters();
				if(attachedNames.length > 0){
					for(var index in attachedNames){
						var subParameter = dataStructure.parameter(attachedNames[index]);
						div.append(  parameterRow( subParameter, eventFlag) );
					}
				}else{
					div.css("padding", "5px 0px");
				}
				
				outerDiv.append(div)
				return outerDiv;
			};
			var listParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = listParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					div.setDisplayNone = function(itemValue){
						valueDiv.setDisplayNone(itemValue);
					};
					div.listItemToggle = function(key, flag){
						valueDiv.listItemToggle(key, flag);
					};
					checkInitialActivate(parameter, div);
				}
				
				return div;
			};
			
			var vectorParameterRow = function( parameter, eventFlag ){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = vectorParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var stringParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = stringParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var commentRow =  function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				var nameDiv = $('<div/>');
				nameDiv.addClass(Layout.ParameterNameDiv);
				nameDiv.prop('style', Style.ParameterNameDiv);
				nameDiv.append('Comment : ');
				div.append(nameDiv);
				var valueDiv = stringParameterValueDiv(parameter, eventFlag); 
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));

				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}
					
				return div;
			};
			
			var parameterRow = function( parameter, eventFlag){
				var div;
				switch(parameter.type()){
				case OSP.Constants.GROUP:
					div = groupParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.NUMERIC:
					div = numericParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.LIST:
					div = listParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.VECTOR:
					div = vectorParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.STRING:
					div = stringParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.COMMENT:
					div = commentRow(parameter, eventFlag);
					break;
				}
				
				var parameterClicked = function( event ){
					event.stopPropagation();
					Liferay.fire( 'parameterSelected', event.data);
				};
				
				div.css("cursor","pointer");
				//console.log('Numeric callback bined');
				div.on('click',
						{
							'targetPortlet': namespace,
							'sourceTag': div,
							'sourceParameter': parameter
						},
						parameterClicked
				);

				return div ;
			};
			
			return {
				editor : function(){
					
					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row-fluid',
							SweepRadioBySlice: 'span4',
							SweepRadioByValue: 'span4',
							SweepMethodValueDiv: 'span11',
							SweepMethodValue: 'span12',
							ParameterNameDiv: 'span3',
							ParameterRangeDiv: 'span8',
							ParameterDescriptionDiv: 'span1',
							ParameterRow: 'row-fluid parameter-row',
					};
					
					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepRangeLowerBoundaryDiv: 'display:inline;',
							SweepRangeLowerBoundary: 'width:40px; text-align:right;',
							SweepRangeOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepRangeOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepRangeDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							ParameterNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							ParameterDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueRangeLowerBoundaryDiv: 'width:60px; text-align:right; display:inline;',
							ValueRangeUpperBoundaryDiv: 'width:60px; display:inline;',
							RangeUnitDiv:'width:20px; display:inline;',
							ValueRangeOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueRangeInputDiv: 'display:inline;',
							ValueRangeInput: 'width: 60px; text-align:right; display:inline;',
							NumericParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							GroupParameterRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;', 
							ParameterRow:'padding:5px 0px;',
							VectorParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							VectorInput: 'width: 40px; text-align:right;',
							StringParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};
					
					var parameters = dataStructure.topLevelParameters();
					//console.log( JSON.stringify(parameters,null,4));
					for(var index in parameters){
						if((index % 2)== 0)
							Style.ParameterRow += ' background-color:#f0f0f0;';
						else
							Style.ParameterRow += ' background-color:#f8f8f8;';
						
						canvas.append(parameterRow( parameters[index], true ));
					}
				},
				form : function(){
					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row-fluid',
							SweepRadioBySlice: 'span4',
							SweepRadioByValue: 'span4',
							SweepMethodValueDiv: 'span11',
							SweepMethodValue: 'span12',
							ParameterNameDiv: 'span3',
							ParameterRangeDiv: 'span8',
							ParameterDescriptionDiv: 'span1',
							ParameterRow: 'row-fluid parameter-row',
					};
					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepRangeLowerBoundaryDiv: 'display:inline;',
							SweepRangeLowerBoundary: 'width:40px; text-align:right;',
							SweepRangeOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepRangeOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepRangeDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							ParameterNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							ParameterDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueRangeLowerBoundaryDiv: 'width:60px; text-align:right; display:inline;',
							ValueRangeUpperBoundaryDiv: 'width:60px; display:inline;',
							RangeUnitDiv:'width:20px; display:inline;',
							ValueRangeOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueRangeInputDiv: 'display:inline;',
							ValueRangeInput: 'width: 60px; text-align:right; display:inline;',
							NumericParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							GroupParameterRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;', 
							ParameterRow:'padding:5px 0px;',
							VectorParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							VectorInput: 'width: 40px; text-align:right;',
							StringParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};

					var parameters = dataStructure.topLevelParameters();
					for(var index in parameters){
						if((index % 2)== 0)
							Style.ParameterRow += ' background-color:#f0f0f0;';
						else
							Style.ParameterRow += ' background-color:#f8f8f8;';
						
						canvas.append(parameterRow( parameters[index] ));
					}
				},
				
				viewer : function(){
					var formattedInputs = dataStructure.activeParameterFormattedInputs();
					console.log("formattedInputs--->"+JSON.stringify(formattedInputs,null,4));
					var inputCount = formattedInputs.length;
					var currentViewPage = 1;
					var viewerDiv = $('<div>');
					var viewSection = $('<div class="row-fluid">');
					
					if(inputCount>1){
						var getPageId = function (page){
							return namespace+'_page_'+page;
						};
						var getPrevPageId = function (page){
							return namespace+'_page_'+(page-1);
						};
						var getNextPageId = function (page){
							return namespace+'_page_'+(page+1);
						};
						
						var previousPage = function (event){
							if( currentViewPage == 1)	return;
							var pageTag = event.data.pageTag;
							$('#'+getPageId(currentViewPage)).toggle();
							$('#'+getPrevPageId(currentViewPage)).toggle();
							currentViewPage -= 1;
							pageTag.text(currentViewPage);
						};
						var nextPage = function(event){
							if( currentViewPage ==  (inputCount)) return;
							var pageTag = event.data.pageTag;
							$('#'+getPageId(currentViewPage)).toggle();
							$('#'+getNextPageId(currentViewPage)).toggle();
							currentViewPage += 1;
							pageTag.text(currentViewPage);
						};
						
						var navSection = $('<div class="row-fluid">');
						var  buttonDiv = $('<div class="span4">');
						var pagerUl = $('<ul class="pager">');
						var prevLi = $('<li class="previous" style="cursor: pointer;"><span>Prev</span></li>');
						var pageLi = $('<li></li>');
						pageLi.text(currentViewPage);
						var nextLi = $('<li class="next" style="cursor: pointer;"><span>Next</span></li>');
						prevLi.find('span').bind('click', {'pageTag':pageLi}, previousPage);
						nextLi.find('span').bind('click', {'pageTag':pageLi}, nextPage);
						
						pagerUl.append(prevLi);
						pagerUl.append(pageLi);
						pagerUl.append(nextLi);
						buttonDiv.append(pagerUl);
						navSection.append(buttonDiv);
						
						viewerDiv.append(navSection);
					}
					
					
					for( var i=0; i<formattedInputs.length; i++){
						var inputDiv = $('<div>');
						inputDiv.prop('id', namespace+'_page_'+(i+1));
						if((i+1) !== currentViewPage )	inputDiv.prop('style', 'display:none');
						var input = formattedInputs[i].toString().replace(/,/g, '');
						inputDiv.html(input);
						viewSection.append(inputDiv);
					}
					
					viewerDiv.append(viewSection);
					canvas.append(viewerDiv);
				}
			};
		}; // End of displayUI 
		
		this.showStructuredDataEditor = function( namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay ){
			var ui = displayUI( this.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.editor();
		};
		
		this.showDataStructureForm = function(namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			var ui = displayUI( this.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.form();
		};
		
		this.showStructuredDataViewer = function(namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			console.log(JSON.stringify(this.structure(),null,4));
			var ui = displayUI( this.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.viewer();
		};
		
		this.deserializeStructure = function( jsonObject ){
			return this.structure(this.newDataStructure( jsonObject));
		};
	}; // End of DataType 
	
	OSP.ScienceApp = function(){
		OSP._OpenObject.apply(this);
		
		var _Port = function( jsonObject ){
			OSP._MapObject.apply(this);
			
			this.name = function( name ){
				if( !this.verifyName(name) )	return false;
				
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments));
			};
			
			this.mandatory = function( mandatory ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.MANDATORY, arguments));
			};
	
			this.dataType = function( dataTypeName, dataTypeVersion ){
				switch( arguments.length ){
				case 0:
					return this.property(OSP.Constants.DATA_TYPE);
				case 1:
					return this.property(OSP.Constants.DATA_TYPE, dataTypeName);
				case 2:
					var dataType = {
						name: dataTypeName,
						version: dataTypeVersion
					};
					return this.property(OSP.Constants.DATA_TYPE, dataType);
				default:
					return false;
				}
			};
			
			this.defaultEditor = function( scienceApp ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_EDITOR, arguments));
			};
			
			this.defaultAnalyzer = function( scienceApp ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_ANALYZER, arguments));
			};
			
			this.getAvailableEditors = function( url, params ){
				
			};
			
			this.getAvailableAnalyzers = function( url, params ){
				
			};
			
			this.verifyName = function(name){
				if( !name )	return true;
				if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(name))  return false;
				else  return true;
			};
		}; // End of _Port 
  
		var InputPort = function( jsonObject ){
			_Port.apply(this);
			
			this.order = function( order ) {
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ORDER, arguments));
			};
			
			this.inputData = function( inputData ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.INPUT_DATA, arguments));
			};
			
			this.inputDataPath = function( path ){
				var inputData = this.inputData();
				if( !inputData )	return false;
				return inputData.fullPath.apply( inputData, arguments );
			};
			
			this.safeInputData = function(){
				var inputData = this.inputData();
				if( !inputData ){
					inputData = new OSP.InputData();
					this.inputData( inputData );
				}
				return inputData;
			};
			
			this.setInputDataPath = function( parent, name, pathType, relative ){
				var inputData = this.safeInputData();
				return inputData.setPath.apply( inputData, arguments );
			};
			
			this.inputDataUri = function( uri ){
				var inputData = this.safeInputData();
				return inputData.uri.apply( inputData, arguments );
			};
			
			this.inputDataPathType = function( pathType ){
				var inputData = this.safeInputData();
				return inputData.type.apply( inputData, arguments );
			};
			
			this.inputDataParentFolderPath = function( parentPath ){
				var inputData = this.safeInputData();
				return inputData.parent.apply( inputData, arguments );
			};
			
			this.inputDataName = function( name ){
				var inputData = this.safeInputData();
				return inputData.name.apply( inputData, arguments );
			};
			
			this.inputDataRelative = function( relative ){
				var inputData = this.safeInputData();
				return inputData.relative.apply( inputData, arguments );
			};
			
			this.sample = function( samplePath ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments));
			};
			
			this.samplePath = function( path ){
				var samplePath = this.sample(); 
				if( !samplePath )	return false;
				return sample.fullPath.apply( samplePath, arguments );
			};
			
			this.safeSample = function (){
				var sample = this.sample();
				if( !sample ){
					sample = new OSP.Path();
					this.sample( sample );
				}
				return sample;
			}
			
			this.setSample = function( id, parent, name, type, relative ){
				var sample = this.safeSample();
				return sample.setPath.apply( sample, arguments );
			};
			
			this.sampleUri = function( uri ){
				var sample = this.safeSample();
				return sample.uri.apply( sample, arguments );
			};
			
			this.samplePathType = function( pathType ){
				var sample = this.safeSample();
				return sample.type.apply( sample, arguments );
			};
			
			this.sampleParentFolderPath = function( parentPath ){
				var sample = this.safeSample();
				return sample.parent.apply( sample, arguments );
			};
			
			this.sampleName = function( name ){
				var sample = this.safeSample();
				return sample.name.apply( sample, arguments );
			};
			
			this.sampleRelative = function( relative ){
				var sample = this.safeSample();
				return sample.relative.apply( sample, arguments );
			};
			
			this.getSampleData = function( url, params ){
				
			};
			
			this.clone = function(){
				return new InputPort( OSP.Util.toJSON(this) );
			};
	
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					switch( key ){
					case OSP.Constants.INPUT_DATA:
						var inputData = new OSP.InputData( jsonObject[key] );
						this.inputData( inputData );
						break;
					case OSP.Constants.SAMPLE:
						var sample = new OSP.Path( jsonObject[key] );
						this.sample( sample );
						break;
					default:
						this._deserialize( key, jsonObject[key] );
					}
				}
			};
			
			this.upgrade = function( oldPort ){
				for( var key in oldPort ){
					this.inputDataPathType( OSP.Constants.FILE );
					switch( key ){
					case 'name':
						this.name( oldPort[key] );
						break;
					case 'default-editor-id':
						this.defaultEditor( oldPort[key] );
						break;
					case 'default-analyzer-id':
						this.defaultAnalyzer( oldPort[key] );
						break;
					case 'port-type-name':
						this.dataType( oldPort[key], '1.0.0' );
						break;
					case 'mandatory':
						this.mandatory( oldPort[key] );
						break;
					case 'port-type-id':
					case 'inputdeckporttype':
						console.log( 'Deprecated: '+ key );
						break;
					default:
						console.log( 'Unknown attribute: '+ key);
					}
				}
				
				return this;
			};
			
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
		}; // End of InputPort 
		this.newInputPort = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new InputPort();
				case 1:
					return new InputPort(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newInputPort');
					return null;
			}
		};
		
		var OutputPort = function( jsonObject ){
			_Port.apply(this);
			
			this.outputData = function( path ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.OUTPUT_DATA, arguments));
			};
			
			
			this.safeOutputData = function(){
				var outputData = this.outputData();
				if( !outputData ){
					outputData = new OSP.Path();
					this.outputData( outputData );
				}
				return outputData;
			};
			
			this.setOutputDataPath = function(id, parent, name, pathType, relative ){
				var outputData = this.safeOutputData();
				return outputData.setPath.apply( outputData, arguments );
			};
			
			this.outputDataPathType = function( pathType ){
				var outputData = this.safeOutputData();
				return outputData.type.apply( outputData, arguments );
			};
			
			this.outputDataParentFolderPath = function( parentPath ){
				var outputData = this.safeOutputData();
				return outputData.parent.apply( outputData, arguments );
			};
			
			this.outputDataName = function( name ){
				var outputData = this.safeOutputData();
				return outputData.name.apply( outputData, arguments );
			};
			
			this.outputDataRelative = function( relative ){
				var outputData = this.safeOutputData();
				return outputData.relative.apply( outputData, arguments );
			};
			
			this.verifyOutput = function( url, params ){
				
				return true;
			};
			
			this.clone = function(){
				return new OutputPort( OSP.Util.toJSON(this) );
			};

			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.OUTPUT_DATA ){
						var outputPath = new OSP.Path( jsonObject[key] );
						this.outputData( outputPath );
					}else
						this.property( key, jsonObject[key] );
				}
			};
			
			this.upgrade = function( oldPort ){
				for( var key in oldPort ){
					switch( key ){
					case 'name':
						this.name( oldPort[key] );
						break;
					case 'default-editor-id':
						this.defaultEditor( oldPort[key] );
						break;
					case 'default-analyzer-id':
						this.defaultAnalyzer( oldPort[key] );
						break;
					case 'port-type-name':
						this.dataType( oldPort[key], '1.0.0' );
						break;
					case 'mandatory':
						this.mandatory( oldPort[key] );
						break;
					case 'data-form':
						var pathType = oldPort[key];
						if( pathType === 'extention' )
							pathType = 'ext';
						
						var fileName = oldPort['file-name'];
						if( fileName.indexOf( '/' ) == 0 )
							fileName = fileName.slice(1, fileName.length);
						
						if(fileName.indexOf( 'result') != 0)
							fileName = 'result/'+fileName;
						
						var filePath = OSP.Util.convertToPath(fileName);

						console.log( 'filePath: '+JSON.stringify(filePath,null,4));
						
						this.setOutputDataPath( 0, filePath.parent, filePath.fileName, pathType, true);
						break;
					case 'port-type-id':
					case 'file-name':
					case 'inputdeckporttype':
					case 'type':
						console.log( 'Deprecated: '+ key );
						break;
					default:
						console.log( 'Unknown attribute: '+ key);
					}
				}
				
				return this;
			};
			
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
		}; // End of OutputPort 
		this.newOutputPort = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new OutputPort();
				case 1:
					return new OutputPort(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newOutputPort');
					return null;
			}
		};
		this.newLogPort = this.newOutputPort;
		
		this.type = function( type ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments));
		};
			
		this.inputPorts = function( inputPorts ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.INPUT_PORTS, arguments));
		};
		
		this.deserializeInputPorts = function( portsJson ){
			var ports = this.safeInputPorts();
			for( var portName in portsJson ){
				var port = this.newInputPort(portsJson[portName]);
				ports[portName] = port;
			}
			
			return ports;
		};
		
		this.inputPortsArray = function(){
			var ports = this.inputPorts();
			if( !ports )	return [];
			
			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}
			
			return portsArray;
		};
		
		this.safeInputPorts = function(){
			var inputPorts = this.inputPorts();
			if( !inputPorts ){
				inputPorts = {};
				this.inputPorts( inputPorts );
			}
			
			return inputPorts;
		};
		
		this.addInputPort = function( inputPort ){
			var inputPorts = this.safeInputPorts();
			inputPorts[inputPort.name()] = inputPort;
			
			return inputPorts;
		};
		
		this.removeInputPort = function( portName ){
			var inputPorts = this.inputPorts();
			if( !inputPorts )	return true;
			
			return delete inputPorts[portName];
		};
		
		this.inputPort = function( portName ){
			var ports = this.inputPorts();
			if( !ports )	return false;
			return ports[portName];
		};
		
		this.logPorts = function( logPorts ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.LOG_PORTS, arguments));
		};
		
		this.deserializeLogPorts = function( portsJson ){
			var ports = this.safeLogPorts();
			for( var portName in portsJson ){
				var port = this.newLogPort(portsJson[portName]);
				ports[portName] = port;
			}
			
			return ports;
		};
		
		this.safeLogPorts = function(){
			var logPorts = this.logPorts();
			if( !logPorts ){
				logPorts = {};
				this.logPorts( logPorts );
			}
			
			return logPorts;
		};
		
		this.logPortsArray = function(){
			var ports = this.logPorts();
			if( !ports )	return [];
			
			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}
			
			return portsArray;
		};
		
		this.addLogPort = function( logPort ){
			var logPorts = this.safeLogPorts();
			logPorts[logPort.name()] = logPort;
			
			return logPorts;
		};
		
		this.removeLogPort = function( portName ){
			var logPorts = this.logPorts();
			if( !logPorts )	return true;
			
			return delete logPorts[portName];
		};
		
		this.logPort = function( portName ){
			var ports = this.logPorts();
			if( !ports )	return false;
			return ports[portName];
		};
		
		this.outputPorts = function( outputPorts ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.OUTPUT_PORTS, arguments));
		};
		
		this.deserializeOutputPorts = function( portsJson ){
			var ports = this.safeOutputPorts();
			for( var portName in portsJson ){
				var port = this.newOutputPort(portsJson[portName]);
				ports[portName] = port;
			}
			
			return ports;
		};
		
		this.outputPortsArray = function(){
			var ports = this.outputPorts();
			if( !ports )	return [];
			
			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}
			
			return portsArray;
		};
		
		this.safeOutputPorts = function(){
			var outputPorts = this.outputPorts();
			if( !outputPorts ){
				outputPorts = {};
				this.outputPorts( outputPorts );
			}
			
			return outputPorts;
		};
		
		this.addOutputPort = function( outputPort ){
			var outputPorts = this.safeOutputPorts();
			outputPorts[outputPort.name()] = outputPort;
			
			return outputPorts;
		};
		
		this.removeOutputPort = function( portName ){
			var outputPorts = this.outputPorts();
			if( !outputPorts )	return true;
			
			return delete outputPorts[portName];
		};
		
		this.outputPort = function( portName ){
			var ports = this.outputPorts();
			if( !ports )	return false;
			return ports[portName];
		};
		
		this.clone = function(){
			return new OSP.ScienceApp( OSP.Util.toJSON(this) );
		};
		
		this.upgradeInputPorts = function( oldPorts ){
			var newPorts = {};
			for( var portName in oldPorts ){
				var oldPort = oldPorts[portName];
				var newPort = this.newInputPort();
				console.log( 'oldPort: ' + JSON.stringify(oldPort,null,4));
				newPort.upgrade( oldPort );
				newPorts[portName] = newPort;
			}
			this.inputPorts( newPorts );
			return newPorts;
		};
		
		this.upgradeOutputPorts = function( oldPorts ){
			var newPorts = {};
			for( var portName in oldPorts ){
				var oldPort = oldPorts[portName];
				var newPort = this.newOutputPort();
				newPort.upgrade( oldPort );
				newPorts[portName] = newPort;
			}
			this.outputPorts( newPorts );
			return newPorts;
		};
	}; // End of ScienceApp 
	
	OSP.Workflow = function( jsonObject ){
		OSP._OpenObject.apply(this);
		
		var ProceedCondition = function( jsonObject ){
			OSP._MapObject.apply(this);
			
			this.comparativeParam = function( param ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE_PARAM, arguments));
			};

			this.comparative = function( operator ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE, arguments));
			};
			
			this.comparativeValue = function( value ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE_VALUE, arguments));
			};
			
			this.updates = function( updates ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.UPDATES, arguments));
			};
			
			this.safeUpdates = function(){
				var updates = this.updates();
				if( !updates ){
					updates = {};
					this.updates(updates);
				}
				
				return updates;
			};
			
			this.addUpdate = function( param, value ){
				var updates = this.safeUpdates();
				updates[param] = value;
				
				return updates;
			};
			
			this.removeUpdate = function( param ){
				var updates = this.safeUpdates();
				delete updates[param];
				
				return updates;
			};
			
			this.action = function( action ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ACTION, arguments));
			};
			
			this.check = function( value ){
				if( this.comparative() === '<' ){
					if( this.comparativeValue() < value )	return this.action();
				}
				else if( this.comparative() === '<=' ){
					if( this.comparativeValue() <= value )	return this.action();
				}
				else if( this.comparative() === '>' ){
					if( this.comparativeValue() > value )	return this.action();
				}
				else if( this.comparative() === '>=' ){
					if( this.comparativeValue() >= value )	return this.action();
				}
				else if( this.comparative() === '=' ){
					if( this.comparativeValue() == value )	return this.action();
				}
				else
					return false;
			};
			
			this.clone = function(){
				return new ProceedCondition( OSP.Util.toJSON(this) );
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of ProceedCondition 
		this.newProceedCondition = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new ProceedCondition();
				case 1:
					return new ProceedCondition(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newProceedCondition');
					return null;
			}
		};
		
		var Connection = function( jsonObject ){
			OSP._MapObject.apply(this);
			OSP._StyleObject.apply(this);
			
			this.sourcePort = function( portName ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SRC_PORT_NAME, arguments));
			};
			
			this.proceedConditions = function( conditions ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PROCEED_CONDITIONS, arguments));
			};
			
			this.safeProceedConditions = function(){
				var conditions = this.proceedConditions();
				if( !conditions ){
					conditions = [];
					this.proceedConditions(conditions);
				}
				
				return conditions;
			};
			
			this.addProceedCondition = function( condition ){
				var conditions = this.safeProceedConditions();
				conditions.push( condition );
				return conditions;
			};
			
			this.removeProceedCondition = function( param ){
				var conditions = this.proceedConditions();
				if( !conditions )	return true;
				
				for( var index in conditions ){
					var condition = conditions[index];
					if( condition.comparativeParam() === param ){
						conditions.splice(index, 1);
						break;
					}
				}
				return conditions;
			};
			
			this.destinationTask = function( taskUuid ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TASK_UUID, arguments));
			};
			
			this.destinationPort = function( portName ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DESTINATION_PORT, arguments));
			};
			

			this.clone = function(){
				return new Connection( OSP.Util.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.PROCEED_CONDITIONS ){
						var conditionAry = jsonObject[key]; 
						for( var index in  conditionAry ){
							var condition = new ProceedCondition(conditionAry[index]);
							this.addProceedCondition(condition);
						}
					}
					else
						this.property( key, jsonObject[key] );
				}
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of Connection 
		this.newConnection = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new Connection();
				case 1:
					return new Connection(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newConnection');
					return null;
			}
		};

		var Task = function( jsonObject ){
			OSP._OpenObject.apply(this);
			OSP._StyleObject.apply(this);
			
			this.appUuid = function( uuid ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.APP_UUID, arguments));
			};
			
			this.connections = function( connections ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.CONNECTIONS, arguments));
			};
			
			this.safeConnections = function(){
				var connections = this.connections();
				if( !connections ){
					connections = [];
					this.connections(connections);
				}
				
				return connections;
			};
			
			this.addConnection = function( connection ){
				var connections = this.safeConnections();
				connections.push( connection );
				return connections;
			};
			
			this.removeConnection = function( srcPort, dstUuid, dstPort ){
				var connections = this.connections();
				if( !connections )	return [];
				
				for( var index in connections ){
					var connection = connections[index];
					if( connection.sourcePort() === srcPort &&
						 connection.destinationTask() === dstUuid && 
						 connection.destinationPort() === dstPort ){
						 connections.splice( index, 1 );
						break;
					}
				}
				
				return connections;
			};

			this.inputs = function( inputs ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.INPUTS, arguments));
			};
			
			this.safeInputs = function(){
				var inputs = this.inputs();
				if( !inputs ){
					inputs = {};
					this.inputs( inputs );
				}
				
				return inputs;
			};
			
			this.inputData = function( portName, input ){
				switch( arguments.length ){
				case 1:
					var inputs = this.inputs();
					if( !inputs )	return false;
					return inputs[portName];
				case 2:
					var inputs = this.inputs();
					if( !inputs ){
						inputs = {};
						this.inputs = inputs;
					}
					inputs[portName] = input;
					return inputs;
				default:
					return false;
				}
			};
			
			this.removeInputData = function( portName ){
				var inputs = this.inputs();
				if( !inputs )	return true;
				delete inputs[portName];
				return inputs;
			};
			
			this.clone = function(){
				return new Task( OSP.Util.toJSON(this) );
			};
			
			this.execute = function(){
				
			};
			
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.CONNECTIONS ){
						var jsonConnections = jsonObject[key];
						for( var index in jsonConnections ){
							this.addConnection( new Connection( jsonConnections[index] ) );
						}
					}
					else
						this.property( key, jsonObject[key] );
				}
			};
		
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of Task 
		
		this.newTask = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new Task();
				case 1:
					return new Task(jsonObject);
				default: 
					colsole.log( 'Arguments mismatch: newTask');
					return null;
			}
		};
		this.tasks = function( tasks ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TASKS, arguments));
		};
		
		this.addTask = function( task ){
			var tasks = this.tasks();
			if( !tasks ){
				tasks = [];
				this.tasks( tasks );
			}
			
			tasks.push( task );
			return tasks;
		};
		
		this.removeTask = function( taskUuid ){
			var tasks = this.tasks();
			if( !tasks )	return true;
			
			for( var index in tasks ){
				var task = tasks[index];
				if( task.uuid() === taskUuid ){
					tasks.splice( index, 1 );
					break;
				}
			}
			
			return tasks;
		};
		
		this.execute = function(){
			
		};

		this.clone = function(){
			return new OSP.Workflow( OSP.toJSON(this) );
		};
		
		if( arguments.length === 1 )
			this.deserialize(jsonObject);
	}; // End of Workflow 
	
	OSP.Simulation = function( jsonObject ){
		OSP._OpenObject.apply(this);
		
		var Job = function( jsonObject ){
			
		};

		this.clone = function(){
			return new OSP.Simulation( OSP.toJSON(this) );
		};
		
		this.deserialize = function( jsonObject ){
			
		};
		
		if( arguments.length === 1 )
			this.deserialize(jsonObject);
	}; // End of Simulation 
})(window);
