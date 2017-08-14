(function(window){
	'use strict';
	   
	if( !window.OSP ){
		window.OSP = {};
		
		window.OSP.Constants = {
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
				INPUT_DATA:'inputData_',
				OUTPUT_PORTS:'outputPorts_',
				OUTPUT_DATA:'outputData_',
				TYPE:'type_',
				APP_UUID:'appUuid_',
				SCIENCE_APP:'scienceApp_',
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
				
				//DataStructure 
				//Parameter types
				STRING : 'string',
				NUMERIC : 'numeric',
				GROUP : 'group',
				COMMENT : 'comment',
				LIST : 'list',
				VECTOR: 'vector',
				FILE: 'file', //deprecated
				//brace and space types
				SQUARE:"SQUARE",
				SQUARE_SPACE:"SQUARE_SPACE",
				ROUND:"ROUND",
				ROUND_SPACE:"ROUND_SPACE",
				
				//property names
				LOWER_LIMIT : 'lowerLimit_',
				UPPER_LIMIT : 'upperLimit_',
				OPERAND : 'operand_',
				LOWER_OPERAND : 'lowerOperand_',
				UPPER_OPERAND : 'upperOperand_',
				PARAMETER_NAME: 'parameterName_',
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
				SLICE:'slice_',
				SLICE_VALUE:'sliceValue_',
				SLICE_MAX:'sliceMax_',
				BY_SLICE:'bySlice_',
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
				FILE_CONTENT:'fileContent_',
				
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
				}
		};
		
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

		OSP.guid = function(){
			 return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(char) {
				 var random = Math.random()*16|0, value = char === 'x' ? random : (random&0x3|0x8);
				 return value.toString(16);
			 });
		};
		
		OSP.Util = {
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
				}
		};

		OSP._MapObject = function(){

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
					else	this.property(key, value);
				}
				
				return true;
			};
		};
		
		OSP._OpenObject = function( jsonObject ){
			OSP._MapObject.apply(this);

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
			
			this.description = function( description ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.DESCRIPTION);
				return this.property.apply(this, args);
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
				return OSP.toLocalizedXml( description, availableLanguageIds, defaultLanguageId );
			};
		};
	
		OSP._StyleObject = function(){
			this.style = function( style ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.STYLE);
				return this.property.apply(this, args);
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
		};
	
		OSP.Path = function( jsonObject ){
			OSP._MapObject.apply(this);
			
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
			
			this.clone = function(){
				return new OSP.Path( OSP.Util.toJSON( this ) );
			};
		};
	
		OSP.InputData = function( jsonObject ){
			OSP._MapObject.apply(this);
			
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

	if( !OSP.Status ){
		OSP.Status = {};
		
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
			STATIC_SOLVER: 40,
			JAVASCRIPT_SOLVER: 41,
			DYNAMIC_SOLVER: 42,
			STATIC_CONVERTER: 43,
			DYNAMIC_CONVERTER: 44,
			EDITOR: 45,
			ANALYZER: 46,
			EDITABLE_ANALYZER: 47
		};
	}
	
	if( !OSP.Data ){
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
				return new OSP.Data( OSP.Util.toJSON(this) );
			};

			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.PATH ){
						result = this.path( new OSP.Path( jsonObject[key]) );
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	break;;
				}
				
				return result;
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}

	if( !OSP.DataType ){
		OSP.DataType = function(jsonObject){
			OSP._OpenObject.apply(this);
			
			var DataStructure = function( jsonObject ){
				OSP._MapObject.apply(this);
				
				var Range = function( jsonObject ) {
					OSP._MapObject.apply( this );
					
					this.lowerLimit = function( limit ) {
						if( limit ){
							if (isNaN(limit)){
								console.log(limit + ' is not a number!');
								return false;
							}
							var upperLimit = this.upperLimit();
							if( upperLimit &&	Number(upperLimit) < limit )
								return false;
						}
						
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.LOWER_LIMIT);
						return this.property.apply(this, args);
					};
					
					this.upperLimit = function( limit ) {
						if( limit ){
							if (isNaN(limit)){
								console.log(limit + ' is not a number!');
								return false;
							}
							var lowerLimit = this.lowerLimit();
							if( lowerLimit &&	Number(lowerLimit) > limit )
								return false;
						}
						
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.UPPER_LIMIT);
						return this.property.apply(this, args);
					};
					
					this.operand = function( operand ) {
						if( operand ){
							if (operand.length != 2 || 
								!/[=<]/.test(operand[0]) ||
								!/[=>]/.test(operand[1]) )
								return false;
						}
						
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.OPERAND);
						return this.property.apply(this, args);
					};
					
					this.lowerBoundary = function( flag ){
						var operand = this.operand();
						if( arguments.length === 1 ){
							if( !operand ){
								operand = [];
								this.operand( operand );
							}
							if(flag === true)
								operand[0] = '=';
							else
								operand[0] = '<';
							
							return operand;
						}
						else{
							if( !operand )	return true;
							else{
								if( operand[0] === '=' ) return true;
								else	return false;
							}
						}
					};
					
					this.upperBoundary = function( flag ){
						var operand = this.operand();
						switch( arguments.length ){
						case 0:
							if( !operand )	return true;
							else{
								if( operand[1] === '=' ) return true;
								else	return false;
							}
						case 1:
							if( !operand ){
								operand = [];
								this.operand( operand );
							}
							if( flag )
								operand[1] = '=';
							else
								operand[1] = '>';
							return operand;
						default:
							return false;
						}
					};
					
					this.setRange = function( lowerLimit, upperLimit, operand ) {
						var result = this.property( OSP.Constants.LOWER_LIMIT, lowerLimit );
						if( result === false )	return result;
						result = this.property( OSP.Constants.UPPER_LIMIT, upperLimit );
						if( result === false )	return result;
						result = this.operand(operand);
						if( result === false )	return result;

						return result;
					};
					
					this.verify = function( value ){
						var lowerBoundaryContained = this.lowerBoundary();
						var upperBoundaryContained = this.upperBoundary();
						var lowerLimit = this.lowerLimit();
						var upperLimit = this.upperLimit();
						
						if( !lowerLimit && !upperLimit )	return true;
						
						if( lowerBoundaryContained && !upperLimit){
							if( Number(value) >= Number(lowerLimit) )	return true;
						}
						else if ( !lowerBoundaryContained && !upperLimit ){
							if( Number(value) > Number(lowerLimit) )	return true;
						}
						else if ( !lowerLimit && upperBoundaryContained ){
							if( Number(value) <= Number(upperLimit) )	return true;
						}
						else if( !lowerLimit && !upperBoundaryContained){
							if( Number(value) < Number(upperLimit) )	return true;
						}
						else if(  lowerBoundaryContained && upperBoundaryContained ){
							if( Number(value) >= Number(lowerLimit) && Number(value) <= Number(upperLimit) )	
								return true;
						}
						else if( !lowerBoundaryContained && upperBoundaryContained ){
							if( Number(value) > Number(lowerLimit) && Number(value) <= Number(upperLimit) )	
								return true;
						}
						else if( lowerBoundaryContained && !upperBoundaryContained ){
							if( Number(value) >= Number(lowerLimit) && Number(value) < Number(upperLimit) )	
								return true;
						}
						else if( !lowerBoundaryContained && !upperBoundaryContained ){
							if( Number(value) > Number(lowerLimit) && Number(value) < Number(upperLimit) )	
								return true;
						}
						
						return false;
					};
					
					this.clone = function(){
						return new Range( OSP.Util.toJSON(this) );
					};
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newRange = function( jsonObject ){
					return new Range(jsonObject);
				};
				
				var _RangeAttributeMethods = function( attribute ){
					var prefix;
					if( attribute )
						prefix = attribute + 'Range';
					else
						prefix = 'range';
					
					this[prefix+'LowerLimit'] = function( limit ){
						var range = this.range();
						switch( arguments.length ){
						case 0:
							if( !range )	return false;
							else	return range.lowerLimit();
						case 1:
							if( !range ){
								range = new Range();
								this.range( range );
							}

							return range.lowerLimit( limit );
						default:
							return false;
						}
					};
					
					this[prefix+'UpperLimit'] = function( limit ){
						var range = this.range();
						switch( arguments.length ){
						case 0:
							if( !range )	return false;
							else	return range.upperLimit();
						case 1:
							if( !range ){
								range = new Range();
								this.range( range );
							}

							return range.upperLimit( limit );
						default:
							return false;
						}
					};
					
					this[prefix+'Operand'] = function( operand ){
						var range = this.range();
						switch( arguments.length ){
						case 0:
							if( !range )	return false;
							else	return range.operand();
						case 1:
							if( !range ){
								range = new Range();
								this.range( range );
							}

							return range.operand( limit );
						default:
							return false;
						}
					};
					
					this[prefix+'LowerBoundary'] = function( flag ){
						var range = this.range();
						switch( arguments.length ){
						case 0:
							if( !range )	return false;
							else	return range.lowerBoundary();
						case 1:
							if( !range ){
								range = new Range();
								this.range( range );
							}

							return range.lowerBoundary( flag );
						default:
							return false;
						}
					};
					
					this[prefix+'UpperBoundary'] = function( flag ){
						var range = this.range();
						switch( arguments.length ){
						case 0:
							if( !range )	return false;
							else	return range.upperBoundary();
						case 1:
							if( !range ){
								range = new Range();
								this.range( range );
							}

							return range.upperBoundary( flag );
						default:
							return false;
						}
					};
					
					this['isIn'+attribute+'Range'] = function( value ){
						var range = this.range();
						if( !range )	return true;
						return range.isInRange( value );
					};
					
					this.setRange = function( lowerLimit, upperLimit, operand ){
						var range = this.range();
						if( !range ){
							range = new Range();
							this.range( range );
						}
						
						return range.setRange(lowerLimit, upperLimit, operand);
					};
				};
				
				var ActivateCondition = function( jsonObject ){
					OSP._MapObject.apply(this);
					
					this.parameterName = function( name ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.PARAMETER_NAME);
						return this.property.apply(this, args);
					};

					this.type = function( type ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.TYPE);
						return this.property.apply(this, args);
					};
				};
				this.newActivateCondition = function( jsonObject ){
					return new ActivateCondition(jsonObject);
				};
				
				var NumericActivateCondition = function( jsonObject ){
					ActivateCondition.apply(this);
					_RangeAttributeMethods.apply(this);
					this.type(OSP.Constants.NUMERIC);
					
					this.range = function( range ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.RANGE);
						return this.property.apply(this, args);
					};
					
					this.setCondition = function(parameterName, lower, upper, operand, assignValue) {
						var range = this.safeRange();
						this.parameterName(parameterName);
						if( arguments.length === 5 )
							this.assignValue(assignValue);
						return range.setRange(lower, upper, operand);
					};
					
					this.checkActivate = function( value ){
						if( this.isInRange(value) == true ){
							if( !this.assignValue() || this.assignValue() === '')
								return true;
							else
								return this.assignValue();
						}
						else
							return false;
					};
					
					this.getAssignValue = function(){
						return this[OSPInputdeck.Constants.ASSIGN_VALUE];
					};
					this.assignValue = function( assignValue ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ASSIGN_VALUE);
						return this.property.apply(this, args);
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
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newNumericActivateCondition = function( jsonObject ){
					return new NumericActivateCondition( jsonObject );
				};
				
				var ListItemActivateCondition = function( jsonObject ){
					ActivateCondition.apply(this);
					this.type(OSP.Constants.VARIABLE_TYPE_LIST);
					
					this.value = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.LIST_ITEM_VALUE);
						return this.property.apply(this, args);
					};
					
					this.setCondition = function(parameterName, listItemValue, assignValue) {
						this.parameterName(parameterName);
						this.value(listItemValue);
						if( arguments.length === 3 )
							this.assignValue(assignValue);
					};
					
					this.assignValue = function(){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ASSIGN_VALUE);
						return this.property.apply(this, args);
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
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newListItemActivateCondition = function( jsonObject ){
					return new ListItemActivateCondition( jsonObject );
				};
				
				var ListItem = function( jsonObject ) {
					OSP._MapObject.apply(this);
					
					this.itemText = function( textObject ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.TEXT);
						return this.property.apply(this, args);
					};
					
					this.itemValue = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};
					
					this.localizedItemText = function(languageId, text) {
						var itemText = this.itemText();
						
						switch( arguments.length ){
						case 1:
							if( !itemText )	return '';
							else	return itemText[languageId];
						case 2:
							if( !itemText ){
								itemText = {};
								this.itemText( itemText );
							}
							return itemText[languageId] = text;
						default:
							return false;
						}
					};
					
					this.removeItemText = function(languageId) {
						var itemText = this.itemText();
						if( !itemText )	return true;
						
						return delete itemText[languageId];
					};
					
					this.itemTextXml = function(availableLanguageIds, defalutLanguageId) {
						var itemText = this.itemText();
						if( !itemText )	itemText = {};
						return OSP.toLocalizedXml( itemText, availableLanguageIds, defalutLanguageId );
					};
					
					this.activateConditions = function( conditions ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ACTIVATE_CONDITIONS);
						return this.property.apply(this, args);
					};
					
					this.addActivateCondition = function(condition) {
						var conditions = this.activateConditions();
						if( !conditions ){
							conditions = [];
							this.activateConditions(conditions);
						}
						conditions.push( condition );
						return conditions;
					};
					
					this.addListItemActivateCondition = function(parameterName, listItemValue, assignValue) {
						var condition = new ListItemActivateCondition();
						condition.setCondition( parameterName, listItemValue, assignValue );
						
						return this.addActivateCondition( condition );
					};
					
					this.addNumericActivateCondition = function(parameterName, min, max, operand, assignValue) {
						var condition = new NumericActivateCondition();
						condition.setCondition( parameterName, min, max, operand, assignValue );
						
						return this.addActivateCondition( condition );
					};
					
					this.removeActivateConditions = function(parameterName) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								this.removeActivateConditions(parameterName);
								break;
							}
						}

						return conditions;
					};
					
					this.removeListItemActivateCondition = function(parameterName, listItemValue) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								listItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						
						return conditions;
					};
					
					this.removeNumericActivateCondition = function( parameterName, min, max ) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								min === condition.rangeLowerLimit() &&
								max === condition.rangeUpperLimit() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						
						return conditions;
					};
					
					this.checkActivate = function(parameterName, value){
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						var activate = false;
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								activate = condition.checkActivate();
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
									if( jsonCondition[OSP.Constants.TYPE] === OSP.Constants.VARIABLE_TYPE_LIST)
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
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newListItem = function( jsonObject ){
					return new ListItem( jsonObject );
				};
				
				var Sweep = function( jsonObject ){
					OSP._MapObject.apply(this);
					_RangeAttributeMethods.apply(this);
					
					this.slice = function( slice ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SLICE);
						return this.property.apply(this, args);
					};
					
					this.sliceValue = function( value ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SLICE_VALUE);
						return this.property.apply(this, args);
					};
					
					this.maxSlice = function( maxSlice ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SLICE_MAX);
						return this.property.apply(this, args);
					};
					
					this.range = function( range ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.RANGE);
						return this.property.apply(this, args);
					};
					
					this.slicedValues = function(){
						var strLower = this.rangeLowerLimit();
						var strUpper = this.rangeUpperLimit();
						if( !strLower || !strUpper ) return false;
						
						lower = Number( strLower );
						upper = Number( strUpper );
						
						var values = [];
						var isExponential = OSP.Util.isExponetioal(strLower);
						var includeLower = this.rangeLowerBoundary();
						var includeUpper = this.rangeUpperBoundary();
						var sliceValue;
						var method = this.sweepBySlice(); 
						
						var index =0;
						var sliceCount;
						
						if( method == true ){
								sliceCount = parseInt(this.slice());
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
						
						if(method)
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
					
					this.sweepBySlice = function( flag ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.BY_SLICE);
						return this.property.apply(this, args);
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
				};
				this.newSweep = function( jsonObject ){
					return new Sweep( jsonObject );
				};

				var _Parameter = function( jsonObject ) {
					OSP._MapObject.apply(this);
					
					this.name = function( name ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.NAME);
						return this.property.apply(this, args);
					};
					
					this.nameText = function( textJsonObj ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.NAME_TEXT);
						return this.property.apply(this, args);
					};
					
					this.localizedNameText = function( languageId, text ) {
						var nameText = this.nameText();
						
						switch( arguments.length ){
						case 1:
							if( !nameText )	return '';
							else	return nameText( languageId );
						case 2:
							if( !nameText )	nameText = {};
							return nameText[languageId] = text;
						default:
							return false;
						}
					};
					
					this.removeLocalizedNameText = function( languageId ) {
						var nameText = this.nameText();
						if( !nameText )	return true;
						
						return nameText[languageId];
					};
					
					this.localizedNameTextXML = function(availableLanguageIds, defaultLanguageId) {
						var nameText = this.nameText();
						if( !nameText )	nameText = {};
						
						return OSP.Util.toLocalizedXML(nameText, availableLanguageIds, defaultLanguageId);
					};
					
					this.type = function( type ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.TYPE);
						return this.property.apply(this, args);
					};
					
					this.activateConditions = function( conditions ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ACTIVATE_CONDITIONS);
						return this.property.apply(this, args);
					};
					
					this.addActivateCondition = function( condition ) {
						var conditions = this.activateConditions();
						if( !conditions ){
							conditions = [];
							this.activateConditions(conditions);
						}
						conditions.push( condition );
						return conditions;
					};
					
					this.addListItemActivateCondition = function(parameterName, listItemValue, assignValue) {
						var condition = new ListItemActivateCondition();
						condition.setCondition( parameterName, listItemValue, assignValue );
						
						return this.addActivateCondition( condition );
					};
					
					this.addNumericActivateCondition = function(parameterName, min, max, operand, assignValue) {
						var condition = new NumericActivateCondition();
						condition.setCondition( parameterName, min, max, operand, assignValue );
						
						return this.addActivateCondition( condition );
					};
					
					this.removeActivateConditions = function(parameterName) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								this.removeActivateConditions(parameterName);
								break;
							}
						}

						return conditions;
					};
					
					this.removeListItemActivateCondition = function(parameterName, listItemValue) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								listItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						
						return conditions;
					};
					
					this.removeNumericActivateCondition = function( parameterName, min, max ) {
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								min === condition.rangeLowerLimit() &&
								max === condition.rangeUpperLimit() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						
						return conditions;
					};
					
					this.checkActivate = function(parameterName, value){
						var conditions = this.activateConditions();
						if( !conditions )	return true;
						
						var activate = false;
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								activate = condition.checkActivate();
								if( activate )	return activate;
							}
						}
						
						return activate;
					};
					
					this.order = function( order ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ORDER);
						return this.property.apply(this, args);
					};

					this.active = function( active ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.ACTIVE);
						return this.property.apply(this, args);
					};

					this.disabled = function( disabled ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.DISABLED);
						return this.property.apply(this, args);
					};

					this.verifyName = function(name){
						if(/[a-zA-Z\\_]/.test(name.charAt(0)) == false) return false;
						if(/[^a-zA-Z0-9\\_]/.test(name))	return false;
						return true;
					};

					this.deserialize = function( jsonObject ){
						for( key in jsonObject){
							switch(key){
							case OSP.Constants.ACTIVATE_CONDITIONS:
								var jsonConditions = jsonObject[key];
								
								for( var index in jsonConditions ){
									var jsonCondition = jsonConditions[index];
									var condition;
									if( jsonCondition[type] === OSP.Constants.VARIABLE_TYPE_LIST )
										condition = new ListItemActivateCondition( jsonCondition );
									else
										condition = new NumericActivateCondition( jsonCondition );
									
									this.addActivateCondition(condition);
								}
								break;
							default:
								this.property( key, jsonObject[key] );	
							}
						}
					};
				};
				
				var NumericParameter = function( jsonObject ){
					Parameter.apply(this);
					//_RangeAttributeMethods.apply(this, 'value');
					
					this.type(OSP.Constants.NUMERIC);
					this.active(true);

					this.unit = function( unit ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.UNIT);
						return this.property.apply(this, args);
					};
					
					this.value = function( value ) {
						if( arguments.length === 1 ){
							if( this.isInValueRange( value ) === false )
								return false;
						}
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};
					
					this.valueRange = function( range ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.RANGE);
						return this.property.apply(this, args);
					};
					
					this.valueRangeLowerLimit = function( limit ) {
						var range = this.valueRange();
						switch( arguments.length ){
						case 0:
							if( range )	return range.lowerLimit();
							else	return false;
						case 1:
							if( !range ){
								range = new Range();
								this.valueRange(range);
							}
							return range.lowerLimit( limit );
						default:
							return false;
						}
					};
					
					this.valueRangeUpperLimit = function( limit ) {
						var range = this.valueRange();
						switch( arguments.length ){
						case 0:
							if( range )	return range.upperLimit();
							else	return false;
						case 1:
							if( !range ){
								range = new Range();
								this.valueRange(range);
							}
							return range.upperLimit( limit );
						default:
							return false;
						}
					};
					
					this.valueRangeOperand = function( operand ) {
						var range = this.valueRange();
						switch( arguments.length ){
						case 0:
							if( range )	return range.operand();
							else	return false;
						case 1:
							if( !range ){
								range = new Range();
								this.valueRange(range);
							}
							return range.operand( operand );
						default:
							return false;
						}
					};
					
					this.valueRangeLowerBoundary = function( flag ){
						var range = this.valueRange();
						if( !range )	return false;
						return range.lowerBoundary( falg );
					};
					
					this.valueRangeUpperBoundary = function( flag ){
						var range = this.valueRange();
						if( !range )	return false;
						return range.upperBoundary( falg );
					};
					
					this.setValueRange = function( lowerLimit, upperLimit, operand ) {
						var range = this.valueRange();
						if( !range ){
							range = new Range();
							this.valueRange( range );
						}
						return range.setRange( lowerLimit, upperLimit, operand );
					};
					
					this.isInValueRange = function( value ){
						var range = this.valueRange();
						if( !range )	return true;
						
						return range.isInRange( value );
					};
					
					this.sweep = function( sweep ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SWEEP);
						return this.property.apply(this, args);
					};
					
					this.sweepable = function( flag ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SWEEPABLE);
						return this.property.apply(this, args);
					};
					
					this.maxSweepSlice = function( max ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.maxSlice.apply(this, arguments);
					};
					
					this.sweepBySlice = function( flag ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.sweepBySlice.apply(this, arguments);
					};
					
					this.sweepSlice = function( slice ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.sweepSlice.apply(this, arguments);
					};
					
					this.sweepSliceValue = function( value ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.sliceValue.apply(this, arguments);
					};
					
					this.slicedValues = function(){
						var sweep = this.getSweep();
						if( !sweep )	return false;
						
						return sweep.slicedValues();
					};
					
					this.sweepRangeLowerLimit = function( limit ) {
						var sweep = this.sweep();
						
						switch( arguments.length ){
						case 0:
							if( !sweep )	return false;
							return sweep.rangeLowerLimit();
						case 1:
							if( !sweep ){
								sweep = new Sweep();
								this.sweep( sweep );
							}
							
							var lowerLimit = this.valueRangeLowerLimit();
							var upperLimit = this.valueRangeUpperLimit();
							
							if( !lowerLimit && !upperLimit )
								return sweep.rangeLowerLimit(limit);
							else if( !lowerLimit && upperLimit ){
								if( Number(limit) < Number(upperLimit) )	
									return sweep.rangeLowerLimit(limit);
							}
							else if( lowerLimit && !upperLimit ){
								if( Number(limit) > Number(lowerLimit) )	
									return sweep.rangeLowerLimit(limit);
							}
							else{
								if( Number(limit) > Number(lowerLimit) && 
									Number(limit) < Number(upperLimit) )	
									return sweep.RangeLowerLimit(limit);
							}
						default:
							return false;
						}
					};
					
					this.sweepRangeUpperLimit = function( limit ) {
						var sweep = this.sweep();
						
						switch( arguments.length ){
						case 0:
							if( !sweep )	return false;
							return sweep.rangeUpperLimit();
						case 1:
							if( !sweep ){
								sweep = new Sweep();
								this.sweep( sweep );
							}
							
							var lowerLimit = this.valueRangeLowerLimit();
							var upperLimit = this.valueRangeUpperLimit();
							
							if( !lowerLimit && !upperLimit )
								return sweep.rangeUpperLimit(limit);
							else if( !lowerLimit && upperLimit ){
								if( Number(limit) < Number(upperLimit) )	
									return sweep.rangeUpperLimit(limit);
							}
							else if( lowerLimit && !upperLimit ){
								if( Number(limit) > Number(lowerLimit) )	
									return sweep.rangeUpperLimit(limit);
							}
							else{
								if( Number(limit) > Number(lowerLimit) && 
									Number(limit) < Number(upperLimit) )	
									return sweep.RangeUpperLimit(limit);
							}
						default:
							return false;
						}
					};
					
					this.sweepRangeOperand = function( operand ) {
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.rangeOperand.apply(this, arguments);
					};
					
					this.sweepRangeLowerBoundary = function( flag ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.rangeLowerBoundary.apply(this, arguments);
					};
					
					this.sweepRangeUpperBoundary = function( flag ){
						var sweep = this.sweep();
						if( arguments.length === 1 && !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.rangeUpperBoundary.apply(this, arguments);
					};

					this.setSweepRange = function(lowerLimit, upperLimit, operand) {
						if( Number(lowerLimit) < this.valueRangeLowerLimit() )
							return false;
						else if( Number(upperLimit) > this.valueRangeUpperLimit())
							return FALSE;
						
						var sweep = this.sweep();
						if( !sweep ){
							sweep = new Sweep();
							this.sweep( sweep );
						}
						
						return sweep.setSweepRange ( lowerLimit, upperLimit, operand );
					};
					
					this.isInSweepRange = function( value ){
						var sweep = this.getSweep();
						if( !sweep )	return false;
						
						return sweep.isInRange(value);
					};
					
					this.removeSweep = function(){
						this.removeProperty(OSP.Constants.SWEEPABLE);
						this.removeProperty(OSP.Constants.SWEEPED);
						this.removeProperty(OSP.Constants.SWEEP);
					};
					
					this.sweeped = function( flag ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SWEEPED);
						return this.property.apply(this, args);
					};
					
					this.deserialize = function (jsonObject){
						for( key in jsonObject){
							switch(key){
							case OSP.Constants.RANGE:
								this.valueRange( new Range(jsonObject[key]) );
								break;
							case OSP.Constants.SWEEP:
								this.sweep( new Sweep( jsonObject[key] ) );
								break;
							default:
								this.property(key, jsonObject[key]);	
							}
						}

						if( arguments.length === 1 )
							this.deserialize(jsonObject);
					};
				};
				this.newNumericParameter = function( jsonObject ){
					return new NumericParameter( jsonObject );
				};
				
				var ListParameter = function( jsonObject ){
					_Parameter.apply(this);
					
					this.type(OSP.Constants.LIST);
					this.active(true);

					this.value = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};
					
					this.list = function( list ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.LIST);
						return this.property.apply(this, args);
					};
					
					this.listItem = function(itemValue, listItem) {
						var list = this.list();
						
						switch( arguments.length ){
						case 1:
							if( !list )	return false;
							return list[itemValue];
						case 2:
							if( !list ){
								list = {};
								this.list( list );
							}
							return list[itemValue] = listItem;
						default:
							return false;
						}
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
							case OSP.Constants.ACTIVATE_CONDITIONS:
								var jsonConditions = jsonObject[key];
								for( var index in jsonConditions ){
									var jsonCondition = jsonConditions[index];
									if( jsonCondition[type] === OSP.Constants.LIST )
										this.addActivateCondition( new ListItemActivateCondition( jsonCondition ) );
									else
										this.addActivateCondition( new NumericActivateCondition( jsonCondition ) );
								}
								break;
							case OSP.Constants.LIST:
								var jsonList = jsonObject[key];
								for( var index in jsonList ){
									this.listItem( new ListItem( jsonList[index] ) );
								}
								break;
							default:
								this.property( key, jsonObject[key] );	
							}
						}
					};

					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newListParameter = function( jsonObject ){
					return new ListParameter( jsonObject );
				};

				var StringParameter = function( jsonObject ){
					_Parameter.apply(this);
					
					this.type(OSP.Constants.STRING);
					this.active(true);

					this.value = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};

					this.clone = function(){
						return new StringParameter( OSP.Util.toJSON(this) );
					};

					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newStringParameter = function( jsonObject ){
					return new StringParameter( jsonObject );
				};

				var VectorParameter = function( jsonObject ){
					_Parameter.apply(this);
					this.type(OSP.Constants.VECTOR);
					this.active(true);

					this.value = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};
					
					this.getValueString = function(startChar, endChar, delimiter, space){
						if(delimiter !== ' ')	delimiter += space;
						var string = JSON.stringify(this.value());
						string = string.replace('[', startChar).replace(']', endChar).replace(/,/g, delimiter).replace(/\"/gi,"");
						return string;
					};
					
					this.dimension = function( dimension ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.DIMENSION);
						return this.property.apply(this, args);
					};

					this.clone = function(){
						return new VectorParameter( OSP.Util.toJSON(this) );
					};
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newVectorParameter = function( jsonObject ){
					return new VectorParameter( jsonObject );
				};
				
				var CommentParameter = function( jsonObject ){
					_Parameter.apply(this);
					this.type(OSP.Constants.COMMENT);
					this.active(true);
					
					this.value = function( value ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE);
						return this.property.apply(this, args);
					};

					this.clone = function(){
						return new CommentParameter( OSP.Util.toJSON(this) );
					};
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newCommentParameter = function(){
					return new OSPInputdeck.CommentParameter();
				};

				var GroupParameter = function( jsonObject ){
					_Parameter.apply(this);
					this.type(OSP.Constants.GROUP);
					this.active(true);
					
					this.attachParameter = function( parameterName ){
						var condisions = this.activateConditions();
						if( !conditions ){
							conditions = [];
							this.activateConditions( conditions );
						}
						
						var condition = new ActivateCondition();
						condition.parameterName( parameterName );
						condisions.push( condition );
						return conditions;
					};
					
					this.detachParameter = function( parameterName ){
						var condisions = this.activateConditions();
						if( !conditions )	return true;
						
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1 );
								return conditions;
							}
						}
						
						return conditions;
					};
					
					this.getAttachedParameterNames = function(){
						var condisions = this.activateConditions();
						if( !conditions )	return [];

						var names= [];
						for( var index in conditions ){
							var condition = conditions[index];
							names.push( condition.parameterName() );
						}
						return names;
					};
					
					this.hasPanameter = function( parameterName ){
						var names = this.getAttachedParameterNames();
						for(var index in names ){
							if(names[index] === parameterName )	return true;
						}
						return false;
					};
					
					this.clone = function(){
						return new CommentParameter( OSP.Util.toJSON(this) );
					};
					
					this.deserialize = function( jsonObject ){
						for( var key in jsonObject ){
							switch( key ){
							case OSP.Constants.ACTIVATE_CONDITIONS:
								var conditions = [];
								this.activateConditions( conditions );
								
								var jsonCondisions = jsonObject[key];
								for( var index in jsonConditions ){
									conditions.push( new ActivateCondition( jsonConditions[index] ) );
								}
								
								break;
							default:
								this.property( key, jsonObject[key] );
							}
						}
					};
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newGroupParameter = function( jsonObject ){
					return new GroupParameter( jsonObject );
				};

				var VectorForm = function( jsonObject ) {
					OSP._MapObject.apply( this );
					
					this.setForm = function(braceChar, delimiter, sample) {
						this.braceChar( braceChar );
						this.delimiter( delimiter );
						this.sample( sample );
					};
					this.braceChar = function( char ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.BRACE_CHAR);
						return this.property.apply(this, args);
					};

					this.delimiter = function( delimiter ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.DELIMITER);
						return this.property.apply(this, args);
					};

					this.space = function( space ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SPACE);
						return this.property.apply(this, args);
					};
					
					this.sample = function( sample ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SAMPLE);
						return this.property.apply(this, args);
					};

					this.clone = function(){
						return new CommentParameter( OSP.Util.toJSON(this) );
					};
					
					if( arguments.length === 1 )
						this.deserialize(jsonObject);
				};
				this.newVectorForm = function( jsonObject ){
					return new VectorForm( jsonObject );
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
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.VALUE_DELIMITER);
						return this.property.apply(this, args);
					};

					this.parameterDelimiter = function( delimiter ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.PARAMETER_DELIMITER);
						return this.property.apply(this, args);
					};
					
					this.commentChar = function( char ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.COMMENT_CHAR);
						return this.property.apply(this, args);
					};
					
					this.space = function( space ) {
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.SPACE);
						return this.property.apply(this, args);
					};
					
					this.controlChar = function( char ){
						var args = Array.from(arguments);
						args.unshift(OSP.Constants.CONTROL_CHAR);
						return this.property.apply(this, args);
					};
					
					if( arguments.length === 1 )
						this.deserialize( jsonObject );
				};
				this.newParameterForm = function( jsonObject ){
					return new ParameterForm( jsonObject );
				};

				this.vectorForm = function( form ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.VECTOR_FORM);
					return this.property.apply(this, args);
				};
				
				this.setVectorForm = function(braceChar, delimiter, sample) {
					var vectorForm = this.vectorForm();
					if( !vectorForm ){
						vectorForm = new VectorForm();
						this.vectorForm( vectorForm );
					}
					
					return vectorForm.setForm(braceChar, delimiter, sample);
				};
				
				this.vectorFormBraceChar = function( char ) {
					var vectorForm = this.vectorForm();
					
					switch( arguments.length ){
					case 0:
						if( !vectorForm )	return false;
						return vectorForm.braceChar();
					case 1:
						if( !vectorForm ){
							vectorForm = new VectorForm();
							this.vectorForm( vectorForm );
						}
						vectorForm.braceChar( char );
					default:
						return false;
					}
				};

				this.vectorFormDelimiter = function( delimiter ) {
					var vectorForm = this.vectorForm();
					
					switch( arguments.length ){
					case 0:
						if( !vectorForm )	return false;
						return vectorForm.delimiter();
					case 1:
						if( !vectorForm ){
							vectorForm = new VectorForm();
							this.vectorForm( vectorForm );
						}
						vectorForm.delimiter( delimiter );
					default:
						return false;
					}
				};
					
				this.vectorFormSpace = function( space ) {
					var vectorForm = this.vectorForm();
					
					switch( arguments.length ){
					case 0:
						if( !vectorForm )	return false;
						return vectorForm.space();
					case 1:
						if( !vectorForm ){
							vectorForm = new VectorForm();
							this.vectorForm( vectorForm );
						}
						vectorForm.space( space );
					default:
						return false;
					}
				};
					
				this.vectorFormSample = function( sample ) {
					var vectorForm = this.vectorForm();
					
					switch( arguments.length ){
					case 0:
						if( !vectorForm )	return false;
						return vectorForm.sample();
					case 1:
						if( !vectorForm ){
							vectorForm = new VectorForm();
							this.vectorForm( vectorForm );
						}
						vectorForm.sample( sample );
					default:
						return false;
					}
				};
					
				this.parameterForm = function( form ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.PARAMETER_FORM);
					return this.property.apply(this, args);
				};
					
				this.setParameterForm = function(valueDelimiter, parameterDelimiter, commentChar, controlChar) {
					var parameterForm = this.parameterForm();
					if( !parameterForm ){
						parameterForm = new ParameterForm();
						this.parameterForm( parameterForm );
					}
					
					return parameterForm.setForm(valueDelimiter, parameterDelimiter, commentChar, controlChar)
				};
				
				this.parameterFormValueDelimiter = function( delimiter ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.VALUE_DELIMITER);
					return this.property.apply(this, args);
				};

				this.parameterFormParameterDelimiter = function( delimiter ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.PARAMETER_DELIMITER);
					return this.property.apply(this, args);
				};
					
				this.parameterFormCommentChar = function( char ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.COMMENT_CHAR);
					return this.property.apply(this, args);
				};
				
				this.parameterFormControlChar = function( char ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.CONTROL_CHAR);
					return this.property.apply(this, args);
				};

				this.parameterFormSpace = function( space ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SPACE);
					return this.property.apply(this, args);
				};

				this.parameters = function( parameters ) {
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.PARAMETERS);
					return this.property.apply(this, args);
				};
				
				this.getParameter = function( parameterName ){
					var parameters = this.parameters();
					for( var index in parameters ){
						var parameter = parameters[index];
						if( parameter.name() === parameterName )
							return parameter;
					}
					
					return false;
				};
				
				this.getParameterByOrder= function( order, groupOrder ){
					var parameters = this.parameters();
					if( !parameters )	return false;
					
					for( var index in parameters ){
						var parameter = parameters[index];
						
						if( !groupOrder ){
							if( !this.isInGroup( parameter.name() ) ){
								if( parameter.order() == order)	return parameter;
							}
						}
						else{
							var group = this.getParameterByOrder(groupOrder);
							var names = group.getAttachedParameterNames();
							for(var i in names ){
								parameter = this.parameter(names[i]);
								if(parameter.order() == order)	return parameter;
							}
						}
					}
				};
				
				this.getTopLevelParameters = function(){
					var parameters = this.parameters();
					if( !parameters )	return false;
					
					var topLevels = [];
					for( var index in parameters ){
						var parameter = parameters[index];
						if( !this.isInGroup(parameter.name()) )
							topLevels.push( parameter );
					}
					return topLevels;
				};
				
				this.getActiveParameters = function(){
					var parameters = this.parameters();
					if( !parameters )	return false;
					
					var actives = [];
					for( var index in parameters ){
						var parameter = parameters[index];
						if( parameter.active() )
							actives.push( parameter );
					}
					return actives;
				};
				
				this.addParameter = function( parameter ) {
					var parameters = this.parameters();
					if( !parameters ){
						parameters = [];
						this.parameters( parameters );
					};
					
					parameters.push( parameter );
					return parameters;
				};
				
				this.removeParameter = function( parameterName ) {
					var parameters = this.parameters();
					if( !parameters )	return true;
					
					for( var index in parameters ){
						var parameter = parameters[index];
						if( parameter.name() === parameterName ){
							parameters.splice( index, 1 );
							break;
						}
					}
					
					return parameters;
				};
				
				this.newParameter = function( parameterName, type ){
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
					if( !parameters ){
						parameters = [];
						this.parameters( parameters );
					}
					parameters.push( parameter );
					return parameter;
				};
				
				this.sweepMax = function( max ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SWEEP_MAX);
					return this.property.apply(this, args);
				};
				
				this.sweepCount = function( count ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SWEEP_COUNT);
					return this.property.apply(this, args);
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
				
				this.getSweepedParameters = function(){
					var parameters = this.parameters();
					
					var sweepedParameters = [];
					for(var i in parameters ){
						var parameter = parameters[i];
						if( parameter.sweeped() )
							sweepedParameters.push( parameter );
					}

					return sweepedParameters;
				};
				
				this.sweeped = function(){
					if( this.sweepCount() === 0)	return false;
					else		return true;
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
				
				this.getParameterNames = function( type ){
					var parameters = this.parameters();
					if( !parameters )	return false;
					
					var names = [];
					for( var i in parameters ){
						if( type ){
							if( names[i].type() === type )
								names.push( names[i].name() );
						}
						else
							names.push( parameters[i].name() );
					}
					return names;
				};
				
				this.deserialize = function( jsonObject ){
					//console.log( JSON.stringify(jsonObject,null,4));
					for( var key in jsonObject ){
						switch ( key ){
						case OSP.Constants.VECTOR_FORM:
							this.vectorForm( new VectorForm( jsonObject[key]) );
							break;
						case OSP.Constants.PARAMETER_FORM:
							this.parameterForm( new ParameterForm( jsonData[key]) );
							break;
						case OSP.Constants.PARAMETERS:
							this.parameters( [] );
							var jsonParameters = jsonObject[key];
							for( var i in jsonParameters ){
								var jsonParameter = jsonParameters[i];
								var parameter;
								switch( jsonParameter[OSP.Constants.TYPE] ){
								case OSP.Constants.COMMENT:
									parameter = new CommentParameter( jsonParameter );
									break;
								case OSP.Constants.GROUP:
									parameter = new GroupParameter( jsonParameter );
									break;
								case OSP.Constants.LIST:
									parameter = new ListParameter( jsonParameter );
									break;
								case OSP.Constants.NUMERIC:
									parameter = new NumericParameter( jsonParameter );
									break;
								case OSP.Constants.STRING:
									parameter = new StringParameter( jsonParameter );
									break;
								case OSP.Constants.VECTOR:
									parameter = new VectorParameter( jsonParameter );
									break;
								default:
									return false;
								}
								
								this.addParameter(parameter);
							}
							break;
						default:
							console.log( key+':'+jsonObject[key]);
							this.property( key, jsonObject[key] );
						}
					}
				};
				
				this.fileContent = function( content ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.FILE_CONTENT);
					return this.property.apply(this, args);
				};
				
				this.clone = function(){
					return new DataStructure( OSP.Util.toJSON(this) );
				};
				
				this.getActiveParameterFormattedInputs = function(){
					var parameterDelimiter = this.parameterFormParameterDelimiter();
					var valueDelimiter = this.parameterFormValueDelimiter();
					var parameterSpaceChar;
					this.parameterFormSpace() ? parameterSpaceChar = ' ': parameterSpaceChar = '';
					var inputdeck = this;
					
					var getFormattedValue = function(parameter){
						var formattedString = [];
						switch(parameter.type()){
						case OSP.Constants.NUMERIC:
							if( parameter.sweeped() ){
								var sweepValues = parameter.slicedValues();
								
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
							formattedString[0] = this.parameterFormCommentChar() +
								parameter.value() + parameterSpaceChar + parameterDelimiter;
							break;
						case OSP.Constants.VECTOR:
							var startBraceChar, endBraceChar; 
							var vectorSpace;
							switch(this.vectorFormBraceChar()){
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
										this.vectorFormDelimiter(),
										vectorSpace) + parameterSpaceChar + parameterDelimiter; 
							break;
						default:
							break;
						}
						//console.log(formattedString);
						return formattedString;
					};

					var getFormattedInputs = function(input, parameters){
						var inputs = [];
						for(var i=0; i<parameters.length; i++){
							var parameter = parameters[i];
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
									var newInputs = getFormattedInputs(cloneInput, parameters.slice(i+1));
									
									sweepInputs = sweepInputs.concat(newInputs);
								}
								return sweepInputs;
							}
						}
						this.setFileContent(input.join("\n")+"\n");
						inputs.push(this.clone());
						return inputs;
					};

					var activeParameters = this.getActiveParameters();
					var formattedInputs = getFormattedInputs([], activeParameters);
					//console.log(JSON.stringify(formattedInputs, null, 4));
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
							var parameter = this.getParameter(parameterName);
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

				if( arguments.length === 1 )
					this.deserialize(jsonObject);
			};
			this.newDataStructure = function( jsonObject ){
				return new DataStructure(jsonObject);
			};

			this.structure = function( structure ){
				if( arguments.length === 1 ){
					if( !(structure instanceof DataStructure) )
						return false;
				}
				
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
				return new OSP.DataType( OSP.Util.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					console.log( 'datatype '+key+':'+jsonObject[key]);
					if( key === OSP.Constants.STRUCTURE ){
						result = this.structure(this.newDataStructure( jsonObject[key]));
						console.log('new structure: '+JSON.stringify(this.structure(),null,4));
					}
					else
						result = this.property(key, jsonObject[key]);
					
					if( !result )	break;
				}
				
				return result;
			};
			
			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		};
	}
	
	if( !OSP.ScienceApp ){
		OSP.ScienceApp = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			var Port = function( jsonObject ){
				OSP._MapObject.apply(this);
				
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
		
				this.dataTypeUuid = function( dataTypeUuid ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.DATA_TYPE_UUID);
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
				
				this.getAvailableEditors = function( url, params ){
					
				};
				
				this.getAvailableAnalyzers = function( url, params ){
					
				};
				
				this.verifyName = function(name){
					if( !name )	return true;
					if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(name))  return false;
					else  return true;
				};
			};

			var InputPort = function( jsonObject ){
				Port.apply(this);
				
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
				
				this.sampleDataUuid = function( dataUuid ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SAMPLE_UUID);
					return this.property.apply(this, args);
				};
				
				this.getSampleData = function( url, params ){
					
				};
				
				this.clone = function(){
					return new InputPort( OSP.Util.toJSON(this) );
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
			this.newInputPort = function( jsonObject ){
				return new InputPort(jsonObject);
			};

			var OutputPort = function( jsonObject ){
				Port.apply(this);
				
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
					return new OutputPort( OSP.Util.toJSON(this) );
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
			this.newOutputPort = function( jsonObject ){
				return new OutputPort(jsonObject);
			};
				
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
				return new OSP.ScienceApp( OSP.Util.toJSON(this) );
			};
			
			this.deserialize = function( jsonObject ){
				var result = true;
				for( var key in jsonObject ){
					if( key === OSP.Constants.DESCRIPTION ){
						result = this.property(key, new OSP.LocalizedText(jsonObject[key]));
					}
					else if( key === OSP.Constants.INPUT_PORTS){
						var inputPortsJsonAry = jsonObject[key];
						for( var index in inputPortsJsonAry ){
							var inputPort = new InputPort(inputPorts[index]);
							this.addInputPort(inputPort);
						}
					}
					else if( key === OSP.Constants.OUTPUT_PORTS ){
						var outputPortsJsonAry = jsonObject[key];
						for( var index in outputPortsJsonAry ){
							var outputPort = new OutputPort(outputPorts[index]);
							this.addOutputPort(outputPort);
						}
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
	
	if( !OSP.Workflow ){
		OSP.Workflow = function( jsonObject ){
			OSP._OpenObject.apply(this);
			
			var ProceedCondition = function( jsonObject ){
				OSP._MapObject.apply(this);
				
				this.comparativeParam = function( param ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.COMPARATIVE_PARAM);
					return this.property.apply(this, args);
				};
				
				this.comparative = function( operator ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.COMPARATIVE);
					return this.property.apply(this, args);
				};
				
				this.comparativeValue = function( value ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.COMPARATIVE_VALUE);
					return this.property.apply(this, args);
				};
				
				this.updates = function( updates ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.UPDATES);
					return this.property.apply(this, args);
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
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.ACTION);
					return this.property.apply(this, args);
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
			};
			this.newProceedCondition = function( jsonObject ){
				return new ProceedCondition(jsonObject);
			};
			
			var Connection = function( jsonObject ){
				OSP._MapObject.apply(this);
				OSP._StyleObject.apply(this);
				
				this.sourcePort = function( portName ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.SRC_PORT_NAME);
					return this.property.apply(this, args);
				};
				
				this.proceedConditions = function( conditions ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.PROCEED_CONDITIONS);
					return this.property.apply(this, args);
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
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.TASK_UUID);
					return this.property.apply(this, args);
				};
				
				this.destinationPort = function( portName ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.DESTINATION_PORT);
					return this.property.apply(this, args);
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
			};
			this.newConnection = function( jsonObject ){
				return new Connection( jsonObject );
			};

			var Task = function( jsonObject ){
				OSP._OpenObject.apply(this);
				OSP._StyleObject.apply(this);
				
				this.appUuid = function( uuid ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.APP_UUID);
					return this.property.apply(this, args);
				};
				
				this.connections = function( connections ){
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.CONNECTIONS);
					return this.property.apply(this, args);
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
					var args = Array.from(arguments);
					args.unshift(OSP.Constants.INPUTS);
					return this.property.apply(this, args);
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
			};
			
			this.newTask = function( jsonObject ){
				return new Task( jsonObject );
			};

			this.tasks = function( tasks ){
				var args = Array.from(arguments);
				args.unshift(OSP.Constants.TASKS);
				return this.property.apply(this, args);
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
		};
	}
	
	
	if( !OSP.Simulation ){
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
		};
	}
})(window);
