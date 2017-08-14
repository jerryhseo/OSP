(function(window){
	'use strict';
	   
	if( !window.OSP ){
		window.OSP = {};
	}

	if( !(window.OSP && window.OSP.Constants) ){
		OSP.Constants = {
				MAP: 'map_',
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
				INPUT_DATA:'inputData_',
				OUTPUT_PORTS:'outputPorts_',
				OUTPUT_DATA:'outputData_',
				TYPE:'type_',
				APP_UUID:'appUuid_',
				SCIENCE_APP:'scienceApp',
				
				// Path type constants
				FILE:'file',
				EXT:'ext',
				FOLDER:'folder',
				
				UUID:'uuid_',
				JOB_NAME : 'jobName_',
				JOB_UUID:'jobUuid_',
				APP_UUID:'appUuid_',
				SCHEDULER_UUID:'schedulerUuid_',
				SIMULATION_UUID:'simaulationUuid_',
				PROCESSORS_PER_NODE:'processorsPerNode_',
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
				}
			};
	}
	
	if( !(window.OSP && window.OSP.Errors) ){
		OSP.Errors = {
			TYPE_MISMATCH:'data type mismatch',
			PATH_TYPE_MISMATCH:'path type mismatch',
			FULL_PATH_GENERATION_FAILED:'missing one of parent path or file name to get full path',
			DESCRIPTION_NOT_EXIST:'description not exist',
			INVALID_PORT_NAME:'invalid port name',
			PORTTYPE_UNDEFINED:'port type undefined',
			INSUFFICIENT_ARGUMENT:'insufficient argument',
			ARGUMENTS_MISMATCH:'arguments mismatch'
		};
	}
	
	if( !OSP.Enumeration ){
		OSP.Enumeration = {};
		
		OSP.Status.Open = {
			PUBLIC: 0,
			RESTRICT: 1,
			PRIVATE: 2
		};
			
		OSP.Status.Simulation = {
			QUEUE: 10,
			RUNNING: 11,
			SUSPEND: 12,
			SUCCESS: 13,
			FAIL: 14,
			CANCEL: 15
		};
			
		OSP.Status.Location = {
			AT_LOCAL: 20,
			AT_SERVER: 21,
			AT_PUBLIC: 22
		};
			
		OSP.Status.Data = {
			EMPTY: 30,
			DIRTY: 31
		};
		
		OSP.Type = {};
		
		OSP.Type.ScienceApp = {
			S_SOLVER: 40,
			JS_SOLVER: 41,
			D_SOLVER: 42,
			S_CONVERTER: 43,
			JS_CONVERTER: 44,
			D_CONVERTER: 45,
			EDITOR: 46,
			ANALYZER: 47
		};
	}
	
	OSP.toJSON = function( ospObject ){
		return JSON.parse(JSON.stringify(ospObject));
	};
	
	OSP.toString = function( ospObject ){
		return JSON.stringify(ospObject,null,4);
	};
	OSP.guid = function(){
		 return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			 var r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8);
			 return v.toString(16);
		 });
	};
	
	if( !(window.OSP && window.OSP._Map) ){
		OSP._Map = function(){
			this.property = function( key, value){
				switch( arguments.length ){
					case 1:
						if( this[key] )
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
			
			this.status = function( status ){
				switch( arguments.length ){
				case 0:
					return this[OSP.Constants.STATUS];
				case 1:
					this[OSP.Constants.STATUS] = status;
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
						return firstObj.removeProperty(secondKey);
	
					return false;
				default:
					return false;
				}
			};
	
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					var value = jsonObject[key];
					if( typeof value === 'function')	return false;
					else
						this.property(key, value);
					
					return true;
				}
			};
			
			this.clone = function(){
				return new OSP.to_Map( OSP.toJSON(this) );
			}
		};
	}
	
	if( !(window.OSP && window.OSP._OpenObject) ){
			OSP._OpenObject = function(){
				OSP._Map.apply(this);
	
				this.uuid = function( uuid ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.UUID);
					return this.property.apply(this, args);
				};
	
				this.name = function( name ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.NAME);
					return this.property.apply(this, args);
				};
	
				this.sampleUuid = function( uuid ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SAMPLE_UUID);
					return this.property.apply(this, args);
				};
	
				this.targetLanguage = function ( languageId ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.TARGET_LANGUAGE);
					return this.property.apply(this, args);
				};
	 
				this.description = function( languageId, text ){
					switch( arguments.length ){
					case 0:
						return this.property(OSP.Constants.DESCRIPTION);
					case 1:
						if( typeof languageId === 'string' ){
						var description = this.property(OSP.Constants.DESCRIPTION);
						if( description )
							return description.property( languageId );
						else
							return '';
					}
					else 	if( arguments[0] instanceof OSP.LocalizedText )
						return this.property(OSP.Constants.DESCRIPTION, arguments[0]);
					else
						return false;
				case 2:
					if( typeof languageId === 'string' ){
						var description = this.property(OSP.Constants.DESCRIPTION);
						if( description )
							return description.property( languageId, text );
						else{
							this.description(new OSP.LocalizedText());
							this.description(languageId, text);
						}
					}
					else
						return false;
				default:
					return false;
				}
			};
	
			this.removeDescription = function( languageId ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DESCRIPTION);
				return this.property.apply(this, args);
			};
		};
	}
	
	if( !(window.OSP && window.OSP.Path) ){
		OSP.Path = function( jsonObject ){
			OSP._Map.apply(this);
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
			
			this.setPath = function( parent, name, type, relative ){
				if( arguments.length < 2 )
					return false;
				
				this.parent(parent);
				this.type(type);
				if( type === OSP.Constants.FOLDER )
					this.folderName(name);
				else if( type === OSP.Constants.EXT )
					this.extension(name.replace('*', '').replace('.', '').replace('/',''));
				else
					this.fileName(name);
				
				this.relative(relative);
				return true;
			};
			
			this.type = function( type ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.TYPE);
				return this.property.apply(this, args);
			};
	
			this.parent = function( parent ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.PARENT);
				return this.property.apply(this, args);
			};
			
			this.name = function( name ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.NAME);
				return this.property.apply(this, args);
			};
	
			this.fileName = function( fileName ){
				if( this.type() !== OSP.Constants.FILE )
					return false;
				
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.NAME);
				return this.property.apply(this, args);
			};
			
			this.relative = function( relative ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.RELATIVE);
				return this.property.apply(this, args);
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
	
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.NAME);
				return this.property.apply(this, args);
			};
			
			this.fullPath = function(){
				if( !(this.parent() && this.fileName()) )
					return false;
					 
				if( this.pathType() === OSP.Constants.EXT )
					return this.parentPath();
				
				return this.parentPath()+'/'+ this.fileName();
			};
		};
	}
	
	if( !(window.OSP && window.OSP.LocalizedText) ){
		OSP.LocalizedText = function( jsonObject ){
			OSP._Map.apply(this);
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
	
			this.text = function(languageId, text) {
				return this.property.apply(this, arguments);
			};
	
			this.removeText = function(languageId) {
				return this.removeProperty.apply(this, arguments);
			};
	
			this.toXml = function(availableLanguageIds, defaultLanguageId) {
				if( !availableLanguageIds )	availableLanguageIds = '';
				if( !defaultLanguageId )	defaultLanguageId = '';
				
				var xml = '<?xml version=\'1.0\' encoding=\'UTF-8\'?>';
				xml += '<root available-locales=\'';
				xml += availableLanguageIds+ '\' ';
				xml += 'default-locale=\'' + defaultLanguageId + '\'>';
	
				for( var languageId in this ){
					if( typeof this[languageId] !== 'string' ){
						continue;
					}
					
					var value = this.property(languageId);
					xml += '<display language-id=\'' + languageId + '\'>' + value
								+ '</display>';
				}
				xml += '</root>';
	
				return xml;
			};
		};
	}
	
	if( !(window.OSP && window.OSP.DataStructure) ){
		OSP.DataStructure = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			this.clone = function(){
				return new OSP.DataStructure( OSP.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.DESCRIPTION ){
						result = this.property(key, new OSP.LocalizedText(jsonObject[key]));
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	return result;
				}
				
				return result;
			};
			
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
	
	if( !(window.OSP && window.OSP.Data) ){
		OSP.Data = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			this.scienceApp = function( scienceApp ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.SCIENCE_APP);
				return this.property.apply(this, args);
			};
			
			this.inputData = function( inputData ){
				if( arguments.length === 1){
					if( !(inputData instanceof OSP.InputData) )
						return false;
				}
				
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.INPUT_DATA);
				return this.property.apply(this, args);
			} ;
			
			this.dataType = function( uuid ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DATA_TYPE);
				return this.property.apply(this, args);
			};
			
			this.path = function( path ){
				if( arguments.length === 1 ){
					if( !(path instanceof OSP.Path) )
						return false;
				}
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.PATH);
				return this.property.apply(this, args);
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
				return path.setPath( parent, name, type, relative );
			};
			
			this.pathParent = function( parent ){
				var path = this.safePath();
				return path.parent( parent );
			};
			
			this.pathType = function( type ){
				var path = this.safePath();
				return path.type(type);
			};
			
			this.pathName = function( name ){
				var path = this.safePath();
				return path.name(name);
			};
			
			this.pathRelative = function( relative ){
				var path = this.safePath();
				return path.relative(relative);
			};
			
			this.clone = function(){
				return new OSP.Data( OSP.toJSON(this) );
			};

			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.DESCRIPTION ){
						result = this.property(key, new OSP.LocalizedText(jsonObject[key]));
					}
					else if( key === OSP.Constants.PATH ){
						result = this.property(key, new OSP.Path( jsonObject[key]));
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	return result;
				}
				
				return result;
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}

	if( !(window.OSP && window.OSP.DataType) ){
		OSP.DataType = function(jsonObject){
			OSP._OpenObject.apply(this);
			
			this.structure = function( structure ){
				if( !(structure instanceof OSP.DataStructure) )
					return false;
				
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.STRUCTURE);
				return this.property.apply(this, args);
			};
			
			this.defaultEditor = function(uuid){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DEFAULT_EDITOR);
				return this.property.apply(this, args);
			};
			
			this.defaultAnalyzer = function(uuid){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DEFAULT_ANALYZER);
				return this.property.apply(this, args);
			};
			
			this.availableEditors = function( editors ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.AVAILABLE_EDITORS);
				return this.property.apply(this, args);
			};
			
			this.availableAnalyzers = function( analyzers ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.AVAILABLE_ANALYZERS);
				return this.property.apply(this, args);
			};
			
			this.addEditor = function( uuid ){
				var editors = this.availableEditors();
				if( !editors ){
					editors = [];
					this.availableEditors(editors);
				}
				editors.push(uuid);
				
				return editors;
			};
			
			this.removeEditor = function( uuid ){
				var editors = this.availableEditors();
				if( !editors )	return false;
				
				for( var key in editors ){
					if( editors[key] === uuid ){
						editors.splice( key, 1);
						if( uuid === this.defaultEditor() )
							this.removeProperty(OSP.Constants.DEFAULT_EDITOR);
						return true;
					}
				}
				
				return false;
			};
			
			this.addAnalyzer = function( uuid ){
				var analyzers = this.availableAnalyzers();
				if( !analyzers ){
					analyzers = [];
					this.availableAnalyzers(analyzers);
				}
				analyzers.push(uuid);
				
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
				return new OSP.DataType( OSP.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.DESCRIPTION ){
						result = this.property(key, new OSP.LocalizedText(jsonObject[key]));
					}
					else if( key === OSP.Constants.STRUCTURE ){
						result = this.property(key, new OSP.DataStructure( jsonObject[key]));
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	return result;
				}
				
				return result;
			};
			
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
	
	if( !(window.OSP && window.OSP.InputData) ){
		OSP.InputData = function( jsonObject ){
			OSP._Map.apply(this);
			
			this.dataUuid = function( uuid ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DATA_UUID);
				return this.property.apply(this, args);
			};
			
			this.path = function( path ){
				if( arguments.length === 1 ){
					if( !(path instanceof OSP.Path) )
						return false;
				}
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.PATH);
				return this.property.apply(this, args);
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
				return path.setPath( parent, name, type, relative );
			};
			
			this.pathParent = function( parent ){
				var path = this.safePath();
				return path.parent( parent );
			};
			
			this.pathType = function( type ){
				var path = this.safePath();
				return path.type(type);
			};
			
			this.pathName = function( name ){
				var path = this.safePath();
				return path.name(name);
			};
			
			this.pathRelative = function( relative ){
				var path = this.safePath();
				return path.relative(relative);
			};
			
			this.context = function( context ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.CONTEXT);
				return this.property.apply(this, args);
			};
			
			this.clone = function(){
				return new OSP.InputData( OSP.toJSON(this) );
			};
	
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.PATH ){
						var path = new OSP.Path(jsonObject[key]);
						this.path(path);
					}
					else
						this.property(key, jsonObject[key]);
				}
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}

	
	if( !(window.OSP && window.OSP._Port) ){
		OSP._Port = function(){
			OSP._Map.apply(this);
			
			this.name = function( name ){
				if( !this.verifyName(name) )	return false;
				
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.NAME);
				return this.property.apply(this, args);
			};
			
			this.mandatory = function( mandatory ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.MANDATORY);
				return this.property.apply(this, args);
			};
	
			this.dataType = function( dataType ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DATA_TYPE);
				return this.property.apply(this, args);
			};
			
			this.type = function( type ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.TYPE);
				return this.property.apply(this, args);
			};
			
			this.defaultEditor = function( scienceApp ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DEFAULT_EDITOR);
				return this.property.apply(this, args);
			};
			
			this.defaultAnalyzer = function( scienceApp ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DEFAULT_ANALYZER);
				return this.property.apply(this, args);
			};
			
			this.availableEditors = function(){
				
			};
			
			this.availableAnalyzers = function(){
				
			};
			
			this.verifyName = function(name){
				if( !name )	return true;
				if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(name))  return OSP.Errors.INVALID_PORT_NAME;
				else  return true;
			};
		};

		OSP.InputPort = function( jsonObject ){
			OSP._Port.apply(this);
			
			this.order = function( order ) {
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.ORDER);
				return this.property.apply(this, args);
			};
	
			this.inputData = function( inputData ){
				if( arguments.length === 1){
					if( !(inputData instanceof OSP.InputData) )
						return false;
				}
				
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.INPUT_DATA);
				return this.property.apply(this, args);
			};
			
			this.sampleData = function( dataUuid ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.SAMPLE_UUID);
				return this.property.apply(this, args);
			};
			
			this.clone = function(){
				return new OSP.InputPort( OSP.toJSON(this) );
			};
	
			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.INPUT_DATA ){
						var inputData = new OSP.InputData( jsonObject[key] );
						this.inputData( inputData );
					}
					else
						this.property( key, jsonObject[key] );
				}
			};
			
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
		};

		OSP.OutputPort = function( jsonObject ){
			OSP._Port.apply(this);
			
			this.outputData = function( path ){
				if( arguments.length === 1 ){
					if( !(path instanceof OSP.Path) )
						return false;
				}
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.OUTPUT_DATA);
				return this.property.apply(this, args);
			};
			
			this.verifyOutput = function( url, params ){
				
				return true;
			};
			
			this.clone = function(){
				return new OSP.OutputPort( OSP.toJSON(this) );
			};

			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.OUTPUT_DATA ){
						var inputData = new OSP.InputData( jsonObject[key] );
						this.inputData( inputData );
					}
					else
						this.property( key, jsonObject[key] );
				}
			};
			
			if( arguments.length === 1 )
				this.deserialize( jsonObject );
		};
	}
	
	if( !(window.OSP && window.OSP.ScienceApp) ){
		OSP.ScienceApp = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			this.inputPorts = function( inputPorts ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.INPUT_PORTS);
				return this.property.apply(this, args);
			};
			
			this.safeInputPorts = function(){
				var inputPorts = this.inputPorts();
				if( !inputPorts ){
					inputPorts = [];
					this.inputPorts( inputPorts );
				}
				
				return inputPorts;
			};
			
			this.addInputPort = function( inputPort ){
				var inputPorts = this.safeInputPorts();
				inputPorts.push(inputPort);
				
				return inputPorts;
			};
			
			this.removeInputPort = function( portName ){
				var inputPorts = this.inputPorts();
				if( !inputPort )	return false;
				
				for( var key in inputPorts ){
					var inputPort = inputPorts[key];
					if( inputPort.name() === portName ){
						inputPorts.splice( key, 1 );
						return inputPorts;
					}
				}
				
				return false;
			};
			
			this.outputPorts = function( outputPorts ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.OUTPUT_PORTS);
				return this.property.apply(this, args);
			};
			
			this.safeOutputPorts = function(){
				var outputPorts = this.outputPorts();
				if( !outputPorts ){
					outputPorts = [];
					this.outputPorts( outputPorts );
				}
				
				return outputPorts;
			};
			
			this.addOutputPort = function( outputPort ){
				var outputPorts = this.safeOutputPorts();
				outputPorts.push(outputPort);
				
				return outputPorts;
			};
			
			this.removeOutputPort = function( portName ){
				var outputPorts = this.outputPorts();
				if( !outputPort )	return false;
				
				for( var key in outputPorts ){
					var outputPort = outputPorts[key];
					if( outputPort.name() === portName ){
						outputPorts.splice( key, 1 );
						return outputPorts;
					}
				}
				
				return false;
			};
			
			this.code = function( code ){
				
			};
			
			this.clone = function(){
				return new OSP.ScienceApp( OSP.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.DESCRIPTION ){
						result = this.property(key, new OSP.LocalizedText(jsonObject[key]));
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	return result;
				}
				
				return result;
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
	
	if( !(window.OSP && window.OSP.Task) ){
		OSP.Style = function( jsonObject ){
			OSP._Map.apply(this);
			
			this.add = function( key, value ){
				
			};
			this.remove = function( key ){
				
			};
		};
		
		OSP.ProceedCondition = function( jsonObject ){
			OSP._Map.apply(this);
			
		};
		
		OSP.Connection = function( jsonObject ){
			OSP._Map.apply(this);
			
			this.sourcePortName = function( portName ){
				
			};
			
			this.proceedConditions = function( conditions ){
				
			};
			
			
			this.destinationTask = function( taskUuid ){
				
			};
			
			this.destinationPortName = function( portName ){
				
			};
			
			this.style = function( style ){
				
			};
			
			this.clone = function(){
				return new OSP.Connection( OSP.toJSON(this) );
			};
			
			this.deserialize = function( jsonObjecct ){
				
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};

		OSP.Task = function( jsonObject ){
			OSP.ScienceApp.apply(this);
			
			this.connections = function( connections ){
				
			};
			
			this.addConnection = function( connection ){
				
			};
			
			this.removeConnection = function( ){
				
			};

			this.inputPortData = function( portName, portData ){
				
			};
			

			
			this.clone = function(){
				return new OSP.Job( OSP.toJSON(this) );
			};
		
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}

	
	if( !(window.OSP && window.OSP.Workflow) ){
		OSP.Workflow = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			this.tasks = function( tasks ){
				
			};
			
			this.execute = function(){
				
			};

			this.clone = function(){
				return new OSP.Workflow( OSP.toJSON(this) );
			};
			
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
	
	
	if( !(window.OSP && window.OSP.Simulation) ){
		OSP.Simulation = function(){
			OSP._OpenObject.apply(this);
			

			this.clone = function(){
				return new OSP.Simulation( OSP.toJSON(this) );
			};
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
})(window);
